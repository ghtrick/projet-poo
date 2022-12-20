import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class Plateau2 extends JPanel {

    JScrollPane j;
    JPanel plateau;
    JPanel haut;
    JPanel bas;
    //0 -> vide ; 1 -> domino pose ; 2 -> domino placable
    LinkedList<LinkedList<Object[]>> emplacementsDominos = new LinkedList<>();
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints c1 = new GridBagConstraints();

    JButton[][] boutons;
    int nbLignes;
    int nbColonnes;
    boolean[][] isClickable;
    JScrollPane jscroll = new JScrollPane();
 
    private int zoomFactor = 1;

    public Plateau2(int nbLignes, int nbColonnes) {
        remplirLinkedList();
        setLayout(new GridBagLayout());
        haut=new JPanel();
        bas = new JPanel();
        plateau = new JPanel();
        jscroll = new JScrollPane(plateau);
        jscroll.setPreferredSize(new Dimension(600, 600));
        jscroll.setWheelScrollingEnabled(true);
        plateau.setLayout(new GridLayout(nbLignes, nbColonnes));
     //   plateau.setPreferredSize(new Dimension(600,600));
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        boutons = new JButton[nbLignes][nbColonnes];
        isClickable = new boolean[nbLignes][nbColonnes];
        for(int i=0; i<isClickable.length; i++) {
            for(int j=0; j<isClickable[0].length; j++) {
                isClickable[i][j] = true;
            }
        }
 
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {               
                boutons[i][j] = new JButton();
                boutons[i][j].setLayout(new GridLayout(5,5));
                boutons[i][j].setIcon(new ImageIcon("./img/0.png"));
                boutons[i][j].setVisible(false);
                int I = i;
                int J = j;
                boutons[i][j].addActionListener(e -> {
                    if(isClickable[I][J]) {
                        if(I==boutons.length-1||J==boutons[0].length-1||I==0||J==0) {
                            return;
                        }
                        boutons[I+1][J].setVisible(true);
                        boutons[I][J+1].setVisible(true);
                        boutons[I-1][J].setVisible(true);
                        boutons[I][J-1].setVisible(true);
                        isClickable[I][J] = false;
                    }

                });

                plateau.add(boutons[i][j]);
            }
        }

        boutons[boutons.length/2][boutons[0].length/2].setVisible(true);
        c.ipadx=1000;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.ipady=200;
        haut.setBackground(Color.RED);
        add(haut,c);
        c.gridy=1;
        c.ipady=600;
        jscroll.setBackground(Color.BLUE);
        add(jscroll,c);
        c.gridy=2;
        c.ipady=200;
        bas.setBackground(Color.GREEN);
        add(bas,c);

        JButton zomm = new JButton();
        JButton zomm2 = new JButton();
        zomm.addActionListener(e->{
            zoomIn();
        });
        zomm2.addActionListener(e->{
            zoomOut();
        });
        add(zomm2);
        add(zomm);
    }

    public void zoomIn() {
        zoomFactor++;
        plateau.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
        // for(int i=0; i<boutons.length;i++) {
        //     for (int j = 0; j < boutons[0].length; j++) {
        //         System.out.println(boutons[i][j].getComponents());
        //         boutons[i][j].getComponent(0).setPreferredSize(new Dimension(boutons[i][j].getWidth()*zoomFactor, boutons[i][j].getHeight()*zoomFactor));
        //     }
        // }
        jscroll.revalidate();
    }
    
    public void zoomOut() {
    if (zoomFactor > 1) {
        zoomFactor--;
        plateau.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
        // for(int i=0; i<boutons.length;i++) {
        //     for (int j = 0; j < boutons[0].length; j++) {
        //         boutons[i][j].getComponent(0).setPreferredSize(new Dimension(boutons[i][j].getWidth()*zoomFactor, boutons[i][j].getHeight()*zoomFactor));
        //     }
        // }
        jscroll.revalidate();
    }// Repaint the panel with the new scale factor
    }

    // public ImageIcon createImage(int[] chiffres) {
    //     if(chiffres.length!=8) return null;
    //     else {
           
    //     }
    // }

    public void remplirLinkedList() {
        for (int i = 0; i<30; i++) {
                emplacementsDominos.add(new LinkedList<>());
            for (int j = 0; j<30; j++) {
                 emplacementsDominos.get(i).add(null);
             }            
         }
    }
    public static void main(String[] args) {
        Plateau2 p = new Plateau2(10, 10);
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        frame.setContentPane(p);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.pack();
    }
}
