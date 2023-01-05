package src.main.java.model;
import java.util.Arrays;

import src.main.java.abstractClass.*;

public class Domino extends AbstractTuile{
    
    public Domino() {
        super(null);
        this.estVide = true;
    }

    public Domino(Chiffre[][] numeros) {
        super(numeros);
        this.estVide = false;
    }

    public boolean test(Chiffre[] x, int orientation) {
        boolean inverse = true;
        if(orientation==1 || orientation==0) inverse = true;
        else inverse = false;
        for (int i = 0; i < 4; i++) {
            if(inverse) {
                if(i<2) {
                    if (Arrays.equals(x, Util.numerosTournes(numeros[i]))) {
                        return true;
                    }
                } else {
                    if (Arrays.equals(x, numeros[i])) {
                        return true;
                    }
                }
            } else {
                if(i>=2) {
                    if (Arrays.equals(x, Util.numerosTournes(numeros[i]))) {
                        return true;
                    }
                } else {
                    if (Arrays.equals(x, numeros[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
