import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.geom.AffineTransform;

public class Plateau extends JPanel {

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

    double scaleFactor = 1.0;
 
    public Plateau(int nbLignes, int nbColonnes) {
        remplirLinkedList();
        setLayout(new GridBagLayout());
        haut=new JPanel();
        bas = new JPanel();
        plateau = new JPanel();
        JScrollPane jcsceoll = new JScrollPane();
        plateau.setLayout(new GridLayout(nbLignes, nbColonnes));
     // plateau.setPreferredSize(new Dimension(1000,600));
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
                JLabel[][] text = new JLabel[5][5];
                text[0][1] = new JLabel("0", SwingConstants.CENTER);
                text[0][1].setFont(new Font("Ariel", 50,50));
                text[0][2] = new JLabel("0", SwingConstants.CENTER);
                text[0][2].setFont(new Font("Ariel", 50,50));
                text[0][3] = new JLabel("0", SwingConstants.CENTER);
                text[0][3].setFont(new Font("Ariel", 50,50));
                text[4][1] = new JLabel("3", SwingConstants.CENTER);
                text[4][1].setFont(new Font("Ariel", 50,50));
                text[4][2] = new JLabel("3", SwingConstants.CENTER);
                text[4][2].setFont(new Font("Ariel", 50,50));
                text[4][3] = new JLabel("3", SwingConstants.CENTER);   
                text[4][3].setFont(new Font("Ariel", 50,50));
                text[1][0] = new JLabel("1", SwingConstants.CENTER);
                text[1][0].setFont(new Font("Ariel", 50,50));
                text[1][4] = new JLabel("2", SwingConstants.CENTER);
                text[1][4].setFont(new Font("Ariel", 50,50));
                text[2][0] = new JLabel("1", SwingConstants.CENTER);
                text[2][0].setFont(new Font("Ariel", 50,50));
                text[2][4] = new JLabel("2", SwingConstants.CENTER);
                text[2][4].setFont(new Font("Ariel", 50,50));
                text[3][0] = new JLabel("1", SwingConstants.CENTER);
                text[3][0].setFont(new Font("Ariel", 50,50));
                text[3][4] = new JLabel("2", SwingConstants.CENTER);
                text[3][4].setFont(new Font("Ariel", 50,50));

                boutons[i][j].setLayout(new GridLayout(5,5));
                boutons[i][j].setSize(10, 10);
                for(int k=0; k<text.length;k++) {
                    for(int l=0; l<text.length; l++) {
                        if(text[k][l]==null) {
                            boutons[i][j].add(new JLabel(""));
                        } else {
                            boutons[i][j].add(text[k][l]);
                        }
                    }
                }
                plateau.add(boutons[i][j]);
            }
        }
        jcsceoll.add(plateau);

        boutons[boutons.length/2][boutons[0].length/2].setVisible(true);
        c.ipadx=1000;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.ipady=200;
        haut.setBackground(Color.RED);
        add(haut,c);
        c.gridy=1;
        c.ipady=600;
        jcsceoll.setBackground(Color.BLUE);
        add(jcsceoll,c);
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

 // Initial scale factor
    }

    public void remplirLinkedList() {
        for (int i = 0; i<30; i++) {
                emplacementsDominos.add(new LinkedList<>());
            for (int j = 0; j<30; j++) {
                 emplacementsDominos.get(i).add(null);
             }            
         }
    }

    // Method to zoom in
    public void zoomIn() {
        scaleFactor *= 1.1; // Increase the scale factor by 10%
        plateau.repaint(); // Repaint the panel with the new scale factor
    }

    // Method to zoom out
    public void zoomOut() {
        scaleFactor /= 1.1; // Decrease the scale factor by 10%
        plateau.repaint(); // Repaint the panel with the new scale factor
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Create an AffineTransform object with the current scale factor
        AffineTransform at = AffineTransform.getScaleInstance(scaleFactor, scaleFactor);

        // Set the transform of the Graphics2D object to the AffineTransform object
        g2d.setTransform(at);
    }
}
    // public void ajoutDominosPlacables(int x, int y) {
    //     int[] u = {x,y+1};
    //     int[] r = {x+1,y};
    //     int[] b = {x,y-1};
    //     int[] l = {x-1,y};
    //     int[][] d = {u,r,b,l};
    //     for (int[] t : d) {
    //         Object[] elementaAjouter = {null, 2};
    //         emplacementsDominos.get(t[1]).add(t[0], elementaAjouter);
    //     }
    //     ajoutBoutonsEtListeners(d);
    // }

    // public void ajoutBoutonsEtListeners(int[][] coordonnees) {
    //     for (int[] t : coordonnees) {
    //         c1.gridx=t[1];
    //         c1.gridy=t[0];
    //         JButton button = new JButton("test");
    //         button.addActionListener(ev -> {
    //             ajoutDominosPlacables(t[0], t[1]);
    //             button.setBorderPainted(false);
    //             button.setFocusPainted(false);
    //         });
    //         plateau.add(button,c1);
    //     }
    // }

