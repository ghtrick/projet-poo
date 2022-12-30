import java.util.LinkedList;

public class PartieDominosTerm extends AbstractJeu {

    public PartieDominosTerm(PlateauDominosTerm plateau, SacDominos sac, int nbJoueurs, int nbBot) {
        super(plateau, sac, nbJoueurs, nbBot);
    }


    @Override
    public int lancerPartie() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void partieFinie(int gagnant) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void choisirJoueurs(int nbJoueurs, int nbBots) {
        int num = 1;
        for (int i = 0; i < nbJoueurs-nbBots; i++) {
            joueurs.add(new Joueur(0, num));
        }
        for (int i = 0; i < nbBots; i++) {
            joueurs.add(new Bot(0, num));
        }
    }

    @Override
    public void piocher() {
        // TODO Auto-generated method stub
        
    }
    
}
