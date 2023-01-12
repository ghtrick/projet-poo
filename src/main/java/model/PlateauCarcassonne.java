package src.main.java.model;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;

import src.main.java.abstractClass.*;

public class PlateauCarcassonne extends AbstractPlateau{

    public PlateauCarcassonne(PartieCarcassonne p) {
        super(new LinkedList<>(), p);
        for (int i = 0; i < 20; i++) {
            plateau.add(new LinkedList<>());
            for (int j = 0; j < 20; j++) {
                plateau.get(i).add(new Domino());
            }
        }
    }

    @Override
    public boolean ajoutTuile(AbstractTuile t, int y, int x) {
        int res = 0;
        LinkedList<Boolean> bool = new LinkedList<>();
        boolean numeroCorrect = true;
        try {
            int[] cotereel = new int[3];
            cotereel[0] = (int) plateau.get(y).get(x+1).getNumeros()[3][0].getVal();
            cotereel[1] = (int) plateau.get(y).get(x+1).getNumeros()[3][1].getVal();
            cotereel[2] = (int) plateau.get(y).get(x+1).getNumeros()[3][2].getVal();
            int[] coteTest = new int[3];
            coteTest[0] = (int) t.getNumeros()[1][0].getVal();
            coteTest[1] = (int) t.getNumeros()[1][1].getVal();
            coteTest[2] = (int) t.getNumeros()[1][2].getVal();

            bool.add(Arrays.equals(cotereel, coteTest));
            for (int x1 :  coteTest) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            int[] cotereel = new int[3];
            cotereel[0] = (int) plateau.get(y+1).get(x).getNumeros()[0][0].getVal();
            cotereel[1] = (int) plateau.get(y+1).get(x).getNumeros()[0][1].getVal();
            cotereel[2] = (int) plateau.get(y+1).get(x).getNumeros()[0][2].getVal();
            int[] coteTest = new int[3];
            coteTest[0] = (int) t.getNumeros()[2][0].getVal();
            coteTest[1] = (int) t.getNumeros()[2][1].getVal();
            coteTest[2] = (int) t.getNumeros()[2][2].getVal();
            bool.add(Arrays.equals(cotereel, coteTest));
            for (int x1 :  coteTest) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            int[] cotereel = new int[3];
            cotereel[0] = (int) plateau.get(y).get(x-1).getNumeros()[1][0].getVal();
            cotereel[1] = (int) plateau.get(y).get(x-1).getNumeros()[1][1].getVal();
            cotereel[2] = (int) plateau.get(y).get(x-1).getNumeros()[1][2].getVal();
            int[] coteTest = new int[3];
            coteTest[0] = (int) t.getNumeros()[3][0].getVal();
            coteTest[1] = (int) t.getNumeros()[3][1].getVal();
            coteTest[2] = (int) t.getNumeros()[3][2].getVal();
            bool.add(Arrays.equals(cotereel, coteTest));
            for (int x1 :  coteTest) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            int[] cotereel = new int[3];
            cotereel[0] = (int) plateau.get(y-1).get(x).getNumeros()[2][0].getVal();
            cotereel[1] = (int) plateau.get(y-1).get(x).getNumeros()[2][1].getVal();
            cotereel[2] = (int) plateau.get(y-1).get(x).getNumeros()[2][2].getVal();
            int[] coteTest = new int[3];
            coteTest[0] = (int) t.getNumeros()[0][0].getVal();
            coteTest[1] = (int) t.getNumeros()[0][1].getVal();
            coteTest[2] = (int) t.getNumeros()[0][2].getVal();
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
            plateau.get(y).set(x, t);
            int res3 = res+jeu.getJoueurs().get(jeu.getJoueurCourant()).getPoint();
            jeu.getJoueurs().get(jeu.getJoueurCourant()).setPoint(res3);
            return true;
        }
        return false;
    }

    @Override
    public ImageIcon createImage(AbstractTuile t) {
        CarteCarcassonne tmp = (CarteCarcassonne)t;
        ImageIcon image = new ImageIcon();
        switch(tmp.index) {
            case 1:
                image = new ImageIcon("./img/carte1.png");
                break;
            case 2: 
                image = new ImageIcon("./img/carte2.png");
                break;
            case 3: 
                image = new ImageIcon("./img/carte3.png");
                break;
            case 4: 
                image = new ImageIcon("./img/carte4.png");
                break;
            case 5: 
                image = new ImageIcon("./img/carte5.png");
                break;
            case 6: 
                image = new ImageIcon("./img/carte6.png");
                break;
            case 7: 
                image = new ImageIcon("./img/carte7.png");
                break;
            case 8: 
                image = new ImageIcon("./img/carte8.png");
                break;
            case 9: 
                image = new ImageIcon("./img/carte9.png");
                break;
            case 10: 
                image = new ImageIcon("./img/carte10.png");
                break;
            case 11: 
                image = new ImageIcon("./img/carte11.png");
                break;
            case 12: 
                image = new ImageIcon("./img/carte12.png");
                break;
            case 13: 
                image = new ImageIcon("./img/carte13.png");
                break;
            case 14: 
                image = new ImageIcon("./img/carte14.png");
                break;
            case 15: 
                image = new ImageIcon("./img/carte15.png");
                break;
            case 16: 
                image = new ImageIcon("./img/carte16.png");
                break;
            case 17: 
                image = new ImageIcon("./img/carte17.png");
                break;
            case 18: 
                image = new ImageIcon("./img/carte18.png");
                break;
            case 19: 
                image = new ImageIcon("./img/carte19.png");
                break;
            case 20: 
                image = new ImageIcon("./img/carte20.png");
                break;
            case 21: 
                image = new ImageIcon("./img/carte21.png");
                break;
            case 22: 
                image = new ImageIcon("./img/carte22.png");
                break;
            case 23: 
                image = new ImageIcon("./img/carte23.png");
                break;
            case 24: 
                image = new ImageIcon("./img/carte24.png");
                break;
        }
        return image;
    }
}
