package src.main.java.view;
import javax.swing.*;
import java.awt.*;

public class VueGenerale extends JFrame{

    public VueGenerale() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        VueMenu menu = new VueMenu(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        VueGenerale v = new VueGenerale();
    }

}

