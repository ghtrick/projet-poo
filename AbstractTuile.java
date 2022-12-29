public abstract class AbstractTuile {
    protected int[][] numeros;
    protected boolean estVide;

    public AbstractTuile(int[][] numeros) {
        this.numeros = numeros;
        this.estVide = false;
    }

    public AbstractTuile() {
        this.estVide = true;
    }
    public int[][] getNumeros() {
        return this.numeros;
    }

    public abstract String affiche();

    public abstract void tourneUneFois();

    public boolean isEstVide() {
        return estVide;
    }
}