import java.awt.*;
import javax.swing.*;

public class PlateauCarcassonne extends Plateau2{
    public PlateauCarcassonne(int nbLignes, int nbColonnes, JFrame frame, ModelJeu model) {
        super(nbLignes, nbColonnes, frame, model);
        boutons[boutons.length/2][boutons[0].length].setIcon(null);
    }
}