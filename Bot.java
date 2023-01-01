public class Bot extends AbstractJoueur{

    ControlBot c;
    
    public Bot(int point, int numeroDeJoueur) {
        super(point, numeroDeJoueur);
    }

    public void Play(AbstractTuile d) {
        for(int i=1; i<c.main.p.boutons.length-1; i++) {
            for (int j = 1; j < c.main.p.boutons[i].length-1; j++) {
                if(c.main.p.boutons[i][j].isVisible() && !c.main.p.isPlaced[i][j]) {
                    if(c.main.p.p.plateau.ajoutTuile(d, i, j)) {
                        c.main.p.clickBouton(i,j);
                        return;
                    }
                }
            }
        }
        c.main.skip();
    }
}
