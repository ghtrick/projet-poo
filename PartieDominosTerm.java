import java.util.LinkedList;
import java.util.Scanner;

public class PartieDominosTerm extends AbstractJeu {

    Scanner scan = new Scanner(System.in);

    public PartieDominosTerm(PlateauDominosTerm plateau, SacDominos sac, int nbJoueurs, int nbBot) {
        super(plateau, sac, nbJoueurs, nbBot);
        plateau.plateau.add(new LinkedList<>());
        plateau.plateau.get(0).add(sac.tuilesDansLeSac.getFirst());
        sac.tuilesDansLeSac.removeFirst();
        lancerPartie();
    }

    public PartieDominosTerm() {
        super(null, new SacDominos(), 0, 0);
        PlateauDominosTerm plat = new PlateauDominosTerm(this);
        plateau = plat;
        plateau.plateau.add(new LinkedList<>());
        plateau.plateau.get(0).add(sac.tuilesDansLeSac.getFirst());
        sac.tuilesDansLeSac.removeFirst();
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
        initJoueurs(res, tab);
    }

    public void initJoueurs(int x, boolean[] bots) {
        joueurs = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            if (bots[i]) {
                Bot b = new Bot(0,i + 1);
                joueurs.add(b);
            } else {
                joueurs.add(new Joueur(0,i + 1));
            }
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
            System.out.println("Tour du joueur "+joueurs.get(joueurCourant).numeroDeJoueur);
            System.out.println("Joueur " + joueurs.get(joueurCourant).numeroDeJoueur + " : " + joueurs.get(joueurCourant).point);
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
                                        dominoPlace = plateau.ajoutTuileTerm((Domino) main, res3, res4);

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
                        System.out.println(aJoue + " aa");
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
        } while (!this.sac.tuilesDansLeSac.isEmpty() || finirPartie);
        partieFinie(max());
        return max();
    }

    public void botJouerText(int joueurCourant) {
        for(int i=-1; i<plateau.plateau.size()+2; i++) {
            for (int j = -1; j < plateau.plateau.get(0).size()+2; j++) {
                if(plateau.ajoutTuileTerm((Domino) main, i, j)) {
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
        if(sac.tuilesDansLeSac.isEmpty()) {
            partieFinie(max()+1);
            return;
        }
        Domino d = (Domino) sac.tuilesDansLeSac.getFirst();
        main=d;
        sac.tuilesDansLeSac.removeFirst();
    }
}
