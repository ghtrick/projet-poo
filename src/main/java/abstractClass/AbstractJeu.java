package src.main.java.abstractClass;
import java.util.LinkedList;
import src.main.java.view.VueMain;
import src.main.java.model.Joueur;
import src.main.java.model.Bot;

public abstract class AbstractJeu {
    protected AbstractPlateau plateau;
    protected AbstractTuile main;
    protected AbstractSac sac;
    protected LinkedList<AbstractJoueur> joueurs;
    protected int joueurCourant;
    protected int nbJoueurs;
    protected int nbBot;
    protected VueMain vmain;

    public AbstractJeu(AbstractPlateau plateau, AbstractSac sac, int nbJoueurs, int nbBot) {
        this.plateau=plateau;
        this.sac=sac;
        this.nbJoueurs=nbJoueurs;
        this.nbBot=nbBot;
        choisirJoueurs(nbJoueurs, nbBot);
        joueurCourant=0;
    }

    public void choisirJoueurs(int nbJoueurs, int nbBot) {
        int num = 1;
        for (int i = 0; i < nbJoueurs-nbBot; i++) {
            joueurs.add(new Joueur(0, num));
            num++;
        }
        for (int i = 0; i < nbBot; i++) {
            joueurs.add(new Bot(0, num));
            num++;
        }
    }

    public int max() {
        if (joueurs.size()==1) {
            return joueurs.get(0).numeroDeJoueur;
        }
        int scoremax = 0;
        int indexmax = 0;
        for (int i = 0; i < joueurs.size(); i++) {
            AbstractJoueur j = joueurs.get(i); 
            if (j.point>scoremax) {
                scoremax=j.point;
                indexmax = i;
            } else if(j.point==scoremax) {
                indexmax = -1;
            }
        }
        if (indexmax==-1) return 0;
        return joueurs.get(indexmax).numeroDeJoueur;
    }

    public abstract int lancerPartie();

    public abstract void partieFinie(int gagnant);

    public abstract void piocher();

    public int getJoueurCourant() {
        return joueurCourant;
    }
    public LinkedList<AbstractJoueur> getJoueurs() {
        return joueurs;
    }
    public AbstractTuile getMain() {
        return main;
    }
    public int getNbBot() {
        return nbBot;
    }
    public int getNbJoueurs() {
        return nbJoueurs;
    }
    public AbstractPlateau getPlateau() {
        return plateau;
    }
    public AbstractSac getSac() {
        return sac;
    }
    public VueMain getVmain() {
        return vmain;
    }

    public void setVmain(VueMain vmain) {
        this.vmain = vmain;
    }

    public void setPlateau(AbstractPlateau plateau) {
        this.plateau = plateau;
    }

    public void setJoueurCourant(int joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }
}
