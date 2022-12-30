import java.util.Arrays;
import java.util.LinkedList;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Util {
    
    public static String afficheDominoListe(LinkedList<AbstractTuile> l) {
        String res = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < l.size(); j++) {
                switch (i) {
                    case 0:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += " "+l.get(j).getNumeros()[0][0] + "" + l.get(j).getNumeros()[0][1] + "" + l.get(j).getNumeros()[0][2]+ "  ";
                        break;
                    case 1:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][0]+"   "+l.get(j).getNumeros()[1][0]+ " ";
                        break;
                    case 2:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][1]+"   "+ l.get(j).getNumeros()[1][1] + " ";
                        break;
                    case 3:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][2]+"   "+l.get(j).getNumeros()[1][2] + " ";
                        break;
                    default:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += " "+l.get(j).getNumeros()[2][0] + "" + l.get(j).getNumeros()[2][1] +""+ l.get(j).getNumeros()[2][2] + "  ";
                        break;
                }
            }
            res+="\n";
        }
        return res;
    }

    public static int[] numerosTournes(int[] t) {
        return new int[]{t[2],t[1],t[0]};
    }

    public static void tourneUneFois(AbstractTuile t) {
        int[][] tmp = new int[4][3];
        tmp[0] = Arrays.copyOf(t.numeros[0], 3);
        tmp[1] = Arrays.copyOf(t.numeros[1], 3);
        tmp[2] = Arrays.copyOf(t.numeros[2], 3);
        tmp[3] = Arrays.copyOf(t.numeros[3], 3);
        t.numeros[0] = numerosTournes(tmp[3]);
        t.numeros[1] = tmp[0];
        t.numeros[2] = numerosTournes(tmp[1]);
        t.numeros[3] = tmp[2];
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
}
