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
            int[] cotereel = new int[3];
            cotereel[0] = (int) plateau.get(y+1).get(x).getNumeros()[3][0].getVal();
            cotereel[1] = (int) plateau.get(y+1).get(x).getNumeros()[3][1].getVal();
            cotereel[2] = (int) plateau.get(y+1).get(x).getNumeros()[3][2].getVal();
            int[] coteTest = new int[3];
            coteTest[0] = (int) t.getNumeros()[1][0].getVal();
            coteTest[1] = (int) t.getNumeros()[1][1].getVal();
            coteTest[2] = (int) t.getNumeros()[1][2].getVal();
            System.out.println(Arrays.toString(cotereel));
            System.out.println(Arrays.toString(coteTest));
            bool.add(Arrays.equals(cotereel, coteTest));
            for (int x1 :  coteTest) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            int[] cotereel = new int[3];
            cotereel[0] = (int) plateau.get(y).get(x-1).getNumeros()[0][0].getVal();
            cotereel[1] = (int) plateau.get(y).get(x-1).getNumeros()[0][1].getVal();
            cotereel[2] = (int) plateau.get(y).get(x-1).getNumeros()[0][2].getVal();
            int[] coteTest = new int[3];
            coteTest[0] = (int) t.getNumeros()[2][0].getVal();
            coteTest[1] = (int) t.getNumeros()[2][1].getVal();
            coteTest[2] = (int) t.getNumeros()[2][2].getVal();
            System.out.println(Arrays.toString(cotereel));
            System.out.println(Arrays.toString(coteTest));
            bool.add(Arrays.equals(cotereel, coteTest));
            for (int x1 :  coteTest) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            int[] cotereel = new int[3];
            cotereel[0] = (int) plateau.get(y-1).get(x).getNumeros()[1][0].getVal();
            cotereel[1] = (int) plateau.get(y-1).get(x).getNumeros()[1][1].getVal();
            cotereel[2] = (int) plateau.get(y-1).get(x).getNumeros()[1][2].getVal();
            int[] coteTest = new int[3];
            coteTest[0] = (int) t.getNumeros()[3][0].getVal();
            coteTest[1] = (int) t.getNumeros()[3][1].getVal();
            coteTest[2] = (int) t.getNumeros()[3][2].getVal();
            System.out.println(Arrays.toString(cotereel));
            System.out.println(Arrays.toString(coteTest));
            bool.add(Arrays.equals(cotereel, coteTest));
            for (int x1 :  coteTest) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            int[] cotereel = new int[3];
            cotereel[0] = (int) plateau.get(y).get(x+1).getNumeros()[2][0].getVal();
            cotereel[1] = (int) plateau.get(y).get(x+1).getNumeros()[2][1].getVal();
            cotereel[2] = (int) plateau.get(y).get(x+1).getNumeros()[2][2].getVal();
            int[] coteTest = new int[3];
            coteTest[0] = (int) t.getNumeros()[0][0].getVal();
            coteTest[1] = (int) t.getNumeros()[0][1].getVal();
            coteTest[2] = (int) t.getNumeros()[0][2].getVal();
            System.out.println(Arrays.toString(cotereel));
            System.out.println(Arrays.toString(coteTest));
            bool.add(Arrays.equals(cotereel, coteTest));
            for (int x1 :  coteTest) {
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
            if (y==-1) {
                System.out.println("1");
                for (int i = 0; i < plateau.size(); i++) {
                    if (x==i) plateau.get(i).addFirst(t);
                    else plateau.get(i).addFirst(new Domino());
                }
            }
            else if (x==-1) {
                System.out.println("4");
                plateau.add(new LinkedList<AbstractTuile>());
                for (int i = 0; i < y; i++) {
                    plateau.get(plateau.size()-1).addFirst(new Domino());
                }
                plateau.get(plateau.size()-1).add(t);
            }
            else if (y==plateau.size()) {
                System.out.println("3");
                plateau.get(x).add(t);
            }
            else if (x==plateau.get(y).size()) {
                System.out.println("2");
                plateau.addFirst(new LinkedList<AbstractTuile>());
                for (int i = 0; i < y; i++) {
                    plateau.get(0).add(new Domino());
                }
                plateau.get(0).add(t);
            }
            else plateau.get(y).set(x, t);
            System.out.println("joueurcourant: "+jeu.getJoueurCourant());
            int res3 = res+jeu.getJoueurs().get(jeu.getJoueurCourant()).getPoint();
            jeu.getJoueurs().get(jeu.getJoueurCourant()).setPoint(res3);
            return true;
        }
        return false;

    }
}
