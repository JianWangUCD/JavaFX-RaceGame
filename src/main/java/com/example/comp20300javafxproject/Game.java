package com.example.comp20300javafxproject;

import javafx.event.ActionEvent;

import java.io.*;

/**
 * The Game Logic
 */
public class Game {
    /**
     * player one
     */
    public Gamer gamer1;
    /**
     * player two
     */
    public Gamer gamer2;
    /**
     * The winner of this round of game (Enter two names to start a new game is a new round)
     */
    public Gamer winner;

    /**
     * Records all previous winners and new winners. It persistently records the previous results even though the game program restarted.
     */
    public Gamers previousGamers;

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

    /**
     * Initialize four dices,
     * set two players' moves to 0,
     * initialize two players' location,
     * initialize the ({@link #winner}),
     */
    public Game(){
        this.gamer1 = new Gamer();
        this.gamer2 = new Gamer();
        this.winner = new Gamer();
        this.dirDiceOne = new DirDice();
        this.dirDiceTwo = new DirDice();
        this.moveDiceOne = new MoveDice();
        this.moveDiceTwo = new MoveDice();
        gamer1.layoutX = 100;
        gamer1.layoutY = 675;
        gamer2.layoutX = 700;
        gamer2.layoutY = 675;
    }
    /**
     * Determine if this square is fire
     * <p>
     * Fire is a common obstacle, the player can not enter the fire, need to go around the way forward.
     * @param layoutX Player's x coordinate
     * @param layoutY Player's y coordinate
     * @return true - fire, false - not fire
     */
    public boolean isFire(double layoutX, double layoutY){
        if(layoutX > 0 && layoutX < 200 && layoutY > 425 && layoutY < 500)
            return true;
        else return layoutX > 600 && layoutX < 800 && layoutY > 350 && layoutY < 425;
    }

    /**
     * Determine if this square is tar pit
     * <p>
     * Tar pits are accessible obstacles. If a player passes through a tar pit, he or she will be stuck.
     * This player will skip the next turn.
     * @param layoutX Player's x coordinate
     * @param layoutY Player's y coordinate
     * @return true - tar pit, false - not tar pit
     */
    public boolean isTarPit(double layoutX, double layoutY){
        if(layoutX > 0 && layoutX < 200 && layoutY > 200 && layoutY < 275)
            return true;
        else return layoutX > 400 && layoutX < 600 && layoutY > 275 && layoutY < 350;
    }

    /**
     * Determine if this square is fence
     * <p>
     * A fence is an obstacle that covers two square spaces, the player can not enter the fence,
     * need to go around the way forward.
     * @param layoutX Player's x coordinate
     * @param layoutY Player's y coordinate
     * @return true - fence, false - not fence
     */
    public boolean isFence(double layoutX, double layoutY){
        return layoutX > 200 && layoutX < 600 && layoutY > 50 && layoutY < 125;
    }

    /**
     * Detect if there is another player in this square
     * @param player another player (not the moving player)
     * @param layoutX x-coordinate of the moving player
     * @param layoutY y-coordinate of the moving player
     * @return true - Another player in this square, false - not
     */
    public boolean isOtherPlayer(Gamer player, double layoutX, double layoutY){
        return layoutX == player.layoutX && layoutY == player.layoutY;
    }

    /**
     * Detect if the player is now outside of board
     * @param layoutX Player's x coordinate
     * @param layoutY Player's y coordinate
     * @return true - outside, false - not outside
     */
    public boolean isEdge(double layoutX, double layoutY){
        return layoutX < 0 || layoutX > 800 || layoutY < 0 || layoutY > 700;
    }

    /**
     * Detect if the player is now in the winning area
     * @param layoutX Player's x coordinate
     * @param layoutY Player's y coordinate
     * @return true - in the winner area, false - not
     */
    public boolean isFinishArea(double layoutX, double layoutY){
        return layoutX > 0 && layoutX < 800 && layoutY > 0 && layoutY < 50;
    }



    /**
     * The core logical method to forward or backward movement
     * <p>
     * Step1: The player gets the "ideal" number of squares moved as determined by dice, execute this method
     * <p>
     * Step2: Each step must be judged whether this square is obstacle. So this method will be executed several times.
     * <p>
     * - If it is another player/fire/fence/edge of the board, player will stop, ending the method and the remaining moves will be stored.
     * If this one is player 1 / 2, state will be updated to {@link #PLAYERONE_CHOOSING} / {@link #PLAYERTWO_CHOOSING}.
     * Player 1 or Player 2 should choose to move left/move right/skip the turn.
     * <p>
     * - If it's a tar pit, the player will get stuck in it and skip the next turn. End method.
     * If this one is player 1 / 2 who is stuck, state will be updated to {@link #PLAYERTWO_DICE} / {@link #PLAYERONE_DICE}.
     * <p>
     * - If everything is fine, then the player will move
     * <p>
     *
     * @param player the moving player
     * @param dirDice The moving player's dice that determine the direction of movement
     * @param moveDice The moving player's dice that determine the number of squares moved
     */
    public void move(Gamer player, DirDice dirDice, MoveDice moveDice){
        int pixelDistance = -75;
        if(dirDice.dir == Direction.forward) ;
        else if(dirDice.dir == Direction.backward)
            pixelDistance = 75;

        if(isFire(player.layoutX, player.layoutY + pixelDistance) ||
                isFence(player.layoutX, player.layoutY + pixelDistance) ||
                isOtherPlayer(player.equals(gamer1) ? gamer2: gamer1,
                        player.layoutX, player.layoutY + pixelDistance) ||
                isEdge(player.layoutX, player.layoutY + pixelDistance)){
            state = player.equals(gamer1) ? PLAYERONE_CHOOSING : PLAYERTWO_CHOOSING;
        }
        else if(isTarPit(player.layoutX, player.layoutY + pixelDistance)){
            player.layoutY += pixelDistance;

            if(player.equals(gamer1)){
                playerOneStuck = true;
                gamer1.increaseMoves();
            }
            else{
                playerTwoStuck = true;
                gamer2.increaseMoves();
            }
            state = player.equals(gamer1) ? PLAYERTWO_DICE: PLAYERONE_DICE;
            moveDice.move -= 1;
        }
        else {
            player.layoutY += pixelDistance;
            if(player.equals(gamer1))
                gamer1.increaseMoves();
            else
                gamer2.increaseMoves();
            if(player.layoutY == 0)
                player.layoutY += 25;

            if(isFinishArea(player.layoutX, player.layoutY)){
                //Finished!
                state = SOMEONE_WON;
            }
            moveDice.move -= 1;
        }
    }

    /**
     * The core logic method when a player encounters an obstacle (other player/edge of the board/fire/fence)
     * <p>
     * This method is executed when the "left" or "right" button is clicked
     * and the state is {@link #PLAYERONE_CHOOSING} or {@link #PLAYERTWO_CHOOSING}.
     * <p>
     * Each step must be judged whether this square is obstacle. So this method will be executed several times.
     * The player will stop in front of any obstacle (including tar pits).
     * <p>
     * Whether the player consumes all the "ideal" moves or stops in front of an obstacle, end the method.
     * And update the state to {@link #PLAYERONE_DICE} or {@link #PLAYERONE_DICE} in
     * {@link ArchitectureController#playerOneLeft(ActionEvent)} or {@link ArchitectureController#playerOneRight(ActionEvent)}
     * or {@link ArchitectureController#playerTwoLeft(ActionEvent)} or {@link ArchitectureController#playerTwoRight(ActionEvent)}.
     * @param player the moving player
     * @param dirDice The moving player's dice that determine the direction of movement
     * @param moveDice The moving player's dice that determine the number of squares moved
     * @param leftOrRight -1 is for left, 1 is for right. Because the x coordinate decreases when moving to the left
     * @return true - normal moving, false - met with an obstacle
     */
    public boolean moveLeftOrRight(Gamer player, DirDice dirDice, MoveDice moveDice, int leftOrRight){
        boolean isPlayerMoving = true;
        int pixelDistance = 200 * leftOrRight;
        if(isFire(player.layoutX + pixelDistance, player.layoutY) ||
                isTarPit(player.layoutX + pixelDistance, player.layoutY) ||
                isFence(player.layoutX + pixelDistance, player.layoutY) ||
                isOtherPlayer(player.equals(gamer1) ? gamer2: gamer1,
                        player.layoutX + pixelDistance, player.layoutY) ||
                isEdge(player.layoutX + pixelDistance, player.layoutY)){
            isPlayerMoving = false;
        }
        else {
            player.layoutX += pixelDistance;
            if(player.equals(gamer1))
                gamer1.increaseMoves();
            else
                gamer2.increaseMoves();
        }

        return isPlayerMoving;
    }

    /**
     * Player 1 throws a die to obtain the direction of movement.
     * <p>
     * Step1: Player 1 throws the dice. If the result is not `miss`, the state is updated to {@link #PLAYERONE_MOVE}.
     * <p>
     * Step2: If the result is `miss`
     * <p>
     * - If player 2 is stuck in the tar pit, update state to {@link #PLAYERONE_DICE}.
     * <p>
     * - Otherwise, the state is updated to {@link #PLAYERTWO_DICE}.
     */
    public void playerOneDir(){
        if(state == PLAYERONE_DICE){
            dirDiceOne.roll();
            if(dirDiceOne.dir != Direction.miss){
                state = PLAYERONE_MOVE;
            }
            else {
                if(playerTwoStuck){
                    state = PLAYERONE_DICE;
                    playerTwoStuck = false;
                }
                else {
                    state = PLAYERTWO_DICE;
                }
            }
        }
    }

    /**
     * Player 1 gets the "ideal" number of squares moved as determined by dice
     * @return true - it is time to move player 1
     */
    public boolean playerOneMoveFirstly(){
        if(state == PLAYERONE_MOVE){
            moveDiceOne.roll();
            return true;
        }
        return false;
    }

    /**
     * Update state after executing {@link #move(Gamer, DirDice, MoveDice)} method.
     */
    public void playerOneMoveSecondly(){
        if(state == SOMEONE_WON){
            doPersistentRecord(gamer1);
            state = NOT_READY;
        }
        else if(state == PLAYERONE_CHOOSING) {
        }
        else {
            if(playerTwoStuck){
                state = PLAYERONE_DICE;
                playerTwoStuck = false;
            }
            else{
                state = PLAYERTWO_DICE;
            }
        }
    }

    /**
     * Player 2 throws a die to obtain the direction of movement.
     * <p>
     * Step1: Player 2 throws the dice. If the result is not `miss`, the state is updated to {@link #PLAYERTWO_MOVE}.
     * <p>
     * Step2: If the result is `miss`
     * <p>
     * - If player 1 is stuck in the tar pit, update state to {@link #PLAYERTWO_DICE}.
     * <p>
     * - Otherwise, the state is updated to {@link #PLAYERONE_DICE}.
     */
    public void playerTwoDir(){
        if(state == PLAYERTWO_DICE){
            dirDiceTwo.roll();
            if(dirDiceTwo.dir != Direction.miss){
                state = PLAYERTWO_MOVE;
            }
            else {
                if(playerOneStuck){
                    state = PLAYERTWO_DICE;
                    playerOneStuck = false;
                }
                else {
                    state = PLAYERONE_DICE;
                }
            }
        }
    }

    /**
     * Player 2 gets the "ideal" number of squares moved as determined by dice
     * @return true - it is time to move player 2
     */
    public boolean playerTwoMoveFirstly(){
        if(state == PLAYERTWO_MOVE){
            moveDiceTwo.roll();
            return true;
        }
        return false;
    }

    /**
     * Update state after executing {@link #move(Gamer, DirDice, MoveDice)} method.
     */
    public void playerTwoMoveSecondly(){
        if(state == SOMEONE_WON){
            doPersistentRecord(gamer2);
            state = NOT_READY;
        }
        else if(state == PLAYERTWO_CHOOSING) {
        }
        else {
            if(playerOneStuck){
                state = PLAYERTWO_DICE;
                playerOneStuck = false;
            }
            else{
                state = PLAYERONE_DICE;
            }
        }
    }

    /**
     * Clear the history of game score board
     */
    public void doClearRecord(){
        try {
            PrintWriter writer =
                    new PrintWriter("Record.ser");
            writer.print("");
            writer.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }

    /**
     * Put the new winner's record into the total record.
     * <p>
     * Step1: get name and moves of winner
     * <p>
     * Step2: set name and moves into {@link #winner} object
     * <p>
     * Step3:
     * <p>
     * - If there is no previous winners, just put this new winner in to ArrayList of {@link #previousGamers}
     * <p>
     * - If there are some previous winners, execute {@link Gamers#mergeNewRecord(Gamer)} and {@link Gamers#sortAllRecords()} methods
     * <p>
     * Step4: execute {@link #doClearRecord()} method
     * <p>
     * Step5: put the total records into a file and store this file locally.
     * @param player The winner of this round of game (Enter two names to start a new game is a new round)
     */
    public void doPersistentRecord(Gamer player){
        String name = player.equals(gamer1) ? gamer1.name : gamer2.name;
        int moves = player.equals(gamer1) ? gamer1.moves : gamer2.moves;
        winner.setName(name);
        winner.setNumWins(1);
        winner.setMoves(moves);
        winner.setAllMoves();
        if(this.previousGamers == null){
            this.previousGamers = new Gamers();
            this.previousGamers.allWinners.add(winner);
        }
        else {
            this.previousGamers.mergeNewRecord(winner);
            this.previousGamers.sortAllRecords();
        }

        doClearRecord();
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream("Record.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(this.previousGamers);
            out.close();
            fileOutputStream.close();
        }catch (IOException ex){

        }
    }

    /**
     * get records of all previous winners
     * @return Records all previous winners
     */
    public Gamers getPreviousGamers(){
        Gamers tempGames = null;
        try{
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream("Record.ser"));
            tempGames = (Gamers) in.readObject();
            in.close();
        }catch (Exception ex){

        }
        return tempGames;
    }

}
