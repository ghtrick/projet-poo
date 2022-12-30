import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class test {
    public static void main(String[] args) {
        Domino d = new Domino();
        Collection<Domino> elements1 = Collections.nCopies(9, d);
        LinkedList<Domino> l = new LinkedList<>(elements1);
        for (Domino d1 : l) {
            System.out.println(d1);
        }
    }
}
