import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //Sac s = new Sac(new LinkedList<>());
        //s.remplirSac();
        int[][] x = {{1,2,3},{4,5,6},{3,2,1},{6,5,4}};
        int[][] y = {{1,2,3},{5,5,5},{1,1,1},{6,6,6}};
        Domino d = new Domino(x);
        Domino d1 = new Domino(y);
        System.out.println(d.affiche());
        System.out.println(d1.affiche());
        //
        System.out.println(d.test(d1.getNumeros()[0]));

        /*
         *    321    456    123
         *   6   4  3   3  6   4
         *   5   5  2   2  5   5
         *   4   6  1   1  4   6
         *    321    654    123
         */
    }
}
