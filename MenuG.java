import javax.swing.*;
import java.awt.*;

public class MenuG extends JPanel{
    JButton jouerDomino;
    JButton jouerCarcassonne;
    JButton quitter; 
    JButton option;
    JLabel text;
    PartieG p;
    JFrame frame;
    int nbJoueurs=2;
    int nbBot=0;

    public MenuG(JFrame frame) {
        this.frame=frame;
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

        option.addActionListener(e->{
            JPanel menuOption = new JPanel();
            menuOption.setLayout(null);
            this.setVisible(false);
            JLabel nbJoueursLabel = new JLabel("Nombres de joueurs : "+nbJoueurs);
            nbJoueursLabel.setFont(new Font("Arial",0,50));
            nbJoueursLabel.setBounds(frame.getWidth()/2-600, frame.getHeight()/2-50,600,100);
            JLabel nbBotsLabel = new JLabel("Nombres de bot : "+nbBot);
            nbBotsLabel.setBounds(frame.getWidth()/2-600, frame.getHeight()/2+100, 600,100);
            nbBotsLabel.setFont(new Font("Arial",0,50));
            JButton plusJoueurs = new JButton(new ImageIcon("./img/plus.png"));
            plusJoueurs.setBounds(frame.getWidth()/2+50, frame.getHeight()/2-50,100,100);
            plusJoueurs.addActionListener(a -> {
                if(nbJoueurs<4)nbJoueurs++;
                nbJoueursLabel.setText("Nombres de joueurs : "+nbJoueurs);
            });
            JButton plusBots = new JButton(new ImageIcon("./img/plus.png"));
            plusBots.setBounds(frame.getWidth()/2+50, frame.getHeight()/2+100, 100,100);
            plusBots.addActionListener(a-> {
                if(nbBot<nbJoueurs) nbBot++;
                nbBotsLabel.setText("Nombres de bot : "+nbBot);
            });
            JButton moinsJoueurs = new JButton(new ImageIcon("./img/moins.png"));
            moinsJoueurs.setBounds(frame.getWidth()/2+155, frame.getHeight()/2-50,100,100);
            moinsJoueurs.addActionListener(a-> {
                if(nbJoueurs>2) nbJoueurs--;
                if(nbBot==nbJoueurs+1) nbBot--;
                nbBotsLabel.setText("Nombres de bot : "+nbBot);
                nbJoueursLabel.setText("Nombres de joueurs : "+nbJoueurs);
            });
            JButton moinsBots = new JButton(new ImageIcon("./img/moins.png"));
            moinsBots.addActionListener(a-> {
                if(nbBot>0) nbBot--;
                nbBotsLabel.setText("Nombres de bot : "+nbBot);
            });
            moinsBots.setBounds(frame.getWidth()/2+155, frame.getHeight()/2+100, 100,100);
            frame.setContentPane(menuOption);
            JButton quitter2 = new JButton("quitter");
            quitter2.setBounds(frame.getWidth()/2-50, frame.getHeight()/2+300, 150, 50);
            quitter2.addActionListener(a -> {
                this.setVisible(true);
                frame.setContentPane(this);
            });
            menuOption.add(nbJoueursLabel);
            menuOption.add(nbBotsLabel);
            menuOption.add(plusBots);
            menuOption.add(moinsBots);
            menuOption.add(moinsJoueurs);
            menuOption.add(plusJoueurs);
            menuOption.add(quitter2);
            frame.setContentPane(menuOption);
        });
    }
}
