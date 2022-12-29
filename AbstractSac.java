import java.util.LinkedList;
import java.util.Random;

public abstract class AbstractSac {
    protected Random rand = new Random();
    protected LinkedList<AbstractTuile> tuilesDansLeSac;

    public AbstractSac() {
        this.tuilesDansLeSac = new LinkedList<>();
    }

    public abstract void remplirSac();
}
