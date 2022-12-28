import javax.swing.*;
import java.awt.*;

public class MenuG extends JPanel{
    JButton jouerDomino;
    JButton jouerCarcassonne;
    JButton quitter; 
    JButton option;
    JLabel text;
    PartieG p;

    public MenuG(JFrame frame) {
        setLayout(null);
        jouerDomino = new JButton("Jouer domino");
        jouerDomino.setBounds(frame.getWidth()/2-65, frame.getHeight()/2-25, 130, 50);
        jouerCarcassonne = new JButton("Jouer Carcassonne");
        jouerCarcassonne.setBounds(frame.getWidth()/2-85, frame.getHeight()/2-25+60, 170, 50);
        option = new JButton("Option");
        option.setBounds(frame.getWidth()/2-50, frame.getHeight()/2-25+120, 100, 50);
        quitter = new JButton("Quitter");
        quitter.setBounds(frame.getWidth()/2-50, frame.getHeight()/2-25+180, 100, 50);
        add(jouerDomino);
        add(jouerCarcassonne);
        add(quitter);
        add(option);

        quitter.addActionListener(e -> {
            System.exit(0);
        });
    }
}
