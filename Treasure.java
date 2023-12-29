
/**
 * The treasure class creates the treasure symbol
 *
 * @author Sriram Soundar
 * @version 1.0
 */
public class Treasure extends Piece
{
    /**
     * @value value of our treasure symbol
     */
    int value;
    /**
     * @Treasure sets out value equal to a number [100,200,300,...,100]
     */
    public Treasure(){
        super('$');
        this.value = value;
        value = (int) (Math.random() * 1001);
        if(value <= 100){
            value = 100;
        }else if(value > 100 && value <= 200){
            value = 200;
        }else if(value > 200 && value <= 300){
            value = 300;
        }else if(value > 300 && value <= 400){
            value = 400;
        }else if(value > 400 && value <= 500){
            value = 500;
        }else if(value > 500 && value <= 600){
            value = 600;
        }else if(value > 600 && value <= 700){
            value = 700;
        }else if(value > 700 && value <= 800){
            value = 800;
        }else if(value > 800 && value <= 900){
            value = 900;
        }else{
            value = 1000;
        }
    }
}
