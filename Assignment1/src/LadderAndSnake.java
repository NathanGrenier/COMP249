// -----------------------------------------------------
// Assignment 1
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 3, 2023
// 
// This program creates a game of snakes and ladders. It perform the calculations needed to keep track of each player and to display those results to the console.
// -----------------------------------------------------

// Imports
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * <p>
 * The LadderAndSnake Class contains the relevant information pertaining the
 * snake and ladder game.
 * </p>
 * It also handles all of the game logic.
 * 
 * @author Nathan Grenier
 * @version 1.0
 * 
 */
public class LadderAndSnake {
    // Private Variables
    private int playerCount;
    private Player[] playerList;
    private Player[] orderedPlayers;
    private int rollCount = 0;
    private int boardRows = 10;
    private int boardColumns = 10;
    private String[][] board;
    private boolean gameStatus; // True: Game is running, False: Game has concluded
    private int turn;
    private int[][] snakePos = { { 16, 6 }, { 48, 30 }, { 64, 60 }, { 79, 19 }, { 93, 68 }, { 95, 24 }, { 97, 76 },
            { 98, 78 } }; // Stores position of snakes on board. {Head,Tail}
    private int[][] ladderPos = { { 1, 38 }, { 4, 14 }, { 9, 31 }, { 21, 42 }, { 28, 84 }, { 36, 44 }, { 51, 67 },
            { 71, 91 }, { 80, 100 } }; // Stores position of ladders on board. {Bottom,Top}

    // Console Colors
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_WHITE = "\u001B[37m";
    public static final String TEXT_RED = "\u001B[91m";
    public static final String TEXT_GREEN = "\u001B[92m";
    public static final String TEXT_YELLOW = "\u001B[93m";
    public static final String TEXT_BLUE = "\u001B[94m";

    /**
     * Paramater constructor for SnakeAndLadder class
     * 
     * @param playerCount Amount of players to play the game.
     */
    public LadderAndSnake(int playerCount) {
        // Initialize playerCount to 2. For our purposes, it will always be 2
        this.playerCount = playerCount;

        // Create list for ordered players. Will be used later to sort the player and
        // determine the playing order
        this.orderedPlayers = new Player[this.playerCount];

        // Create 2 Player objects and add them to this.playerList
        this.playerList = new Player[this.playerCount];
        for (int i = 0; i < this.playerCount; i++) {
            this.playerList[i] = new Player(i + 1);
        }

        // Initialize board with all white spaces
        this.board = new String[this.boardRows][this.boardColumns];
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                this.board[row][col] = "     ";
            }
        }

        // Set gameStatus to true
        this.gameStatus = true;
    }

    /**
     * <p>
     * Calculates the row and column of a number corresponding to the SnakeAndLadder
     * game board.
     * </p>
     * <p>
     * Row zero is located at the bottom of the board
     * </p>
     * <p>
     * Ex: row-0 col-0 corresponds to the 1 position on the board
     * </p>
     * 
     * @param pos Position
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
            int[] posArray = { row, col };
            return posArray;

        } else { // For all other cases (i.e when the pos is not a multiple of 10)
            row = Math.floorDiv(pos, 10);
            if (row % 2 == 0) {
                col = (pos % 10) - 1;
            } else {
                col = -(pos % 10) + 10;
            }
            int[] posArray = { row, col };
            return posArray;
        }
    }

    /**
     * Adds Strings to each position on the board that either contains a snake,
     * ladder, player or the start/end positions.
     * 
     */
    private void updateBoard() {
        // Clear the board
        this.board = new String[this.boardRows][this.boardColumns];
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                this.board[row][col] = "     ";
            }
        }

        // Populate board with respective icons
        // Snakes
        for (int i = 0; i < this.snakePos.length; i++) {
            for (int pos : this.snakePos[i]) {
                int row = this.calCoordinate(pos)[0];
                int col = this.calCoordinate(pos)[1];
                this.board[9 - row][col] = "   " + TEXT_RED + "S" + (i + 1) + TEXT_RESET; // the 9-row corrects for the
                                                                                          // fact that the this.board
                                                                                          // array's first row starts at
                                                                                          // the top instead of the
                                                                                          // bottom
            }
        }
        // Ladders
        for (int i = 0; i < this.ladderPos.length; i++) {
            for (int pos : this.ladderPos[i]) {
                int row = this.calCoordinate(pos)[0];
                int col = this.calCoordinate(pos)[1];
                this.board[9 - row][col] = "   " + TEXT_GREEN + "L" + (i + 1) + TEXT_RESET; // the 9-row corrects for
                                                                                            // the fact that the
                                                                                            // this.board array's first
                                                                                            // row starts at the top
                                                                                            // instead of the bottom
            }
        }

        // Players
        for (Player player : playerList) {
            // If the player has pos 0, do not display him on the board
            if (player.getPos() == 0) {
                continue;
            }
            int row = this.calCoordinate(player.getPos())[0];
            int col = this.calCoordinate(player.getPos())[1];
            this.board[9 - row][col] = TEXT_YELLOW + "P" + player.getPlayerNum() + TEXT_RESET
                    + this.board[9 - row][col].substring(2, this.board[9 - row][col].length()); // Substring used to
                                                                                                // place the ladder and
                                                                                                // player on the same
                                                                                                // tile
        }

        // Start and End
        this.board[9][0] = TEXT_BLUE + "!=" + TEXT_RESET + this.board[9][0].substring(2, this.board[9][0].length());
        // Check to see if a player at the end. To avoid a visual glitch, the end symbol
        // is not diaplyed
        if (!(this.board[0][0].subSequence(5, 6).equals("P"))) {
            this.board[0][0] = TEXT_BLUE + "#=" + TEXT_RESET + this.board[0][0].substring(2, this.board[0][0].length());
        }
    }

    /**
     * Displays the state of the game board to the console
     * 
     */
    private void displayBoard() {
        // Head
        System.out.printf("---------------------------------------------------------------------------------\n"
                + "|                   Game Board                     Turn: %d                     |\n"
                + "---------------------------------------------------------------------------------\n"
                + "|                                 <-----                                        |\n", this.turn);
        // Body
        for (int row = 0; row < this.board.length; row++) {
            System.out.print("|");
            for (int col = 0; col < this.board[0].length; col++) {
                System.out.printf(" %s |", this.board[row][col]);
            }
            System.out.println();
        }

        // Tail
        System.out.println("|                                 ----->                                        |\n"
                + "---------------------------------------------------------------------------------\n"
                + "|" + TEXT_YELLOW + "P1: Player_1, P2: Player_2," + TEXT_RED + " S: Snake," + TEXT_GREEN
                + " L: Ladder," + TEXT_BLUE + " !=: Start, #=: End " + TEXT_RESET + "           |\n"
                + "---------------------------------------------------------------------------------");
    }

    /**
     * Simulates a virtual dice roll using random.
     * 
     * @return random integer value between 1 and 6
     */
    private int flipDice() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Return random number from 1 to 6
    }

    /**
     * Rolls the dice for each player and sorts them based on their roll from
     * highest to lowest.
     * 
     * @param list players to roll the dice for
     * @return list of sorted players (based on dice roll)
     */
    private Player[] rollOrder(Player[] list) {
        // Roll the dice for each player who is unordered
        for (Player player : list) {
            int roll = this.flipDice();
            player.setPreviousRoll(roll);
            // Display the roll of each player
            System.out.printf("Player %d rolled a %d\n", player.getPlayerNum(), player.getPreviousRoll());
        }

        // Sort the unorderedPlayers based on their previous rolls
        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (list[i].getPreviousRoll() < list[j].getPreviousRoll()) {
                    Player temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
        return list;
    }

    /**
     * <p>
     * Establishes the roll order of the playerLists
     * </p>
     * <p>
     * The order of the players is determined by the first roll
     * </p>
     * <p>
     * If a certain number of players get the same dice roll value, they are
     * rerolled
     * </p>
     * <p>
     * These rerolled players will be assigned order values based on the first roll
     * </p>
     * <p>
     * Ex: P1=6, P2=4, P3=4; P1 is first then depending on the reroll, P2 and P3 are
     * assigned order values
     * </p>
     * 
     * @param list list of players to order
     */
    private void createRollOrder(Player[] list) {
        Player[] rerollList = new Player[0];
        this.rollCount++;

        // CurrentPos is how many players are before the first null in orderedPlayers
        int currentPos = 0;
        for (int j = 0; j < this.orderedPlayers.length; j++) {
            if (this.orderedPlayers[j] != null) {
                currentPos++;
            } else {
                break;
            }
        }

        int iterations = currentPos + list.length; // How many iterations in the loop we want

        // Loops over all players
        for (int j = currentPos, i = 0; j < iterations; j++, i++) { // i is used to index the list that was passed in. j
                                                                    // is corresponds to the index position in the
                                                                    // orderedPlayers list

            // If we are evaluating the last player, don't compare his roll but still see if
            // he can be added to the orderedPlayers list
            if (j == iterations - 1) {
                if (!(Arrays.asList(rerollList).contains(list[i]))) {
                    // If the current orderedPlayer index = j is occupied, go to the next one
                    for (int k = j; k < this.orderedPlayers.length; k++) {
                        // If no player has been assigned at this position, assign the current player
                        if (this.orderedPlayers[k] == null) {
                            // assign the player a position and add the player to orderedPlayers
                            list[i].setPlayOrder((j + 1));
                            this.orderedPlayers[(list[i].getPlayOrder() + (k - j)) - 1] = list[i]; // k-j is how many
                            // positions we skiped
                            break;
                        }
                    }
                }
                continue;
            }
            // Check for duplicate rolls.
            if (list[i].getPreviousRoll() == list[i + 1].getPreviousRoll()) {
                // Check to see if the player we are currently evaluating is not already in the
                // rerollList. If true, add that player
                if (!Arrays.asList(rerollList).contains(list[i])) {
                    rerollList = Arrays.copyOf(rerollList, rerollList.length + 1);
                    rerollList[rerollList.length - 1] = list[i];
                }
                // Add the player we compared the current player to the reroll List
                rerollList = Arrays.copyOf(rerollList, rerollList.length + 1);
                rerollList[rerollList.length - 1] = list[i + 1];
            } else {
                // If the current player is in the reroll list, skip this player. Else, add the
                // current player to orderedPlayers
                if ((Arrays.asList(rerollList).contains(list[i]))) {
                    continue;
                } else {
                    for (int k = j; k < this.orderedPlayers.length; k++) {
                        if (this.orderedPlayers[k] == null) {
                            // assign the player a position and add the player to orderedPlayers
                            list[i].setPlayOrder((j + 1));
                            this.orderedPlayers[(list[i].getPlayOrder() + (k - j)) - 1] = list[i];
                            break;
                        }
                    }
                }
            }
        }
        // Reroll the players in the reroll list
        if (rerollList.length > 0) {
            // Display message indicating the players who tied
            System.out.print("A tie was achieved between ");
            for (int i = 0; i < rerollList.length; i++) {
                System.out.printf("player %d", rerollList[i].getPlayerNum());
                if (i != rerollList.length - 1) {
                    System.out.print(" and ");
                }
            }
            System.out.print(". Rerolling for those players.\n");
            createRollOrder(rollOrder(rerollList));
        } else {
            // Copy the ordered list into the player list. Only doing this because other
            // code uses playerList more. Could be changed.
            this.playerList = this.orderedPlayers.clone();

            // Display the roll order to the user
            System.out.print("Reached final decision on playing order: ");
            for (int i = 0; i < this.playerList.length; i++) {
                if (i == this.playerList.length - 1) {
                    System.out.printf("Player%d.\n", this.playerList[i].getPlayerNum());
                    continue;
                }
                System.out.printf("Player%d then ", this.playerList[i].getPlayerNum());
            }
            // Player order in array format
            int[] playingOrderArrayFormat = new int[this.playerCount];
            for (int i = 0; i < this.playerList.length; i++) {
                playingOrderArrayFormat[i] = this.playerList[i].getPlayerNum();
            }
            System.out.println("Array format of playing order = " + Arrays.toString(playingOrderArrayFormat));

            System.out.printf("It took %d attemps before a decision could be made.\n", rollCount);
        }
    }

    /**
     * Updates the position of the player objects by a certain dice roll amount.
     * 
     */
    private void updatePosition() {

        this.turn++; // Increment the turn timer

        // Roll for each player and update their positions
        for (Player player : this.playerList) {
            int roll = this.flipDice();
            player.setPos(player.getPos() + roll);
            // Display the current player's roll
            System.out.printf("Player%d rolled a %d; ", player.getPlayerNum(), roll);

            // Check to see if a player has gone over the 100th tile and update their
            // position.
            if (player.getPos() > 100) {
                player.setPos(100 - (player.getPos() % 100));
            }

            // Check to see if the player is on the head of a snake and update their
            // position
            for (int i = 0; i < this.snakePos.length; i++) {
                if (player.getPos() == this.snakePos[i][0]) {
                    // Display to user that they are on a snake
                    System.out.printf("Player%d landed on the head of snake %d, ", player.getPlayerNum(), i + 1);
                    player.setPos(this.snakePos[i][1]);
                }
            }

            // Check to see if player is at the bottom of a ladder and update their position
            for (int i = 0; i < this.ladderPos.length; i++) {
                if (player.getPos() == this.ladderPos[i][0]) {
                    // Display to user that they are on a ladder
                    System.out.printf("Player%d landed at the bottom of ladder %d, ", player.getPlayerNum(), i + 1);
                    player.setPos(this.ladderPos[i][1]);
                }
            }

            // Display the current player's updated position
            System.out.printf("Now in square %d\n", player.getPos());

            // Check to see if a player is on top of another player. If true, send the
            // player that was on the tile first to position 0
            for (Player otherPlayer : this.playerList) {
                // Skip the player that is moving
                if (otherPlayer == player) {
                    continue;
                } else {
                    if (player.getPos() == otherPlayer.getPos()) {
                        // Display to the user that a player landed on another
                        System.out.printf("Player%d landed on player%d. Player%d sent back to the start\n",
                                player.getPlayerNum(), otherPlayer.getPlayerNum(), otherPlayer.getPlayerNum());
                        otherPlayer.setPos(0);
                    }
                }
            }

            // Check to see if a player has won the game
            if (player.getPos() == 100) {
                // A player has won. Display this to the user and end the game
                System.out.printf("Player%d has reached tile 100 and has won the game!\n", player.getPlayerNum());
                this.gameStatus = false;
                break;
            }
        }
    }

    /**
     * Waits for the user to enter any key.
     * When a user enters a key, the next turn will render
     */
    private void nextTurn() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Press any key for the next turn...");
        scan.nextLine();
    }

    /**
     * Initiates the core engine of the game.
     * Allows the players to start playing the game
     */
    public void play() {
        this.createRollOrder(this.rollOrder(this.playerList));
        do {
            this.nextTurn();
            this.updatePosition();
            this.updateBoard();
            this.displayBoard();
        } while (this.gameStatus);
    }

}
