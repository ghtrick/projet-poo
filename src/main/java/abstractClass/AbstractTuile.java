package src.main.java.abstractClass;
import java.util.Arrays;

public abstract class AbstractTuile {
    protected AbstractElement[][] numeros;
    protected int nbFoisTourne;
    protected boolean estVide;

    public AbstractTuile(AbstractElement[][] numeros) {
        this.numeros=numeros;
    }

    public boolean isEstVide() {
        return estVide;
    }

    public AbstractElement[][] getNumeros() {
        return numeros;
    }
    public int getNbFoisTourne() {
        return nbFoisTourne;
    }
    public void setNbFoisTourne(int nbFoisTourne) {
        this.nbFoisTourne = nbFoisTourne;
    }

}