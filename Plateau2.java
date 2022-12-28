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

public class Plateau2 extends JPanel {

    PartieG p;
    ModelJeu model;
    JPanel plateau;
    JPanel haut;
    LinkedList<JLabel> nombrePoints = new LinkedList<>();
    //0 -> vide ; 1 -> domino pose ; 2 -> domino placable
    LinkedList<LinkedList<Domino>> emplacementsDominos = new LinkedList<>();
    private JButton[][] boutons;
    int nbLignes;
    int nbColonnes;
    JScrollPane jscroll = new JScrollPane();
    Point initialPosition;
    JButton quitter;
    JLabel reste;
 
    private int zoomFactor = 3;

    public Plateau2(int nbLignes, int nbColonnes, JFrame frame, ModelJeu model){
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
                        boutons[I][J].setEnabled(false);                        
                        p.skip();
                      
                    }
                    System.out.println();
                    System.out.println(Arrays.toString(p.dominoCourantChiffre[0]));
                    System.out.println(Arrays.toString(p.dominoCourantChiffre[1]));
                    System.out.println(Arrays.toString(p.dominoCourantChiffre[2]));
                    System.out.println(Arrays.toString(p.dominoCourantChiffre[3]));
                    String res = "";
                    System.out.println(emplacementsDominos.size());
                    for (int l = 0; l < emplacementsDominos.size(); l++) {
                        res+=Partie.afficheDominoListe(emplacementsDominos.get(l));
                    }
                    System.out.println(res);
                    

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
        System.out.println();
        System.out.println(Arrays.toString(d.getNumeros()[0]));
        System.out.println(Arrays.toString(d.getNumeros()[1]));
        System.out.println(Arrays.toString(d.getNumeros()[2]));
        System.out.println(Arrays.toString(d.getNumeros()[3]));
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
        boutons[boutons.length/2][boutons[0].length/2].setEnabled(false);
        
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

    public ImageIcon createImage(int[][] chiffres) {
        try {

            int[][] tmp = {chiffres[0],chiffres[3],chiffres[1],chiffres[2]};

            BufferedImage image1 = ImageIO.read(new File("img/"+tmp[0][0]+"b.png"));
            BufferedImage image2 = ImageIO.read(new File("img/"+tmp[0][1]+"b.png"));
            BufferedImage image3 = ImageIO.read(new File("img/"+tmp[0][2]+"b.png"));
            BufferedImage image4 = ImageIO.read(new File("img/"+tmp[1][0]+"b.png"));
            BufferedImage image5 = ImageIO.read(new File("img/"+tmp[1][1]+"b.png"));
            BufferedImage image6 = ImageIO.read(new File("img/"+tmp[1][2]+"b.png"));
            BufferedImage image7 = ImageIO.read(new File("img/"+tmp[2][0]+"b.png"));
            BufferedImage image8 = ImageIO.read(new File("img/"+tmp[2][1]+"b.png"));
            BufferedImage image9 = ImageIO.read(new File("img/"+tmp[2][2]+"b.png"));
            BufferedImage image10 = ImageIO.read(new File("img/"+tmp[3][0]+"b.png"));
            BufferedImage image11 = ImageIO.read(new File("img/"+tmp[3][1]+"b.png"));
            BufferedImage image12 = ImageIO.read(new File("img/"+tmp[3][2]+"b.png"));
          
            // Créer une nouvelle image avec la largeur et la hauteur des deux images
            int width = image1.getWidth()*5;
            int height = image1.getHeight()*5;
            BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          
            // Dessiner les deux images côte à côte
            Graphics g = combined.getGraphics();
            g.drawImage(image1, width/5, 0, null);
            g.drawImage(image2, width/5*2, 0, null);
            g.drawImage(image3, width/5*3, 0, null);
            g.drawImage(image4, 0, height/5, null);
            g.drawImage(image5, 0, height/5*2, null);
            g.drawImage(image6, 0, height/5*3, null);
            g.drawImage(image7, width/5*4, height/5, null);
            g.drawImage(image8, width/5*4, height/5*2, null);
            g.drawImage(image9, width/5*4, height/5*3, null);
            g.drawImage(image10, width/5, height/5*4, null);
            g.drawImage(image11, width/5*2, height/5*4, null);
            g.drawImage(image12, width/5*3, height/5*4, null);

            ImageIcon image = new ImageIcon(combined);
            return image;
          } catch (Exception e) {System.out.println(e);}
        return null;
    }

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
