import java.util.LinkedList;
import java.util.Random;

public class Joueur {
    static Random rand = new Random();
    private LinkedList<Domino> main = new LinkedList<Domino>();

    public Joueur(LinkedList<Domino> main) {
        this.main = main;
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


}
