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
 * <p>
 * The PlayLadderAndSnake Class runs the game.
 * </p>
 * A user will be asked to enter the number of players they want and the game
 * will comence.
 * 
 * @author Nathan Grenier
 * @version 1.0
 * 
 */
public class PlayLadderAndSnake {
    public static void main(String[] args) {
        // Display welcome message and prompt user for number of players
        System.out.print(
                "Welcome to Nathan Grenier's Snake and Ladders game. To start playing, please enter the number of player you want (int): ");

        // Ask the user for the player count
        int playerCount = getPlayerCount();

        // Create SnakeAndLadder game with the specified playerCount
        LadderAndSnake game = new LadderAndSnake(playerCount);

        // Play the game
        game.play();

        // Display to user that the program has ended
        System.out.print("Thank you for playing Nathan Grenier's SnakesAndLadders!");
    }

    /**
     * Prompts the user to enter the number of players they want in the game of
     * snakes and ladders.
     * 
     * @return number of players
     */
    private static int getPlayerCount() {
        int playerCount = 0;
        // Create scannar object
        Scanner in = new Scanner(System.in);

        // Read and validate user input
        do {
            if (in.hasNextInt()) {
                playerCount = in.nextInt();
            } else {
                System.out.print(
                        "The value you entered was not valid. Please enter an integer corresponding to the number of player you want: ");
                in.nextLine();
            }
        } while (playerCount == 0);

        // Check if the entered playerCount is valid or not
        if (playerCount > 2) {
            // Notify user of playerCount Error
            System.out.printf(
                    "Initialization was attempted for %d number of players; however, this is only expected for an extended version of the game. Value will be set to 2%n",
                    playerCount);
        } else if (playerCount < 2) {
            // Notify user of playerCount Error
            System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
            // Terminate Program
            System.exit(1);
        }

        // We are harcoding the playerCount to always be 2 as per instruction
        return 2;
    }
}
