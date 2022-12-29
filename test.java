import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class test{
    JFrame frame=new JFrame();
    JPanel panel=new JPanel();

    public test() {
        frame.setSize(300,300);
        panel.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("./img/carte17.png");
        JButton j = new JButton(icon);
        j.addActionListener(e->{
            System.out.println("test");
            ImageIcon tmp = (ImageIcon)j.getIcon();
            j.setIcon(tourneImage(tmp));
        });
        panel.add(j, BorderLayout.CENTER);
        panel.setVisible(true);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
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

    public static void main(String[] args) {
        test t = new test();
    }
}