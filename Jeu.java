import java.util.LinkedList;

public abstract class Jeu {
    protected LinkedList<LinkedList<Domino>> plateau;
    protected Joueur joueur1;
    protected Joueur joueur2;

    public Jeu() {
        LinkedList<Domino> d = new LinkedList<>();
        this.plateau=new LinkedList<>();
        plateau.add(d);
        this.joueur1=new Joueur(new LinkedList<>(), 0);
        this.joueur2=new Joueur(new LinkedList<>(), 0);
    }

    public LinkedList<LinkedList<Domino>> getPlateau() {
		return plateau;
	}

    public void setPlateau(LinkedList<LinkedList<Domino>> a) {
        plateau=a;
    }

    abstract String affichePlateau();
    abstract boolean ajoutDomino(Domino d, int x, int y);
    abstract Joueur partie2Joueurs();
    abstract int point(int x, int y);
}
