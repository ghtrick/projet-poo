package src.main.java.view;
import javax.swing.JButton;
import javax.swing.JFrame;

import src.main.java.model.*;
import src.main.java.abstractClass.*;

public class VueMenu extends AbstractPanel{

    JButton jouerDomino = new JButton("Jouer domino");
    JButton jouerCarcassonne = new JButton("Jouer Carcassonne");
    JButton quitter = new JButton("Quitter");
    
    public VueMenu(JFrame j) {
        super(j);

        setLayout(null);

        jouerDomino.setBounds(j.getWidth()/2-65, j.getHeight()/2-25, 130, 50); 
        jouerCarcassonne.setBounds(j.getWidth()/2-85, j.getHeight()/2-25+60, 170, 50);        
        quitter.setBounds(j.getWidth()/2-50, j.getHeight()/2-25+120, 100, 50);

        setQuitter();
        setJouerDomino();
        setJouerCarcassonne();

        add(jouerDomino);
        add(jouerCarcassonne);
        add(quitter);

        setVisible(true);
        j.setContentPane(this);
    }

    public void setJouerDomino() {
        jouerDomino.addActionListener(e -> {
            VueChoixJoueurs choixJoueurs = new VueChoixJoueurs(j, true);
            this.setVisible(false);
            j.setContentPane(choixJoueurs);
        });
    }

    public void setJouerCarcassonne() {
        jouerCarcassonne.addActionListener(e -> {
            VueChoixJoueurs choixJoueurs = new VueChoixJoueurs(j, false);
            this.setVisible(false);
            j.setContentPane(choixJoueurs);
        });
    }

    public void setQuitter() {
        quitter.addActionListener(e -> {
            System.exit(0);
        });
    }
}
