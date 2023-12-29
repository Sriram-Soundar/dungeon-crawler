import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Modifier;



public class MovementGradingTests {


    @Test
    void abstractPieceTest() throws Exception {
        Class<Piece> clazz = Piece.class;
        assertEquals(true, Modifier.isAbstract(clazz.getModifiers()));
    }

    @Test
    public void playerTest(){
        Player p = new Player();
        assertEquals(0,p.score);
    }

    @Test
    public void playerCollideTreasureTest(){
        Player p = new Player();
        Treasure t = new Treasure();
        String msg = p.collide((Piece)t);
        System.out.println(msg);
        assertEquals(p.score,t.value);
        assertEquals(true, msg.length() > 0);
    }

    @Test
    public void playerCollideExitTest(){
        Player p = new Player();
        Exit t = new Exit();
        String msg = p.collide((Piece)t);
        System.out.println(msg);
        assertEquals(true, msg.length() > 0);
    }

    @Test
    public void playerCollideEnemyTest(){
        Player p = new Player();
        Enemy t = new Enemy();
        String msg = p.collide((Piece)t);
        System.out.println(msg);
        assertEquals(true, msg.length() > 0);
    }

    @Test
    public void enemyCollidePlayerTest(){
        Enemy e = new Enemy();
        Player p = new Player();
        String msg = e.collide((Piece)p);
        assertEquals(true, msg.length() > 0);

    }

    @Test
    public void playerMoveTreasureTest(){
        int size = 5;
        Board b = new Board(size);
        b.setPlayer(new Move(size-1,size-1));
        Treasure t = new Treasure();
        b.board[size-2][size-1] = t;
        b.movePlayer(-1,0);
        Piece p = (b.board[size-2][size-1]);
        assertEquals(true, p instanceof Player);
        assertEquals(t.value, ((Player)p).score);
    }

    @Test
    public void enemyMoveTest(){
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        int other = 0;

        for(int i=0;i<100;i++) {
            Board b = new Board(3);
            b.setPlayer(new Move(2,2));
            Move src = new Move(1, 1);
            Move[] oneEnemy = {src};
            b.enemies = oneEnemy;
            b.board[src.row][src.col] = new Enemy();
            Move dest = b.getValidEnemyMove(src);
            if (isUp(src, dest)) up++;
            else if (isDown(src, dest)) down++;
            else if (isRight(src, dest)) right++;
            else if (isLeft(src, dest)) left++;
            else other++;
        }

        //Not trapped no other collisions.  May fail for those doing extra credit.
        assertEquals(true, up > 0);
        assertEquals(true, down > 0);
        assertEquals(true, left > 0);
        assertEquals(true, right > 0);
        assertEquals(true, other == 0);
    }

    @Test
    public void enemyOccupiedTest(){
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        int other = 0;

        for(int i=0;i<100;i++) {
            Board b = new Board(3);
            Move src = new Move(1, 1);
            b.setPlayer(new Move(src.row+1,src.col));//down
            Move[] oneEnemy = {src};
            b.enemies = oneEnemy;
            b.board[src.row][src.col] = new Enemy();//src
            b.board[src.row-1][src.col] = new Treasure();//up
            b.board[src.row][src.col-1] = new Exit();//left
            b.board[src.row][src.col+1] = new Enemy();//right

            Move dest = b.getValidEnemyMove(src);
            if (isUp(src, dest)) up++;
            else if (isDown(src, dest)) down++;
            else if (isRight(src, dest)) right++;
            else if (isLeft(src, dest)) left++;
            else other++;


        }

        assertEquals(true, up > 0, "should be able to eat treasure");
        assertEquals(true, down > 0, "Should be able to eat player");
        assertEquals(true, left == 0, "can't eat exit");
        assertEquals(true, right == 0, "can't eat enemy");
        assertEquals(true, other == 0, "not trapped");
    }

    private boolean isUp(Move src, Move dest){
        return (src.col == dest.col && src.row-1 == dest.row);
    }
    private boolean isDown(Move src, Move dest){
        return (src.col == dest.col && src.row+1 == dest.row);
    }

    private boolean isLeft(Move src, Move dest){
        return (src.col-1 == dest.col && src.row == dest.row);
    }

    private boolean isRight(Move src, Move dest){
        return (src.col+1 == dest.col && src.row == dest.row);
    }

    @Test
    public void enemyTrappedMoveTest(){
        Board b = new Board(3);
        for(int i =0;i<b.size;i++){
            for(int j=0;j<b.size;j++){
                b.board[i][j] = new Enemy();
            }
        }
        b.board[1][1] = new Exit();

        for(int i =0;i<b.size;i++){
            for(int j=0;j<b.size;j++){
                if(!(i == 1 && j ==1)) {
                    Move m = b.getValidEnemyMove(new Move(i, j));
                    assertEquals(i, m.row);
                    assertEquals(j, m.col);
                }
            }
        }
    }
}