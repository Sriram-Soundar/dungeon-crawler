
/**
 * The main class runs our game
 *
 * @author Sriram Soundar
 * @version 1.0
 */
//enemy movement errors
//do I need to ask for board size if they escape and want to continue?
// fix enemy movement errors, high score implementation?
// need help with free spaces implementation..
public class Main
{
    /**
     * @Main where we play our game using the play method
     */
    public static void main(String[] args){
        Dungeon game = new Dungeon();
        game.play();
    }
}
