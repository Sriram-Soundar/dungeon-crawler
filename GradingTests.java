import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradingTests {

    @Test
    public void sizeTest(){
        int size = 5;
        Board b = new Board(size);
        assertEquals(size, b.size);
    }

    @Test
    public void setEnemies() {
        int size = 9;
        Board b = new Board(size);
        b.setEnemies(5);

        //Make sure there's the right number of enemies
        int countEnemies = 0;
        for(int i=0;i<b.size;i++){
            for(int j=0;j<b.size;j++){
                if(b.board[i][j] != null && ((Piece)(b.board[i][j])).symbol == '&'){
                    countEnemies++;
                }
            }
        }
        assertEquals(5,countEnemies);

        //Make sure they are linked to the instance variable
        for(int i=0; i<b.enemies.length;i++){
            Move loc = b.enemies[i];
            assertEquals('&', ((Piece)(b.board[loc.row][loc.col])).symbol);
        }
    }

    @Test
    public void setTreasure() {
        //There is no instance variable, so count the number
        Board b = new Board(9);
        b.setTreasure(4);
        int val = -1;
        boolean allSame = true;
        int countTreasure = 0;
        for(int i=0;i<b.size;i++){
            for(int j=0;j<b.size;j++){
                if(b.board[i][j] != null && ((Piece)(b.board[i][j])).symbol == '$'){
                    Treasure t = (Treasure)b.board[i][j];
                    assertTrue(t.value >= 100);
                    assertTrue(t.value <= 1000);
                    assertEquals(0,t.value%100);
                    if(val < 0){
                        val = t.value;
                    }
                    if(t.value != val){
                        allSame = false;
                    }
                    countTreasure++;
                }
            }
        }
        assertFalse(allSame);
        assertEquals(4,countTreasure);
    }

    @Test
    public void setPlayer() {

        //Setting it at lower right hand courner and checking the instance variable
        int size=8;
        Board b = new Board(size);
        b.setPlayer(new Move(size-1,size-1));
        assertEquals('*', ((Piece)(b.board[size-1][size-1])).symbol);
        assertEquals('*', ((Piece)(b.board[b.myLoc.row][b.myLoc.col])).symbol);
    }


    @Test
    public void setExit() {
        //Count that there is exactly 1 exit on the board.  Doesn't matter where it is, since it is random
        Board b = new Board(9);
        b.setExit();
        int countExit = 0;
        for(int i=0;i<b.size;i++){
            for(int j=0;j<b.size;j++){
                if(b.board[i][j] != null && ((Piece)(b.board[i][j])).symbol == '@'){
                    countExit++;
                }
            }
        }
        assertEquals(1,countExit);
    }

    @Test
    public void testSetup(){
        int size = 5;
        Board b = new Board(size);
        b.setPlayer(new Move(size-1,size-1));
        b.setEnemies(2);
        b.setTreasure(1);
        b.setExit();

        System.out.println(b);
    }

    @Test
    public void isValidMove() {
        //Choosing size 5 to make sure it's based on size, not max
        int size = 5;
        Board b = new Board(size);
        int outLeft = -1;
        int outTop = -1;
        int outRight = size + 1;
        int outBottom = size + 1;
        int validLoc = size/2; //integer division truncates
        Move topMiddle = new Move(outTop,validLoc);
        Move topLeft = new Move(outTop, outLeft);
        Move topRight = new Move(outTop, outRight);
        Move bottomMiddle = new Move(outBottom, validLoc);
        Move bottomLeft = new Move(outBottom, outLeft);
        Move bottomRight = new Move(outBottom, outRight);
        Move middleLeft = new Move(validLoc, outLeft);
        Move middleRight = new Move(validLoc, outRight);
        Move validMid= new Move(validLoc,validLoc);
        Move validUpperLeft= new Move(0,0);
        Move validLowerRight= new Move(size-1,size-1);

        assertEquals(false, b.isValid(topMiddle));
        assertEquals(false, b.isValid(topRight));
        assertEquals(false, b.isValid(topLeft));
        assertEquals(false, b.isValid(bottomMiddle));
        assertEquals(false, b.isValid(bottomRight));
        assertEquals(false, b.isValid(bottomLeft));
        assertEquals(false, b.isValid(middleLeft));
        assertEquals(false, b.isValid(middleRight));
        assertEquals(true, b.isValid(validMid));
        assertEquals(true,b.isValid(validUpperLeft));
        assertEquals(true, b.isValid(validLowerRight));
    }
}