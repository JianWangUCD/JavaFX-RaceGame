package com.example.comp20300javafxproject;

public class Game {
    public Gamer gamer1;
    public Gamer gamer2;

    /**
     * A dice that determines the direction of player one's movement
     */
    public DirDice dirDiceOne;
    /**
     * A dice that determines the direction of player two's movement
     */
    public DirDice dirDiceTwo;
    /**
     * A dice that determines the number of squares moved by player one
     */
    public MoveDice moveDiceOne;
    /**
     * A dice that determines the number of squares moved by player two
     */
    public MoveDice moveDiceTwo;
    /**
     * An unready state means that the previous game has just ended or the game has not yet started.
     */
    static final int NOT_READY = 0;
    /**
     * This state means that player one should throw the dice to decide the direction of movement at this time
     */
    static final int PLAYERONE_DICE = 1;
    /**
     * This state means that player one should throw the dice to determine the number of squares to move at this time
     */
    static final int PLAYERONE_MOVE = 2;
    /**
     * This state means that at this time the player one is blocked by other obstacles
     * and should choose to move left/move right/skip the turn.
     * <p>
     * Obstacles include: other players, board edges, fire, tar pits, fences
     */
    static final int PLAYERONE_CHOOSING = 3;
    /**
     * This state means that player two should throw the dice to decide the direction of movement at this time
     */
    static final int PLAYERTWO_DICE = 4;
    /**
     * This state means that player two should throw the dice to determine the number of squares to move at this time
     */
    static final int PLAYERTWO_MOVE = 5;
    /**
     * This state means that at this time the player two is blocked by other obstacles
     * and should choose to move left/move right/skip the turn.
     * <p>
     * Obstacles include: other players, board edges, fire, tar pits, fences
     */
    static final int PLAYERTWO_CHOOSING = 6;
    /**
     * This status means that one of players has won
     */
    static final int SOMEONE_WON = 7;
    /**
     * Game state
     */
    public int state = NOT_READY;
    /**
     * True represents player one being stuck in the tar pit. Player one will miss the next turn.
     */
    public boolean playerOneStuck = false;
    /**
     * True represents player two being stuck in the tar pit. Player two will miss the next turn.
     */
    public boolean playerTwoStuck = false;

    public Game(){
        this.gamer1 = new Gamer();
        this.gamer2 = new Gamer();
        this.dirDiceOne = new DirDice();
        this.dirDiceTwo = new DirDice();
        this.moveDiceOne = new MoveDice();
        this.moveDiceTwo = new MoveDice();
    }

    public void playerOneDir(){
        if(state == PLAYERONE_DICE){
            dirDiceOne.roll();
            if(dirDiceOne.dir != Direction.miss){
                //
                state = PLAYERONE_MOVE;
            }
            else {
                if(playerTwoStuck){
                    state = PLAYERONE_DICE;
                    playerTwoStuck = false;
                    //
                }
                else {
                    //
                    //
                    state = PLAYERTWO_DICE;
                    //
                }
            }
        }
        if(state == PLAYERONE_DICE){
            dirDiceOne.roll();
            if(dirDiceOne.dir != Direction.miss){
                playerOneDirInfo.setText(playerOneNameText.getText() + " should go " + dirDiceOne.dir);
                state = PLAYERONE_MOVE;
            }
            else{
                if(playerTwoStuck){
                    state = PLAYERONE_DICE;
                    playerTwoStuck = false;
                    setPlayerTurn(playerOne);
                }
                else{
                    playerOneDirInfo.setText(playerOneNameText.getText() + " skips this turn");
                    playerOneMoveInfo.setText("do not move");
                    state = PLAYERTWO_DICE;
                    setPlayerTurn(playerTwo);
                }
            }
        }
    }
}
