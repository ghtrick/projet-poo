package src.main.java.model;

import src.main.java.controller.ControlBot;
import src.main.java.abstractClass.*;

public class Bot extends AbstractJoueur{

    ControlBot c;
    
    public Bot(int point, int numeroDeJoueur) {
        super(point, numeroDeJoueur);
    }

    public void Play(AbstractTuile d) {
        for(int i=1; i<c.getMain().getP().getBoutons().length-1; i++) {
            for (int j = 1; j < c.getMain().getP().getBoutons()[i].length-1; j++) {
                if(c.getMain().getP().getBoutons()[i][j].isVisible() && !c.getMain().getP().getIsPlaced()[i][j]) {
                    if(c.getMain().getP().getP().getPlateau().ajoutTuile(d, i, j)) {
                        c.getMain().getP().clickBouton(i,j);
                        return;
                    }
                }
            }
        }
        c.getMain().skip();
    }
    public ControlBot getC() {
        return c;
    }
    public void setC(ControlBot c) {
        this.c = c;
    }
}
