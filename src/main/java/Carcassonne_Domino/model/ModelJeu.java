package src.main.java.Carcassonne_Domino.model;
import java.util.Arrays;
import java.util.LinkedList;
import src.main.java.Carcassonne_Domino.AbstractClasses.AbstractJeu;
import src.main.java.Carcassonne_Domino.view_Terminal.Partie;

public class ModelJeu{
    
    public Partie p;
    public Sac s = new Sac();
    public Joueur joueur1 = new Joueur(null, 0);
    public Joueur joueur2 = new Joueur(null, 0);
    public int taillePlateau;

    public ModelJeu() {
        s = new Sac();
        s.remplirSac();
        LinkedList<Domino> s1 = s.getDominosSac();
        s1.removeFirst();
        s.setDominosSac(s1);
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

    

}
