public class CarteCarcassonne extends AbstractTuile {
    protected int index;

    public CarteCarcassonne(int index) {
        super(null);
        this.index=index;
        switch (index) {
            case 1:
                numeros = new int[][]{{0,0,0},{0,0,0},{0,1,0},{0,1,0}};
                break;
        
            case 2:
                numeros = new int[][]{{3,2,3},{0,1,0},{0,1,0},{0,0,0}};
                break;

            case 3:
                numeros = new int[][]{{2,2,3},{0,1,0},{0,1,0},{2,2,3}};
                break;

            case 4:
                numeros = new int[][]{{2,2,2},{2,2,3},{0,1,0},{2,2,3}};
                break;

            case 5:
                numeros = new int[][]{{2,2,2},{2,2,3},{0,0,0},{2,2,3}};
                break;

            case 6:
                numeros = new int[][]{{2,2,3},{0,1,0},{0,1,0},{2,2,3}};
                break;

            case 7:
                numeros = new int[][]{{3,2,3},{0,1,0},{0,1,0},{0,1,0}};
                break;

            case 8:
                numeros = new int[][]{{0,1,0},{0,0,0},{0,1,0},{0,0,0}};
                break;

            case 9:
                numeros = new int[][]{{0,0,0},{0,1,0},{0,1,0},{0,1,0}};
                break;

            case 10:
                numeros = new int[][]{{3,2,3},{0,0,0},{0,1,0},{0,1,0}};
                break;

            case 11:
                numeros = new int[][]{{3,2,3},{0,1,0},{0,0,0},{0,1,0}};
                break;

            case 12:
                numeros = new int[][]{{0,0,0},{3,2,3},{0,0,0},{3,2,3}};
                break;
            
            case 13:
                numeros = new int[][]{{2,2,3},{0,0,0},{3,0,0},{2,2,3}};
                break;
        
            case 14:
                numeros = new int[][]{{0,0,0},{0,0,0},{0,1,0},{0,0,0}};
                break;

            case 15:
                numeros = new int[][]{{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
                break;

            case 16:
                numeros = new int[][]{{2,2,2},{2,2,3},{0,0,0},{2,2,3}};
                break;

            case 17:
                numeros = new int[][]{{3,2,3},{3,2,3},{0,0,0},{0,0,0}};
                break;

            case 18:
                numeros = new int[][]{{3,2,3},{0,0,0},{0,0,0},{0,0,0}};
                break;

            case 19:
                numeros = new int[][]{{0,0,0},{3,2,3},{0,0,0},{3,2,3}};
                break;

            case 20:
                numeros = new int[][]{{2,2,2},{2,2,3},{0,1,0},{2,2,3}};
                break;

            case 21:
                numeros = new int[][]{{2,2,2},{2,2,2},{2,2,2},{2,2,2}};
                break;

            case 22:
                numeros = new int[][]{{0,1,0},{0,1,0},{0,1,0},{0,1,0}};
                break;

            case 23:
                numeros = new int[][]{{2,2,3},{0,0,0},{0,0,0},{2,2,3}};
                break;

            case 24:
                numeros = new int[][]{{0,0,0},{3,2,3},{0,0,0},{3,2,3}};
                break;    
        }
    }
}
