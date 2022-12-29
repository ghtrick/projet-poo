import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.awt.event.MouseEvent;

public class Plateau2 extends AbstractPlateau {

    public Plateau2(int nbLignes, int nbColonnes, JFrame frame, ModelJeu model){
        super(nbLignes, nbColonnes, frame, model);
}

    @Override
    public ImageIcon createImage(int[][] chiffres) {
         try {
            int[][] tmp = {chiffres[0],chiffres[3],chiffres[1],chiffres[2]};

            BufferedImage image1 = ImageIO.read(new File("img/"+tmp[0][0]+"b.png"));
            BufferedImage image2 = ImageIO.read(new File("img/"+tmp[0][1]+"b.png"));
            BufferedImage image3 = ImageIO.read(new File("img/"+tmp[0][2]+"b.png"));
            BufferedImage image4 = ImageIO.read(new File("img/"+tmp[1][0]+"b.png"));
            BufferedImage image5 = ImageIO.read(new File("img/"+tmp[1][1]+"b.png"));
            BufferedImage image6 = ImageIO.read(new File("img/"+tmp[1][2]+"b.png"));
            BufferedImage image7 = ImageIO.read(new File("img/"+tmp[2][0]+"b.png"));
            BufferedImage image8 = ImageIO.read(new File("img/"+tmp[2][1]+"b.png"));
            BufferedImage image9 = ImageIO.read(new File("img/"+tmp[2][2]+"b.png"));
            BufferedImage image10 = ImageIO.read(new File("img/"+tmp[3][0]+"b.png"));
            BufferedImage image11 = ImageIO.read(new File("img/"+tmp[3][1]+"b.png"));
            BufferedImage image12 = ImageIO.read(new File("img/"+tmp[3][2]+"b.png"));
          
            // Créer une nouvelle image avec la largeur et la hauteur des deux images
            int width = image1.getWidth()*5;
            int height = image1.getHeight()*5;
            BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          
            // Dessiner les deux images côte à côte
            Graphics g = combined.getGraphics();
            g.drawImage(image1, width/5, 0, null);
            g.drawImage(image2, width/5*2, 0, null);
            g.drawImage(image3, width/5*3, 0, null);
            g.drawImage(image4, 0, height/5, null);
            g.drawImage(image5, 0, height/5*2, null);
            g.drawImage(image6, 0, height/5*3, null);
            g.drawImage(image7, width/5*4, height/5, null);
            g.drawImage(image8, width/5*4, height/5*2, null);
            g.drawImage(image9, width/5*4, height/5*3, null);
            g.drawImage(image10, width/5, height/5*4, null);
            g.drawImage(image11, width/5*2, height/5*4, null);
            g.drawImage(image12, width/5*3, height/5*4, null);
            ImageIcon image = new ImageIcon(combined);
            return image;
            
          } catch (Exception e) {System.out.println(e);
        }
        return null;
    }
}
