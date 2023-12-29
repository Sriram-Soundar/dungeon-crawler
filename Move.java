
/**
 * The move class creates the move object and prints it out
 *
 * @author Sriram Soundar
 * @version 1.0
 */
public class Move
{
    /**
     * @row the row value of a piece on the board
     * @col the column value of a piece on the board
     */
    int row;
    int col;
    /**
     * @Move creates a tuple (row,col)
     */
    public Move(int row, int col){
        this.row = row;
        this.col = col;
    }
    /**
     * @toString prints out tuple
     */
    public String toString(){
        return "(" + row + "," + col + ")";
    }
}
