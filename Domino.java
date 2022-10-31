import java.util.Arrays;

public class Domino {

    private final int[][] numeros;
    private char orientation;

    public Domino(int[][] numeros, char orientation) {
        this.numeros = numeros;
        this.orientation=orientation;
    }

    public void affiche() {
        for (int[] t: this.numeros) {
            System.out.println(Arrays.toString(t));
        }
    }
}
