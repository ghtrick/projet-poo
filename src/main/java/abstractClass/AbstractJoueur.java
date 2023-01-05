package src.main.java.abstractClass;
public abstract class AbstractJoueur {
    protected int point;
    protected int numeroDeJoueur;

    public AbstractJoueur(int point, int numeroDeJoueur) {
        this.point=point;
        this.numeroDeJoueur=numeroDeJoueur;
    }

    public int getNumeroDeJoueur() {
        return numeroDeJoueur;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
