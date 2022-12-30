import java.util.LinkedList;
import javax.swing.*;

public class PlateauDominosTerm extends AbstractPlateau{

    public PlateauDominosTerm(PartieDominosTerm p) {
        super(new LinkedList<>(),p);
    }

    @Override
    public boolean ajoutTuile(AbstractTuile t, int y, int x) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ImageIcon createImage(AbstractTuile t) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
