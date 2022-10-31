import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Sac s = new Sac(new LinkedList<>());
        s.remplirSac();
        s.getDominosSac().get(0).affiche();
    }
}
