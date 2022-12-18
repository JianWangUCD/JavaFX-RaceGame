package com.example.comp20300javafxproject;

import java.util.Random;

/**
 * Three options for movement
 */
enum Direction{
    forward, backward, miss
}
/**
 * The Dice that determine direction of movement
 */
public class DirDice {
    public Random random;
    /**
     * The direction of movement determined by dice
     */
    public Direction dir;
    public DirDice(){
        random = new Random();
    }
    /**
     * set direction of movement
     */
    public void roll(){
        int num = random.nextInt(4);
        if(num == 0 || num == 1)
            this.dir = Direction.forward; //
        else if(num == 2)
            this.dir = Direction.backward; //
        else
            this.dir = Direction.miss;
    }
}
