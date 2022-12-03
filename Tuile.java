public abstract class Tuile {
    protected int[][] numeros;
    protected boolean estVide;

    public Tuile(int[][] numeros) {
        this.numeros = numeros;
        this.estVide = false;
    }

    public Tuile() {
        this.estVide = true;
    }
    public int[][] getNumeros() {
        return this.numeros;
    }

    abstract String affiche();
    abstract void tourneUneFois();

    public boolean isEstVide() {
        return estVide;
    }
}