
/**
 * The piece class creates a piece that is a char symbol
 * 
 * @author Sriram Soundar
 * @version 1.0
 */
public abstract class Piece
{
    /**
     * @Symbol the char value of our piece
     */
    public char symbol;
    public Piece(char symbol){
        this.symbol = symbol;
    }

    public String toString(){
     return "" + symbol;
    }
}
