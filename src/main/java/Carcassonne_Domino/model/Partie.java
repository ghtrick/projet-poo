package src.main.java.Carcassonne_Domino.model;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Partie extends AbstractJeu{

    private LinkedList<LinkedList<Domino>> plateau;
    private Sac s;
    private Joueur joueur1;
    private Joueur joueur2;

    Scanner scan = new Scanner(System.in);

    public Partie() {
        super();
        this.s = new Sac(new LinkedList<>());
        s.remplirSac();
        plateau.get(0).add(/*s.getDominosSac().get(0)*/new Domino(new int[][]{{6,6,6},{2,2,2},{1,1,1},{3,3,3}}));
        LinkedList<Domino> s1 = s.getDominosSac();
        s1.removeFirst();
        s.setDominosSac(s1);
        LinkedList<Domino> mainJ1 = new LinkedList<>();
        LinkedList<Domino> mainJ2 = new LinkedList<>();
        for (int j = 0; j < 7; j++) {
            mainJ1.add(s.getDominosSac().get(0));
            LinkedList<Domino> s2 = s.getDominosSac();
            s1.removeFirst();
            s.setDominosSac(s2);
            mainJ2.add(s.getDominosSac().get(0));
            LinkedList<Domino> s3 = s.getDominosSac();
            s1.removeFirst();
            s.setDominosSac(s3);
        }
        this.joueur1.setMain(mainJ1);
        this.joueur2.setMain(mainJ2);
    }

    public static String afficheDominoListe(LinkedList<Domino> l) {
        String res = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < l.size(); j++) {
                switch (i) {
                    case 0:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += " "+l.get(j).getNumeros()[0][0] + "" + l.get(j).getNumeros()[0][1] + "" + l.get(j).getNumeros()[0][2]+ "  ";
                        break;
                    case 1:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][0]+"   "+l.get(j).getNumeros()[1][0]+ " ";
                        break;
                    case 2:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][1]+"   "+ l.get(j).getNumeros()[1][1] + " ";
                        break;
                    case 3:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][2]+"   "+l.get(j).getNumeros()[1][2] + " ";
                        break;
                    default:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += " "+l.get(j).getNumeros()[2][0] + "" + l.get(j).getNumeros()[2][1] +""+ l.get(j).getNumeros()[2][2] + "  ";
                        break;
                }
            }
            res+="\n";
        }
        return res;
    }

    public String affichePlateau() {
        String res = "";
        for (int i = 0; i < plateau.size(); i++) {
            res+=afficheDominoListe(plateau.get(i));
        }
        return res;
    }

    public boolean ajoutDomino(Domino d, int x, int y) {
        LinkedList<Boolean> bool = new LinkedList<>();
        boolean numeroCorrect = true;
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x).getNumeros()[3], d.getNumeros()[1]));
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y+1).get(x).getNumeros()[0], d.getNumeros()[2]));
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x).getNumeros()[1], d.getNumeros()[3]));
        } catch (Exception e) {}
        try {
           bool.add(Arrays.equals(plateau.get(y-1).get(x).getNumeros()[2], d.getNumeros()[0])); 
        } catch (Exception e) {}
        for(int i=0; i<bool.size(); i++) {
            if(!bool.get(i)) {
                numeroCorrect = false;
            }
        }
     //   if (bool.isEmpty()) numeroCorrect = false;
        if(numeroCorrect) {
            if(x==-1 || x==plateau.get(0).size()) {
                if(x==-1) x=0;
                for(int i=0; i<plateau.size(); i++) {
                    if(i!=y) plateau.get(i).add(x, new Domino());
                    else plateau.get(i).add(x, d);
                } 
            } else if (y==-1 || y==plateau.size()) {
                if (y==-1) {
                    y=0;
                    plateau.add(y, new LinkedList<Domino>());            
                    for(int i=0; i<plateau.get(y+1).size(); i++) {
                        if(i!=x) plateau.get(y).add(i, new Domino());
                       else { 
                            plateau.get(y).add(i, d);
                        }
                    } 
                } else {
                    plateau.add(y, new LinkedList<Domino>());             
                    for(int i=0; i<plateau.get(y-1).size(); i++) {
                        if(i!=x) plateau.get(y).add(i, new Domino());
                        else {
                            plateau.get(y).add(i, d);
                        }
                    } 
                }
            } else {
                if(plateau.get(y).get(x).isEstVide()) {
                    plateau.get(y).set(x, d);
                }
            }
        }
        return numeroCorrect;
    }

    public Joueur partie2Joueurs() {
        boolean finirPartie = false;
        Joueur joueurCourant = this.joueur1;
        do {
            System.out.println(this.affichePlateau());
            System.out.println("---------------------------------------------------------------");
            joueurCourant.piocher(s);
            System.out.println(afficheDominoListe(joueurCourant.getMain()));
            System.out.println((joueurCourant.equals(joueur1)) ? "Tour du joueur1" : "Tour du joueur2");
            System.out.println((joueurCourant.equals(joueur1)) ? "Joueur1 : " + joueur1.getPoint() : "Joueur2 : " + joueur2.getPoint());
            int res0 = -1;
            
            do {
                System.out.println("Taper votre action : (0 pour jouer, 1 pour passer son tour, 2 pour abandonner)");
                String action = scan.nextLine();
                
                try {
                    res0 = Integer.parseInt(action);
                } catch (Exception e) {System.out.println("Mauvais numéro");}
                
            } while(res0 <= -1 || res0>2);
                     
            if (res0 == 0) {
                int res3 = -2;
                int res4 = -2;
                boolean aJoue = false;
                boolean dominoPlace = false;
                int res = -1;
                
                do {
                    System.out.println("Quel domino voulez vous selectionner");
                    String dominoAJouer = scan.nextLine();
                    
                    try {
                        res = Integer.parseInt(dominoAJouer);
                        System.out.println("Taper votre action : (0 pour tourner le domino, 1 pour taper les coordonnées où vous voulez placer le domino, 2 pour passer son tour)");
                        int res2 = -1;
                        String actionAEffectuer = "";
                        
                        do {
                            actionAEffectuer = scan.nextLine();
                            
                            try {
                                res2 = Integer.parseInt(actionAEffectuer);
                            } catch (Exception e) {System.out.println("erreur action mal sélectionnée");}
                            
                        } while (res2 <= -1 || res2 >= 3);
                        
                        if (res2 == 0) {
                            LinkedList<Domino> mainTemp = joueurCourant.getMain();
                            mainTemp.get(res).tourneUneFois();
                            joueurCourant.setMain(mainTemp);
                            System.out.println(afficheDominoListe(joueurCourant.getMain()));
                        } 
                        
                        else if(res2 == 1) {
                            String x = "";
                            String y = "";
                            do {
                                System.out.println("x?");
                                x = scan.nextLine();
                                System.out.println("y?");
                                y = scan.nextLine();
                                try {
                                    res3 = Integer.parseInt(x);
                                    res4 = Integer.parseInt(y);
                                } catch (Exception e) {}
                                try {
                                    dominoPlace = ajoutDomino(joueurCourant.getMain().get(res), res3, res4);
                                    System.out.println(dominoPlace);
                                    aJoue = dominoPlace;
                                } catch (Exception e) {}
                            } while (res3<=-2 || res4 <=-2);
                        }
                        else if(res2 == 2) {
                            System.out.println("Vous avez passé votre tour");
                            aJoue=true;
                        }
                    } catch (Exception e) {System.out.println("erreur domino mal sélectionné");}

                } while (((res <= -1 || res >= joueurCourant.getMain().size()) || !aJoue));
                
                if (dominoPlace) { 
                    if(res3==-1) res3 = 0;
                    if(res4==-1) res4 = 0;
                    if(joueurCourant.equals(joueur1)) joueur1.setPoint(point(res3,res4)+joueur1.getPoint());
                    else joueur2.setPoint(point(res3,res4)+joueur2.getPoint());
                    LinkedList<Domino> liste = joueurCourant.getMain();
                    liste.remove(res);
                    joueurCourant.setMain(liste);
                }
                joueurCourant = (joueurCourant.equals(joueur1)) ? joueur2 : joueur1;
            }
     
            else if (res0 == 2) {
                if(joueur1.getPoint()==joueur2.getPoint()) return null;
                return (joueur1.getPoint()>joueur2.getPoint()) ? joueur1 : joueur2;
            } 
           
            else if (res0 == 1) {
                joueurCourant = (joueurCourant.equals(joueur1)) ? joueur2 : joueur1;
            }
            
        } while (!this.s.getDominosSac().isEmpty() || finirPartie);
        
        if(joueur1.getPoint()==joueur2.getPoint()) return null;
        return (joueur1.getPoint()>joueur2.getPoint()) ? joueur1 : joueur2;
    }

    public void partieFini() {
        System.out.println("Joueur1 : " + joueur1.getPoint());
        System.out.println("Joueur2 : " + joueur1.getPoint());
        System.out.println((joueur1.getPoint()>joueur2.getPoint()) ? "Joueur1 a gagné" : "Joueur2 a gagné");
    }

    public int point(int x, int y) {
        int res = 0;
        try {
            if(Arrays.equals(plateau.get(y).get(x).getNumeros()[3], plateau.get(y).get(x-1).getNumeros()[1])) {
                for(int i=0; i<plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= plateau.get(y).get(x).getNumeros()[3][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(plateau.get(y).get(x).getNumeros()[0], plateau.get(y+1).get(x).getNumeros()[2])) {
                for(int i=0; i<plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= plateau.get(y).get(x).getNumeros()[3][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(plateau.get(y).get(x).getNumeros()[1], plateau.get(y).get(x+1).getNumeros()[3])) {
                for(int i=0; i<plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= plateau.get(y).get(x).getNumeros()[3][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(plateau.get(y-1).get(x).getNumeros()[2], plateau.get(y-1).get(x).getNumeros()[0])) {
                for(int i=0; i<plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= plateau.get(y).get(x).getNumeros()[3][i];
                }
           }
        } catch (Exception e) {}
        return res;
    }
 }
