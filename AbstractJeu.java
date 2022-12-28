import java.util.LinkedList;


public abstract class AbstractJeu {    
    public LinkedList<LinkedList<Domino>> plateau;

    public AbstractJeu() {
        LinkedList<Domino> d = new LinkedList<>();
        this.plateau=new LinkedList<>();
        plateau.add(d);
    }

    public LinkedList<LinkedList<Domino>> getPlateau() {
		return plateau;
	}

    public void setPlateau(LinkedList<LinkedList<Domino>> a) {
        plateau=a;
    }

    public abstract boolean ajoutDomino(Domino d, int x, int y);

    public abstract int partie2Joueurs() throws InterruptedException;

    public abstract void partieFini(int gagnant);
    
    }
