package src.main.java.Carcassonne_Domino.model;
import java.util.LinkedList;
import java.util.Random;

public class Joueur {
    static Random rand = new Random();
    private int point;
    private Domino main;

    public Joueur(Domino main, int point) {
        this.main = main;
        this.point=point;
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
