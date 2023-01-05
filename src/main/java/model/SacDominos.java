package src.main.java.model;

import java.util.LinkedList;


import src.main.java.abstractClass.*;

public class SacDominos extends AbstractSac{

    public SacDominos() {
        super();
        remplirSac();
    }

    //Cette fonction permet de trier les nombres contenant un chiffre compris de 4 à 9 parmi les nombres allant de 0 à 333
    public void triNombres(LinkedList<Integer> l) {
        for(int k = 0; k<8; k++) {
            for (int i = 0; i < 333; i++) {
            String s = String.valueOf(i);
            boolean a = true;
                for (int j = 0; j < s.length(); j++) {
                    if(Integer.parseInt(String.valueOf(s.charAt(j)))>3) {
                        a = false;
                    }
                }
                if(a) l.add(i);
            }
        }
    }

    public void randomParmiLesNombresTries(LinkedList<Integer> l) {
        for (int i = 0; i < 124; i++) {
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
            Chiffre[][] numeros = new Chiffre[4][3];
            int cpt = 0;
            for (int j: tab) {
                Chiffre[] t;
                String s = String.valueOf(j);
                if (j<10) {
                    t = new Chiffre[]{new Chiffre(0), new Chiffre(0), new Chiffre(Integer.parseInt(String.valueOf(s.charAt(0))))};
                }
                else if (j<100) {
                    t = new Chiffre[]{new Chiffre(0),new Chiffre(Integer.parseInt(String.valueOf(s.charAt(0)))),new Chiffre(Integer.parseInt(String.valueOf(s.charAt(1))))};
                }
                else {
                    t = new Chiffre[]{new Chiffre(Integer.parseInt(String.valueOf(s.charAt(0)))),new Chiffre(Integer.parseInt(String.valueOf(s.charAt(1)))),new Chiffre(Integer.parseInt(String.valueOf(s.charAt(2))))};
                }
                numeros[cpt] = t;
                cpt++;
            }
            Domino dom = new Domino(numeros);
            this.tuilesDansLeSac.add(dom);
        }
    }

    @Override
    public void remplirSac() {
        LinkedList<Integer> l = new LinkedList<Integer>();
        triNombres(l);
        randomParmiLesNombresTries(l);
    }
}
