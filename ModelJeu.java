import java.util.Arrays;
import java.util.LinkedList;

public class ModelJeu{
    
    public Partie p;
    public Sac s = new Sac();
    public LinkedList<Joueur> joueurs = new LinkedList<>(); 
    public PartieG partie;

    public ModelJeu(boolean isDomino) {
        if (isDomino) s = new Sac();
        else s = new SacCarcassonne(); 
        s.remplirSac();
        LinkedList<Domino> s1 = s.getDominosSac();
        s1.removeFirst();
        s.setDominosSac(s1);
    }

    public void initJoueurs(int x, boolean[] bots, boolean isText) {
        if(isText) {
            for (int i = 0; i < x; i++) {
                if (bots[i]) {
                    Bot b = new Bot(null, 0, i+1);
                    b.model = this;
                    joueurs.add(b);
                } else {
                    joueurs.add(new Joueur(null, 0, i+1));
                }
            }
        } else {
            for (int i = 0; i < x; i++) {
                if (bots[i]) {
                    Bot b = new Bot(null, 0, i+1);
                    b.model = this;
                    b.partie = partie;
                    joueurs.add(b);
                } else {
                    joueurs.add(new Joueur(null, 0, i+1));
                }
            }
        }
    }

    public int point(int x, int y){
        int res = 0;
        try {
            if(Arrays.equals(p.plateau.get(y).get(x).getNumeros()[3], p.plateau.get(y).get(x-1).getNumeros()[1])) {
                for(int i=0; i<p.plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= p.plateau.get(y).get(x-1).getNumeros()[1][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(p.plateau.get(y).get(x).getNumeros()[0], p.plateau.get(y-1).get(x).getNumeros()[2])) {
                for(int i=0; i<p.plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= p.plateau.get(y-1).get(x).getNumeros()[2][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(p.plateau.get(y).get(x).getNumeros()[1], p.plateau.get(y).get(x+1).getNumeros()[3])) {
                for(int i=0; i<p.plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= p.plateau.get(y).get(x+1).getNumeros()[3][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(p.plateau.get(y).get(x).getNumeros()[2], p.plateau.get(y+1).get(x).getNumeros()[0])) {
                for(int i=0; i<p.plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= p.plateau.get(y+1).get(x).getNumeros()[0][i];
                }
           }
        } catch (Exception e) {}
        return res;
    }

    public int max() {
        int scoremax = 0;
        int indexmax = 0;
        for (int i = 0; i < joueurs.size(); i++) {
            Joueur j = joueurs.get(i); 
            if (j.getPoint()>scoremax) {
                scoremax=j.getPoint();
                indexmax = i;
            } else if(j.getPoint()==scoremax) {
                indexmax = -1;
            }
        }
        return joueurs.get(indexmax).numeroDeJoueur;
    }

}
