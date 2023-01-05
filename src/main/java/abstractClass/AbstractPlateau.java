package src.main.java.abstractClass;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;

public abstract class AbstractPlateau {
    protected AbstractJeu jeu;
    protected LinkedList<LinkedList<AbstractTuile>> plateau;
    protected int nbLignes;
    protected int nbColonnes;

    public AbstractPlateau(LinkedList<LinkedList<AbstractTuile>> plateau, AbstractJeu jeu) {
        this.plateau = plateau;
        this.jeu=jeu;
    }

    public abstract boolean ajoutTuile(AbstractTuile t, int y, int x);

    public abstract ImageIcon createImage(AbstractTuile t);

    public AbstractJeu getJeu() {
        return jeu;
    }

    public LinkedList<LinkedList<AbstractTuile>> getPlateau() {
        return plateau;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getNbLignes() {
        return nbLignes;
    }
 
}
