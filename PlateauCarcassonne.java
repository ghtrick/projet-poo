import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.LinkedList;

public class PlateauCarcassonne extends AbstractPlateau{
    public PlateauCarcassonne(int nbLignes, int nbColonnes, JFrame frame, ModelJeu model) {
        super(nbLignes, nbColonnes, frame, model);
    }

    public int[][] tournerUnefois(int[][] numeros) {
        int[][] tmp = new int[4][3];
        tmp[0] = Arrays.copyOf(numeros[0], 3);
        tmp[1] = Arrays.copyOf(numeros[1], 3);
        tmp[2] = Arrays.copyOf(numeros[2], 3);
        tmp[3] = Arrays.copyOf(numeros[3], 3);
        numeros[0] = Domino.numerosTournes(tmp[3]);
        numeros[1] = tmp[0];
        numeros[2] = Domino.numerosTournes(tmp[1]);
        numeros[3] = tmp[2];
        return numeros;
    }
 
    @Override
    public ImageIcon createImage(int[][] chiffres) {
        ImageIcon icon = new ImageIcon("image.jpg");
        // System.out.println(Arrays.toString(chiffres[0]));
        // System.out.println(Arrays.toString(chiffres[1]));
        // System.out.println(Arrays.toString(chiffres[2]));
        // System.out.println(Arrays.toString(chiffres[3]));
        ImageIcon image = new ImageIcon();
        int res = 0;
        switch(res) {
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
        // for(int i=0; i<d.tourne; i++) {
        //     image = tourneImage(image);
        // }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return image;
    }

    public ImageIcon test(ImageIcon img, int i, int tourne){
        if (img==null) {
            switch(i+1) {
                case 1:
                    img = new ImageIcon("./img/carte1.png");
                    break;
                case 2:
                    img = new ImageIcon("./img/carte2.png");
                    break;
                case 3:
                    img = new ImageIcon("./img/carte3.png");
                    break;
                case 4:
                    img = new ImageIcon("./img/carte4.png");
                    break;
                case 5:
                    img = new ImageIcon("./img/carte5.png");
                    break;
                case 6:
                    img = new ImageIcon("./img/carte6.png");
                    break;
                case 7:
                    img = new ImageIcon("./img/carte7.png");
                    break;
                case 8:
                    img = new ImageIcon("./img/carte8.png");
                    break;
                case 9:
                    img = new ImageIcon("./img/carte9.png");
                    break;
                case 10:
                    img = new ImageIcon("./img/carte10.png");
                    break;
                case 11:
                    img = new ImageIcon("./img/carte11.png");
                    break;
                case 12:
                    img = new ImageIcon("./img/carte12.png");
                    break;
                case 13:
                    img = new ImageIcon("./img/carte13.png");
                    break;
                case 14:
                    img = new ImageIcon("./img/carte14.png");
                    break;
                case 15:
                    img = new ImageIcon("./img/carte15.png");
                    break;
                case 16:
                    img = new ImageIcon("./img/carte16.png");
                    break;
                case 17:
                    img = new ImageIcon("./img/carte17.png");
                    break;
                case 18:
                    img = new ImageIcon("./img/carte18.png");
                    break;
                case 19:
                    img = new ImageIcon("./img/carte19.png");
                    break;
                case 20:
                    img = new ImageIcon("./img/carte20.png");
                    break;
                case 21:
                    img = new ImageIcon("./img/carte21.png");
                    break;
                case 22:
                    img = new ImageIcon("./img/carte22.png");
                    break;
                case 23:
                    img = new ImageIcon("./img/carte23.png");
                    break;
                case 24:
                    img = new ImageIcon("./img/carte24.png");
                    break;
            }
        }
        for (int j = 0; j < tourne; j++) {
            img=tourneImage(img);
        }
        return img;
    }

    public ImageIcon tourneImage(ImageIcon icon){
        
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
}