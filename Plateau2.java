import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Plateau2 {
    JFrame frame;
    JButton[][] boutons;
    int nbLignes;
    int nbColonnes;
 
    public Plateau2(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        frame = new JFrame("Plateau");
        frame.setLayout(new GridLayout(nbLignes, nbColonnes));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(3);
        boutons = new JButton[nbLignes][nbColonnes];
 
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                boutons[i][j] = new JButton();
                boutons[i][j].setVisible(false);
                int I = i;
                int J = j;
                boutons[i][j].addActionListener(e -> {
                    if(I==boutons.length||J==boutons[0].length||I==0||J==0) {
                        return;
                    }
                    boutons[I+1][J].setVisible(true);
                    boutons[I][J+1].setVisible(true);
                    boutons[I-1][J].setVisible(true);
                    boutons[I][J-1].setVisible(true);
                });
                frame.add(boutons[i][j]);
            }
        }

        boutons[boutons.length/2][boutons[0].length/2].setVisible(true);
 
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Plateau2 p = new Plateau2(50, 50);
    }
}