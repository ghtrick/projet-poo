import java.util.LinkedList;
import java.util.Random;

public class Joueur {
    static Random rand = new Random();
    private LinkedList<Domino> main = new LinkedList<Domino>();
    private int point;

    public Joueur(LinkedList<Domino> main, int point) {
        this.main = main;
        this.point=point;
    }

    public void Jouer(int i) {
        main.remove(i);
    }

    public void piocher(Sac s) {
        int r = rand.nextInt(s.getDominosSac().size()-1);
        main.add(s.getDominosSac().get(r));
        LinkedList<Domino> l = s.getDominosSac();
        l.remove(r);
        s.setDominosSac(l);
    }

    public void setMain(LinkedList<Domino> mainJ1) {
        this.main=mainJ1;
    }


}
