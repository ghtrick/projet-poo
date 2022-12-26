import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.awt.event.MouseEvent;

public class Plateau2 extends JPanel {

    JPanel plateau;
    JPanel haut;
    //0 -> vide ; 1 -> domino pose ; 2 -> domino placable
    LinkedList<LinkedList<Object[]>> emplacementsDominos = new LinkedList<>();
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints c1 = new GridBagConstraints();

    JButton[][] boutons;
    int nbLignes;
    int nbColonnes;
    JScrollPane jscroll = new JScrollPane();
    Point initialPosition;
 
    private int zoomFactor = 1;

    public Plateau2(int nbLignes, int nbColonnes, JFrame frame) {
        remplirLinkedList();
        setLayout(new BorderLayout());
        haut=new JPanel();
        plateau = new JPanel();
        jscroll = new JScrollPane();
        jscroll.setViewportView(plateau);
        // jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        // jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
        System.out.println(jscroll.getWidth());
        plateau.setPreferredSize(new Dimension(jscroll.getWidth(),jscroll.getWidth()));

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
                int[] truc = new int[0];
                boutons[i][j].addActionListener(e -> {
                    if(I==0||J==0||I==boutons.length-1||J==boutons[0].length-1) return;
                    ImageIcon image = createImage(truc);
                    Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
                    ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                    imageIconScaled.setImage(imageScaled);
                    boutons[I][J].setIcon(imageIconScaled);
                    boutons[I+1][J].setVisible(true);
                    boutons[I][J+1].setVisible(true);
                    boutons[I-1][J].setVisible(true);
                    boutons[I][J-1].setVisible(true);
                    boutons[I][J].setEnabled(false);
                });

                plateau.add(boutons[i][j]);
            }
        }

        boutons[boutons.length/2][boutons[0].length/2].setVisible(true);
        //c.ipadx = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.gridy=0;
         haut.setBackground(Color.RED);
        // add(haut,c);
        // c.gridy=1;
        // jscroll.setBackground(Color.BLUE);
        // add(jscroll,c);
        add(haut,BorderLayout.NORTH);
        add(jscroll,BorderLayout.SOUTH);

        JButton zomm = new JButton();
        JButton zomm2 = new JButton();
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
                int[] truc = new int[0];
                ImageIcon image = createImage(truc);
                Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
                ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                imageIconScaled.setImage(imageScaled);
                boutons[i][j].setIcon(imageIconScaled);
            }
        }
        jscroll.revalidate();
    }

    public void zoomIn() {
        zoomFactor++;
        plateau.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
        for(int i=0; i<boutons.length;i++) {
            for (int j = 0; j < boutons[0].length; j++) {
                int[] truc = new int[0];
                ImageIcon image = createImage(truc);
                Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
                ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                imageIconScaled.setImage(imageScaled);
                boutons[i][j].setIcon(imageIconScaled);
            }
        }
        jscroll.revalidate();
    }
    
    public void zoomOut() {
    if (zoomFactor > 1) {
        zoomFactor--;
        plateau.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
        for(int i=0; i<boutons.length;i++) {
            for (int j = 0; j < boutons[0].length; j++) {
                int[] truc = new int[0];
                ImageIcon image = createImage(truc);
                Image imageScaled = image.getImage().getScaledInstance(600/nbLignes*zoomFactor, 600/nbColonnes*zoomFactor, Image.SCALE_SMOOTH); 
                ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                imageIconScaled.setImage(imageScaled);
                boutons[i][j].setIcon(imageIconScaled);
            }
        }
        jscroll.revalidate();
    }// Repaint the panel with the new scale factor
    }

    public ImageIcon createImage(int[] chiffres) {
        try {
            // Charger les deux images
            BufferedImage image1 = ImageIO.read(new File("./img/0b.png"));
            BufferedImage image2 = ImageIO.read(new File("./img/1b.png"));
            BufferedImage image3 = ImageIO.read(new File("./img/2b.png"));
            BufferedImage image4 = ImageIO.read(new File("./img/3b.png"));
          
            // Créer une nouvelle image avec la largeur et la hauteur des deux images
            int width = image1.getWidth()*5;
            int height = image1.getHeight()*5;
            BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          
            // Dessiner les deux images côte à côte
            Graphics g = combined.getGraphics();
            g.drawImage(image1, width/5, 0, null);
            g.drawImage(image1, width/5*2, 0, null);
            g.drawImage(image1, width/5*3, 0, null);
            g.drawImage(image2, 0, height/5, null);
            g.drawImage(image2, 0, height/5*2, null);
            g.drawImage(image2, 0, height/5*3, null);
            g.drawImage(image3, width/5*4, height/5, null);
            g.drawImage(image3, width/5*4, height/5*2, null);
            g.drawImage(image3, width/5*4, height/5*3, null);
            g.drawImage(image4, width/5, height/5*4, null);
            g.drawImage(image4, width/5*2, height/5*4, null);
            g.drawImage(image4, width/5*3, height/5*4, null);

            ImageIcon image = new ImageIcon(combined);
            return image;
          } catch (Exception e) {}
        return null;
    }

    public void remplirLinkedList() {
        for (int i = 0; i<30; i++) {
                emplacementsDominos.add(new LinkedList<>());
            for (int j = 0; j<30; j++) {
                 emplacementsDominos.get(i).add(null);
             }            
         }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(3);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Plateau2 p = new Plateau2(10, 10,frame);
                JScrollPane j = new JScrollPane(p);
                frame.setContentPane(p);
                //frame.setResizable(false);
                frame.getContentPane().setBackground(Color.BLUE);
            }
        });
        frame.setVisible(true);
    }
}
