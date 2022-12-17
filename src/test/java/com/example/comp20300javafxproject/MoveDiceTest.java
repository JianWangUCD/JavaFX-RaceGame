package com.example.comp20300javafxproject;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveDiceTest {

    @RepeatedTest(10)
    void roll() {
        MoveDice moveDice = new MoveDice();
        moveDice.roll();
        assertTrue(moveDice.move == 1 || moveDice.move == 2 || moveDice.move == 3 || moveDice.move == 4);
    }
}