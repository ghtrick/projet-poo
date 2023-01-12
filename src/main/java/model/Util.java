package src.main.java.model;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;

import src.main.java.model.*;
import src.main.java.abstractClass.*;

public class Util {

    public static String affichePlateau(PlateauDominosTerm p) {
        String res = "";
        for (int i = 0; i < p.getPlateau().size(); i++) {
            res+=afficheDominoListe(p.getPlateau().get(i));
        }
        return res;
    }
    
    public static String afficheDominoListe(LinkedList<AbstractTuile> l) {
        String res = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < l.size(); j++) {
                switch (i) {
                    case 0:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += " "+l.get(j).getNumeros()[0][0].getVal() + "" + l.get(j).getNumeros()[0][1].getVal() + "" + l.get(j).getNumeros()[0][2].getVal()+ "  ";
                        break;
                    case 1:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][0].getVal()+"   "+l.get(j).getNumeros()[1][0].getVal()+ " ";
                        break;
                    case 2:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][1].getVal()+"   "+ l.get(j).getNumeros()[1][1].getVal() + " ";
                        break;
                    case 3:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][2].getVal()+"   "+l.get(j).getNumeros()[1][2].getVal() + " ";
                        break;
                    default:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += " "+l.get(j).getNumeros()[2][0].getVal() + "" + l.get(j).getNumeros()[2][1].getVal() +""+ l.get(j).getNumeros()[2][2].getVal() + "  ";
                        break;
                }
            }
            res+="\n";
        }
        return res;
    }

    public static AbstractElement[] numerosTournes(AbstractElement[] t) {
        return new AbstractElement[]{t[2],t[1],t[0]};
    }

    public static Chiffre[] numerosTournesChiffre(Chiffre[] t) {
        return new Chiffre[]{t[2],t[1],t[0]};
    }

    public static void tourneUneFois(AbstractTuile t) {
        if (t instanceof Domino) {
            Chiffre[][] tmp = new Chiffre[4][3];
            tmp[0] = Arrays.copyOf((Chiffre[]) t.getNumeros()[0], 3);
            tmp[1] = Arrays.copyOf((Chiffre[]) t.getNumeros()[1], 3);
            tmp[2] = Arrays.copyOf((Chiffre[]) t.getNumeros()[2], 3);
            tmp[3] = Arrays.copyOf((Chiffre[]) t.getNumeros()[3], 3);
            t.getNumeros()[0] = numerosTournesChiffre(tmp[3]);
            t.getNumeros()[1] = tmp[0];
            t.getNumeros()[2] = numerosTournesChiffre(tmp[1]);
            t.getNumeros()[3] = tmp[2];
        } else {
            AbstractElement[][] tmp = new AbstractElement[4][3];
            tmp[0] = Arrays.copyOf(t.getNumeros()[0], 3);
            tmp[1] = Arrays.copyOf(t.getNumeros()[1], 3);
            tmp[2] = Arrays.copyOf(t.getNumeros()[2], 3);
            tmp[3] = Arrays.copyOf(t.getNumeros()[3], 3);
            t.getNumeros()[0] = numerosTournes(tmp[3]);
            t.getNumeros()[1] = tmp[0];
            t.getNumeros()[2] = numerosTournes(tmp[1]);
            t.getNumeros()[3] = tmp[2];
        }
    }

    public static ImageIcon tourner(ImageIcon icon) {
        // création de l'objet AffineTransform
        AffineTransform transform = new AffineTransform();

        // définition de la transformation de rotation de 90 degrés
        transform.rotate(Math.toRadians(90), icon.getIconWidth() / 2, icon.getIconHeight() / 2);

        // création de l'objet Graphics2D à partir de l'image de l'ImageIcon
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        // dessin de l'image en appliquant la transformation de rotation
        g.drawImage(icon.getImage(), transform, null);

        // création d'un nouvel objet ImageIcon à partir de l'image pivotée
        ImageIcon rotatedIcon = new ImageIcon(image);
        return rotatedIcon;
    }

    public static ImageIcon placerPion(ImageIcon image, int j, int x, int y) {
        try {
            Image imageB = image.getImage();

            BufferedImage bufferedImage = new BufferedImage(imageB.getWidth(null), imageB.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            // Dessinez l'image sur le BufferedImage
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(imageB, 0, 0, null);
            g2d.dispose();

            BufferedImage overlayImage = ImageIO.read(new File("./img/pion"+j+".png"));

            // Créez un objet Graphics2D à partir de l'image de base
            Graphics2D g2 = bufferedImage.createGraphics();

            // Dessinez l'image superposée sur l'image de base en utilisant l'objet Graphics2D
            g2.drawImage(overlayImage, x, y, null);

            // Libérez les ressources de l'objet Graphics2D
            g2.dispose();

            // Créez un nouvel objet ImageIcon à partir de l'image de base modifiée
            ImageIcon imageIcon = new ImageIcon(bufferedImage);

            return imageIcon;
        } catch(Exception e) {}
        return null;
    }
}
