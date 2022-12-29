import javax.swing.*; 
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.*;

public class PartieG extends AbstractJeu{

    ModelJeu model;
    MenuG menu;
    Plateau2 plateau;
    PlateauCarcassonne plateauCarcassonne;
    public JFrame frame;
    public DominoCourant dominoCourant;
    int[][] dominoCourantChiffre;
    int joueurCourant = 1;
    JButton dominoCourantButton;

    public PartieG() {
        frame = new JFrame();
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

    public void skip() {
        if(model.s.getDominosSac().isEmpty()) {
            partieFini(model.max()+1);  
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
            dominoCourant.skip.setEnabled(false);
            dominoCourant.turnLeft.setEnabled(false);
            dominoCourant.turnRight.setEnabled(false);
            System.out.println("test");
            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    dominoCourant.skip.setEnabled(true);
                    dominoCourant.turnLeft.setEnabled(true);
                    dominoCourant.turnRight.setEnabled(true);
                    ((Bot)model.joueurs.get(joueurCourant-1)).Play(d1);
                    System.out.println("rtest");
                    timer.cancel();
                }
            }, 3000, 3000);
            
        }
    }

    @Override
    public void partieFini(int Gagnant) {
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
            model = new ModelJeu(true);
            model.partie = this;
            menu.setVisible(false);
            plateau = new Plateau2(20, 20, frame, model);
            boolean[] bots = new boolean[menu.nbJoueurs];
            for(int i=0; i<menu.nbBot; i++) {
                bots[menu.nbJoueurs-1-i] = true;
            }
            System.out.println(Arrays.toString(bots));
            model.initJoueurs(menu.nbJoueurs, bots, false);
            plateau.p = this;
            dominoCourant = new DominoCourant(model, plateau, this);
            plateau.initialiserQuitter();
            frame.setContentPane(plateau);
        });

        menu.jouerCarcassonne.addActionListener(e -> {
            model = new ModelJeu(false);
            model.partie = this;
            menu.setVisible(false);
            plateauCarcassonne = new PlateauCarcassonne(20, 20, frame, model);
            boolean[] bots = new boolean[menu.nbJoueurs];
            for(int i=0; i<menu.nbBot; i++) {
                bots[menu.nbJoueurs-1-i] = true;
            }
            System.out.println(Arrays.toString(bots));
            model.initJoueurs(menu.nbJoueurs, bots, false);
            plateau.p = this;
            dominoCourant = new DominoCourant(model, plateau, this);
            plateau.initialiserQuitter();
            frame.setContentPane(plateau);
        });
        return 0;
    }

    public void setOption() {

    }

    public static void main(String[] args) {
        PartieG p = new PartieG();
    }
}
