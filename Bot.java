import java.util.*;

public class Bot extends Joueur {
    ModelJeu model;
    PartieG partie;

    public Bot(Domino main, int point) {
        super(main,point);
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
}