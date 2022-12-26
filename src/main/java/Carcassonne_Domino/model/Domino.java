package src.main.java.Carcassonne_Domino.model;
import java.util.Arrays;

import src.main.java.Carcassonne_Domino.AbstractClasses.AbstractTuile;

public class Domino extends AbstractTuile{

    /*
     *          123     123
     *         C   4   6   1
     *         B   5   6   2
     *         A   6   6   3
     *          987     111
     *               
     * 
     */

    private int[][] numeros;
    private boolean estVide;

    public Domino(int[][] numeros) {
        this.numeros = numeros;
        this.estVide = false;
    }

    public boolean isEstVide() {
        return estVide;
    }

    public Domino() {
        this.estVide = true;
    }

    public int[][] getNumeros() {
        return this.numeros;
    }

    public static int[] numerosTournes(int[] t) {
        return new int[]{t[2],t[1],t[0]};
    }

    /*public boolean testNumeros(String s, Domino d) {
        boolean b = Boolean.parseBoolean(s);
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (b) {
                    if (Arrays.equals(numerosTournes(d.getNumeros()[j]),this.getNumeros()[i])) {
                        return true;
                    }
                }
                else {
                    if (Arrays.equals(d.getNumeros()[j],this.getNumeros()[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean estJouableUnCote(Domino d) {
        switch (this.orientation) {
            case 0:
                return testNumeros("j>=2",d);
            case 1:
                return testNumeros("j!=0 && j!=3",d);
            case 2:
                return testNumeros("j<2",d); 
            default:
                return testNumeros("j!=1 && j!=2",d);    
        }
    }*/

    public String affiche() {
        String res="";
        if (isEstVide()) {
            res+="     "+"\n";
            res+="     "+"\n";
            res+="     "+"\n";
        } else {
            res += " "+numeros[0][0] + "" + numeros[0][1] + "" + numeros[0][2]+"\n";
            res += numeros[3][0]+"   "+numeros[1][0]+"\n";
            res += numeros[3][1]+"   "+ numeros[1][1]+"\n";
            res += numeros[3][2]+"   "+numeros[1][2]+"\n";
            res += " "+numeros[2][0] + "" + numeros[2][1] +""+ numeros[2][2]+"\n";
        }
        return res;
    }

    public void tourneUneFois() {
        int[][] tmp = new int[4][3];
        tmp[0] = Arrays.copyOf(numeros[0], 3);
        tmp[1] = Arrays.copyOf(numeros[1], 3);
        tmp[2] = Arrays.copyOf(numeros[2], 3);
        tmp[3] = Arrays.copyOf(numeros[3], 3);
        numeros[0] = numerosTournes(tmp[3]);
        numeros[1] = tmp[0];
        numeros[2] = numerosTournes(tmp[1]);
        numeros[3] = tmp[2];
    }

    public boolean test(int[] x, int orientation) {
        boolean inverse = true;
        if(orientation==1 || orientation==0) inverse = true;
        else inverse = false;
        for (int i = 0; i < 4; i++) {
            if(inverse) {
                if(i<2) {
                    if (Arrays.equals(x, numerosTournes(numeros[i]))) {
                        return true;
                    }
                } else {
                    if (Arrays.equals(x, numeros[i])) {
                        return true;
                    }
                }
            } else {
                if(i>=2) {
                    if (Arrays.equals(x, numerosTournes(numeros[i]))) {
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

    /* if(orientation == 0) i<2
     * if(orientation == 1) i<2
     * if(orientation == 2) i>=2
     * if(orientation == 3) i>=2
     * 
     */
}
