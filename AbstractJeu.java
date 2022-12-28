import java.util.LinkedList;


public abstract class AbstractJeu {    
    public LinkedList<LinkedList<Domino>> plateau;
    protected Joueur joueur1;
    protected Joueur joueur2;

    public AbstractJeu() {
        LinkedList<Domino> d = new LinkedList<>();
        this.plateau=new LinkedList<>();
        plateau.add(d);
        this.joueur1=new Joueur(new Domino(), 0);
        this.joueur2=new Joueur(new Domino(), 0);
    }

    public LinkedList<LinkedList<Domino>> getPlateau() {
		return plateau;
	}

    public void setPlateau(LinkedList<LinkedList<Domino>> a) {
        plateau=a;
    }

    public abstract boolean ajoutDomino(Domino d, int x, int y);

    public abstract int partie2Joueurs();

    public abstract void partieFini(int gagnant);
    
    }
