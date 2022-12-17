package com.example.comp20300javafxproject;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirDiceTest {

    @RepeatedTest(10)
    void roll() {
        DirDice dirDice = new DirDice();
        dirDice.roll();
        assertTrue(dirDice.dir == Direction.forward || dirDice.dir == Direction.backward || dirDice.dir == Direction.miss);
    }
}