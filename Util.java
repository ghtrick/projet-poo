import java.util.Arrays;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.File;

public class Util {
    
    public static String afficheDominoListe(LinkedList<Domino> l) {
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

    public static ImageIcon createImageDomino(AbstractTuile d) {
        try {
           int[][] tmp = {d.numeros[0],d.numeros[3],d.numeros[1],d.numeros[2]};

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
