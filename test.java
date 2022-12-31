
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
            j.setIcon(Util.placerPion(tmp, 4, 20, 20));
        });
        panel.add(j, BorderLayout.CENTER);
        panel.setVisible(true);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        test t = new test();
        int[] tab = {1,2,3};
    }
}