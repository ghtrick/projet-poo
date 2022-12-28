import javax.swing.*; 
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.*;

public class PartieG extends AbstractJeu{

    ModelJeu model;
    MenuG menu;
    Plateau2 plateau;
    public JFrame frame;
    public JFrame dominoCourant;
    int[][] dominoCourantChiffre;
    int joueurCourant = 1;
    JButton dominoCourantButton;

    public PartieG() {
        dominoCourant = new JFrame();
        model = new ModelJeu();
        frame = new JFrame();
        dominoCourant = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(3);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                menu = new MenuG(frame);
                menu.setVisible(true);
                frame.setContentPane(menu);
                partie2Joueurs();
            }
        });
        frame.setVisible(true);
    }

    public void setJFrame() {
        dominoCourantButton = new JButton();
        dominoCourant.setSize(320,370);
        dominoCourant.setVisible(true);
        dominoCourant.setLayout(null);
        dominoCourantButton.setBounds(25,10,250,250);
        Domino d = model.s.getDominosSac().getFirst();
        model.s.getDominosSac().removeFirst();
        dominoCourantChiffre = d.getNumeros();
        ImageIcon truc = plateau.createImage(d.getNumeros());
        Image imageScaled = truc.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        dominoCourantButton.setIcon(imageIconScaled);
        dominoCourant.setTitle("Joueur "+joueurCourant+ " - Score: "+model.joueurs.get(joueurCourant-1).getPoint());

        JButton turnLeft = new JButton();  
        turnLeft.setBackground(new Color(59,136,195));
        dominoCourant.add(turnLeft);
        turnLeft.setBounds(10,275,50,50);
        ImageIcon left = new ImageIcon("./img/arrow_right.png");
        imageScaled = left.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        turnLeft.setIcon(imageIconScaled);
        
        turnLeft.addActionListener(e->{
            Domino tmp = new Domino(dominoCourantChiffre);
            tmp.tourneUneFois();
            tmp.tourneUneFois();
            tmp.tourneUneFois();
            dominoCourantChiffre=tmp.getNumeros();
            ImageIcon truc4 = plateau.createImage(dominoCourantChiffre);
            Image imageScaled4 = truc4.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
            ImageIcon imageIconScaled4 = new ImageIcon(imageScaled4); 
            imageIconScaled4.setImage(imageScaled4);
            dominoCourantButton.setIcon(imageIconScaled4);
        });

        JButton turnRight = new JButton();
        turnRight.setBackground(new Color(59,136,195));
        dominoCourant.add(turnRight);
        turnRight.setBounds(245,275,50,50);
        ImageIcon right = new ImageIcon("./img/arrow_left.png");
        imageScaled = right.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        turnRight.setIcon(imageIconScaled);

        turnRight.addActionListener(e->{
            Domino tmp2 = new Domino(dominoCourantChiffre);
            tmp2.tourneUneFois();
            dominoCourantChiffre = tmp2.getNumeros();
            ImageIcon truc2 = plateau.createImage(dominoCourantChiffre);
            Image imageScaled2 = truc2.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
            ImageIcon imageIconScaled2 = new ImageIcon(imageScaled2); 
            imageIconScaled2.setImage(imageScaled2);
            dominoCourantButton.setIcon(imageIconScaled2);
        });

        JButton skip = new JButton();
        skip.setBackground(new Color(59,136,195));
        dominoCourant.add(skip);
        skip.setBounds(101,275,100,50);
        turnRight.setBounds(245,275,50,50);
        ImageIcon skipI = new ImageIcon("./img/skip.png");
        imageScaled = skipI.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH); 
        imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        skip.setIcon(imageIconScaled);
        skip.addActionListener(e-> {
            skip();
        });

        dominoCourant.add(dominoCourantButton);
        
        dominoCourant.setResizable(false);
        dominoCourant.setAlwaysOnTop(true);
        dominoCourant.setDefaultCloseOperation(0);
        
    }

    public int max() {
        int scoremax = 0;
        int indexmax = 0;
        for (int i = 0; i < model.joueurs.size(); i++) {
            Joueur j = model.joueurs.get(i); 
            if (j.getPoint()>scoremax) {
                scoremax=j.getPoint();
                indexmax = i;
            } else if(j.getPoint()==scoremax) {
                indexmax = -1;
            }
        }
        return indexmax;
    }

    public void skip() {
        if(model.s.getDominosSac().isEmpty()) {
            partieFini(max()+1);  
            return; 
        }
        Domino d1 = model.s.getDominosSac().getFirst();
        dominoCourantChiffre = d1.getNumeros();
        model.s.getDominosSac().removeFirst();
        ImageIcon truc1 = plateau.createImage(d1.getNumeros());
        Image imageScaled1 = truc1.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled1 = new ImageIcon(imageScaled1); 
        imageIconScaled1.setImage(imageScaled1);
        dominoCourantButton.setIcon(imageIconScaled1);
        if(joueurCourant<model.joueurs.size())joueurCourant++;
        else joueurCourant=1;
        dominoCourant.setTitle("Joueur "+joueurCourant+ " - Score: "+model.joueurs.get(joueurCourant-1).getPoint());
        plateau.reste.setText("Domino restant: "+model.s.getDominosSac().size());
        if(model.joueurs.get(joueurCourant-1) instanceof Bot) {
            
            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    ((Bot)model.joueurs.get(joueurCourant-1)).Play(d1);
                }
            }, 3000, 3000);
        }
    } 

    @Override
    public void partieFini(int Gagnant) {
        System.out.println("Gagnant: "+Gagnant);
        JButton menu = new JButton("Menu");
        dominoCourant.setVisible(false);
        plateau.setVisible(false);
        JPanel fin = new JPanel();
        fin.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
        fin.setLayout(null);
        JLabel finT = new JLabel();
        finT.setBounds(frame.getWidth()/2-200, frame.getHeight()/2-250, 500, 500);
        if(Gagnant!=0) {
            finT.setText("Joueur" + Gagnant + " à gagné");
            finT.setFont(new Font("Arial", 0 , 50));
            fin.add(finT,BorderLayout.CENTER);
        } else {
            finT.setText("Égalité");
            finT.setFont(new Font("Arial", 0 , 50));
            finT.setBounds(frame.getWidth()/2-100, frame.getHeight()/2-250, 500, 500);
            fin.add(finT,BorderLayout.CENTER);
        }
        menu.setBounds(frame.getWidth()/2-100, frame.getHeight()/2+150, 100, 50);
        menu.addActionListener(e-> {
            frame.dispose();
            PartieG p = new PartieG();
        });
        fin.add(menu, BorderLayout.SOUTH);
        frame.setContentPane(fin);
    }

    @Override
    public boolean ajoutDomino(Domino d, int y, int x) {
        int res = 0;
        LinkedList<Boolean> bool = new LinkedList<>();
        System.out.println(Arrays.toString(d.getNumeros()[0]));
        System.out.println(Arrays.toString(d.getNumeros()[1]));
        System.out.println(Arrays.toString(d.getNumeros()[2]));
        System.out.println(Arrays.toString(d.getNumeros()[3]));
        boolean numeroCorrect = true;
        try {
            bool.add(Arrays.equals(plateau.emplacementsDominos.get(y).get(x+1).getNumeros()[3], d.getNumeros()[1]));
            for (int x1 :  d.getNumeros()[1]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.emplacementsDominos.get(y+1).get(x).getNumeros()[0], d.getNumeros()[2]));
            for (int x1 :  d.getNumeros()[2]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.emplacementsDominos.get(y).get(x-1).getNumeros()[1], d.getNumeros()[3]));
            for (int x1 :  d.getNumeros()[3]) {
                res+=x1;
            }
        } catch (Exception e) {}
        try {
           bool.add(Arrays.equals(plateau.emplacementsDominos.get(y-1).get(x).getNumeros()[2], d.getNumeros()[0]));
            for (int x1 :  d.getNumeros()[0]) {
                res+=x1;
            } 
        } catch (Exception e) {}
        for(int i=0; i<bool.size(); i++) {
            if(!bool.get(i)) {
                numeroCorrect = false;
            }
        }
        if (bool.isEmpty()) {
            System.out.println("corentin t'es null");
            numeroCorrect = false;
        }
        if(numeroCorrect) {
            plateau.emplacementsDominos.get(y).set(x, d);
            int res3 = res+model.joueurs.get(joueurCourant-1).getPoint();
            model.joueurs.get(joueurCourant-1).setPoint(res3);
            return true;
        }
        return false;
    }

    @Override
    public int partie2Joueurs() {
        menu.jouerDomino.addActionListener(e -> {
            menu.setVisible(false);
            ((Bot)model.joueurs.get(1)).model = model;
            ((Bot)model.joueurs.get(1)).partie = this;
            plateau = (model.taillePlateau==0) ? new Plateau2(20, 20, frame, model) : new Plateau2(model.taillePlateau, model.taillePlateau, frame, model);
            plateau.p = this;
            plateau.initialiserQuitter();
            frame.setContentPane(plateau);
            setJFrame();
        });
        return 0;
    }

    public static void main(String[] args) {
        PartieG p = new PartieG();
    }
}
