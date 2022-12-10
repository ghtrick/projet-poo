package src.main.java.Carcassonne_Domino.view_Terminal;

import src.main.java.Carcassonne_Domino.model.AbstractJeu;
import src.main.java.Carcassonne_Domino.model.Domino;
import src.main.java.Carcassonne_Domino.model.Joueur;
import src.main.java.Carcassonne_Domino.model.ModelJeu;

public class PartieTerm extends AbstractJeu {

    public ModelJeu m;

    public PartieTerm() {
        super();
    }

    public String affichePlateau() {
        return null;
    }

    @Override
    public boolean ajoutDomino(Domino d, int x, int y) {
        return false;
    }

    public Joueur partie2Joueurs() {
        return null;
    }
}