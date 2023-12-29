import java.util.LinkedList;
import java.util.List;

/**
 * Class board contains the methods for creating and printing a board,
 * making sure moves are in bounds, and adding players, enemies and treasures
 * 
 *
 * @author Sriram Soundar
 * @version 1.0
 */

public class Board
{
    /**
     * @size Size of the board, size x size
     * @Piece The board made up of piece's (symbols)
     * @myLoc The tuple that is the row and column the player is located at 
     * @Move An array that stores the locations(tuples) of enemies.
     */
    public int size;
    public Piece [][] board;
    Move myLoc;
    public Move [] enemies; 
    
    public Board(int size){
        this.size = size;
        if(size >= 1 && size <= 9){
            this.board = new Piece[size][size];
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size ; j++){
                    board[i][j]= null;
                }
            }
        }else{
            this.board = null;
            this.size = -1;
        }
    }
    /**
     * @isValid checks if the move is within the bounds of the board
     */
    public boolean isValid(Move m){
        if(m.row < 0 || m.row >= size || m.col < 0 || m.col >= size){
            return false;
        }else{
            return true;
        }
    }
    /**
     * @setPlayer sets the player symbol(*) at the bottom right of the board
     */
    public void setPlayer(Move loc){
        this.myLoc = loc;
        if(isValid(loc) == false){
            System.out.println("Move is out of bounds, pwease try again.");        
        }else{
            Piece player = new Player();
            board[loc.row][loc.col] = player; 
        }
    }

    /**
     *
     * @param deltaRow is the number corresponding to amount of rows it moves along (-1, 0, 1)
     * @param deltaCol is the number corresponding to the amount of columns it moves across (-1, 0, 1)
     * @return returns a string if it collides with a treasure, enemy, or exit.
     */
    public String movePlayer(int deltaRow, int deltaCol){
        Move playerMove = new Move(myLoc.row + deltaRow, myLoc.col + deltaCol);
        String message = "";
        if(isValid(playerMove) == false){
            return "Your move is invalid...please try again";
        }
        else{
            Player sri = (Player) board[myLoc.row][myLoc.col];
            if(board[playerMove.row][playerMove.col] != null){
                message = sri.collide(board[playerMove.row][playerMove.col]);
            }
                board [myLoc.row][myLoc.col] = null;
                board[playerMove.row][playerMove.col] = sri;
                this.myLoc = playerMove;
        }
        return message;
    }

    /**
     *
     * @return returns the players score.
     */
    public int getPlayerScore(){
        Player player = (Player) board[myLoc.row][myLoc.col];
        return player.score;
    }
    /**
     * @setEnemies adds int num number of enemies to the board at randomiezed locations 
     */
    public void setEnemies(int num){
        this.enemies = new Move[num];
        for(int i = 0; i < enemies.length; i++){
            int randomRow = (int) (Math.random() * (size));
            int randomCol = (int) (Math.random() * (size));
            while(board[randomRow][randomCol] != null){
                randomRow = (int) (Math.random() * (size));
                randomCol = (int) (Math.random() * (size));
            }
            Move enemie = new Move(randomRow, randomCol);
            enemies[i] = enemie;
            Piece enemy = new Enemy();
            board[randomRow][randomCol] = enemy; 
        }
    }

    /**
     *
     * @param m takes in a enemy moves and checks to see if it is out of bounds or collides with another enemy or exit
     * @return returns true or false contingent on whether the move is valid or not.
     */
    public boolean isValidEnemyMove(Move m){
        if(m.row < 0 || m.row >= size || m.col < 0 || m.col >= size){
            return false;
        }else if(board[m.row][m.col] == null){
            return true;
        }else if(board[m.row][m.col] instanceof Enemy || board[m.row][m.col] instanceof Exit){
            return false;
        }else if(board[m.row][m.col] instanceof Treasure || board[m.row][m.col] instanceof Player){
            return true;
        }
        return false;
    }

    /**
     *
     * @param  move is an enemy move
     * @return returns a random valid enemy move from a linked list of valid enemy moves that was created in the method
     */
    public Move getValidEnemyMove(Move move) {
        List<Move> enemyMoves = new LinkedList<>();
        Move up = new Move(move.row -1, move.col);
        Move down = new Move(move.row + 1, move.col);
        Move left = new Move(move.row, move.col-1);
        Move right = new Move(move.row, move.col+1);

        enemyMoves.add(up);
        enemyMoves.add(down);
        enemyMoves.add(left);
        enemyMoves.add(right);
        int i = 0;
        while(i < enemyMoves.size()){
            Move enemyMove = enemyMoves.get(i);
            if(!(isValidEnemyMove(enemyMove))){
                enemyMoves.remove(i);
            }else{
                i++;
            }
        }
        if(enemyMoves.size() == 0){
            return move;
        }
        int random = (int) (Math.random() * enemyMoves.size());
        return enemyMoves.get(random);
    }

    /**
     *
     * @return This method moves the enemy left right or up and down randomly and returns a game over string if the enemy collides with a player.
     */
    public String moveEnemies(){
        boolean playerHit = false;
        for(int i = 0; i < enemies.length; i++){
            Move enemyLoc = enemies[i];
            Move enemyMove = getValidEnemyMove(enemyLoc);
            if(isValidEnemyMove(enemyMove) == true && !(board[enemyMove.row][enemyMove.col] instanceof Player)){
                board[enemyLoc.row][enemyLoc.col] = null;
                board [enemyMove.row][enemyMove.col] = new Enemy();
                enemies[i] = new Move(enemyMove.row, enemyMove.col);
            } else if (isValidEnemyMove(enemyMove) == false) {
                board[enemyLoc.row][enemyLoc.col] = new Enemy();
                enemies[i] = new Move(enemyLoc.row, enemyLoc.col);
            } else if(board[enemyMove.row][enemyMove.col] instanceof Player){
              playerHit = true;
            }
        }
        if(playerHit == true){
            return "Game over...";
        }else{
            return "";
        }
    }

    /**
     * @setTreasure Randomly plaves int num number of treasure tokens on the board with values 
     * ranging from [100,200,300,...,1000].
     */
    public void setTreasure(int num){
        for(int i = 0; i < num; i++){
            int randomRow = (int) (Math.random() * (size));
            int randomCol = (int) (Math.random() * (size));
            while(board[randomRow][randomCol] != null){
                randomRow = (int) (Math.random() * (size));
                randomCol = (int) (Math.random() * (size));
            }
            Piece treasure = new Treasure();
            board[randomRow][randomCol] = treasure;
        }
    }
    /**
     * @setExit randomly places an exit symbol (@) on the board in an unoccupiod spot 
     * on the board
     */
    public void setExit(){
        int randomRow = (int) (Math.random() * (size));
        int randomCol = (int) (Math.random() * (size));
        while(board[randomRow][randomCol] != null){
            randomRow = (int) (Math.random() * (size));
            randomCol = (int) (Math.random() * (size));
        }
        Piece exit = new Exit();
        board[randomRow][randomCol] = exit;
    }
    /**
     * @toString prints out a board
     */
    public String toString(){
        String pboard = "   ";
        for(int i = 1; i <= this.size; i++){
            pboard += i + " ";
        }
        pboard += "\n   ";
        for(int i = 0; i < this.size; i++){
            pboard += "__";
        }
        pboard += "\n";
        char rowChar = 'A';
        for(int i = 0; i < this.size; i++){
            pboard += (char)('A' + i);
            pboard += " |";
            for(int j = 0; j < this.size; j++){
                if(this.board[i][j] == null){
                    pboard += "_" + " ";
                }else{
                    Piece p = this.board[i][j];
                    pboard += p.symbol + " ";
                }
            }
            pboard += "|";
            pboard += "\n";
        }
        pboard += "   ";
        for(int i = 0; i<this.size; i++){
            pboard += "__";
        }
        return pboard;
    }
}
