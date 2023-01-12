package src.main.java.view;

import src.main.java.model.*;
import src.main.java.abstractClass.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;


public class VuePartie extends AbstractPanel {

    protected boolean modeDeJeu;
    protected AbstractJeu p;

    protected VueMain main;
    protected JPanel plateauPanel;
    protected JPanel topPanel;
    protected JScrollPane scrollPanel;
    protected JLabel reste;
    protected int zoomFactor = 3;
    protected JButton[][] boutons;
    protected boolean[][] isPlaced;
    protected JButton zoomIn;
    protected JButton zoomOut;
    protected JButton quitter;
    protected Point initialPosition;
    protected JButton abandonner;

    public VuePartie(JFrame j, boolean modeDeJeu, int nbJoueurs, int nbBot) {
        super(j);
        this.modeDeJeu=modeDeJeu;

        boutons = new JButton[20][20];
        
        if (modeDeJeu) {
            p = new PartieDominos(null, new SacDominos(), nbJoueurs, nbBot);
            p.setPlateau(new PlateauDominos((PartieDominos) p));
        } else {
            p = new PartieCarcassonne(null, new SacCartesCarcassonne(), nbJoueurs, nbBot);
            p.setPlateau(new PlateauCarcassonne((PartieCarcassonne) p));
        }

        this.setLayout(new BorderLayout());

        setReste();
        setZoomIn();
        setZoomOut();
        setTopPanel();
        setPlateauPanel();
        setScrollPanel();
        setAbandonner();
        scrollPanel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialPosition = e.getPoint();
            }
        });
        scrollPanel.addMouseMotionListener(new MouseMotionAdapter() {
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
        setIsPlaced();
        setBoutons();
        setBoutonsPlus();
        initBoutonDepart();
        setQuitter();
        
        add(topPanel,BorderLayout.NORTH);
        add(scrollPanel,BorderLayout.SOUTH);

        main = new VueMain(p, this, j);
        zoomIn();
    }

    public void setTopPanel() {
        topPanel=new JPanel();
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(j.getWidth(), j.getHeight()/5-j.getInsets().top));
        topPanel.setBackground(Color.RED);
        topPanel.add(reste);
        topPanel.add(zoomIn);
        topPanel.add(zoomOut);
    }

    public void setPlateauPanel() {
        plateauPanel = new JPanel();
        plateauPanel.setLayout(new GridLayout(20,20));
        plateauPanel.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
    }

    public void setScrollPanel() {
        initialPosition=new Point();
        scrollPanel = new JScrollPane();
        scrollPanel.setViewportView(plateauPanel);
        scrollPanel.setPreferredSize(new Dimension(j.getWidth(), j.getHeight()*4/5));
        scrollPanel.setWheelScrollingEnabled(true);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(50);
        scrollPanel.getHorizontalScrollBar().setUnitIncrement(50);
    }

    public void setReste() {
        reste = new JLabel("Domino restant: "+(p.getSac().getTuilesDansLeSac().size()-2));
        reste.setBounds(j.getWidth()/2-250, 50, 500,200);
        reste.setFont(new Font("Arial",0,50));
    }

    public void setIsPlaced() {
        isPlaced = new boolean[20][20];
        for(boolean[] b : isPlaced) {
            for (boolean c : b) {
                c=false;
            }
        }
    }

    public void setBoutons() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {               
                boutons[i][j] = new JButton();
                boutons[i][j].setLayout(new GridLayout(5,5));
                boutons[i][j].setVisible(false);
                int I = i;
                int J = j;
                boutons[i][j].addActionListener(e -> {
                    LinkedList<AbstractTuile> l = new LinkedList<>();
                    l.add(p.getMain());
                    for (LinkedList<AbstractTuile> t : p.getPlateau().getPlateau()) {
                        System.out.println(Util.afficheDominoListe(t));
                    }
                    if (!isPlaced[I][J]) {
                        if (p.getPlateau().ajoutTuile(p.getMain(), I, J)) {
                            if(p.getMain() instanceof CarteCarcassonne) main.placerPion(I, J);
                            else clickBouton(I,J);
                        }
                    }
                });
                plateauPanel.add(boutons[i][j]);
            }
        }
        boutons[boutons.length/2][boutons[0].length/2].setVisible(true);
    }

    public void setZoomIn() {
        zoomIn = new JButton("Zoom+");
        zoomIn.setBounds(j.getWidth()-130, 10, 100, 50);
        zoomIn.addActionListener(e->{
            zoomIn();
        });
    }

    public void setZoomOut() {
        zoomOut = new JButton("Zoom-");
        zoomOut.setBounds(j.getWidth()-240, 10, 100, 50);
        zoomOut.addActionListener(e->{
            zoomOut();
        });
    }

    public void setQuitter() {
        quitter = new JButton("quitter");
        quitter.setBounds(10, 10, 100, 50);
        quitter.addActionListener(e -> {
            plateauPanel.setVisible(false);
            main.dispose();
            j.dispose();
            VueGenerale v = new VueGenerale();
        });
        topPanel.add(quitter);
    }

    public void zoomIn() {
        zoomFactor++;
        plateauPanel.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
        for(int i=0; i<boutons.length;i++) {
            for (int j = 0; j < boutons[0].length; j++) {
                ImageIcon image = (ImageIcon) boutons[i][j].getIcon();
                Image imageScaled = image.getImage().getScaledInstance(600/20*zoomFactor, 600/20*zoomFactor, Image.SCALE_SMOOTH); 
                ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                imageIconScaled.setImage(imageScaled);
                boutons[i][j].setIcon(imageIconScaled);
            }
        }
        scrollPanel.revalidate();
    }

    public void zoomOut() {
        if (zoomFactor > 4) {
            zoomFactor--;
            plateauPanel.setPreferredSize(new Dimension(600 * zoomFactor, 600 * zoomFactor));
            for(int i=0; i<boutons.length;i++) {
                for (int j = 0; j < boutons[0].length; j++) {
                    ImageIcon image = (ImageIcon) boutons[i][j].getIcon();
                    Image imageScaled = image.getImage().getScaledInstance(600/20*zoomFactor, 600/20*zoomFactor, Image.SCALE_SMOOTH); 
                    ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                    imageIconScaled.setImage(imageScaled);
                    boutons[i][j].setIcon(imageIconScaled);
                }
            }
            scrollPanel.revalidate();
        }// Repaint the panel with the new scale factor
    }

    public void setBoutonsPlus() {
        for(int i=0; i<boutons.length;i++) {
            for (int j = 0; j < boutons[0].length; j++) {
                ImageIcon image = new ImageIcon("./img/plus.png");
                Image imageScaled = image.getImage().getScaledInstance(600/20*zoomFactor, 600/20*zoomFactor, Image.SCALE_SMOOTH); 
                ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
                imageIconScaled.setImage(imageScaled);
                boutons[i][j].setIcon(imageIconScaled);
            }
        }
    }

    public void setAbandonner() {
        abandonner = new JButton("Abandonner");
        abandonner.setBounds(10, 70, 200, 50);
        abandonner.addActionListener(e -> {
            p.getJoueurs().remove(p.getJoueurCourant());
            // System.out.println(Arrays.toString(p.getJoueurs().toArray()));
            // System.out.println(p.getJoueurs().size());
            p.setJoueurCourant(p.getJoueurCourant()-1);
            p.setNbJoueurs(p.getNbJoueurs()-1);
            main.skip();
        });
        topPanel.add(abandonner);
    }

    public void initBoutonDepart() {
        p.piocher();
        ImageIcon image = p.getPlateau().createImage(p.getMain());
        Image imageScaled = image.getImage().getScaledInstance(600/20*zoomFactor, 600/20*zoomFactor, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaled = new ImageIcon(imageScaled); 
        imageIconScaled.setImage(imageScaled);
        boutons[boutons.length/2][boutons[0].length/2].setIcon(imageIconScaled);
        boutons[boutons.length/2+1][boutons[0].length/2].setVisible(true);
        boutons[boutons.length/2][boutons[0].length/2+1].setVisible(true);
        boutons[boutons.length/2-1][boutons[0].length/2].setVisible(true);
        boutons[boutons.length/2][boutons[0].length/2-1].setVisible(true);
        isPlaced[boutons.length/2][boutons[0].length/2] = true;
        p.getPlateau().getPlateau().get(10).set(10, p.getMain());
    }

    public void clickBouton(int I, int J) {
        if (!isPlaced[I][J]) {
            if (p.getPlateau().getPlateau().isEmpty()) {
                VueFin fin = new VueFin(j, main);
                setVisible(false);
                fin.partieFini(p.max());
                return;
            }
            if (I == 0 || J == 0 || I == boutons.length - 1 || J == boutons[0].length - 1) return;
            ImageIcon image = p.getPlateau().createImage(p.getMain());
            System.out.println("test");
            System.out.println(image.toString());
            if (p instanceof PartieCarcassonne && p.getJoueurs().get(p.getJoueurCourant()) instanceof Joueur) {
                image = (ImageIcon) main.dominoCourantButton2.getIcon();
            }
            Image imageScaled = image.getImage().getScaledInstance(600 / 20 * zoomFactor, 600 / 20 * zoomFactor, Image.SCALE_SMOOTH);
            ImageIcon imageIconScaled = new ImageIcon(imageScaled);
            imageIconScaled.setImage(imageScaled);
            boutons[I][J].setIcon(imageIconScaled);
            boutons[I + 1][J].setVisible(true);
            boutons[I][J + 1].setVisible(true);
            boutons[I - 1][J].setVisible(true);
            boutons[I][J - 1].setVisible(true);
            isPlaced[I][J] = true;
            main.skip();
            LinkedList<AbstractTuile> l = new LinkedList<>();
            l.add(p.getMain());
        }
    }

    public VueMain getMain() {
        return main;
    }

    public AbstractJeu getP() {
        return p;
    }
    public boolean[][] getIsPlaced() {
        return isPlaced;
    }
    public JButton[][] getBoutons() {
        return boutons;
    }
    
}
