package src.main.java.abstractClass;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel{

    protected JFrame j;

    public AbstractPanel(JFrame j) {
        this.j=j;    
    }

    public JFrame getJ() {
        return j;
    }
}
