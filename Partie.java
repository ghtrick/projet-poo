import java.util.Arrays;
import java.util.LinkedList;

public class Partie {

    private LinkedList<LinkedList<Domino>> plateau;
    private Sac s;
    private Joueur joueur1;
    private Joueur joueur2;

    public Partie() {
        LinkedList<Domino> d = new LinkedList<>();
        this.plateau=new LinkedList<>();
        plateau.add(d);
        this.s = new Sac(new LinkedList<>());
        s.remplirSac();
        plateau.get(0).add(s.getDominosSac().get(0));
        LinkedList<Domino> s1 = s.getDominosSac();
        s1.removeFirst();
        s.setDominosSac(s1);
        this.joueur1=new Joueur(new LinkedList<>(), 0);
        this.joueur2=new Joueur(new LinkedList<>(), 0);
        LinkedList<Domino> mainJ1 = new LinkedList<>();
        LinkedList<Domino> mainJ2 = new LinkedList<>();
        for (int j = 0; j < 7; j++) {
            mainJ1.add(s.getDominosSac().get(0));
            LinkedList<Domino> s2 = s.getDominosSac();
            s1.removeFirst();
            s.setDominosSac(s2);
            mainJ2.add(s.getDominosSac().get(0));
            LinkedList<Domino> s3 = s.getDominosSac();
            s1.removeFirst();
            s.setDominosSac(s3);
        }
        this.joueur1.setMain(mainJ1);
        this.joueur2.setMain(mainJ2);
    }

    public static String afficheDominoListe(LinkedList<Domino> l) {
        String res = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < l.size(); j++) {
                switch (i) {
                    case 0:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += " "+l.get(j).getNumeros()[0][0] + "" + l.get(j).getNumeros()[0][1] + "" + l.get(j).getNumeros()[0][2]+ "  ";
                        break;
                    case 1:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][0]+"   "+l.get(j).getNumeros()[1][0]+ " ";
                        break;
                    case 2:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][1]+"   "+ l.get(j).getNumeros()[1][1] + " ";
                        break;
                    case 3:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += l.get(j).getNumeros()[3][2]+"   "+l.get(j).getNumeros()[1][2] + " ";
                        break;
                    default:
                        if(l.get(j).isEstVide()) res+="      ";
                        else res += " "+l.get(j).getNumeros()[2][0] + "" + l.get(j).getNumeros()[2][1] +""+ l.get(j).getNumeros()[2][2] + "  ";
                        break;
                }
            }
            res+="\n";
        }
        return res;
    }

    public String affichePlateau() {
        String res = "";
        for (int i = 0; i < plateau.size(); i++) {
            res+=afficheDominoListe(plateau.get(i));
        }
        return res;
    }

    public void ajoutDomino(Domino d, int x, int y) {
        LinkedList<Boolean> bool = new LinkedList<>();
        boolean numeroCorrect = true;
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x).getNumeros()[3], d.getNumeros()[1]));
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y+1).get(x).getNumeros()[0], d.getNumeros()[2]));
        } catch (Exception e) {}
        try {
            bool.add(Arrays.equals(plateau.get(y).get(x).getNumeros()[1], d.getNumeros()[3]));
        } catch (Exception e) {}
        try {
           bool.add(Arrays.equals(plateau.get(y-1).get(x).getNumeros()[2], d.getNumeros()[0])); 
        } catch (Exception e) {}
        for(int i=0; i<bool.size(); i++) {
            if(!bool.get(i)) {
                numeroCorrect = false;
            }
        }
        if(numeroCorrect) {
            if(x==-1 || x==plateau.get(0).size()) {
                if(x==-1) x=0;
                for(int i=0; i<plateau.size(); i++) {
                    if(i!=y) plateau.get(i).add(x, new Domino());
                    else plateau.get(i).add(x, d);
                } 
            } else if (y==-1 || y==plateau.size()) {
                if (y==-1) {
                    y=0;
                    plateau.add(y, new LinkedList<Domino>());            
                    for(int i=0; i<plateau.get(y+1).size(); i++) {
                        if(i!=x) plateau.get(y).add(i, new Domino());
                       else { 
                            plateau.get(y).add(i, d);
                        }
                    } 
                } else {
                    plateau.add(y, new LinkedList<Domino>());             
                    for(int i=0; i<plateau.get(y-1).size(); i++) {
                        if(i!=x) plateau.get(y).add(i, new Domino());
                        else {
                            plateau.get(y).add(i, d);
                        }
                    } 
                }
            } else {
                if(plateau.get(y).get(x).isEstVide()) {
                    plateau.get(y).set(x, d);
                }
            }
        }
    }

	public LinkedList<LinkedList<Domino>> getPlateau() {
		return plateau;
	}

    public void setPlateau(LinkedList<LinkedList<Domino>> a) {
        plateau=a;
    }
}
