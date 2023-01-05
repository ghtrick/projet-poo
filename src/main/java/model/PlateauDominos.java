package src.main.java.model;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.File;
import src.main.java.abstractClass.*;


public class PlateauDominos extends AbstractPlateau{

    public PlateauDominos(PartieDominos p) {
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
            System.out.println(Arrays.toString(cotereel));
            System.out.println(Arrays.toString(coteTest));

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
            System.out.println(Arrays.toString(cotereel));
            System.out.println(Arrays.toString(coteTest));
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
            System.out.println(Arrays.toString(cotereel));
            System.out.println(Arrays.toString(coteTest));
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
            plateau.get(y).set(x, t);
            int res3 = res+jeu.getJoueurs().get(jeu.getJoueurCourant()).getPoint();
            jeu.getJoueurs().get(jeu.getJoueurCourant()).setPoint(res3);
            return true;
        }
        return false;
    }

    @Override
    public ImageIcon createImage(AbstractTuile t) {
        try {
            AbstractElement[][] tmp = {t.getNumeros()[0],t.getNumeros()[3],t.getNumeros()[1],t.getNumeros()[2]};
 
            BufferedImage image1 = ImageIO.read(new File("img/"+tmp[0][0].getVal()+"b.png"));
            BufferedImage image2 = ImageIO.read(new File("img/"+tmp[0][1].getVal()+"b.png"));
            BufferedImage image3 = ImageIO.read(new File("img/"+tmp[0][2].getVal()+"b.png"));
            BufferedImage image4 = ImageIO.read(new File("img/"+tmp[1][0].getVal()+"b.png"));
            BufferedImage image5 = ImageIO.read(new File("img/"+tmp[1][1].getVal()+"b.png"));
            BufferedImage image6 = ImageIO.read(new File("img/"+tmp[1][2].getVal()+"b.png"));
            BufferedImage image7 = ImageIO.read(new File("img/"+tmp[2][0].getVal()+"b.png"));
            BufferedImage image8 = ImageIO.read(new File("img/"+tmp[2][1].getVal()+"b.png"));
            BufferedImage image9 = ImageIO.read(new File("img/"+tmp[2][2].getVal()+"b.png"));
            BufferedImage image10 = ImageIO.read(new File("img/"+tmp[3][0].getVal()+"b.png"));
            BufferedImage image11 = ImageIO.read(new File("img/"+tmp[3][1].getVal()+"b.png"));
            BufferedImage image12 = ImageIO.read(new File("img/"+tmp[3][2].getVal()+"b.png"));
          
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

