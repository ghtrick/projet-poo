import java.util.Arrays;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;

public abstract class AbstractPlateau {
    protected AbstractJeu jeu;
    protected LinkedList<LinkedList<AbstractTuile>> plateau;
    protected int nbLignes;
    protected int nbColonnes;

    public AbstractPlateau(LinkedList<LinkedList<AbstractTuile>> plateau, AbstractJeu jeu) {
        this.plateau = plateau;
        this.jeu=jeu;
    }

    public boolean ajoutTuileTerm(Domino t, int x, int y) {
        int res = 0;
        LinkedList<Boolean> bool = new LinkedList<>();
        boolean numeroCorrect = true;
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x+1).getNumeros()[3], t.numeros[1]));
            for (int x1 :  t.numeros[1]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y+1).get(x).getNumeros()[0], t.numeros[2]));
            for (int x1 :  t.numeros[2]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x-1).getNumeros()[1], t.numeros[3]));
            for (int x1 :  t.numeros[3]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y-1).get(x).getNumeros()[2], t.numeros[0]));
            for (int x1 :  t.numeros[0]) {
                res+=x1;
            }
        } catch (Exception e) {}
        for(int i=0; i<bool.size(); i++) {
            if(!bool.get(i)) {
                numeroCorrect = false;
            }
        }
        if (bool.isEmpty()) {
            numeroCorrect = false;
        }
        if(numeroCorrect) {
            if(x==-1 || x==plateau.get(0).size()) {
                if(x==-1) x=0;
                for(int i=0; i<plateau.size(); i++) {
                    if(i!=y) plateau.get(i).add(x, new Domino());
                    else plateau.get(i).add(x, t);
                }
            } else if (y==-1 || y==plateau.size()) {
                if (y==-1) {
                    y=0;
                    plateau.add(y, new LinkedList<AbstractTuile>());
                    for(int i=0; i<plateau.get(y+1).size(); i++) {
                        if(i!=x) plateau.get(y).add(i, new Domino());
                        else {
                            plateau.get(y).add(i, t);
                        }
                    }
                } else {
                    plateau.add(y, new LinkedList<AbstractTuile>());
                    for(int i=0; i<plateau.get(y-1).size(); i++) {
                        if(i!=x) plateau.get(y).add(i, new Domino());
                        else {
                            plateau.get(y).add(i, t);
                        }
                    }
                }
            } else {
                if(plateau.get(y).get(x).isEstVide()) {
                    plateau.get(y).set(x, t);
                }
            }
            System.out.println("joueurcourant: "+jeu.joueurCourant);
            int res3 = res+jeu.joueurs.get(jeu.joueurCourant).point;
            jeu.joueurs.get(jeu.joueurCourant).point = res3;
            return true;
        }
        return false;
    }

    public boolean ajoutTuile(AbstractTuile t, int y, int x) {
        int res = 0;
        LinkedList<Boolean> bool = new LinkedList<>();
        boolean numeroCorrect = true;
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x+1).numeros[3], t.numeros[1]));
            for (int x1 :  t.numeros[1]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y+1).get(x).numeros[0], t.numeros[2]));
            for (int x1 :  t.numeros[2]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x-1).numeros[1], t.numeros[3]));
            for (int x1 :  t.numeros[3]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
           bool.add(Arrays.equals(plateau.get(y-1).get(x).numeros[2], t.numeros[0]));
            for (int x1 :  t.numeros[0]) {
                res+=x1;
            }
        } catch (Exception e) {}

        for(int i=0; i<bool.size(); i++) {
            if(!bool.get(i)) {
                numeroCorrect = false;
            }
        }
        if (bool.isEmpty()) {
            numeroCorrect = false;
        }
        if(numeroCorrect) {
            plateau.get(y).set(x, t);
            int res3 = res+jeu.joueurs.get(jeu.joueurCourant).point;
            jeu.joueurs.get(jeu.joueurCourant).point = res3;
            return true;
        }
        return false;
    }

    public abstract ImageIcon createImage(AbstractTuile t);
 
}
