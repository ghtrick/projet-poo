package src.main.java.Carcassonne_Domino.view_Terminal;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import src.main.java.Carcassonne_Domino.AbstractClasses.AbstractJeu;
import src.main.java.Carcassonne_Domino.model.Domino;
import src.main.java.Carcassonne_Domino.model.Joueur;
import src.main.java.Carcassonne_Domino.model.ModelJeu;

public class Partie extends AbstractJeu{

    public LinkedList<LinkedList<Domino>> plateau = new LinkedList<>();
    public ModelJeu model;

    Scanner scan = new Scanner(System.in);

    public Partie() {
        super();
        plateau.add(new LinkedList<>());
        model = new ModelJeu();
        model.p = this;
        plateau.get(0).add(model.s.getDominosSac().getFirst());
        model.s.getDominosSac().removeFirst();
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
        if (bool.isEmpty()) {
            System.out.println("corentin t'es null");
            numeroCorrect = false;
        }
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
        Joueur joueurCourant = model.joueur1;
        do {
            System.out.println(this.affichePlateau());
            System.out.println("---------------------------------------------------------------");
            joueurCourant.piocher(model.s);
            LinkedList<Domino> maintmp = new LinkedList<>();
            maintmp.add(joueurCourant.getMain());
            System.out.println(afficheDominoListe(maintmp));
            System.out.println((joueurCourant.equals(model.joueur1)) ? "Tour du joueur1" : "Tour du joueur2");
            System.out.println((joueurCourant.equals(model.joueur1)) ? "Joueur1 : " + model.joueur1.getPoint() : "Joueur2 : " + model.joueur2.getPoint());
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
                    
                do {
                    try {
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
                            LinkedList<Domino> mainTemp = new LinkedList<>();
                            mainTemp.add(joueurCourant.getMain());
                            mainTemp.get(0).tourneUneFois();
                            joueurCourant.setMain(mainTemp.get(0));
                            System.out.println(afficheDominoListe(mainTemp));
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
                                    dominoPlace = ajoutDomino(joueurCourant.getMain(), res3, res4);
                                    
                                    aJoue = dominoPlace;
                                } catch (Exception e) {}
                            } while (res3<=-2 || res4 <=-2);
                        }
                        else if(res2 == 2) {
                            System.out.println("Vous avez passé votre tour");
                            joueurCourant.setMain(null);
                            aJoue=true;
                        }
                    } catch (Exception e) {System.out.println("erreur domino mal sélectionné");}
                    System.out.println(aJoue + " aa");
                } while (!aJoue);
                
                if (dominoPlace) { 
                    if(res3==-1) res3 = 0;
                    if(res4==-1) res4 = 0;
                    if(joueurCourant.equals(model.joueur1)) model.joueur1.setPoint(model.point(res3,res4)+model.joueur1.getPoint());
                    else model.joueur2.setPoint(model.point(res3,res4)+model.joueur2.getPoint());
                    joueurCourant.setMain(null);
                }
                joueurCourant = (joueurCourant.equals(model.joueur1)) ? model.joueur2 : model.joueur1;
            }
     
            else if (res0 == 2) {
                if(model.joueur1.getPoint()==model.joueur2.getPoint()) return null;
                return (model.joueur1.getPoint()>model.joueur2.getPoint()) ? model.joueur1 : model.joueur2;
            } 
           
            else if (res0 == 1) {
                joueurCourant = (joueurCourant.equals(model.joueur1)) ? model.joueur2 : model.joueur1;
            }
            
        } while (!this.model.s.getDominosSac().isEmpty() || finirPartie);
        
        if(model.joueur1.getPoint()==model.joueur2.getPoint()) return null;
        return (model.joueur1.getPoint()>model.joueur2.getPoint()) ? model.joueur1 : model.joueur2;
    }

    public void partieFini(Joueur gagnant) {
        System.out.println((model.joueur1.equals(gagnant)) ? "Joueur1 a gagné" : "Joueur2 a gagné");
    }

}
