
/**
 * The player class creates the player symbol
 *
 * @author Sriram Soundar
 * @version 1.0
 */
public class Player extends Piece
{
    /**
     * @Player sets out player symbol equal to a *
     */
    public int score = 0;

    public Player(){
        super('*');
    }

    public String collide(Piece object){
        if(object instanceof Treasure){
            this.score += ((Treasure) object).value;
            return "You found $" + ((Treasure) object).value + " berries!";
        }
        else if(object instanceof Enemy){
            return "Game over...";
        }
        else if(object instanceof Exit){
            return "You escaped the Dungeon! Congrats!";
        }
    return "";
    }
}
