import java.util.Random;
import java.util.LinkedList;

public class Sac {

    Random rand = new Random();
    private LinkedList<Domino> dominosSac;

    public Sac(LinkedList<Domino> dominosSac) {
        this.dominosSac = dominosSac;
    }

    public void remplirSac() {
        LinkedList<Integer> l = new LinkedList<Integer>();
        for (int i = 0; i < 667; i++) {
            String s = String.valueOf(i);
            boolean a = true;
            for (int j = 0; j < s.length(); j++) {
                if(Integer.parseInt(String.valueOf(s.charAt(j)))>6) {
                    a = false;
                }
            }
            if(a) l.add(i);
        }
        for (int i = 345; i < 350; i++) {
            l.add(777);
        }
        for (int i = 0; i < 87; i++) {
            int h = rand.nextInt(l.size());
            int top=l.get(h);
            l.remove(h);
            int d = rand.nextInt(l.size());
            int right=l.get(d);
            l.remove(d);
            int b = rand.nextInt(l.size());
            int bottom=l.get(b);
            l.remove(b);
            int g = rand.nextInt(l.size());
            int left=l.get(g);
            l.remove(g);
            int[] tab = {top,right,bottom,left};
            int[][] numeros = new int[4][3];
            int cpt = 0;
            for (int j: tab) {
                int[] t;
                String s = String.valueOf(j);
                if (j<10) {
                    t = new int[]{0, 0, Integer.parseInt(String.valueOf(s.charAt(0)))};
                }
                else if (j<100) {
                    t = new int[]{0,Integer.parseInt(String.valueOf(s.charAt(0))),Integer.parseInt(String.valueOf(s.charAt(1)))};
                }
                else {
                    t = new int[]{Integer.parseInt(String.valueOf(s.charAt(0))),Integer.parseInt(String.valueOf(s.charAt(1))),Integer.parseInt(String.valueOf(s.charAt(2)))};
                }
                numeros[cpt] = t;
                cpt++;
            }
            Domino dom = new Domino(numeros);
            this.dominosSac.add(dom);
        }
    }

    public LinkedList<Domino> getDominosSac() {
        return dominosSac;
    }

    public void setDominosSac(LinkedList<Domino> dominosSac) {
        this.dominosSac = dominosSac;
    }
}
