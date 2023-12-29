
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The DungeonCrawlerTester class tests to make sure the isValid()
 * and the printing of the board are running properly.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DungeonCrawlerTester
{
    /**
     * Default constructor for test class DungeonCrawlerTester
     */
    public DungeonCrawlerTester()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * @BoardToStringTest tests boards of size 3 and 4 to make sure the board is 
     * printing correctly
     */
    @Test
    public void BoardToStringTest(){
        Board b = new Board(3);
        String actual = b.toString();
        String expect = 
            "   1 2 3 \n" +
            "   ______\n" +
            "A |_ _ _ |\n" + 
            "B |_ _ _ |\n" +
            "C |_ _ _ |\n" +
            "   ______";

        assertEquals(expect, actual);

        Board c = new Board(4);
        String actualTwo = c.toString();
        String expectTwo = 
            "   1 2 3 4 \n" + 
            "   ________\n" +
            "A |_ _ _ _ |\n" +
            "B |_ _ _ _ |\n" +
            "C |_ _ _ _ |\n" +
            "D |_ _ _ _ |\n" +
            "   ________";

        assertEquals(expectTwo, actualTwo);

    }
    /**
     * @isValidTest tests moves on the board to make sure they are in 
     * bounds of a board of size 3
     */
    @Test
    public void isValidTest(){
        Board b = new Board(3);
        Move m = new Move(3,3);
        Move n = new Move(4,4);
        Move o = new Move(1,2);
        Move p = new Move(3,4);

        assertEquals(true, b.isValid(m));
        assertEquals(false,b.isValid(n));
        assertEquals(true,b.isValid(o));
        assertEquals(false,b.isValid(p));

        Board big = new Board(5);
        Move bigMove = new Move(4,4);
        assertEquals(true,big.isValid(bigMove));
    }
    @Test
    //public void collideTest(){
    //  Piece t = new Treasure();
    //Piece e = new Enemy();
    //Piece p = new Player();

    //assertEquals("Game over...", t.collide(p));

    //}


    //public void isValidPlayerTest(){
    //  Board b = new Board(3);
    //Move player = new Move(3,3);
    //b.setPlayer(player);

    // int num = player.row;
    //int number = b.; got stuck here :(

    //}
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
