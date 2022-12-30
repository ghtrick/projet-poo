import java.awt.*;
import java.util.TimerTask;

import javax.swing.*;

public class VueMain extends JFrame{

    AbstractJeu jeu;
    VuePartie p;
    JButton dominoCourantButton;
    JButton turnLeft; 
    JButton turnRight;
    JButton skip;
    
    public VueMain(AbstractJeu jeu, VuePartie p) {
       
        this.setSize(320,370);
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("Joueur "+jeu.joueurCourant+ " - Score: "+jeu.joueurs.get(jeu.joueurCourant-1).point);

        setDominoCourantButton();
        setTurnLeft();
        setTurnRight();
        setSkip();

        this.add(dominoCourantButton);
        this.add(turnLeft);
        this.add(turnRight);
        this.add(skip);

    }

    public void setDominoCourantButton() {
        dominoCourantButton = new JButton();
        dominoCourantButton.setBounds(25,10,250,250);
        jeu.piocher();
        ImageIcon truc = Util.createImageDomino(jeu.main);
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
            ImageIcon truc4 = Util.createImageDomino(jeu.main);
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
            ImageIcon truc2 = Util.createImageDomino(jeu.main);
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

        ImageIcon truc = Util.createImageDomino(jeu.main);
        Image imageScaled = truc.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        dominoCourantButton.setIcon(imageIconScaled);
        
        this.setTitle("Joueur "+jeu.joueurCourant+ " - Score: "+jeu.joueurs.get(jeu.joueurCourant-1).point);
        
        p.reste.setText("Domino restant: "+jeu.sac.tuilesDansLeSac.size());
        
        if(jeu.joueurs.get(jeu.joueurCourant-1)instanceof Bot) {
            Bot b = (Bot) jeu.joueurs.get(jeu.joueurCourant-1);
            skip.setEnabled(false);
            turnLeft.setEnabled(false);
            turnRight.setEnabled(false);
            System.out.println("test");
            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    skip.setEnabled(true);
                    turnLeft.setEnabled(true);
                    turnRight.setEnabled(true);
                    b.Play((Domino) jeu.main);
                    System.out.println("rtest");
                    timer.cancel();
                }
            }, 3000, 3000);
        }
    }
    
    
}
