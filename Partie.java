import java.util.LinkedList;

public class Partie {

    LinkedList<LinkedList<Domino>> plateau;

    public Partie() {
        this.plateau=new LinkedList<>();
    }

    public void affichePlateau() {
        for (int i = 0; i < plateau.size(); i++) {
            for (int j = 0; j < plateau.get(i).size(); j++) {
                
            }
        }
    }

    public void ajoutDomino(Domino d, int x, int y) {
        if(x==0 || x==plateau.get(y).size()) {
            for(int i=0; i<plateau.size(); i++) {
                if(i!=y) plateau.get(i).add(x, new Domino());
                else plateau.get(i).add(x, d);
            } 
        } else if (y==0 || y==plateau.size()) {
            plateau.add(y, new LinkedList<Domino>());
            for(int i=0; i<plateau.get(y+1).size(); i++) {
                if(i!=x) plateau.get(y).add(i, new Domino());
                else plateau.get(y).add(i, d);
            }
        } else {
            if(plateau.get(y).get(x).isEstVide()) {
                plateau.get(y).set(x, d);
            }
        }
    }
}
