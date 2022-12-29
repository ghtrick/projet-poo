import java.util.LinkedList;

public abstract class AbstractJeu {
    protected AbstractPlateau plateau;
    protected AbstractTuile main;
    protected AbstractSac sac;
    protected LinkedList<AbstractJoueur> joueurs;

    public abstract int lancerPartie();

    public abstract void partieFinie(int gagnant);

    public abstract void choisirJoueurs();

    public abstract void piocher(AbstractSac s);
}
