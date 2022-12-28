import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

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
            bool.add(Arrays.equals(plateau.get(y).get(x+1).getNumeros()[3], d.getNumeros()[1]));
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y+1).get(x).getNumeros()[0], d.getNumeros()[2]));
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x-1).getNumeros()[1], d.getNumeros()[3]));
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

    public void choisirJoueurs() {
        System.out.println("combien de joueurs ?");
        int res = 0;
        do {
            try {
                String action = scan.nextLine();
                res = Integer.parseInt(action);
            } catch (Exception e) {}
        } while (res<=0);
        int tmp = 0;
        boolean[] tab = new boolean[res];
        do {
            System.out.println("Joueur " + (tmp+1) + " est-t-il il bot ? (oui ou non)");
            String joueurBot = scan.nextLine();
            if(joueurBot.equals("oui")) {
                tab[tmp] = true;
                tmp++;
            } else if(joueurBot.equals("non")) {
                tab[tmp] = false;
                tmp++;
            } else {
                System.out.println("Mauvaise réponse répondez par oui ou par non");
            }
        } while(tmp<res);
        model.initJoueurs(res, tab, true);
    }

    public int partie2Joueurs() {
        choisirJoueurs();
        boolean finirPartie = false;
        int joueurCourant = 0;
        do {
            System.out.println(this.affichePlateau());
            System.out.println("---------------------------------------------------------------");
            model.joueurs.get(joueurCourant).piocher(model.s);
            LinkedList<Domino> maintmp = new LinkedList<>();
            maintmp.add(model.joueurs.get(joueurCourant).getMain());
            System.out.println(afficheDominoListe(maintmp));
            System.out.println("Tour du joueur "+model.joueurs.get(joueurCourant).numeroDeJoueur);
            System.out.println("Joueur " + model.joueurs.get(joueurCourant).numeroDeJoueur + " : " + model.joueurs.get(joueurCourant).getPoint());
            int res0 = -1;
            if (model.joueurs.get(joueurCourant) instanceof Bot) {
                ((Bot)model.joueurs.get(joueurCourant)).jouerText(joueurCourant);
                if (joueurCourant+1==model.joueurs.size()) joueurCourant=0;
                else joueurCourant++;
            } else {
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
                                mainTemp.add(model.joueurs.get(joueurCourant).getMain());
                                mainTemp.get(0).tourneUneFois();
                                model.joueurs.get(joueurCourant).setMain(mainTemp.get(0));
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
                                        dominoPlace = ajoutDomino(model.joueurs.get(joueurCourant).getMain(), res3, res4);
                                        
                                        aJoue = dominoPlace;
                                    } catch (Exception e) {}
                                } while (res3<=-2 || res4 <=-2);
                            }
                            else if(res2 == 2) {
                                System.out.println("Vous avez passé votre tour");
                                model.joueurs.get(joueurCourant).setMain(null);
                                aJoue=true;
                            }
                        } catch (Exception e) {System.out.println("erreur domino mal sélectionné");}
                        System.out.println(aJoue + " aa");
                    } while (!aJoue);
                    
                    if (dominoPlace) { 
                        if(res3==-1) res3 = 0;
                        if(res4==-1) res4 = 0;
                        model.joueurs.get(joueurCourant).setPoint(model.point(res3,res4)+model.joueurs.get(joueurCourant).getPoint());
                        model.joueurs.get(joueurCourant).setMain(null);
                    }
                    if (joueurCourant+1==model.joueurs.size()) joueurCourant=0;
                    else joueurCourant++;
                } else if (res0 == 2) {
                    if(model.joueurs.size()==2) {
                        model.joueurs.remove(joueurCourant);
                        return model.max();
                    }
                    model.joueurs.remove(joueurCourant);
                    joueurCourant = joueurCourant+1%model.joueurs.size();
                } 
                
                else if (res0 == 1) {
                    joueurCourant = joueurCourant+1%model.joueurs.size();
                }
            
            }
        } while (!this.model.s.getDominosSac().isEmpty() || finirPartie);
        
        return model.max();
    }

    public void partieFini(int gagnant) {
        if (gagnant!=-1) {
            System.out.println("Joueur " + gagnant + " a gagné");
        } else {
            System.out.println("égalité");
        }
    }

}
