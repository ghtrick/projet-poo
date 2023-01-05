package src.main.java.view;
import javax.swing.*;
import java.awt.*;

import src.main.java.model.*;
import src.main.java.abstractClass.*;

public class VueChoixJoueurs extends AbstractPanel{

    int nbJoueurs=2;
    int nbBot=0;
    boolean modeDeJeu;
    JLabel nbJoueursLabel = new JLabel("Nombres de joueurs : "+nbJoueurs);
    JLabel nbBotsLabel = new JLabel("Nombres de bot : "+nbBot);
    JButton plusJoueurs = new JButton(new ImageIcon("./img/plus.png"));
    JButton plusBots = new JButton(new ImageIcon("./img/plus.png"));
    JButton moinsJoueurs = new JButton(new ImageIcon("./img/moins.png"));
    JButton moinsBots = new JButton(new ImageIcon("./img/moins.png"));
    JButton quitter = new JButton("quitter");
    JButton lancerPartie = new JButton("Lancer la partie");

    public VueChoixJoueurs(JFrame j, boolean modeDeJeu) {
        super(j);
        this.modeDeJeu=modeDeJeu;

        this.setLayout(null);
        
        setNbJoueursLabel();
        setNbBotsLabel();
        setPlusJoueurs();
        setMoinsJoueurs();
        setPlusBots();
        setMoinsBots();
        setLancerPartie();
        setQuitter();

        this.add(nbJoueursLabel);
        this.add(nbBotsLabel);
        this.add(plusBots);
        this.add(moinsBots);
        this.add(moinsJoueurs);
        this.add(plusJoueurs);
        this.add(lancerPartie);
        this.add(quitter);

        this.setVisible(true);
    }

    public void setNbJoueursLabel() {
        nbJoueursLabel.setFont(new Font("Arial",0,50));
        nbJoueursLabel.setBounds(j.getWidth()/2-600, j.getHeight()/2-50,600,100);
    }

    public void setNbBotsLabel() {
        nbBotsLabel.setFont(new Font("Arial",0,50));
        nbBotsLabel.setBounds(j.getWidth()/2-600, j.getHeight()/2+100, 600,100);
    }

    public void setPlusJoueurs() {
        plusJoueurs.setBounds(j.getWidth()/2+50, j.getHeight()/2-50,100,100);
        plusJoueurs.addActionListener(a -> {
            if(nbJoueurs<5)nbJoueurs++;
            nbJoueursLabel.setText("Nombres de joueurs : "+nbJoueurs);
        });
    }

    public void setPlusBots() {
        plusBots.setBounds(j.getWidth()/2+50, j.getHeight()/2+100, 100,100);
        plusBots.addActionListener(a-> {
            if(nbBot<nbJoueurs-1) nbBot++;
            nbBotsLabel.setText("Nombres de bot : "+nbBot);
        });
    }

    public void setMoinsJoueurs() {
        moinsJoueurs.setBounds(j.getWidth()/2+155, j.getHeight()/2-50,100,100);
        moinsJoueurs.addActionListener(a-> {
            if(nbJoueurs>2) nbJoueurs--;
            if(nbBot==nbJoueurs+1) nbBot--;
            nbBotsLabel.setText("Nombres de bot : "+nbBot);
            nbJoueursLabel.setText("Nombres de joueurs : "+nbJoueurs);
        });
    }

    public void setMoinsBots() {
        moinsBots.setBounds(j.getWidth()/2+155, j.getHeight()/2+100, 100,100);
        moinsBots.addActionListener(a-> {
            if(nbBot>0) nbBot--;
            nbBotsLabel.setText("Nombres de bot : "+nbBot);
        });
    }

    public void setLancerPartie() {
        lancerPartie.setBounds(j.getWidth()/2-50, j.getHeight()/2+300, 150, 50);
        lancerPartie.addActionListener(a -> {
            VuePartie partie = new VuePartie(j, modeDeJeu, nbJoueurs, nbBot);
            this.setVisible(false);
            j.setContentPane(partie);
        });
    }

    public void setQuitter() {
        quitter.setBounds(j.getWidth()/2-50, j.getHeight()/2+400, 150, 50);
        quitter.addActionListener(a -> {
            j.dispose();
            VueGenerale v = new VueGenerale();
        });
    }
}
    
