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
        game.dirDiceOne.dir = Direction.forward;
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
        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 3;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
        }
        assertEquals(3, game.gamer2.moves);
        assertEquals(450, game.gamer2.layoutY);
    }

    @Test
    @DisplayName("player 1 meets fire")
    void test3(){
        Game game = new Game();
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 3;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
        }
        assertEquals(2, game.gamer1.moves);
        assertEquals(PLAYERONE_CHOOSING, game.state);
        assertEquals(525, game.gamer1.layoutY);
    }
    @Test
    @DisplayName("player 1 meets fire and move right")
    void test4(){
        Game game = new Game();
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 3;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
        }
        assertEquals(PLAYERONE_CHOOSING, game.state);

        assertEquals(1, game.moveDiceOne.move);
        for(int i = 0; i < game.moveDiceOne.move; i++){
            game.moveLeftOrRight(game.gamer1, game.dirDiceOne, game.moveDiceOne, 1);
        }
        assertEquals(3, game.gamer1.moves);
        assertEquals(525, game.gamer1.layoutY);
        assertEquals(300, game.gamer1.layoutX);
    }

    @Test
    @DisplayName("player 1 meets fire and move left") // The left side is the left edge of board
    void test5(){
        Game game = new Game();
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 3;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
        }
        assertEquals(PLAYERONE_CHOOSING, game.state);

        for(int i = 0; i < game.moveDiceOne.move; i++){
            game.moveLeftOrRight(game.gamer1, game.dirDiceOne, game.moveDiceOne, -1); // -1 means moving left
        }
        assertEquals(2, game.gamer1.moves);
        assertEquals(525, game.gamer1.layoutY);
        assertEquals(100, game.gamer1.layoutX);
    }

    @Test
    @DisplayName("player 1 meets fire and then choose to miss this turn")
    void test6(){
        Game game = new Game();
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 3;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
        }
        assertEquals(PLAYERONE_CHOOSING, game.state);
        game.state = PLAYERTWO_DICE;

        assertEquals(2, game.gamer1.moves);
        assertEquals(525, game.gamer1.layoutY);
        assertEquals(100, game.gamer1.layoutX);
    }

    @Test
    @DisplayName("player 2 meets fire")
    void test7(){
        Game game = new Game();
        game.state = PLAYERTWO_MOVE;
        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 4;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
        }
        assertEquals(3, game.gamer2.moves);
        assertEquals(PLAYERTWO_CHOOSING, game.state);
        assertEquals(450, game.gamer2.layoutY);
    }

    @Test
    @DisplayName("player 2 meets fire and move left")
    void test8(){
        Game game = new Game();
        game.state = PLAYERTWO_MOVE;
        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 4;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
        }
        assertEquals(PLAYERTWO_CHOOSING, game.state);

        assertEquals(1, game.moveDiceTwo.move);
        for(int i = 0; i < game.moveDiceTwo.move; i++){
            game.moveLeftOrRight(game.gamer2, game.dirDiceTwo, game.moveDiceTwo, -1);
        }
        assertEquals(4, game.gamer2.moves);
        assertEquals(450, game.gamer2.layoutY);
        assertEquals(500, game.gamer2.layoutX);
    }

    @Test
    @DisplayName("player 2 meets fire and move right") // The right side is the right edge of board
    void test9(){
        Game game = new Game();
        game.state = PLAYERTWO_MOVE;
        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 4;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
        }
        assertEquals(PLAYERTWO_CHOOSING, game.state);

        assertEquals(1, game.moveDiceTwo.move);
        for(int i = 0; i < game.moveDiceTwo.move; i++){
            game.moveLeftOrRight(game.gamer2, game.dirDiceTwo, game.moveDiceTwo, 1);
        }
        assertEquals(3, game.gamer2.moves);
        assertEquals(450, game.gamer2.layoutY);
        assertEquals(700, game.gamer2.layoutX);
    }

    @Test
    @DisplayName("player 2 meets fire and then choose to miss this turn")
    void test10(){
        Game game = new Game();
        game.state = PLAYERTWO_MOVE;
        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 4;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
        }
        assertEquals(PLAYERTWO_CHOOSING, game.state);

        assertEquals(3, game.gamer2.moves);
        assertEquals(450, game.gamer2.layoutY);
        assertEquals(700, game.gamer2.layoutX);
    }

    @Test
    @DisplayName("player 1 meets tar pit")
    void test11(){
        Game game = new Game();
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 1;
        game.gamer1.layoutX = 500;
        game.gamer1.layoutY = 375;
        game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
        game.playerOneMoveSecondly();
        assertEquals(1, game.gamer1.moves);
        assertTrue(game.playerOneStuck);
        assertEquals(PLAYERTWO_DICE, game.state);

        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 1;
        game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
        game.playerTwoMoveSecondly();
        assertEquals(PLAYERTWO_DICE, game.state);
        assertFalse(game.playerOneStuck);
    }

    @Test
    @DisplayName("player 2 meets tar pit")
    void test12(){
        Game game = new Game();
        game.state = PLAYERTWO_MOVE;
        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 1;
        game.gamer2.layoutX = 100;
        game.gamer2.layoutY = 300;
        game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
        game.playerTwoMoveSecondly();
        assertEquals(1, game.gamer2.moves);
        assertTrue(game.playerTwoStuck);
        assertEquals(PLAYERONE_DICE, game.state);

        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 1;
        game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
        game.playerOneMoveSecondly();
        assertEquals(PLAYERONE_DICE, game.state);
        assertFalse(game.playerTwoStuck);
    }

    @Test
    @DisplayName("player 1 meets player 2")
    void test13(){
        Game game = new Game();
        game.moveDiceOne.move = 3;
        game.state = PLAYERONE_CHOOSING;
        for(int i = 0; i < game.moveDiceOne.move; i++){
            if(!game.moveLeftOrRight(game.gamer1, game.dirDiceOne, game.moveDiceOne, 1))
                break;
        }
        assertEquals(2, game.gamer1.moves);
        assertEquals(500, game.gamer1.layoutX);
        assertEquals(675, game.gamer1.layoutY);
    }
    @Test
    @DisplayName("player 2 meets player 1")
    void test14(){
        Game game = new Game();
        game.moveDiceTwo.move = 3;
        game.state = PLAYERTWO_CHOOSING;
        for(int i = 0; i < game.moveDiceTwo.move; i++){
            if(!game.moveLeftOrRight(game.gamer2, game.dirDiceTwo, game.moveDiceTwo, -1))
                break;
        }
        assertEquals(2, game.gamer2.moves);
        assertEquals(300, game.gamer2.layoutX);
        assertEquals(675, game.gamer2.layoutY);
    }

    @Test
    @DisplayName("player 1 meets the edge of board")
    void test15(){
        Game game = new Game();
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.backward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 2;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
            if(game.state == PLAYERONE_CHOOSING)
                break;
        }
        assertEquals(0, game.gamer1.moves);
        assertEquals(PLAYERONE_CHOOSING, game.state);
        assertEquals(2, game.moveDiceOne.move);
        game.playerOneMoveSecondly();

        for(int i = 0; i < game.moveDiceOne.move; i++){
            game.moveLeftOrRight(game.gamer1, game.dirDiceOne, game.moveDiceOne, 1);
        }
        assertEquals(2, game.gamer1.moves);
        assertEquals(675, game.gamer1.layoutY);
        assertEquals(500, game.gamer1.layoutX);
    }

    @Test
    @DisplayName("player 2 meets the edge of board")
    void test16(){
        Game game = new Game();
        game.state = PLAYERTWO_MOVE;
        game.dirDiceTwo.dir = Direction.backward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 2;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
            if(game.state == PLAYERTWO_CHOOSING)
                break;
        }
        assertEquals(0, game.gamer2.moves);
        assertEquals(PLAYERTWO_CHOOSING, game.state);
        assertEquals(2, game.moveDiceTwo.move);
        game.playerTwoMoveSecondly();

        for(int i = 0; i < game.moveDiceTwo.move; i++){
            game.moveLeftOrRight(game.gamer2, game.dirDiceTwo, game.moveDiceTwo, -1);
        }
        assertEquals(2, game.gamer2.moves);
        assertEquals(675, game.gamer2.layoutY);
        assertEquals(300, game.gamer2.layoutX);
    }

    @Test
    @DisplayName("player 1 meets fence")
    void test17(){
        Game game = new Game();
        game.gamer1.layoutX = 300;
        game.gamer1.layoutY = 300;
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 4;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
            if(game.state == PLAYERONE_CHOOSING)
                break;
        }
        game.playerOneMoveSecondly();
        assertEquals(2, game.gamer1.moves);
        assertEquals(PLAYERONE_CHOOSING, game.state);
        assertEquals(300, game.gamer1.layoutX);
        assertEquals(150, game.gamer1.layoutY);

        //Assume that going to the left next
        for(int i = 0; i < game.moveDiceOne.move; i++){
            if(!game.moveLeftOrRight(game.gamer1, game.dirDiceOne, game.moveDiceOne, -1))
                break;
        }
        assertEquals(3, game.gamer1.moves);
        assertEquals(150, game.gamer1.layoutY);
        assertEquals(100, game.gamer1.layoutX);
    }

    @Test
    @DisplayName("player 2 meets fence")
    void test18(){
        Game game = new Game();
        game.gamer2.layoutX = 300;
        game.gamer2.layoutY = 300;
        game.state = PLAYERTWO_MOVE;
        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 4;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
            if(game.state == PLAYERTWO_CHOOSING)
                break;
        }
        game.playerTwoMoveSecondly();
        assertEquals(2, game.gamer2.moves);
        assertEquals(PLAYERTWO_CHOOSING, game.state);
        assertEquals(300, game.gamer2.layoutX);
        assertEquals(150, game.gamer2.layoutY);

        //Assume that going to the right next
        for(int i = 0; i < game.moveDiceTwo.move; i++){
            if(!game.moveLeftOrRight(game.gamer2, game.dirDiceTwo, game.moveDiceTwo, 1))
                break;
        }
        assertEquals(4, game.gamer2.moves);
        assertEquals(150, game.gamer2.layoutY);
        assertEquals(700, game.gamer2.layoutX);
    }

    @Test
    @DisplayName("The player 1 enters the finish area")
    void test19(){
        Game game = new Game();
        game.gamer1.layoutX = 100;
        game.gamer1.layoutY = 150;
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 4;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
            if(game.state == SOMEONE_WON)
                break;
        }
        assertEquals(SOMEONE_WON, game.state);
        assertEquals(2, game.gamer1.moves);
        assertEquals(100, game.gamer1.layoutX);
        assertEquals(25, game.gamer1.layoutY);
    }

    @Test
    @DisplayName("The player 2 enters the finish area")
    void test20(){
        Game game = new Game();
        game.gamer2.layoutX = 700;
        game.gamer2.layoutY = 225;
        game.state = PLAYERTWO_MOVE;
        game.dirDiceTwo.dir = Direction.forward;
        game.playerTwoMoveFirstly();
        game.moveDiceTwo.move = 4;
        int idealMoves = game.moveDiceTwo.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
            if(game.state == SOMEONE_WON)
                break;
        }
        assertEquals(SOMEONE_WON, game.state);
        assertEquals(3, game.gamer2.moves);
        assertEquals(700, game.gamer2.layoutX);
        assertEquals(25, game.gamer2.layoutY);
    }

    @Test
    @DisplayName("player 1 wins")
    void test21(){
        Game game = new Game();
        game.gamer1.setName("Riki");
        game.gamer1.layoutX = 100;
        game.gamer1.layoutY = 150;
        game.state = PLAYERONE_MOVE;
        game.dirDiceOne.dir = Direction.forward;
        game.playerOneMoveFirstly();
        game.moveDiceOne.move = 4;
        int idealMoves = game.moveDiceOne.move;
        for(int i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
            if(game.state == SOMEONE_WON)
                break;
        }
        assertEquals(SOMEONE_WON, game.state);
        assertEquals(2, game.gamer1.moves);
        assertEquals(100, game.gamer1.layoutX);
        assertEquals(25, game.gamer1.layoutY);

        game.playerOneMoveSecondly();
        assertEquals(NOT_READY, game.state);
        assertEquals("Riki", game.winner.name);
        assertEquals(2, game.winner.moves);
        assertEquals("2", game.winner.allMoves);
        assertEquals(1, game.winner.numWins);
    }

    // double stuck ?
    // 左移/右移时遇到障碍？
    // someone wins
    // doPersistentRecord
    // getPreviousGamers
    // doClearRecord
    // new winners (2+ records) ?

}