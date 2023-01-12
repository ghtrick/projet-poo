package src.main.java.view;
import src.main.java.controller.ControlBot;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.TimerTask;
import java.awt.event.*;
import javax.swing.*;

import src.main.java.model.*;
import src.main.java.abstractClass.*;
public class VueMain extends JFrame{

    JFrame jf;
    AbstractJeu jeu;
    VuePartie p;
    JButton dominoCourantButton;
    JButton dominoCourantButton2;
    JButton turnLeft; 
    JButton turnRight;
    JButton skip;
    JButton skip2;
    int i;
    int j;
    
    public VueMain(AbstractJeu jeu, VuePartie p, JFrame jf) {

        this.jf=jf;
        this.jeu=jeu;
        this.p=p;
       
        this.setSize(320,370);
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("Joueur "+jeu.getJoueurs().get(jeu.getJoueurCourant()).getNumeroDeJoueur()+ " - Score: "+jeu.getJoueurs().get(jeu.getJoueurCourant()).getPoint());
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
        this.add(skip2);
        this.add(dominoCourantButton2);

        jeu.setVmain(this);
    }

    public void setDominoCourantButton() {
        dominoCourantButton = new JButton();
        dominoCourantButton.setBounds(25,10,250,250);
        jeu.piocher();
        ImageIcon truc = jeu.getPlateau().createImage(jeu.getMain());
        Image imageScaled = truc.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        dominoCourantButton.setIcon(imageIconScaled);

        dominoCourantButton2 = new JButton();
        dominoCourantButton2.setBounds(25,10,250,250);
        dominoCourantButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            // Récupération des coordonnées du clic
            int x = e.getX();
            int y = e.getY();
            ImageIcon newImg = Util.placerPion((ImageIcon) dominoCourantButton2.getIcon(), jeu.getJoueurs().get(jeu.getJoueurCourant()).getNumeroDeJoueur(), x, y);
            dominoCourantButton2.setIcon(newImg);
            turnLeft.setVisible(true);
            turnRight.setVisible(true);
            p.clickBouton(i, j);
            dominoCourantButton2.setVisible(false);
            dominoCourantButton.setVisible(true);
            }
        });
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
            Util.tourneUneFois(jeu.getMain());
            Util.tourneUneFois(jeu.getMain());
            Util.tourneUneFois(jeu.getMain());
            LinkedList<AbstractTuile> l = new LinkedList<>();
            l.add(jeu.getMain());
            System.out.println(Util.afficheDominoListe(l));
            ImageIcon truc4 = new ImageIcon();
            if (jeu.getPlateau() instanceof PlateauCarcassonne) {
                truc4 = Util.tourner((ImageIcon) dominoCourantButton.getIcon());
                truc4 = Util.tourner((ImageIcon) truc4);
                truc4 = Util.tourner((ImageIcon) truc4);
                if (jeu.getMain().getNbFoisTourne()==1) jeu.getMain().setNbFoisTourne(0);
                else if (jeu.getMain().getNbFoisTourne()==2) jeu.getMain().setNbFoisTourne(1);
                else if (jeu.getMain().getNbFoisTourne()==3) jeu.getMain().setNbFoisTourne(2);
                else jeu.getMain().setNbFoisTourne(3);
            } else {
                truc4 = jeu.getPlateau().createImage(jeu.getMain());
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
            Util.tourneUneFois(jeu.getMain());
            ImageIcon truc2 = new ImageIcon();
            if (jeu.getPlateau() instanceof PlateauCarcassonne) {
                truc2 = Util.tourner((ImageIcon) dominoCourantButton.getIcon());
                if (jeu.getMain().getNbFoisTourne()==3) jeu.getMain().setNbFoisTourne(0);
                else jeu.getMain().setNbFoisTourne(jeu.getMain().getNbFoisTourne()+1);
            } else {
                truc2 = jeu.getPlateau().createImage(jeu.getMain());
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

        skip2 = new JButton();
        skip2.setBackground(new Color(59,136,195));
        skip2.setBounds(101,275,100,50);

        ImageIcon skipII = new ImageIcon("./img/skip.png");
        Image imageScaled2 = skipII.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled2 = new ImageIcon(imageScaled2); 
        imageIconScaled2.setImage(imageScaled2);
        skip2.setIcon(imageIconScaled2);

        skip2.addActionListener(e -> {
            turnLeft.setVisible(true);
            turnRight.setVisible(true);
            p.clickBouton(i, j);
            skip2.setVisible(false);
            skip.setVisible(true);
            dominoCourantButton2.setVisible(false);
            dominoCourantButton.setVisible(true);
        });

    }

    public void skip() {
        if (p.p.getJoueurs().size()==1 || p.p.getSac().getTuilesDansLeSac().isEmpty()) {
            setVisible(false);
            p.setVisible(false);
            VueFin fin = new VueFin(jf, this);
            fin.partieFini(p.p.max());
            return;
        }
        jeu.piocher();

        if (jeu.getJoueurCourant()==jeu.getNbJoueurs()-1) {
            jeu.setJoueurCourant(0);
        } else {
            jeu.setJoueurCourant(jeu.getJoueurCourant()+1);
        } 

        ImageIcon truc = jeu.getPlateau().createImage(jeu.getMain());
        Image imageScaled = truc.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        dominoCourantButton.setIcon(imageIconScaled);
        
        AbstractJoueur joueurCourant = jeu.getJoueurs().get(jeu.getJoueurCourant());
        if(jeu instanceof PartieDominos) {
            this.setTitle("Joueur " + joueurCourant.getNumeroDeJoueur() + " - Score: " + joueurCourant.getPoint());
        } else {
            this.setTitle("Joueur " + joueurCourant.getNumeroDeJoueur());
        }
        p.reste.setText("Domino restant: " + jeu.getSac().getTuilesDansLeSac().size());
        
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
                    b.Play(jeu.getMain());
                    timer.cancel();
                }
            }, 3000, 3000);
        }
    }

    public void placerPion(int i, int j) {
        // setAutoRequestFocus(true);
        this.i = i;
        this.j = j;
        ImageIcon truc2 = (ImageIcon)dominoCourantButton.getIcon();
        Image imageScaled2 = truc2.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled2 = new ImageIcon(imageScaled2); 
        imageIconScaled2.setImage(imageScaled2);
        dominoCourantButton2.setIcon(imageIconScaled2);
        skip.setVisible(false);
        skip2.setVisible(true);
        turnLeft.setVisible(false);
        turnRight.setVisible(false);
        dominoCourantButton.setVisible(false);
        dominoCourantButton2.setVisible(true);
        JLabel pion = new JLabel("Placez ou non votre pion");
    }
    
    public void initControlBot() {
        for (AbstractJoueur j : jeu.getJoueurs()) {
            if (j instanceof Bot) {
                Bot b = (Bot) j;
                b.setC(new ControlBot(this, b));
            }
        }
    }

    public AbstractJeu getJeu() {
        return jeu;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public JButton getDominoCourantButton() {
        return dominoCourantButton;
    }

    public JButton getDominoCourantButton2() {
        return dominoCourantButton2;
    }

    public JButton getSkip() {
        return skip;
    }

    public JButton getSkip2() {
        return skip2;
    }

    public JButton getTurnLeft() {
        return turnLeft;
    }

    public JButton getTurnRight() {
        return turnRight;
    }

    public VuePartie getP() {
        return p;
    }


}
