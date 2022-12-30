import javax.swing.*;
import java.awt.*;

public class VueGenerale extends JFrame{

    public VueGenerale() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        VueMenu menu = new VueMenu(this);
    }

    public static void main(String[] args) {
        VueGenerale v = new VueGenerale();
    }

}

