package com.example.comp20300javafxproject;

import java.util.Random;

/**
 * The dice that determine how many squares to move
 */
public class MoveDice {
    /**
     * Number of squares moved
     */
    public int move;
    public Random random;
    public MoveDice(){
        random = new Random();
    }
    /**
     * set number of squares moved
     */
    public void roll(){
        this.move = random.nextInt(4) + 1;
    }
}
