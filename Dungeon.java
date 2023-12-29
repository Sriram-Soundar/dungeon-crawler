import java.util.Objects;
import java.util.Scanner;
/**
 * The Dungeon class takes in user input to create the desired sized board 
 *
 * @author Sriram Soundar
 * @version 1.0
 */ 

public class Dungeon {
    private Board goodBoard;
    int totalScore = 0;
    int highScore = 0;

    int freeSpaces;
    boolean notEnoughSpace = false;

    /**
     * @init is a helper method that sets up the game
     * @param level is the level of the dungeon we are on, it increases if the player clears the level prior
     * @param size used to create the board of size: size*size.
     */
    public void init(int level, int size){
        int numEnemies = 2;
        int numTreasures = 1;
        freeSpaces = size * size;
        if(7 + level > freeSpaces){
            System.out.println("Game over...not enough space :(");
            notEnoughSpace = true;
            return;
        }
        goodBoard = new Board(size);

        Move player = new Move(size - 1, size - 1);
        goodBoard.setPlayer(player);
        goodBoard.setTreasure(5);
        goodBoard.setEnemies(level);
        goodBoard.setExit();
        System.out.println(goodBoard);
    }

    /**
     * @return
     * @play This play method is what takes in user input and allows the user to play our dungeon crawler game
     * with the keys (W,A,S,D)
     * It keeps track of the level the player is on, deaths, totals scores, high scores and prints messages
     * and prompts throughout the game.
     */
    public void play(){
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int level = 1;
        int boardSize = -1;
        while(playAgain) {
            while (boardSize < 3 || boardSize > 9) {
                System.out.println("How big would you like your board to be?");
                try {
                    boardSize = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Input is not valid please enter a number [3,9]");
                    continue;
                }

                if (boardSize >= 3 && boardSize <= 9) {
                    System.out.println("Let's Play!");
                }
            }

            init(level, boardSize);
            boolean escaped = false;

            while (!escaped) {
                String result = playerTurn();
                System.out.println(result);
                System.out.println(goodBoard);

                if (result.equals("Game over...")) {
                    int playerScore = goodBoard.getPlayerScore();
                    System.out.println("Game over...you were eaten. Score: " + playerScore);
                    System.out.println("You died on level: " + level);
                    System.out.println("Score for this level:" + goodBoard.getPlayerScore());
                    System.out.println("Total Score:" + totalScore);
                    boardSize = -1;
                    totalScore = 0;
                    level = 1;
                    break;
                }
                else if (result.equals("You escaped the Dungeon! Congrats!")) {
                    int playerScore = goodBoard.getPlayerScore();
                    totalScore += playerScore;
                    System.out.println("Level " + level + " complete!");
                    System.out.println("Score for this level:" + goodBoard.getPlayerScore());
                    System.out.println("Total Score:" + totalScore);
                    level++;
                    escaped = true;
                    freeSpaces = boardSize * boardSize;
                    break;
                }

                String enemyResult = goodBoard.moveEnemies();
                System.out.println(enemyResult);

                if (enemyResult.equals("Game over...")) {
                    int playerScore = goodBoard.getPlayerScore();
                    System.out.println("Game over...you were eaten. Score: " + playerScore);
                    System.out.println("You died on level: " + level);
                    System.out.println("Score for this level:" + goodBoard.getPlayerScore());
                    System.out.println("Total Score:" + totalScore);
                    boardSize = -1;
                    totalScore = 0;
                    level = 1;
                    break;
                }

            }

            if(totalScore > highScore){
                highScore = totalScore;
                System.out.println("Congrats!! New high score:" + highScore);
            }

            System.out.println("Type 'quit' to quit playing. Press any other key to continue...");
            String input = scanner.nextLine().toLowerCase();
            if(input.equals("quit")){
                playAgain = false;
            }
        }
        System.out.println("Thank you for playing. Have a nice day :)");
    }

    /**
     *
     * @return returns a board with the player moved based on user input. Returns prompt if move is invalid.
     */
    public String playerTurn() {
            Scanner scanner = new Scanner(System.in);
            String playerMove = "";
            while (true) {
                System.out.println("Please enter a move (W, A, S, D): ");
                playerMove = scanner.nextLine().toLowerCase();

                if (playerMove.equals("w") || playerMove.equals("a") || playerMove.equals("s") || playerMove.equals("d")) {
                    int deltaRow = 0;
                    int deltaCol = 0;
                    if (playerMove.equals("w")) {
                        deltaRow = -1;
                    } else if (playerMove.equals("a")) {
                        deltaCol = -1;
                    } else if (playerMove.equals("s")) {
                        deltaRow = 1;
                    } else if (playerMove.equals("d")) {
                        deltaCol = 1;
                    }
                    return goodBoard.movePlayer(deltaRow, deltaCol);
                } else {
                    System.out.println("Invalid move. Please enter a valid move (W, A, S, D): ");
                }
            }

    }

}


