import java.util.LinkedList;

public abstract class AbstractPlateau {
    protected LinkedList<LinkedList<AbstractTuile>> plateau;

    public AbstractPlateau(LinkedList<LinkedList<AbstractTuile>> plateau) {
        this.plateau = plateau;
    }

    public abstract boolean ajoutTuile(AbstractTuile t, int y, int x);

    
}
