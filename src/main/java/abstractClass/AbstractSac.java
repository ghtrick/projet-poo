package src.main.java.abstractClass;
import java.util.LinkedList;
import java.util.Random;

public abstract class AbstractSac {
    protected Random rand = new Random();
    protected LinkedList<AbstractTuile> tuilesDansLeSac;

    public AbstractSac() {
        this.tuilesDansLeSac = new LinkedList<>();
    }

    public abstract void remplirSac();
    
    public LinkedList<AbstractTuile> getTuilesDansLeSac() {
        return tuilesDansLeSac;
    }
}
