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
        main.add(s.getDominosSac().get(0));
        LinkedList<Domino> l = s.getDominosSac();
        l.remove(0);
        s.setDominosSac(l);
    }

    public void setMain(LinkedList<Domino> mainJ1) {
        this.main=mainJ1;
    }

    public LinkedList<Domino> getMain() {
        return main;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point=point;
    }

}
