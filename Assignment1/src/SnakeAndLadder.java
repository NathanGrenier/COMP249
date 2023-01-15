 // -----------------------------------------------------
 // Assignment 1
 // Question: Part 1
 // Written by: Nathan Grenier, 40250986
 // COMP 249
 // Due Date: Febuary 3, 2023
 // 
 // - In a comment, give a general explanation of what your program does. As the programming questions get 
 //   more complex, the explanations will get lengthier.
 // - Include comments in your program describing the main steps in your program.
 // - Display a welcome message which includes your name(s).
 // - Display clear prompts for users when you are expecting the user to enter data from the keyboard.
 // - All output should be displayed with clear messages and in an easy to read format.
 // - End your program with a closing message so that the user knows that the program has terminated. */
 // -----------------------------------------------------

 // Imports
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Random;

 /**
  * The SnakeAndLadder Class contains the relevant information pertaining the snake and ladder game.
  * This includes: The game board, gameStatus, current turn
  * It also handles all of the game logic
  *  
  * @author Nathan Grenier
  * @version 1.0
  * 
  */
public class SnakeAndLadder {
    // Private Variables
    private int playerCount;
    private Player[] playerList;
    private int boardRows = 10;
    private int boardColumns = 10;
    private String[][] board;
    private int[][] boardPos;
    private boolean gameStatus;     // True: Game is running, False: Game has concluded
    private int turn;
    private int[][] snakePos = {{16,6},{48,30},{64,60},{79,19},{93,68},{95,24},{97,76},{98,78}};    // Stores position of snakes on board. {Head,Tail}
    private int[][] ladderPos = {{1,38},{4,14},{9,31},{21,42},{28,84},{36,44},{51,67},{71,91},{80,100}};   // Stores position of ladders on board. {Bottom,Top}

    // Console Colors
    public static final String TEXT_RESET  = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_WHITE = "\u001B[37m";
    public static final String TEXT_RED = "\u001B[91m";
    public static final String TEXT_GREEN = "\u001B[92m";
    public static final String TEXT_YELLOW = "\u001B[93m";
    public static final String TEXT_BLUE = "\u001B[94m";

    /**
     * Paramater constructor for SnakeAndLadder class
     * 
     * @param playerCount
     */
    public SnakeAndLadder(int playerCount) {
        if (playerCount > 2) {
            // Notify user of playerCount Error
            System.out.printf("Initialization was attempted for %d member of players; however, this is only" 
                            + " expected for an extended version the game. Value will be set to 2%n", playerCount);
        } else if (playerCount < 2) {
            // Notify user of playerCount Error
            System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
            // Terminate Program 
            System.exit(1);
        }
        // Initialize playerCount to 2. For our purposes, it will always be 2
        this.playerCount = 2;
        
        // Create 2 Player objects and add them to playerList
        this.playerList = new Player[this.playerCount];
        for (int i=0; i < this.playerCount; i++) {
            this.playerList[i] = new Player(i+1); 
        }

        // Initialize board with all white spaces
        this.board = new String[this.boardRows][this.boardColumns];
        for (int row=0; row < this.board.length; row++) {
            for (int col=0; col < this.board[0].length; col++) {
                this.board[row][col] = "     ";
            }
        }
        
        /* 
        // Initialize board positions 
        //Formula: If row is even: (row * 10) + col | If row is odd: (row * 10) + (11 - col)
        this.boardPos = new int[this.boardRows][this.boardColumns];
        for (int row=0; row < this.board.length; row++) {
            for (int col=0; col < this.board[0].length; col++) {
                if (row % 2 == 0) {
                    this.boardPos[row][col] = (row * 10) + (col + 1);
                } else {
                    this.boardPos[row][col] = (row * 10) + (10 - col);
                }
            }
        }
        */

        // Set gameStatus to true
        this.gameStatus = true;
    }

    /**
     * Calculates the row and column of a number corresponding to the SnakeAndLadder game board.
     * Row zero is located at the bottom of the board
     * Ex: row-0 col-0 corresponds to the 1 position on the board
     * 
     * @param pos
     * @return Integer array containing the row and column values
     */
    private int[] calCoordinate(int pos) {
        int col;
        int row;
        // If the number is a multiple of 10, a special case is considered
        if (pos % 10 == 0) {
            row = (pos / 10) - 1;
            if (row % 2 == 0) {
                col = 9;
            } else {    
                col = 0;
            }
            int[] posArray = {row, col};
            return posArray;

        } else {    // For all other cases (i.e when the pos is not a multiple of 10)
            row = Math.floorDiv(pos, 10);
            if (row % 2 == 0) {
                col = (pos % 10) - 1;
            } else {
                col = -(pos % 10) + 10;
            }
            int[] posArray = {row, col};
            return posArray;
        }
    }

    /**
     * Adds Strings to each position on the board that either contains a snake, ladder, player or the start/end positions.
     * 
     */
    private void updateBoard() {
        // Populate board with respective icons
        // Snakes
        for (int i=0; i < this.snakePos.length; i++) {
            for (int pos: this.snakePos[i]) {
                int row = this.calCoordinate(pos)[0];
                int col = this.calCoordinate(pos)[1];
                this.board[9-row][col] = "   " + TEXT_RED + "S" + (i+1) + TEXT_RESET;   // the 9-row corrects for the fact that the this.board array's first row starts at the top instead of the bottom
            }
        }
        // Ladders
        for (int i=0; i < this.ladderPos.length; i++) {
            for (int pos: this.ladderPos[i]) {
                int row = this.calCoordinate(pos)[0];
                int col = this.calCoordinate(pos)[1];
                this.board[9-row][col] = "   " + TEXT_GREEN + "L" + (i+1) + TEXT_RESET;   // the 9-row corrects for the fact that the this.board array's first row starts at the top instead of the bottom
            }
        }

        // Players
        for (Player player: playerList) {
            // If the player has pos 0, do not display him on the board
            if (player.getPos() == 0) {
                continue;
            }
            int row = this.calCoordinate(player.getPos())[0];
            int col = this.calCoordinate(player.getPos())[1];
            this.board[9-row][col] = TEXT_YELLOW + "P" +  player.getPlayerNum() + TEXT_RESET + this.board[9-row][col].substring(2,this.board[9-row][col].length());   // Substring used to place the ladder and player on the same tile
        }

        // Start and End
        this.board[9][0] = TEXT_BLUE + "!=" + TEXT_RESET + this.board[9][0].substring(2,this.board[9][0].length());
        this.board[0][0] = TEXT_BLUE + "#=" + TEXT_RESET + this.board[0][0].substring(2,this.board[0][0].length());
    }

    /**
     * Displays the state of the game board to the console
     * 
     * @return Print a representation of the 2D array board to the console
     */
    private void displayBoard() {
        // Head
        System.out.printf("---------------------------------------------------------------------------------\n"
                        + "|                   Game Board                      Turn: %d                     |\n"
                        + "---------------------------------------------------------------------------------\n"
                        + "|                                 <-----                                        |\n", this.turn);
        // Body
        for (int row=0; row < this.board.length; row++) {
            System.out.print("|");
            for (int col=0; col < this.board[0].length; col++) {
                System.out.printf(" %s |", this.board[row][col]);
            }
            System.out.println();
        }
        
        // Tail
        System.out.println("|                                 ----->                                        |\n"
                         + "---------------------------------------------------------------------------------\n"
                         + "|" + TEXT_YELLOW + "P1: Player_1, P2: Player_2,"+ TEXT_RED + " S: Snake," + TEXT_GREEN + " L: Ladder," + TEXT_BLUE + " !=: Start, #=: End " + TEXT_RESET + "           |\n" 
                         + "---------------------------------------------------------------------------------");
    }

    /**
     * Simulates a virtual dice roll using random.
     * @return random integer value between 1 and 6 
     */
    private int flipDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;   // Return random number from 1 to 6
    }

    private void generateRollOrder() {
        int rollCount = 0;  // Keep track of how many rolls it takes to determin roll order
        // Output start message
        System.out.println("Now deciding which player will start playing:");

        // Roll the dice for each player
        for (Player player: this.playerList) {
            int roll = this.flipDice();
            player.setPreviousRoll(roll);
        }

        

        System.out.println(this.playerList);


    }

    /**
     * Updates the position of the player objects by a certain dice roll amount.
     * 
     * @param player
     */
    private void updatePosition() {
        this.turn++;    // Increment the turn timer

        // Roll for each player and update their positions
        for (Player player: this.playerList) {
            player.setPos(player.getPos() + this.flipDice());
            // Check to see if the player is on the head of a snake and update their position
            for (int[] snake: this.snakePos) {
                if (player.getPos() == snake[0]) {
                    player.setPos(snake[1]);
                }
            }
            // Check to see if player is at the bottom of a ladder and update their position
            for (int[] ladder: this.ladderPos) {
                if (player.getPos() == ladder[0]) {
                    player.setPos(ladder[1]);
                }
            }
            // Check to see if a player is on top of another player. If true, send the player that was on the tile first to position 0
            for (Player otherPlayer: this.playerList) {
                // Skip the player that is moving
                if (otherPlayer == player) {
                    continue;
                } else {
                    if (player.getPos() == otherPlayer.getPos()) {
                        otherPlayer.setPos(0);
                    }
                }    
            }
            
        }

    }

    /**
     * Initiates the core engine of the game.
     * Allows the players to start playing the game
     */
    public void play() {
        do {
            this.updateBoard();
            this.displayBoard();
            this.generateRollOrder();
            this.gameStatus = false;
        } while (this.gameStatus);
    }

}
