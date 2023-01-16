 // -----------------------------------------------------
 // Assignment 1
 // Question: Part 2
 // Written by: Nathan Grenier, 40250986
 // COMP 249

 // Due Date: Febuary 3, 2023
 // -----------------------------------------------------

 // Imports
 import java.util.Scanner;

 /**
  * The PlayLadderAndSnake Class runs the game.
  * A user will be asked to enter the number of players they want and the game will comence
  * 
  * @author Nathan Grenier
  * @version 1.0
  * 
  */

public class PlayLadderAndSnake {
    public static void main(String[] args) {
        int playerCount = 0;
        // Display welcome message and prompt user for number of players
        System.out.print("Welcome to Nathan Grenier's Snake and Ladders game. To start playing, please enter the number of player you want (int): ");
        // Create scannar object
        Scanner in = new Scanner(System.in);
        
        // Read and validate user input
        do {
            if (in.hasNextInt()) {
                playerCount = in.nextInt();
            } else {
                System.out.print("The value you entered was not valid. Please enter an integer corresponding to the number of player you want: ");
                in.nextLine();
            }
        } while(playerCount == 0);

        // Create SnakeAndLadder game with the specified playerCount
        LadderAndSnake game = new LadderAndSnake(playerCount);
        
        // Play the game
        game.play();

        // Display to user that the program has ended
        System.out.print("Thank you for playing Nathan Grenier's SnakesAndLadders!")
        ;
    }
}
