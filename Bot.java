public class Bot extends AbstractJoueur{

    VueMain main;
    
    public Bot(int point, int numeroDeJoueur) {
        super(point, numeroDeJoueur);
    }

    public void Play(Domino d) {
        for(int i=0; i<main.p.boutons.length; i++) {
            for (int j = 0; j < main.p.boutons[i].length; j++) {
                if(main.p.boutons[i][j].isVisible()) {
                    if(main.p.p.plateau.ajoutTuile(d, i, j)) {
                        main.p.boutons[i][j].doClick();
                        return;
                    }
                }
            }
        }
        main.skip();
    }

    /*public void jouerText(int joueurCourant) {
        for(int i=-1; i<model.p.plateau.size()+2; i++) {
            for (int j = -1; j < model.p.plateau.get(0).size()+2; j++) {
                if(model.p.ajoutDomino(main, i, j)) {
                    model.joueurs.get(joueurCourant).setPoint(model.point(i,j)+model.joueurs.get(joueurCourant).getPoint());
                    return;
                }
            }
        }
    }*/
}
