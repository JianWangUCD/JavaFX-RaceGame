package com.example.comp20300javafxproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static com.example.comp20300javafxproject.Game.NOT_READY;
import static com.example.comp20300javafxproject.Game.PLAYERONE_DICE;
import static com.example.comp20300javafxproject.Game.PLAYERONE_MOVE;
import static com.example.comp20300javafxproject.Game.PLAYERONE_CHOOSING;
import static com.example.comp20300javafxproject.Game.PLAYERTWO_DICE;
import static com.example.comp20300javafxproject.Game.PLAYERTWO_MOVE;
import static com.example.comp20300javafxproject.Game.PLAYERTWO_CHOOSING;
import static com.example.comp20300javafxproject.Game.SOMEONE_WON;

class GameTest {
    @Test
    void sampleTest(){
        Game game = new Game();
        game.gamer1.setName("A");
        game.gamer2.setName("B");
        game.state = PLAYERONE_DICE;
        game.playerOneDir();
        if(game.dirDiceOne.dir != Direction.miss){
            assertEquals(PLAYERONE_MOVE, game.state);
        }
        else {
            assertEquals(PLAYERTWO_DICE, game.state);
        }
    }

    @Test
    @DisplayName("player 1 goes forward and moves 2")
    void test1(){
        Game game = new Game();
        game.state = PLAYERONE_MOVE;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 2;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
        }
        assertEquals(2, game.gamer1.moves);
        assertEquals(525, game.gamer1.layoutY);
    }

    @Test
    @DisplayName("player 2 goes forward and moves 3")
    void test2(){
        Game game = new Game();
        game.state = PLAYERTWO_MOVE;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 3;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
        }
        assertEquals(3, game.gamer2.moves);
        assertEquals(450, game.gamer2.layoutY);
    }

//    @Test
//    void isFire() {
//    }
//
//    @Test
//    void isTarPit() {
//    }
//
//    @Test
//    void isFence() {
//    }
//
//    @Test
//    void isOtherPlayer() {
//    }
//
//    @Test
//    void isEdge() {
//    }
//
//    @Test
//    void isFinishArea() {
//    }
//
//    @Test
//    void move() {
//    }
//
//    @Test
//    void moveLeftOrRight() {
//    }
//
//    @Test
//    void playerOneDir() {
//    }
//
//    @Test
//    void playerOneMoveFirstly() {
//    }
//
//    @Test
//    void playerOneMoveSecondly() {
//    }
//
//    @Test
//    void playerTwoDir() {
//    }
//
//    @Test
//    void playerTwoMoveFirstly() {
//    }
//
//    @Test
//    void playerTwoMoveSecondly() {
//    }
//
//    @Test
//    void doClearRecord() {
//    }
//
//    @Test
//    void doPersistentRecord() {
//    }
//
//    @Test
//    void getPreviousGamers() {
//    }
}