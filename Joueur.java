import java.util.LinkedList;
import java.util.Random;

public class Joueur {
    static Random rand = new Random();
    protected int point;
    protected Domino main;
    protected int numeroDeJoueur;

    public Joueur(Domino main, int point, int numeroDeJoueur) {
        this.main = main;
        this.point=point;
        this.numeroDeJoueur=numeroDeJoueur;
    }

    public void piocher(Sac s) {
        main=s.getDominosSac().get(0);
        LinkedList<Domino> l = s.getDominosSac();
        l.remove(0);
        s.setDominosSac(l);
    }

    public void setMain(Domino mainJ1) {
        this.main=mainJ1;
    }

    public Domino getMain() {
        return main;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point=point;
    }

}
