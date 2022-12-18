package com.example.comp20300javafxproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Player of the game
 */
public class Gamer implements Serializable, Comparable<Gamer> {
    public double layoutX;
    public double layoutY;
    /**
     * Player name
     */
    public String name;
    /**
     * Number of times the player has won
     */
    public int numWins = 0;
    /**
     * Number of squares moved in the player's first winning game
     */
    public int moves = 0;
    /**
     * Shows the number of squares moved in all winning games for the player
     */
    public String allMoves;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * Record the number of squares moved by the player on the first win
     */
    public void setAllMoves(){
        this.allMoves = Integer.toString(this.moves);
    }

    public void increaseMoves(){
        this.moves++;
    }

    /**
     * For comparing multiple players in an ArrayList, the player with more wins will have a smaller index
     * <p>
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Gamer other){
        if(this.numWins > other.numWins)
            return -1;
        else if(this.numWins < other.numWins)
            return 1;
        else
            return 0;
    }

}

