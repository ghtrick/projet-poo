import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.awt.event.MouseEvent;

public abstract class AbstractPlateau extends JPanel{
    protected PartieG p;
    protected ModelJeu model;
    protected JPanel plateau;
    protected JPanel haut;
    protected LinkedList<JLabel> nombrePoints = new LinkedList<>();
    //0 -> vide ; 1 -> domino pose ; 2 -> domino placable
    protected LinkedList<LinkedList<Domino>> emplacementsDominos = new LinkedList<>();
    protected JButton[][] boutons;
    protected int nbLignes;
    protected int nbColonnes;
    protected JScrollPane jscroll = new JScrollPane();
    protected Point initialPosition;
    protected JButton quitter;
    protected JLabel reste;
    protected boolean[][] isPlaced;
 
    protected int zoomFactor = 3;

    public AbstractPlateau(int nbLignes, int nbColonnes, JFrame frame, ModelJeu model){
        remplirLinkedList(nbLignes,nbColonnes);
        this.model = model;
        setLayout(new BorderLayout());
        haut=new JPanel();
        haut.setLayout(null);
        plateau = new JPanel();
        reste = new JLabel("Domino restant: "+(model.s.getDominosSac().size()-2));
        reste.setBounds(frame.getWidth()/2-250, 50, 500,200);
        reste.setFont(new Font("Arial",0,50));
        jscroll = new JScrollPane();
        jscroll.setViewportView(plateau);
        jscroll.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialPosition = e.getPoint();
            }
        });
            
        jscroll.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JScrollPane scrollPane = (JScrollPane) e.getSource();
                int x = initialPosition.x - e.getX();
                int y = initialPosition.y - e.getY();
                int newX = scrollPane.getHorizontalScrollBar().getValue() + x;
                int newY = scrollPane.getVerticalScrollBar().getValue() + y;
                scrollPane.getHorizontalScrollBar().setValue(newX);
                scrollPane.getVerticalScrollBar().setValue(newY);
                initialPosition = e.getPoint();     
            }
        });
        haut.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/5-frame.getInsets().top));
        jscroll.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()*4/5));

        jscroll.setWheelScrollingEnabled(true);
        plateau.setLayout(new GridLayout(nbLignes, nbColonnes));

        isPlaced = new boolean[nbLignes][nbColonnes];
        for(boolean[] b : isPlaced) {
            for (boolean c : b) {
                c=false;
            }
        }

        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        boutons = new JButton[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {               
                boutons[i][j] = new JButton();
                boutons[i][j].setLayout(new GridLayout(5,5));
                boutons[i][j].setVisible(false);
                int I = i;
                int J = j;
                boutons[i][j].addActionListener(e -> {
                    if(!isPlaced[I][J]) {
                        if(p.ajoutDomino(new Domino(p.dominoCourantChiffre),I,J)) {
                            if(I==0||J==0||I==boutons.length-1||J==boutons[0].length-1) return;
                            ImageIcon image = createImage(p.dominoCourantChiffre);
                            Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
                            ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                            imageIconScaled.setImage(imageScaled);
                            boutons[I][J].setIcon(imageIconScaled);
                            boutons[I+1][J].setVisible(true);
                            boutons[I][J+1].setVisible(true);
                            boutons[I-1][J].setVisible(true);
                            boutons[I][J-1].setVisible(true);                      
                            p.skip();
                            isPlaced[I][J] = true;
                        }  
                        for(int k = 0; k<emplacementsDominos.size(); k++) {
                            System.out.println(Partie.afficheDominoListe(emplacementsDominos.get(k)));
                        }
                    }
                });

                plateau.add(boutons[i][j]);
            }
        }

        boutons[boutons.length/2][boutons[0].length/2].setVisible(true);
        haut.setBackground(Color.RED);
        add(haut,BorderLayout.NORTH);
        add(jscroll,BorderLayout.SOUTH);
        haut.add(reste);

        JButton zomm = new JButton("zoom +");
        JButton zomm2 = new JButton("zoom -");
        zomm.setBounds(frame.getWidth()-130, 10, 100, 50);
        zomm2.setBounds(frame.getWidth()-240, 10, 100, 50);
        
        zomm.addActionListener(e->{
            zoomIn();
        });
        zomm2.addActionListener(e->{
            zoomOut();
        });
        haut.add(zomm2);
        haut.add(zomm);
        plateau.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
        for(int i=0; i<boutons.length;i++) {
            for (int j = 0; j < boutons[0].length; j++) {
                ImageIcon image = new ImageIcon("./img/plus.png");
                Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
                ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                imageIconScaled.setImage(imageScaled);
                boutons[i][j].setIcon(imageIconScaled);
            }
        }
        Domino d = model.s.getDominosSac().getFirst();
        emplacementsDominos.get(boutons.length/2).add(boutons[0].length/2, d);
        model.s.getDominosSac().removeFirst();
        ImageIcon image = createImage(d.getNumeros());
        Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        boutons[boutons.length/2][boutons[0].length/2].setIcon(imageIconScaled);
        boutons[boutons.length/2+1][boutons[0].length/2].setVisible(true);
        boutons[boutons.length/2][boutons[0].length/2+1].setVisible(true);
        boutons[boutons.length/2-1][boutons[0].length/2].setVisible(true);
        boutons[boutons.length/2][boutons[0].length/2-1].setVisible(true);
        
        jscroll.revalidate();
    }

    public void zoomIn() {
        zoomFactor++;
        plateau.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
        for(int i=0; i<boutons.length;i++) {
            for (int j = 0; j < boutons[0].length; j++) {
                ImageIcon image = (ImageIcon) boutons[i][j].getIcon();
                Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
                ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                imageIconScaled.setImage(imageScaled);
                boutons[i][j].setIcon(imageIconScaled);
            }
        }
        jscroll.revalidate();
    }
    
    public void zoomOut() {
        if (zoomFactor > 2) {
            zoomFactor--;
            plateau.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
            for(int i=0; i<boutons.length;i++) {
                for (int j = 0; j < boutons[0].length; j++) {
                    ImageIcon image = (ImageIcon) boutons[i][j].getIcon();
                    Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
                    ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                    imageIconScaled.setImage(imageScaled);
                    boutons[i][j].setIcon(imageIconScaled);
                }
            }
            jscroll.revalidate();
        }// Repaint the panel with the new scale factor
    }

    public void initialiserQuitter() {
        quitter = new JButton("quitter");
        quitter.setBounds(10, 10, 100, 50);
        quitter.addActionListener(e -> {
            p.plateau.setVisible(false);
            p.dominoCourant.dispose();
            p.frame.setContentPane(p.menu);
            p.menu.setVisible(true);
            p.frame.dispose();
            PartieG p = new PartieG();
        });
        haut.add(quitter);
    }

    public abstract ImageIcon createImage(int[][] chiffres);

    public void remplirLinkedList(int a, int b) {
        for (int i = 0; i<a; i++) {
                emplacementsDominos.add(new LinkedList<>());
            for (int j = 0; j<b; j++) {
                emplacementsDominos.get(i).add(new Domino());
            }            
        }
    }

    public JButton[][] getBoutons() {
        return boutons;
    }

    // public static void main(String[] args) {
    //     JFrame frame = new JFrame();
    //     frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    //     frame.setDefaultCloseOperation(3);
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             Plateau2 p = new Plateau2(20, 20,frame);
    //             JScrollPane j = new JScrollPane(p);
    //             frame.setContentPane(p);
    //             //frame.setResizable(false);
    //             frame.getContentPane().setBackground(Color.BLUE);
    //             frame.addComponentListener(new ComponentListener() {
    //                 public void componentResized(ComponentEvent e) {
    //                     p.haut.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/5-frame.getInsets().top));
    //                     p.jscroll.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()*4/5));
    //                 }
                    
    //                 // les autres méthodes de l'interface ComponentListener ne sont pas utilisées dans cet exemple
    //                 public void componentMoved(ComponentEvent e) {}
    //                 public void componentShown(ComponentEvent e) {}
    //                 public void componentHidden(ComponentEvent e) {}
    //               });
    //         }
    //     });
    //     frame.setVisible(true);
    // }
}
