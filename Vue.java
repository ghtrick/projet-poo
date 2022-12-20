import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

public class Vue extends JFrame{
    public Vue() {
        Plateau p = new Plateau(10, 10);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800,800);
        setVisible(true);
        setDefaultCloseOperation(1);
        setContentPane(p);
        pack();
    }

    public static void main(String[] args) {
        Vue v = new Vue();
    }
}
