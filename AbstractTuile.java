import java.util.Arrays;

public abstract class AbstractTuile {
    protected int[][] numeros;
    protected boolean estVide;

    public AbstractTuile(int[][] numeros) {
        this.numeros=numeros;
    }

    public static int[] numerosTournes(int[] t) {
        return new int[]{t[2],t[1],t[0]};
    }

    public void tourneUneFois() {
        int[][] tmp = new int[4][3];
        tmp[0] = Arrays.copyOf(numeros[0], 3);
        tmp[1] = Arrays.copyOf(numeros[1], 3);
        tmp[2] = Arrays.copyOf(numeros[2], 3);
        tmp[3] = Arrays.copyOf(numeros[3], 3);
        numeros[0] = numerosTournes(tmp[3]);
        numeros[1] = tmp[0];
        numeros[2] = numerosTournes(tmp[1]);
        numeros[3] = tmp[2];
    }

}