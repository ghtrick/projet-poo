import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //Sac s = new Sac(new LinkedList<>());
        //s.remplirSac();
        int[][] x = {{1,1,1},{1,1,1},{1,1,1},{1,1,1}};
        int[][] y = {{1,1,1},{1,1,1},{1,1,1},{1,1,1}};
        /*System.out.println(d.affiche());
        System.out.println(d1.affiche());*/
        //
        //System.out.println(d.test(d1.getNumeros()[0],0));
        Domino d = new Domino(x);
        Domino d1 = new Domino(y);
        Partie p = new Partie();
        LinkedList<LinkedList<Domino>> a = p.getPlateau();
        a.get(0).set(0, d);
        p.setPlateau(a);
        //p.ajoutDomino(d1, 0, 0);
        p.ajoutDomino(d1, 0, 1);
        p.ajoutDomino(d1, 0, -1);
        //p.ajoutDomino(d1, -1, 1);
        //p.ajoutDomino(d1, 0, 1);
        //p.ajoutDomino(d1, 1, 1);
        //p.ajoutDomino(d1, -1, 1);
        //p.ajoutDomino(d1, 3, 1);
        //p.ajoutDomino(d1, 4, 2);
        System.out.println(p.affichePlateau()); 
        /*
         *    321    456    123
         *   6   4  3   3  6   4
         *   5   5  2   2  5   5
         *   4   6  1   1  4   6
         *    321    654    123
         */
    }
}
