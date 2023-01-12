package src.main.java.model;
import java.util.LinkedList;
import java.util.Scanner;

import src.main.java.abstractClass.*;
public class PartieDominosTerm extends AbstractJeu {

    Scanner scan = new Scanner(System.in);

    public PartieDominosTerm(PlateauDominosTerm plateau, SacDominos sac, int nbJoueurs, int nbBot) {
        super(plateau, sac, nbJoueurs, nbBot);
        plateau.getPlateau().add(new LinkedList<>());
        plateau.getPlateau().get(0).add(sac.getTuilesDansLeSac().getFirst());
        sac.getTuilesDansLeSac().removeFirst();
        lancerPartie();
    }

    public PartieDominosTerm() {
        super(null, new SacDominos(), 0, 0);
        PlateauDominosTerm plat = new PlateauDominosTerm(this);
        plateau = plat;
        plateau.getPlateau().add(new LinkedList<>());
        plateau.getPlateau().get(0).add(sac.getTuilesDansLeSac().getFirst());
        sac.getTuilesDansLeSac().removeFirst();
        lancerPartie();
    }

    public void reqChoisirJoueurs() {
        System.out.println("combien de joueurs ?");
        int res = 0;
        do {
            try {
                String action = scan.nextLine();
                res = Integer.parseInt(action);
            } catch (Exception e) {}
        } while (res<=0);
        initJoueurs(res);
    }

    public void initJoueurs(int x) {
        joueurs = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            joueurs.add(new Joueur(0,i + 1));
        }
    }

    @Override
    public int lancerPartie() {
        reqChoisirJoueurs();
        boolean finirPartie = false;
        int joueurCourant = 0;
        do {
            System.out.println(Util.affichePlateau((PlateauDominosTerm) plateau));
            System.out.println("---------------------------------------------------------------");
            piocher();
            LinkedList<AbstractTuile> maintmp = new LinkedList<>();
            maintmp.add((Domino) main);
            System.out.println(Util.afficheDominoListe(maintmp));
            System.out.println("Tour du joueur "+joueurs.get(joueurCourant).getNumeroDeJoueur());
            System.out.println("Joueur " + joueurs.get(joueurCourant).getNumeroDeJoueur() + " : " + joueurs.get(joueurCourant).getPoint());
            int res0 = -1;
            if (joueurs.get(joueurCourant) instanceof Bot) {
                botJouerText(joueurCourant);
                if (joueurCourant+1==joueurs.size()) joueurCourant=0;
                else joueurCourant++;
                System.out.println("JoueurCourant ici: "+joueurCourant);
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
                                LinkedList<AbstractTuile> mainTemp = new LinkedList<>();
                                mainTemp.add((Domino) main);
                                Util.tourneUneFois((Domino) main);
                                main=mainTemp.get(0);
                                System.out.println(Util.afficheDominoListe(mainTemp));
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
                                        dominoPlace = plateau.ajoutTuile((Domino) main, res3, res4);

                                        aJoue = dominoPlace;
                                    } catch (Exception e) {}
                                } while (res3<=-2 || res4 <=-2);
                            }
                            else if(res2 == 2) {
                                System.out.println("Vous avez passé votre tour");
                                main=null;
                                aJoue=true;
                            }
                        } catch (Exception e) {System.out.println("erreur domino mal sélectionné");}
                    } while (!aJoue);

                    if (joueurCourant+1==joueurs.size()) joueurCourant=0;
                    else joueurCourant++;
                } else if (res0 == 2) {
                    if(joueurs.size()==2) {
                        joueurs.remove(joueurCourant);
                        partieFinie(max());
                        return max();
                    }
                    joueurs.remove(joueurCourant);
                    if (joueurCourant==joueurs.size()) joueurCourant = 0;
                    if (joueurCourant!=0) joueurCourant = (joueurCourant+1)%joueurs.size();
                }

                else if (res0 == 1) {
                    joueurCourant = (joueurCourant+1)%joueurs.size();
                }

            }
        } while (!this.sac.getTuilesDansLeSac().isEmpty() || finirPartie);
        partieFinie(max());
        return max();
    }

    public void botJouerText(int joueurCourant) {
        for(int i=-1; i<plateau.getPlateau().size()+2; i++) {
            for (int j = -1; j < plateau.getPlateau().get(0).size()+2; j++) {
                if(plateau.ajoutTuile((Domino) main, i, j)) {
                    return;
                }
            }
        }
    }

    @Override
    public void partieFinie(int gagnant) {
        if (gagnant!=-1) {
            System.out.println("Joueur " + gagnant + " a gagné");
        } else {
            System.out.println("égalité");
        }
    }

    @Override
    public void choisirJoueurs(int nbJoueurs, int nbBots) {
        int num = 1;
        for (int i = 0; i < nbJoueurs-nbBots; i++) {
            joueurs.add(new Joueur(0, num));
        }
        for (int i = 0; i < nbBots; i++) {
            joueurs.add(new Bot(0, num));
        }
    }

    @Override
    public void piocher() {
        if(sac.getTuilesDansLeSac().isEmpty()) {
            partieFinie(max()+1);
            return;
        }
        Domino d = (Domino) sac.getTuilesDansLeSac().getFirst();
        main=d;
        sac.getTuilesDansLeSac().removeFirst();
    }
}
