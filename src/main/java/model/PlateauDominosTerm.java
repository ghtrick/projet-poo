package src.main.java.model;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.*;

import src.main.java.abstractClass.*;
public class PlateauDominosTerm extends AbstractPlateau{

    public PlateauDominosTerm(PartieDominosTerm p) {
        super(new LinkedList<>(),p);
    }

    @Override
    public ImageIcon createImage(AbstractTuile t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean ajoutTuile(AbstractTuile t, int y, int x) {
        int res = 0;
        LinkedList<Boolean> bool = new LinkedList<>();
        boolean numeroCorrect = true;
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x+1).getNumeros()[3], t.getNumeros()[1]));
            for (AbstractElement x1 :  t.getNumeros()[1]) {
                res+=(int) x1.getVal();
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y+1).get(x).getNumeros()[0], t.getNumeros()[2]));
            for (AbstractElement x1 :  t.getNumeros()[2]) {
                res+=(int) x1.getVal();
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x-1).getNumeros()[1], t.getNumeros()[3]));
            for (AbstractElement x1 :  t.getNumeros()[3]) {
                res+=(int) x1.getVal();
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y-1).get(x).getNumeros()[2], t.getNumeros()[0]));
            for (AbstractElement x1 :  t.getNumeros()[0]) {
                res+=(int) x1.getVal();
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
            System.out.println("joueurcourant: "+jeu.getJoueurCourant());
            int res3 = res+jeu.getJoueurs().get(jeu.getJoueurCourant()).getPoint();
            jeu.getJoueurs().get(jeu.getJoueurCourant()).setPoint(res3);
            return true;
        }
        return false;

    }
}
