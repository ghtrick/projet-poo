import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class PartieCarcassonne extends AbstractJeu{

    public PartieCarcassonne(PlateauCarcassonne plateau, SacCartesCarcassonne sac, int nbJoueurs, int nbBot) {
        super(plateau, sac, nbJoueurs, nbBot);
    }

    @Override
    public int lancerPartie() {
        return 0;
    }

    @Override
    public void partieFinie(int gagnant) {
        
    }

    @Override
    public void piocher() {
        
    }
    
}
