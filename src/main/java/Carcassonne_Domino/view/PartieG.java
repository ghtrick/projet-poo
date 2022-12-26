package src.main.java.Carcassonne_Domino.view;
import src.main.java.Carcassonne_Domino.AbstractClasses.AbstractJeu;
import src.main.java.Carcassonne_Domino.model.Domino;
import src.main.java.Carcassonne_Domino.model.Joueur;
import src.main.java.Carcassonne_Domino.model.ModelJeu;
import javax.swing.*;
import java.awt.*;

public class PartieG extends AbstractJeu{

    ModelJeu model;
    MenuG menu;
    Plateau2 plateau;
    public JFrame frame;

    public PartieG() {
        model = new ModelJeu();
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


    @Override
    public boolean ajoutDomino(Domino d, int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Joueur partie2Joueurs() {
        menu.jouerDomino.addActionListener(e -> {
            menu.setVisible(false);
            plateau = (model.taillePlateau==0) ? new Plateau2(20, 20, frame) : new Plateau2(model.taillePlateau, model.taillePlateau, frame);
            plateau.p = this;
            frame.setContentPane(plateau);
        });
        return null;
    }
}
