import java.util.LinkedList;

public class PlateauDominos extends AbstractPlateau{

    public PlateauDominos() {
        super(new LinkedList<>());
    }

    @Override
    public boolean ajoutTuile(AbstractTuile t, int y, int x) {
        return false;
    }
    
}
