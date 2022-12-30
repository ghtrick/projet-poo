import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;


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
