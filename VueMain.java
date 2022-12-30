import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.TimerTask;
import java.awt.event.*;
import javax.swing.*;

public class VueMain extends JFrame{

    AbstractJeu jeu;
    VuePartie p;
    JButton dominoCourantButton;
    JButton turnLeft; 
    JButton turnRight;
    JButton skip;
    
    public VueMain(AbstractJeu jeu, VuePartie p) {

        this.jeu=jeu;
        this.p=p;
       
        this.setSize(320,370);
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("Joueur "+jeu.joueurs.get(jeu.joueurCourant).numeroDeJoueur+ " - Score: "+jeu.joueurs.get(jeu.joueurCourant).point);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(0);

        setDominoCourantButton();
        setTurnLeft();
        setTurnRight();
        setSkip();
        initControlBot();

        this.add(dominoCourantButton);
        this.add(turnLeft);
        this.add(turnRight);
        this.add(skip);

        jeu.vmain=this;
    }

    public void setDominoCourantButton() {
        dominoCourantButton = new JButton();
        dominoCourantButton.setBounds(25,10,250,250);
        jeu.piocher();
        ImageIcon truc = jeu.plateau.createImage(jeu.main);
        Image imageScaled = truc.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        dominoCourantButton.setIcon(imageIconScaled);
    }

    public void setTurnLeft() {
        turnLeft = new JButton();  
        turnLeft.setBounds(10,275,50,50);
        turnLeft.setBackground(new Color(59,136,195));

        ImageIcon left = new ImageIcon("./img/arrow_right.png");
        Image imageScaled = left.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        turnLeft.setIcon(imageIconScaled);

        turnLeft.addActionListener(e->{
            Util.tourneUneFois(jeu.main);
            Util.tourneUneFois(jeu.main);
            Util.tourneUneFois(jeu.main);
            ImageIcon truc4 = new ImageIcon();
            if (jeu.plateau instanceof PlateauCarcassonne) {
                truc4 = Util.tourner((ImageIcon) dominoCourantButton.getIcon());
                truc4 = Util.tourner((ImageIcon) truc4);
                truc4 = Util.tourner((ImageIcon) truc4);
                if (jeu.main.nbFoisTourne==1) jeu.main.nbFoisTourne=0;
                else if (jeu.main.nbFoisTourne==2) jeu.main.nbFoisTourne=1;
                else if (jeu.main.nbFoisTourne==3) jeu.main.nbFoisTourne=2;
                else jeu.main.nbFoisTourne=3;
            } else {
                truc4 = jeu.plateau.createImage(jeu.main);
            }
            Image imageScaled4 = truc4.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
            ImageIcon imageIconScaled4 = new ImageIcon(imageScaled4); 
            imageIconScaled4.setImage(imageScaled4);
            dominoCourantButton.setIcon(imageIconScaled4);
        });
    }

    public void setTurnRight() {
        turnRight = new JButton();
        turnRight.setBackground(new Color(59,136,195));
        turnRight.setBounds(245,275,50,50);

        
        ImageIcon right = new ImageIcon("./img/arrow_left.png");
        Image imageScaled = right.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        turnRight.setIcon(imageIconScaled);

        turnRight.addActionListener(e->{
            Util.tourneUneFois(jeu.main);
            ImageIcon truc2 = new ImageIcon();
            if (jeu.plateau instanceof PlateauCarcassonne) {
                truc2 = Util.tourner((ImageIcon) dominoCourantButton.getIcon());
                if (jeu.main.nbFoisTourne==3) jeu.main.nbFoisTourne=0;
                else jeu.main.nbFoisTourne++;
            } else {
                truc2 = jeu.plateau.createImage(jeu.main);
            }
            Image imageScaled2 = truc2.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
            ImageIcon imageIconScaled2 = new ImageIcon(imageScaled2); 
            imageIconScaled2.setImage(imageScaled2);
            dominoCourantButton.setIcon(imageIconScaled2);
        });
    }

    public void setSkip() {
        skip = new JButton();
        skip.setBackground(new Color(59,136,195));
        skip.setBounds(101,275,100,50);

        ImageIcon skipI = new ImageIcon("./img/skip.png");
        Image imageScaled = skipI.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        skip.setIcon(imageIconScaled);

        skip.addActionListener(e-> {
            skip();
        });
    }

    public void skip() {

        jeu.piocher();

        if (jeu.joueurCourant==jeu.nbJoueurs-1) {
            jeu.joueurCourant=0;
        } else {
            jeu.joueurCourant+=1;
        } 

        ImageIcon truc = jeu.plateau.createImage(jeu.main);
        Image imageScaled = truc.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        dominoCourantButton.setIcon(imageIconScaled);
        
        AbstractJoueur joueurCourant = jeu.joueurs.get(jeu.joueurCourant);
        if(jeu instanceof PartieDominos) {
            this.setTitle("Joueur " + joueurCourant.numeroDeJoueur + " - Score: " + joueurCourant.point);
        } else {
            this.setTitle("Joueur " + joueurCourant.numeroDeJoueur);
        }
        p.reste.setText("Domino restant: " + jeu.sac.tuilesDansLeSac.size());
        
        if(joueurCourant instanceof Bot) {
            Bot b = (Bot) joueurCourant;
            skip.setEnabled(false);
            turnLeft.setEnabled(false);
            turnRight.setEnabled(false);
            for (int i = 0; i < p.boutons.length; i++) {
                for (int j = 0; j < p.boutons[i].length; j++) {
                    if (!p.isPlaced[i][j]) {
                        p.boutons[i][j].setEnabled(false);
                    }
                }
            }
            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    skip.setEnabled(true);
                    turnLeft.setEnabled(true);
                    turnRight.setEnabled(true);
                    for (int i = 0; i < p.boutons.length; i++) {
                        for (int j = 0; j < p.boutons[i].length; j++) {
                            if (!p.isPlaced[i][j]) {
                                p.boutons[i][j].setEnabled(true);
                            }
                        }
                    }
                    b.Play(jeu.main);
                    timer.cancel();
                }
            }, 3000, 3000);
        }
    }

    public void placerPion() {
        turnLeft.setVisible(false);
        turnRight.setVisible(false);
        JLabel pion = new JLabel("Placez ou non votre pion");
        dominoCourantButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Récupération des coordonnées du clic
                int x = e.getX();
                int y = e.getY();

            }
        });

    }
    
    public void initControlBot() {
        for (AbstractJoueur j : jeu.joueurs) {
            if (j instanceof Bot) {
                Bot b = (Bot) j;
                b.c = new ControlBot(this, b);
            }
        }
    }
}
