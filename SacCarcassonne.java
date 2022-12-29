import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class SacCarcassonne extends Sac {

    DominoCarcassonne carte1 = new DominoCarcassonne(new int[][]{{0,0,0},{0,0,0},{0,1,0},{0,1,0}},1);
    DominoCarcassonne carte2 = new DominoCarcassonne(new int[][]{{3,2,3},{0,1,0},{0,1,0},{0,0,0}},2);
    DominoCarcassonne carte3 = new DominoCarcassonne(new int[][]{{2,2,3},{0,1,0},{0,1,0},{2,2,3}},3);
    DominoCarcassonne carte4 = new DominoCarcassonne(new int[][]{{2,2,2},{2,2,3},{0,1,0},{2,2,3}},4);
    DominoCarcassonne carte5 = new DominoCarcassonne(new int[][]{{2,2,2},{2,2,3},{0,0,0},{2,2,3}},5);
    DominoCarcassonne carte6 = new DominoCarcassonne(new int[][]{{2,2,3},{0,1,0},{0,1,0},{2,2,3}},6);
    DominoCarcassonne carte7 = new DominoCarcassonne(new int[][]{{3,2,3},{0,1,0},{0,1,0},{0,1,0}},7);
    DominoCarcassonne carte8 = new DominoCarcassonne(new int[][]{{0,1,0},{0,0,0},{0,1,0},{0,0,0}},8);
    DominoCarcassonne carte9 = new DominoCarcassonne(new int[][]{{0,0,0},{0,1,0},{0,1,0},{0,1,0}},9);
    DominoCarcassonne carte10 = new DominoCarcassonne(new int[][]{{3,2,3},{0,0,0},{0,1,0},{0,1,0}},10);
    DominoCarcassonne carte11 = new DominoCarcassonne(new int[][]{{3,2,3},{0,1,0},{0,0,0},{0,1,0}},11);
    DominoCarcassonne carte12 = new DominoCarcassonne(new int[][]{{0,0,0},{3,2,3},{0,0,0},{3,2,3}},12);
    DominoCarcassonne carte13 = new DominoCarcassonne(new int[][]{{2,2,3},{0,0,0},{3,0,0},{2,2,3}},13);
    DominoCarcassonne carte14 = new DominoCarcassonne(new int[][]{{0,0,0},{0,0,0},{0,1,0},{0,0,0}},14);
    DominoCarcassonne carte15 = new DominoCarcassonne(new int[][]{{0,0,0},{0,0,0},{0,0,0},{0,0,0}},15);
    DominoCarcassonne carte16 = new DominoCarcassonne(new int[][]{{2,2,2},{2,2,3},{0,0,0},{2,2,3}},16);
    DominoCarcassonne carte17 = new DominoCarcassonne(new int[][]{{3,2,3},{3,2,3},{0,0,0},{0,0,0}},17);
    DominoCarcassonne carte18 = new DominoCarcassonne(new int[][]{{3,2,3},{0,0,0},{0,0,0},{0,0,0}},18);
    DominoCarcassonne carte19 = new DominoCarcassonne(new int[][]{{0,0,0},{3,2,3},{0,0,0},{3,2,3}},19);
    DominoCarcassonne carte20 = new DominoCarcassonne(new int[][]{{2,2,2},{2,2,3},{0,1,0},{2,2,3}},20);
    DominoCarcassonne carte21 = new DominoCarcassonne(new int[][]{{2,2,2},{2,2,2},{2,2,2},{2,2,2}},21);
    DominoCarcassonne carte22 = new DominoCarcassonne(new int[][]{{0,1,0},{0,1,0},{0,1,0},{0,1,0}},22);
    DominoCarcassonne carte23 = new DominoCarcassonne(new int[][]{{2,2,3},{0,0,0},{0,0,0},{2,2,3}},23);
    DominoCarcassonne carte24 = new DominoCarcassonne(new int[][]{{0,0,0},{3,2,3},{0,0,0},{3,2,3}},24);

    public SacCarcassonne() {
        super();
    }

    @Override
    public void remplirSac() {

        Random rand = new Random();

        LinkedList<DominoCarcassonne> list1 = new LinkedList<>();
        Collection<DominoCarcassonne> elements1 = Collections.nCopies(9, carte1);
        Collection<DominoCarcassonne> elements2 = Collections.nCopies(3, carte2);
        Collection<DominoCarcassonne> elements3 = Collections.nCopies(2, carte3);
        Collection<DominoCarcassonne> elements4 = Collections.nCopies(1, carte4);
        Collection<DominoCarcassonne> elements5 = Collections.nCopies(1, carte5);
        Collection<DominoCarcassonne> elements6 = Collections.nCopies(3, carte6);
        Collection<DominoCarcassonne> elements7 = Collections.nCopies(3, carte7);
        Collection<DominoCarcassonne> elements8 = Collections.nCopies(8, carte8);
        Collection<DominoCarcassonne> elements9 = Collections.nCopies(4, carte9);
        Collection<DominoCarcassonne> elements10 = Collections.nCopies(5, carte10);
        Collection<DominoCarcassonne> elements11 = Collections.nCopies(2, carte11);
        Collection<DominoCarcassonne> elements12 = Collections.nCopies(3, carte12);
        Collection<DominoCarcassonne> elements13 = Collections.nCopies(4, carte13);
        Collection<DominoCarcassonne> elements14 = Collections.nCopies(2, carte14);
        Collection<DominoCarcassonne> elements15 = Collections.nCopies(3, carte15);
        Collection<DominoCarcassonne> elements16 = Collections.nCopies(2, carte16);
        Collection<DominoCarcassonne> elements17 = Collections.nCopies(4, carte17);
        Collection<DominoCarcassonne> elements18 = Collections.nCopies(3, carte18);
        Collection<DominoCarcassonne> elements19 = Collections.nCopies(1, carte19);
        Collection<DominoCarcassonne> elements20 = Collections.nCopies(2, carte20);
        Collection<DominoCarcassonne> elements21 = Collections.nCopies(1, carte21);
        Collection<DominoCarcassonne> elements22 = Collections.nCopies(1, carte22);
        Collection<DominoCarcassonne> elements23 = Collections.nCopies(2, carte23);
        Collection<DominoCarcassonne> elements24 = Collections.nCopies(3, carte24);
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
            dominosSac.add(list1.get(a));
            list1.remove(a);
        }
    }
}
