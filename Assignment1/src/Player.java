 // -----------------------------------------------------
 // Assignment 1
 // Question: Part 1
 // Written by: Nathan Grenier, 40250986
 // COMP 249
 // Due Date: Febuary 3, 2023
 // -----------------------------------------------------

 /**
 * The Player Class represents a player in the snake and ladder game.
 * 
 * @author Nathan Grenier
 * @version 1.0
 * 
 */
public class Player {
    private int pos;
    private int playerNum;
    private int previousRoll;
    private int playOrder;

    /**
     * Player constructor.
     * Assign the playerNumber by passing it as a paramater
     * 
     * @param playerNum
     */
    public Player(int playerNum) {
        this.pos = 0;
        this.playerNum = playerNum;
    }

    // Getters
    /**
     * Get the position of a player.
     * 
     * @return The position of a player (int)
     */
    public int getPos() {
        return this.pos;
    }

    /**
     * Get the player's number.
     * 
     * @return The player's number (int)
     */
    public int getPlayerNum() {
        return this.playerNum;
    }

    /**
     * Get the player's order.
     * 
     * @return The playOrder of a player (int)
     */
    public int getPlayOrder() {
        return this.playOrder;
    }

    /**
     * Get the palyer's previous roll.
     * 
     * @return The player's previous roll
     */
    public int getPreviousRoll() {
        return this.previousRoll;
    }

    // Setters
    /**
     * Set the player's position.
     * 
     * @param pos
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * Set the player's play order.
     * 
     * @param playOrder
     */
    public void setPlayOrder(int playOrder) {
        this.playOrder = playOrder;
    }

    /**
     * Set the player's previous roll.
     * 
     * @param previousRoll
     */
    public void setPreviousRoll(int previousRoll) {
        this.previousRoll = previousRoll;
    }
}
