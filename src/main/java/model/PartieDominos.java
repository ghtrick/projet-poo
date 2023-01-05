package src.main.java.model;
import java.util.Arrays;
import java.util.LinkedList;

import src.main.java.abstractClass.*;
import src.main.java.view.VueMain;

public class PartieDominos extends AbstractJeu {

    VueMain vmain;

    public PartieDominos(PlateauDominos plateau, SacDominos sac, int nbJoueurs, int nbBot) {
        super(plateau, sac, nbJoueurs, nbBot);
    }

    @Override
    public int lancerPartie() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void partieFinie(int gagnant) {
        
    }

    @Override
    public void choisirJoueurs(int nbJoueurs, int nbBots) {
        joueurs = new LinkedList<>();
        int num = 1;
        for (int i = 0; i < nbJoueurs-nbBots; i++) {
            joueurs.add(new Joueur(0, num));
            num++;
        }
        for (int i = 0; i < nbBots; i++) {
            joueurs.add(new Bot(0, num));
            num++;
        }
    }

    @Override
    public void piocher() {
        if(sac.getTuilesDansLeSac().isEmpty()) {
            partieFinie(max()+1);  
            return; 
        }
        Domino d = (Domino) sac.getTuilesDansLeSac().getFirst();
        main=d;
        sac.getTuilesDansLeSac().removeFirst();
    }

}
