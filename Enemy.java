
/**
 * The enemy class creates the enemy symbol 
 *
 * @author Sriram Soundar
 * @version 1.0
 */
public class Enemy extends Piece
{
    /**
     * @Enemy sets out Enemy symbol equal to a &
     */
    public Enemy(){
    super('&');
    }

    public String collide(Piece object){
        if (object instanceof Player) {
            return "Game over...";
        }else{
            return "";
        }

    }
}
