import java.util.*;

public class Bot extends Joueur {
    ModelJeu model;
    PartieG partie;
    int numeroDeJoueur;

    public Bot(Domino main, int point, int numeroDeJoueur) {
        super(main,point, numeroDeJoueur);
    }

    public void Play(Domino d) {
        for(int i=0; i<partie.plateau.getBoutons().length; i++) {
            for (int j = 0; j < partie.plateau.getBoutons()[i].length; j++) {
                if(partie.plateau.getBoutons()[i][j].isVisible()) {
                    if(partie.ajoutDomino(d, i, j)) {
                        partie.plateau.getBoutons()[i][j].doClick();
                        return;
                    }
                }
            }
        }
        partie.skip();
    }

    public void jouerText(int joueurCourant) {
        for(int i=-1; i<model.p.plateau.size()+2; i++) {
            for (int j = -1; j < model.p.plateau.get(0).size()+2; j++) {
                if(model.p.ajoutDomino(main, i, j)) {
                    model.joueurs.get(joueurCourant).setPoint(model.point(i,j)+model.joueurs.get(joueurCourant).getPoint());
                    return;
                }
            }
        }
    }
}