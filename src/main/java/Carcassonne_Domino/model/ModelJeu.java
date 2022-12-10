package src.main.java.Carcassonne_Domino.model;
import java.util.Arrays;

import src.main.java.Carcassonne_Domino.view_Terminal.PartieTerm;

public class ModelJeu {
    
    public PartieTerm p;

    public int point(int x, int y){
        int res = 0;
        try {
            if(Arrays.equals(p.plateau.get(y).get(x).getNumeros()[3], p.plateau.get(y).get(x-1).getNumeros()[1])) {
                for(int i=0; i<p.plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= p.plateau.get(y).get(x).getNumeros()[3][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(p.plateau.get(y).get(x).getNumeros()[0], p.plateau.get(y+1).get(x).getNumeros()[2])) {
                for(int i=0; i<p.plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= p.plateau.get(y).get(x).getNumeros()[3][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(p.plateau.get(y).get(x).getNumeros()[1], p.plateau.get(y).get(x+1).getNumeros()[3])) {
                for(int i=0; i<p.plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= p.plateau.get(y).get(x).getNumeros()[3][i];
                }
            }
        } catch (Exception e) {}
        try {
            if(Arrays.equals(p.plateau.get(y-1).get(x).getNumeros()[2], p.plateau.get(y-1).get(x).getNumeros()[0])) {
                for(int i=0; i<p.plateau.get(y).get(x).getNumeros()[3].length; i++) {
                    res+= p.plateau.get(y).get(x).getNumeros()[3][i];
                }
           }
        } catch (Exception e) {}
        return res;
    }

    

}
