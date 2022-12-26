package src.main.java.Carcassonne_Domino.view;
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
        jouerDomino.setBounds(frame.getWidth()/2-35, frame.getHeight()/2-15, 70, 30);
        jouerCarcassonne = new JButton("Jouer Carcassonne");
        jouerCarcassonne.setBounds(frame.getWidth()/2-35, frame.getHeight()/2-15+35, 70, 30);
        option = new JButton("Option");
        option.setBounds(frame.getWidth()/2-35, frame.getHeight()/2-15+70, 70, 30);
        quitter = new JButton("Quitter");
        quitter.setBounds(frame.getWidth()/2-35, frame.getHeight()/2-15+105, 70, 30);
        add(jouerDomino);
        add(jouerCarcassonne);
        add(quitter);
        add(option);

        quitter.addActionListener(e -> {
            System.exit(0);
        });
    }
}
