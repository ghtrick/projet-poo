import java.util.Arrays;

public abstract class AbstractTuile {
    protected int[][] numeros;
    protected boolean estVide;

    public AbstractTuile(int[][] numeros) {
        this.numeros=numeros;
    }

    public boolean isEstVide() {
        return estVide;
    }

    public int[][] getNumeros() {
        return numeros;
    }

}