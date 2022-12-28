import java.awt.*;
import javax.swing.*;

public class DominoCourant extends JFrame{

    ModelJeu model;
    Plateau2 plateau;
    PartieG p;
    JButton turnLeft; 
    JButton turnRight;
    JButton skip;

    public DominoCourant(ModelJeu model, Plateau2 plateau, PartieG p) {
        this.model=model;
        this.plateau=plateau;
        this.p=p;
        p.dominoCourantButton = new JButton();
        this.setSize(320,370);
        this.setVisible(true);
        this.setLayout(null);
        p.dominoCourantButton.setBounds(25,10,250,250);
        Domino d = model.s.getDominosSac().getFirst();
        model.s.getDominosSac().removeFirst();
        p.dominoCourantChiffre = d.getNumeros();
        ImageIcon truc = plateau.createImage(d.getNumeros());
        Image imageScaled = truc.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        p.dominoCourantButton.setIcon(imageIconScaled);
        this.setTitle("Joueur "+p.joueurCourant+ " - Score: "+model.joueurs.get(p.joueurCourant-1).getPoint());

        turnLeft = new JButton();  
        turnLeft.setBackground(new Color(59,136,195));
        this.add(turnLeft);
        turnLeft.setBounds(10,275,50,50);
        ImageIcon left = new ImageIcon("./img/arrow_right.png");
        imageScaled = left.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        turnLeft.setIcon(imageIconScaled);
        
        turnLeft.addActionListener(e->{
            Domino tmp = new Domino(p.dominoCourantChiffre);
            tmp.tourneUneFois();
            tmp.tourneUneFois();
            tmp.tourneUneFois();
            p.dominoCourantChiffre=tmp.getNumeros();
            ImageIcon truc4 = plateau.createImage(p.dominoCourantChiffre);
            Image imageScaled4 = truc4.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
            ImageIcon imageIconScaled4 = new ImageIcon(imageScaled4); 
            imageIconScaled4.setImage(imageScaled4);
            p.dominoCourantButton.setIcon(imageIconScaled4);
        });

        turnRight = new JButton();
        turnRight.setBackground(new Color(59,136,195));
        this.add(turnRight);
        turnRight.setBounds(245,275,50,50);
        ImageIcon right = new ImageIcon("./img/arrow_left.png");
        imageScaled = right.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        turnRight.setIcon(imageIconScaled);

        turnRight.addActionListener(e->{
            Domino tmp2 = new Domino(p.dominoCourantChiffre);
            tmp2.tourneUneFois();
            p.dominoCourantChiffre = tmp2.getNumeros();
            ImageIcon truc2 = plateau.createImage(p.dominoCourantChiffre);
            Image imageScaled2 = truc2.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
            ImageIcon imageIconScaled2 = new ImageIcon(imageScaled2); 
            imageIconScaled2.setImage(imageScaled2);
            p.dominoCourantButton.setIcon(imageIconScaled2);
        });

        skip = new JButton();
        skip.setBackground(new Color(59,136,195));
        this.add(skip);
        skip.setBounds(101,275,100,50);
        turnRight.setBounds(245,275,50,50);
        ImageIcon skipI = new ImageIcon("./img/skip.png");
        imageScaled = skipI.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        skip.setIcon(imageIconScaled);
        skip.addActionListener(e-> {
            p.skip();
        });

        this.add(p.dominoCourantButton);
        
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(0);
        
    }
}
