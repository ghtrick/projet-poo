import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class SacCartesCarcassonne extends AbstractSac {

    public SacCartesCarcassonne() {
        super();
        remplirSac();
    }

    @Override
    public void remplirSac() {
        CarteCarcassonne carte1 = new CarteCarcassonne(1);
        CarteCarcassonne carte2 = new CarteCarcassonne(2);
        CarteCarcassonne carte3 = new CarteCarcassonne(3);
        CarteCarcassonne carte4 = new CarteCarcassonne(4);
        CarteCarcassonne carte5 = new CarteCarcassonne(5);
        CarteCarcassonne carte6 = new CarteCarcassonne(6);
        CarteCarcassonne carte7 = new CarteCarcassonne(7);
        CarteCarcassonne carte8 = new CarteCarcassonne(8);
        CarteCarcassonne carte9 = new CarteCarcassonne(9);
        CarteCarcassonne carte10 = new CarteCarcassonne(10);
        CarteCarcassonne carte11 = new CarteCarcassonne(11);
        CarteCarcassonne carte12 = new CarteCarcassonne(12);
        CarteCarcassonne carte13 = new CarteCarcassonne(13);
        CarteCarcassonne carte14 = new CarteCarcassonne(14);
        CarteCarcassonne carte15 = new CarteCarcassonne(15);
        CarteCarcassonne carte16 = new CarteCarcassonne(16);
        CarteCarcassonne carte17 = new CarteCarcassonne(17);
        CarteCarcassonne carte18 = new CarteCarcassonne(18);
        CarteCarcassonne carte19 = new CarteCarcassonne(19);
        CarteCarcassonne carte20 = new CarteCarcassonne(20);
        CarteCarcassonne carte21 = new CarteCarcassonne(21);
        CarteCarcassonne carte22 = new CarteCarcassonne(22);
        CarteCarcassonne carte23 = new CarteCarcassonne(23);
        CarteCarcassonne carte24 = new CarteCarcassonne(24);

        LinkedList<CarteCarcassonne> list1 = new LinkedList<>();
        Collection<CarteCarcassonne> elements1 = Collections.nCopies(9, carte1);
        Collection<CarteCarcassonne> elements2 = Collections.nCopies(3, carte2);
        Collection<CarteCarcassonne> elements3 = Collections.nCopies(2, carte3);
        Collection<CarteCarcassonne> elements4 = Collections.nCopies(1, carte4);
        Collection<CarteCarcassonne> elements5 = Collections.nCopies(1, carte5);
        Collection<CarteCarcassonne> elements6 = Collections.nCopies(3, carte6);
        Collection<CarteCarcassonne> elements7 = Collections.nCopies(3, carte7);
        Collection<CarteCarcassonne> elements8 = Collections.nCopies(8, carte8);
        Collection<CarteCarcassonne> elements9 = Collections.nCopies(4, carte9);
        Collection<CarteCarcassonne> elements10 = Collections.nCopies(5, carte10);
        Collection<CarteCarcassonne> elements11 = Collections.nCopies(2, carte11);
        Collection<CarteCarcassonne> elements12 = Collections.nCopies(3, carte12);
        Collection<CarteCarcassonne> elements13 = Collections.nCopies(4, carte13);
        Collection<CarteCarcassonne> elements14 = Collections.nCopies(2, carte14);
        Collection<CarteCarcassonne> elements15 = Collections.nCopies(3, carte15);
        Collection<CarteCarcassonne> elements16 = Collections.nCopies(2, carte16);
        Collection<CarteCarcassonne> elements17 = Collections.nCopies(4, carte17);
        Collection<CarteCarcassonne> elements18 = Collections.nCopies(3, carte18);
        Collection<CarteCarcassonne> elements19 = Collections.nCopies(1, carte19);
        Collection<CarteCarcassonne> elements20 = Collections.nCopies(2, carte20);
        Collection<CarteCarcassonne> elements21 = Collections.nCopies(1, carte21);
        Collection<CarteCarcassonne> elements22 = Collections.nCopies(1, carte22);
        Collection<CarteCarcassonne> elements23 = Collections.nCopies(2, carte23);
        Collection<CarteCarcassonne> elements24 = Collections.nCopies(3, carte24);
        list1.addAll(elements1);
        list1.addAll(elements2);
        list1.addAll(elements3);
        list1.addAll(elements4);
        list1.addAll(elements5);
        list1.addAll(elements6);
        list1.addAll(elements7);
        list1.addAll(elements8);
        list1.addAll(elements9);
        list1.addAll(elements10);
        list1.addAll(elements11);
        list1.addAll(elements12);
        list1.addAll(elements13);
        list1.addAll(elements14);
        list1.addAll(elements15);
        list1.addAll(elements16);
        list1.addAll(elements17);
        list1.addAll(elements18);
        list1.addAll(elements19);
        list1.addAll(elements20);
        list1.addAll(elements21);
        list1.addAll(elements22);
        list1.addAll(elements23);
        list1.addAll(elements24);
        for (int i = 0; i < 72; i++) {
            int a = rand.nextInt(list1.size());
            tuilesDansLeSac.add(list1.get(a));
            list1.remove(a);
        }
    }
    
}
