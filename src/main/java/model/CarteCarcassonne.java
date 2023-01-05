package src.main.java.model;

import src.main.java.abstractClass.*;
import java.util.LinkedList;

public class CarteCarcassonne extends AbstractTuile {
    protected int index;

    public CarteCarcassonne() {
        super(null);
    }

    public CarteCarcassonne(int index) {
        super(null);
        this.index=index;
        nbFoisTourne=0;
        switch (index) {
            case 1:
                numeros = new AbstractElement[][]{{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()}};
                break;
        
            case 2:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()}};
                break;

            case 3:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 4:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Ville(),new Ville(),new Ville()},{new Herbe(),new Chemin(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 5:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 6:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 7:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()}};
                break;

            case 8:
                numeros = new AbstractElement[][]{{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()}};
                break;

            case 9:
                numeros = new AbstractElement[][]{{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()}};
                break;

            case 10:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()}};
                break;

            case 11:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()}};
                break;

            case 12:
                numeros = new AbstractElement[][]{{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;
            
            case 13:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;
        
            case 14:
                numeros = new AbstractElement[][]{{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()}};
                break;

            case 15:
                numeros = new AbstractElement[][]{{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()}};
                break;

            case 16:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 17:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()}};
                break;

            case 18:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()}};
                break;

            case 19:
                numeros = new AbstractElement[][]{{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 20:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Ville(),new Ville(),new Ville()},{new Herbe(),new Chemin(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 21:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Ville(),new Ville(),new Ville()},{new Ville(),new Ville(),new Ville()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 22:
                numeros = new AbstractElement[][]{{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()},{new Herbe(),new Chemin(),new Herbe()}};
                break;

            case 23:
                numeros = new AbstractElement[][]{{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;

            case 24:
                numeros = new AbstractElement[][]{{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()},{new Herbe(),new Herbe(),new Herbe()},{new Ville(),new Ville(),new Ville()}};
                break;    
        }
    }
}
