package com.example.comp20300javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.*;
import java.util.ArrayList;

/**
 * The Controller of JavaFx Project
 */
public class ArchitectureController {
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
    private int state = NOT_READY;
    /**
     * True represents player one being stuck in the tar pit. Player one will miss the next turn.
     */
    private boolean playerOneStuck = false;
    /**
     * True represents player two being stuck in the tar pit. Player two will miss the next turn.
     */
    private boolean playerTwoStuck = false;

    /**
     * A dice that determines the direction of player one's movement
     */
    private DirDice dirDiceOne;
    /**
     * A dice that determines the direction of player two's movement
     */
    private DirDice dirDiceTwo;
    /**
     * A dice that determines the number of squares moved by player one
     */
    private MoveDice moveDiceOne;
    /**
     * A dice that determines the number of squares moved by player two
     */
    private MoveDice moveDiceTwo;

    private Game game;
//    /**
//     * Number of squares moved by player one
//     */
//    private int playerOneMoves;
//    /**
//     * Number of squares moved by player two
//     */
//    private int playerTwoMoves;
    /**
     * The winner of this round of game (Enter two names to start a new game is a new round)
     */
    private Gamer winner;
    /**
     * Records all previous winners and new winners. It persistently records the previous results even though the game program restarted.
     */
    private Gamers previousGamers;

    /**
     * The circle representing player one
     */
    @FXML
    private Circle playerOne;
    /**
     * The circle representing player two
     */
    @FXML
    private Circle playerTwo;
    /**
     * show player 1 should do a choice (move left / move right / miss this turn)
     */
    @FXML
    private Label playerOneChoice;
    /**
     * show player 2 should do a choice (move left / move right / miss this turn)
     */
    @FXML
    private Label playerTwoChoice;
    /**
     * show player 1 direction info
     */
    @FXML
    private Label playerOneDirInfo;
    /**
     * show player 2 direction info
     */
    @FXML
    private Label playerTwoDirInfo;
    /**
     * show number of squares moved of player 1
     */
    @FXML
    private Label playerOneMoveInfo;
    /**
     * show number of squares moved of player 2
     */
    @FXML
    private Label playerTwoMoveInfo;
    /**
     * the text input box for player one name
     */
    @FXML
    private TextField playerOneNameInput;
    /**
     * the inputted player one name
     */
    @FXML
    private Label playerOneNameText;
    /**
     * the text input box for player two name
     */
    @FXML
    private TextField playerTwoNameInput;
    /**
     * the inputted player two name
     */
    @FXML
    private Label playerTwoNameText;
    /**
     * Show the name of the player who should move this turn
     */
    @FXML
    private Label turnInfo;
    /**
     * show "please enter names to start game"
     */
    @FXML
    private Label readyText;
    /**
     * show the winner name in the center of game window
     */
    @FXML
    private Label winnerText;
    /**
     * show player 1 name
     */
    @FXML
    private Label playerOneMoveRecord;
    /**
     * show player 1 current moves
     */
    @FXML
    private Label playerOneMoveRecordNums;
    /**
     * show player 2 name
     */
    @FXML
    private Label playerTwoMoveRecord;
    /**
     * show player 2 current moves
     */
    @FXML
    private Label playerTwoMoveRecordNums;
    /**
     * show last game winner info (name and moves)
     */
    @FXML
    private Label lastGameWinnerInfo;

    /**
     * All moves of the 8th player on scoreboard
     */
    @FXML
    private Label topEightPlayerMoves;
    /**
     * Name of the 8th player on scoreboard
     */
    @FXML
    private Label topEightPlayerName;
    /**
     * Number of wins for the 8th player on scoreboard
     */
    @FXML
    private Label topEightPlayerWins;
    /**
     * All moves of the 5th player on scoreboard
     */
    @FXML
    private Label topFivePlayerMoves;
    /**
     * Number of wins for the 5th player on scoreboard
     */
    @FXML
    private Label topFivePlayerName;
    /**
     * Number of wins for the 5th player on scoreboard
     */
    @FXML
    private Label topFivePlayerWins;
    /**
     * All moves of the 4th player on scoreboard
     */
    @FXML
    private Label topFourPlayerMoves;
    /**
     * Name of the 4th player on scoreboard
     */
    @FXML
    private Label topFourPlayerName;
    /**
     * Number of wins for the 4th player on scoreboard
     */
    @FXML
    private Label topFourPlayerWins;
    /**
     * All moves of the 9th player on scoreboard
     */
    @FXML
    private Label topNinePlayerMoves;
    /**
     * Name of the 9th player on scoreboard
     */
    @FXML
    private Label topNinePlayerName;
    /**
     * Number of wins for the 9th player on scoreboard
     */
    @FXML
    private Label topNinePlayerWins;
    /**
     * All moves of the 1st player on scoreboard
     */
    @FXML
    private Label topOnePlayerMoves;
    /**
     * Name of the 1st player on scoreboard
     */
    @FXML
    private Label topOnePlayerName;
    /**
     * Number of wins for the 1st player on scoreboard
     */
    @FXML
    private Label topOnePlayerWins;
    /**
     * All moves of the 7th player on scoreboard
     */
    @FXML
    private Label topSevenPlayerMoves;
    /**
     * Name of the 7th player on scoreboard
     */
    @FXML
    private Label topSevenPlayerName;
    /**
     * Number of wins for the 7th player on scoreboard
     */
    @FXML
    private Label topSevenPlayerWins;
    /**
     * All moves of the 6th player on scoreboard
     */
    @FXML
    private Label topSixPlayerMoves;
    /**
     * Name of the 6th player on scoreboard
     */
    @FXML
    private Label topSixPlayerName;
    /**
     * Number of wins for the 6th player on scoreboard
     */
    @FXML
    private Label topSixPlayerWins;
    /**
     * All moves of the 10th player on scoreboard
     */
    @FXML
    private Label topTenPlayerMoves;
    /**
     * Name of the 10th player on scoreboard
     */
    @FXML
    private Label topTenPlayerName;
    /**
     * Number of wins for the 10th player on scoreboard
     */
    @FXML
    private Label topTenPlayerWins;
    /**
     * All moves of the 3rd player on scoreboard
     */
    @FXML
    private Label topThreePlayerMoves;
    /**
     * Name of the 3rd player on scoreboard
     */
    @FXML
    private Label topThreePlayerName;
    /**
     * Number of wins for the 3rd player on scoreboard
     */
    @FXML
    private Label topThreePlayerWins;
    /**
     * All moves of the 2nd player on scoreboard
     */
    @FXML
    private Label topTwoPlayerMoves;
    /**
     * Name of the 2nd player on scoreboard
     */
    @FXML
    private Label topTwoPlayerName;
    /**
     * Number of wins for the 2nd player on scoreboard
     */
    @FXML
    private Label topTwoPlayerWins;
    /**
     * Whether the 30 Labels that make up the scoreboard have been added to the corresponding 3 ArrayList.
     * <p>
     * The scoreboard is made up of 30 Labels, with 10 Labels showing the names of the 10 top players,
     * 10 Labels showing the number of wins by the 10 top players,
     * and 10 Labels showing the number of squares moved by the 10 top players.
     * <p>
     * When the {@link #showPreviousRecord} method and {@link #showNewWholeRecords} method are executed,
     * the 30 Labels in the ArrayList will set the specific values of the records in turn
     * and display them on the scoreboard.
     *
     */
    private boolean isScoreBoardReady = false;
    /**
     * Whether the score board already shows the previous results. True means that it was shown.
     * <p>
     * When the "Start" button or "Show Record" button is clicked, the previous records will be shown.
     */
    private boolean showedPreviousRecord = false;
    /**
     * It stores 10 Labels with the names of 10 top players
     */
    private ArrayList<Label> tenApexGamersName;
    /**
     * It stores 10 Labels with the number of wins by the 10 top players
     */
    private ArrayList<Label> tenApexGamersWins;
    /**
     * It stores 10 Labels with the number of squares moved by the 10 top players
     */
    private ArrayList<Label> tenApexGamersMoves;


    /**
     * Initialize four dices,
     * set two players' moves to 0,
     * initialize the ({@link #winner}),
     * initialize the 3 ArrayList that stores the 30 Labels of the scoreboard
     */
    public ArchitectureController(){
        this.dirDiceOne = new DirDice();
        this.dirDiceTwo = new DirDice();
        this.moveDiceOne = new MoveDice();
        this.moveDiceTwo = new MoveDice();
//        this.playerOneMoves = 0;
//        this.playerTwoMoves = 0;
        this.winner = new Gamer();
        this.tenApexGamersName = new ArrayList<>();
        this.tenApexGamersWins = new ArrayList<>();
        this.tenApexGamersMoves = new ArrayList<>();

        this.game = new Game();
    }

    /**
     * Put the 10 top player name labels into the ArrayList
     */
    public void setTenApexGamersName(){
        this.tenApexGamersName.add(topOnePlayerName);
        this.tenApexGamersName.add(topTwoPlayerName);
        this.tenApexGamersName.add(topThreePlayerName);
        this.tenApexGamersName.add(topFourPlayerName);
        this.tenApexGamersName.add(topFivePlayerName);
        this.tenApexGamersName.add(topSixPlayerName);
        this.tenApexGamersName.add(topSevenPlayerName);
        this.tenApexGamersName.add(topEightPlayerName);
        this.tenApexGamersName.add(topNinePlayerName);
        this.tenApexGamersName.add(topTenPlayerName);
    }

    /**
     * Put the 10 top player wins labels into the ArrayList
     */
    public void setTenApexGamersWins(){
        this.tenApexGamersWins.add(topOnePlayerWins);
        this.tenApexGamersWins.add(topTwoPlayerWins);
        this.tenApexGamersWins.add(topThreePlayerWins);
        this.tenApexGamersWins.add(topFourPlayerWins);
        this.tenApexGamersWins.add(topFivePlayerWins);
        this.tenApexGamersWins.add(topSixPlayerWins);
        this.tenApexGamersWins.add(topSevenPlayerWins);
        this.tenApexGamersWins.add(topEightPlayerWins);
        this.tenApexGamersWins.add(topNinePlayerWins);
        this.tenApexGamersWins.add(topTenPlayerWins);
    }

    /**
     * Put the 10 top player moves labels into the ArrayList
     */
    public void setTenApexGamersMoves(){
        this.tenApexGamersMoves.add(topOnePlayerMoves);
        this.tenApexGamersMoves.add(topTwoPlayerMoves);
        this.tenApexGamersMoves.add(topThreePlayerMoves);
        this.tenApexGamersMoves.add(topFourPlayerMoves);
        this.tenApexGamersMoves.add(topFivePlayerMoves);
        this.tenApexGamersMoves.add(topSixPlayerMoves);
        this.tenApexGamersMoves.add(topSevenPlayerMoves);
        this.tenApexGamersMoves.add(topEightPlayerMoves);
        this.tenApexGamersMoves.add(topNinePlayerMoves);
        this.tenApexGamersMoves.add(topTenPlayerMoves);
    }

    /**
     * Show the name of the player who should move this turn
     * <p>
     * Player one is shown in blue and player two is shown in red
     * @param player player 1 or player 2
     */
    public void setPlayerTurn(Circle player){
        if(player.equals(playerOne)){
            turnInfo.setText("--" + playerOneNameText.getText() + "'s turn--");
            turnInfo.setTextFill(Color.web("#1e90ff"));
        }
        else {
            turnInfo.setText("--" + playerTwoNameText.getText() + "'s turn--");
            turnInfo.setTextFill(Color.web("#ff421f"));
        }
    }

    /**
     * Initialize some content, execute this method after a player wins.
     * <p>
     * Initialize the text of the two name input boxes
     * and other game information (direction of movement, number of squares to move, etc.).
     * <p>
     * Initialize the winner ({@link #winner})
     * <p>
     * Initialize {@link #state} to the very beginning of the game - {@link #NOT_READY}
     */
    public void initAfterFinish(){
        playerOneNameInput.clear();
        playerTwoNameInput.clear();
        turnInfo.setText("<Player X's turn>");
        turnInfo.setTextFill(Color.web("#000000"));
        playerOneNameText.setText("<Player1 Name>");
        playerTwoNameText.setText("<Player2 Name>");
        playerOneDirInfo.setText("<None>");
        playerTwoDirInfo.setText("<None>");
        playerOneMoveInfo.setText("<None>");
        playerTwoMoveInfo.setText("<None>");
        playerOneChoice.setText("<choose?>");
        playerTwoChoice.setText("<choose?>");
        playerOneMoveRecord.setText("<P1 Name> moves:");
        playerTwoMoveRecord.setText("<P2 Name> moves:");
        playerOneMoveRecordNums.setText("0");
        playerTwoMoveRecordNums.setText("0");
        this.winner = new Gamer();

        state = NOT_READY;
        readyText.setText("please enter names to start game");
    }

    /**
     * Update the number of moving squares for different players
     * @param player player 1 or player 2
     */
    public void updatePlayerMoves(Circle player){
        if(player.equals(playerOne)){
            //this.playerOneMoves++;
            game.gamer1.increaseMoves();
            playerOneMoveRecordNums.setText(Integer.toString(this.game.gamer1.moves));
        }
        else {
            //this.playerTwoMoves++;
            game.gamer2.increaseMoves();
            playerTwoMoveRecordNums.setText(Integer.toString(this.game.gamer2.moves));
        }
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
    public boolean isOtherPlayer(Circle player, double layoutX, double layoutY){
        return layoutX == player.getLayoutX() && layoutY == player.getLayoutY();
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
     * Step2: Each step must be judged whether this square is obstacle.
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
     * Step3: If the player consumes all the "ideal" moves, state will update to {@link #PLAYERONE_DICE} / {@link #PLAYERTWO_DICE},
     * at which time player1/2 should roll the dice.
     * @param player the moving player
     * @param dirDice The moving player's dice that determine the direction of movement
     * @param moveDice The moving player's dice that determine the number of squares moved
     */
    public void move(Circle player, DirDice dirDice, MoveDice moveDice){
        int pixelDistance = -75;
        if(dirDice.dir == Direction.forward) ;
        else if(dirDice.dir == Direction.backward)
            pixelDistance = 75;

        int i;
        for(i = 0; i < moveDice.move; i++){
            if(isFire(player.getLayoutX(), player.getLayoutY() + pixelDistance) ||
                    isFence(player.getLayoutX(), player.getLayoutY() + pixelDistance) ||
                    isOtherPlayer(player.equals(playerOne) ? playerTwo: playerOne,
                            player.getLayoutX(), player.getLayoutY() + pixelDistance) ||
                    isEdge(player.getLayoutX(), player.getLayoutY() + pixelDistance)){
                state = player.equals(playerOne) ? PLAYERONE_CHOOSING : PLAYERTWO_CHOOSING;
                break;
            }
            else if(isTarPit(player.getLayoutX(), player.getLayoutY() + pixelDistance)){
                player.setLayoutY(player.getLayoutY() + pixelDistance);
                if(player.equals(playerOne)){
                    playerOneStuck = true;
                    playerOneDirInfo.setText(playerOneNameText.getText() + " was stuck");
                    playerOneMoveInfo.setText("do not move");
                    updatePlayerMoves(playerOne);
                }
                else{
                    playerTwoStuck = true;
                    playerTwoDirInfo.setText(playerTwoNameText.getText() + " was stuck");
                    playerTwoMoveInfo.setText("do not move");
                    updatePlayerMoves(playerTwo);
                }
                state = player.equals(playerOne) ? PLAYERTWO_DICE: PLAYERONE_DICE;
                break;
            }
            else {
                player.setLayoutY(player.getLayoutY() + pixelDistance);
                if(player.equals(playerOne))
                    updatePlayerMoves(playerOne);
                else
                    updatePlayerMoves(playerTwo);
                if(player.getLayoutY() == 0)
                    player.setLayoutY(player.getLayoutY() + 25);
                if(isFinishArea(player.getLayoutX(), player.getLayoutY())){
                    //Finished!
                    String winner = player.equals(playerOne) ? playerOneNameText.getText() : playerTwoNameText.getText();
                    winnerText.setText(winner + " win!");
                    state = SOMEONE_WON;
                    break;
                }
            }
        }
        if(i == moveDice.move){
            if(player.equals(playerOne)){
                state = PLAYERTWO_DICE;
                setPlayerTurn(playerTwo);
            }
            else {
                state = PLAYERONE_DICE;
                setPlayerTurn(playerOne);
            }
        }
        else
            moveDice.move -= i;
    }

    /**
     * The core logic method when a player encounters an obstacle (other player/edge of the board/fire/fence)
     * <p>
     * This method is executed when the "left" or "right" button is clicked
     * and the state is {@link #PLAYERONE_CHOOSING} or {@link #PLAYERTWO_CHOOSING}.
     * <p>
     * Each step must be judged whether this square is obstacle.
     * The player will stop in front of any obstacle (including tar pits).
     * <p>
     * Whether the player consumes all the "ideal" moves or stops in front of an obstacle, end the method.
     * And update the state to {@link #PLAYERONE_DICE} or {@link #PLAYERONE_DICE}.
     * @param player the moving player
     * @param dirDice The moving player's dice that determine the direction of movement
     * @param moveDice The moving player's dice that determine the number of squares moved
     * @param leftOrRight -1 is for left, 1 is for right. Because the x coordinate decreases when moving to the left
     */
    public void moveLeftOrRight(Circle player, DirDice dirDice, MoveDice moveDice, int leftOrRight){
        int pixelDistance = 200 * leftOrRight;
        for(int i = 0; i < moveDice.move; i++){
            if(isFire(player.getLayoutX() + pixelDistance, player.getLayoutY()) ||
                    isTarPit(player.getLayoutX() + pixelDistance, player.getLayoutY()) ||
                    isFence(player.getLayoutX() + pixelDistance, player.getLayoutY()) ||
                    isOtherPlayer(player.equals(playerOne) ? playerTwo: playerOne,
                            player.getLayoutX() + pixelDistance, player.getLayoutY()) ||
                    isEdge(player.getLayoutX() + pixelDistance, player.getLayoutY())){
                break;
            }
            else {
                player.setLayoutX(player.getLayoutX() + pixelDistance);
                if(player.equals(playerOne))
                    updatePlayerMoves(playerOne);
                else
                    updatePlayerMoves(playerTwo);
            }
        }
        state = player.equals(playerOne) ? PLAYERTWO_DICE: PLAYERONE_DICE;
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
     * @param event Hit the "Direction" button of player 1
     */
    @FXML
    void playerOneDir(ActionEvent event) {
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

    /**
     * This method is executed if the "Move" button of player 1 is hit and the state is {@link #PLAYERONE_MOVE}.
     * <p>
     * Step1: Player 1 gets the "ideal" number of squares moved as determined by dice,
     * execute {@link #move(Circle, DirDice, MoveDice)} method.
     * <p>
     * Step2: check current state
     * <p>
     * - {@link #SOMEONE_WON}: execute {@link #doPersistentRecord(Circle)} method, Circle param is PlayerOne.
     * execute {@link #showNewWholeRecords()} method. execute {@link #initAfterFinish()} method.
     * Set the state to {@link #NOT_READY}
     * <p>
     * - {@link #PLAYERONE_CHOOSING}: update some info and wait for the "Left"/"Right"/"Miss" button to be hit
     * <p>
     * - else: If player 1 was stuck in tar pit, update state to {@link #PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link #PLAYERONE_DICE}.
     * @param event Hit the "Move" button of player 1
     */
    @FXML
    void playerOneMove(ActionEvent event) {
        if(state == PLAYERONE_MOVE){
            moveDiceOne.roll();
            playerOneMoveInfo.setText("Number of squares to move: " + moveDiceOne.move);

            move(playerOne, dirDiceOne, moveDiceOne);
            if(state == SOMEONE_WON){
                lastGameWinnerInfo.setText(playerOneNameText.getText() + " won, moved " +
                        this.game.gamer1.moves + " squares.");
                doPersistentRecord(playerOne);
                showNewWholeRecords();
                initAfterFinish();
                state = NOT_READY;
            }
            else if(state == PLAYERONE_CHOOSING){
                playerOneChoice.setText("Please make a choice" + " (moves: " + moveDiceOne.move +")");
            }
            else {
                if(playerTwoStuck){
                    state = PLAYERONE_DICE;
                    playerTwoStuck = false;
                    setPlayerTurn(playerOne);
                }
                else{
                    state = PLAYERTWO_DICE;
                    setPlayerTurn(playerTwo);
                }
            }
        }
    }

    /**
     * This method is executed if the "Left" button of player 1 is hit and the state is {@link #PLAYERONE_CHOOSING}.
     * <p>
     * Step1: execute {@link #moveLeftOrRight(Circle, DirDice, MoveDice, int)} method, Circle param is PlayerOne.
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link #PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link #PLAYERONE_DICE}.
     * @param event Hit the "Left" button of player 1
     */
    @FXML
    void playerOneLeft(ActionEvent event) {
        if(state == PLAYERONE_CHOOSING){
            moveLeftOrRight(playerOne, dirDiceOne, moveDiceOne, -1);
            playerOneChoice.setText("<None>");
            if(playerTwoStuck){
                state = PLAYERONE_DICE;
                playerTwoStuck = false;
                setPlayerTurn(playerOne);
            }
            else{
                state = PLAYERTWO_DICE;
                setPlayerTurn(playerTwo);
            }
        }
    }

    /**
     * This method is executed if the "Miss" button of player 1 is hit and the state is {@link #PLAYERONE_CHOOSING}.
     * <p>
     * Step1: update state to {@link #PLAYERTWO_DICE}
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link #PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link #PLAYERONE_DICE}.
     * @param event Hit the "Miss" button of player 1
     */
    @FXML
    void playerOneMiss(ActionEvent event) {
        if(state == PLAYERONE_CHOOSING){
            state = PLAYERTWO_DICE;
            playerOneChoice.setText("<None>");
            if(playerTwoStuck){
                state = PLAYERONE_DICE;
                playerTwoStuck = false;
                setPlayerTurn(playerOne);
            }
            else{
                state = PLAYERTWO_DICE;
                setPlayerTurn(playerTwo);
            }
        }
    }

    /**
     * This method is executed if the "Right" button of player 1 is hit and the state is {@link #PLAYERONE_CHOOSING}.
     * <p>
     * Step1: execute {@link #moveLeftOrRight(Circle, DirDice, MoveDice, int)} method, Circle param is PlayerOne.
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link #PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link #PLAYERONE_DICE}.
     * @param event Hit the "Right" Button of player 1
     */
    @FXML
    void playerOneRight(ActionEvent event) {
        if(state == PLAYERONE_CHOOSING){
            moveLeftOrRight(playerOne, dirDiceOne, moveDiceOne, 1);
            playerOneChoice.setText("<None>");
            if(playerTwoStuck){
                state = PLAYERONE_DICE;
                playerTwoStuck = false;
                setPlayerTurn(playerOne);
            }
            else{
                state = PLAYERTWO_DICE;
                setPlayerTurn(playerTwo);
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
     * @param event Hit the "Direction" Button of player 2
     */
    @FXML
    void playerTwoDir(ActionEvent event) {
        if(state == PLAYERTWO_DICE){
            dirDiceTwo.roll();
            if(dirDiceTwo.dir != Direction.miss){
                playerTwoDirInfo.setText(playerTwoNameText.getText() + " should go " + dirDiceTwo.dir);
                state = PLAYERTWO_MOVE;
            }
            else{
                if(playerOneStuck){
                    state = PLAYERTWO_DICE;
                    playerOneStuck = false;
                    setPlayerTurn(playerTwo);
                }
                else{
                    playerTwoDirInfo.setText(playerTwoNameText.getText() + " skips this turn");
                    playerTwoMoveInfo.setText("do not move");
                    state = PLAYERONE_DICE;
                    setPlayerTurn(playerOne);
                }
            }
        }
    }

    /**
     * This method is executed if the "Move" button of player 2 is hit and the state is {@link #PLAYERTWO_MOVE}.
     * <p>
     * Step1: Player 1 gets the "ideal" number of squares moved as determined by dice,
     * execute {@link #move(Circle, DirDice, MoveDice)} method.
     * <p>
     * Step2: check current state
     * <p>
     * - {@link #SOMEONE_WON}: execute {@link #doPersistentRecord(Circle)} method, Circle param is PlayerTwo.
     * execute {@link #showNewWholeRecords()} method. execute {@link #initAfterFinish()} method.
     * Set the state to {@link #NOT_READY}
     * <p>
     * - {@link #PLAYERONE_CHOOSING}: update some info and wait for the "Left"/"Right"/"Miss" button to be hit
     * <p>
     * - else: If player 1 was stuck in tar pit, update state to {@link #PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link #PLAYERONE_DICE}.
     * @param event Hit the "Move" Button of player 2
     */
    @FXML
    void PlayerTwoMove(ActionEvent event) {
        if(state == PLAYERTWO_MOVE){
            moveDiceTwo.roll();
            playerTwoMoveInfo.setText("Number of squares to move: " + moveDiceTwo.move);

            move(playerTwo, dirDiceTwo, moveDiceTwo);
            if(state == SOMEONE_WON){
                lastGameWinnerInfo.setText(playerTwoNameText.getText() + " won, moved " +
                        this.game.gamer2.moves + " squares.");
                doPersistentRecord(playerTwo);
                showNewWholeRecords();
                initAfterFinish();
                state = NOT_READY;
            }
            else if(state == PLAYERTWO_CHOOSING){
                playerTwoChoice.setText("Please make a choice" + " (moves: " + moveDiceTwo.move +")");
            }
            else {
                if(playerOneStuck){
                    state = PLAYERTWO_DICE;
                    playerOneStuck = false;
                    setPlayerTurn(playerTwo);
                }
                else{
                    state = PLAYERONE_DICE;
                    setPlayerTurn(playerOne);
                }
            }
        }
    }

    /**
     * This method is executed if the "Left" button of player 2 is hit and the state is {@link #PLAYERTWO_CHOOSING}.
     * <p>
     * Step1: execute {@link #moveLeftOrRight(Circle, DirDice, MoveDice, int)} method, Circle param is PlayerTwo.
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link #PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link #PLAYERONE_DICE}.
     * @param event Hit the "Left" button of player 2
     */
    @FXML
    void playerTwoLeft(ActionEvent event) {
        if(state == PLAYERTWO_CHOOSING){
            moveLeftOrRight(playerTwo, dirDiceTwo, moveDiceTwo, -1);
            playerTwoChoice.setText("<None>");
            if(playerOneStuck){
                state = PLAYERTWO_DICE;
                playerOneStuck = false;
                setPlayerTurn(playerTwo);
            }
            else{
                state = PLAYERONE_DICE;
                setPlayerTurn(playerOne);
            }
        }
    }

    /**
     * This method is executed if the "Miss" button of player 2 is hit and the state is {@link #PLAYERTWO_CHOOSING}.
     * <p>
     * Step1: update state to {@link #PLAYERONE_DICE}
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link #PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link #PLAYERONE_DICE}.
     * @param event Hit the "Miss" button of player 2
     */
    @FXML
    void playerTwoMiss(ActionEvent event) {
        if(state == PLAYERTWO_CHOOSING){
            state = PLAYERONE_DICE;
            playerTwoChoice.setText("<None>");
            if(playerOneStuck){
                state = PLAYERTWO_DICE;
                playerOneStuck = false;
                setPlayerTurn(playerTwo);
            }
            else{
                state = PLAYERONE_DICE;
                setPlayerTurn(playerOne);
            }
        }
    }

    /**
     * This method is executed if the "Right" button of player 2 is hit and the state is {@link #PLAYERTWO_CHOOSING}.
     * <p>
     * Step1: execute {@link #moveLeftOrRight(Circle, DirDice, MoveDice, int)} method, Circle param is PlayerTwo.
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link #PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link #PLAYERONE_DICE}.
     * @param event Hit the "Right" Button of player 2
     */
    @FXML
    void playerTwoRight(ActionEvent event) {
        if(state == PLAYERTWO_CHOOSING){
            moveLeftOrRight(playerTwo, dirDiceTwo, moveDiceTwo, 1);
            playerTwoChoice.setText("<None>");
            if(playerOneStuck){
                state = PLAYERTWO_DICE;
                playerOneStuck = false;
                setPlayerTurn(playerTwo);
            }
            else{
                state = PLAYERONE_DICE;
                setPlayerTurn(playerOne);
            }
        }
    }

    /**
     * When the game is in the NOT_READY state, the game can start after clicking "Start"
     * if all the player name input fields have non-empty/non-all-space names entered.
     * @param event Hit the "Start" Button
     */
    @FXML
    void startGame(ActionEvent event) {
        if(state != NOT_READY)
            return;
        if(playerOneNameInput.getText().trim().equals("") || playerTwoNameInput.getText().trim().equals("")) ;
        else {
            winnerText.setText("");
            playerOneNameText.setText(playerOneNameInput.getText());
            playerTwoNameText.setText(playerTwoNameInput.getText());
            readyText.setText("");
            playerOneChoice.setText("<None>");
            playerTwoChoice.setText("<None>");
            playerOneMoveRecord.setText(playerOneNameInput.getText() + " moves:");
            playerTwoMoveRecord.setText(playerTwoNameInput.getText() + " moves:");
//            this.playerOneMoves = 0;
//            this.playerTwoMoves = 0;
            game = new Game(); // 如果只是moves和wins需要重置，可以这样，如果有下次游戏不会重置的内容，就要改一下
            lastGameWinnerInfo.setText("");
            playerOne.setLayoutX(100);
            playerOne.setLayoutY(675);
            playerTwo.setLayoutX(700);
            playerTwo.setLayoutY(675);
            state = PLAYERONE_DICE;
            setPlayerTurn(playerOne);
        }

        if(!isScoreBoardReady){
            setTenApexGamersName();
            setTenApexGamersWins();
            setTenApexGamersMoves();
            isScoreBoardReady = true;
        }
    }

    /**
     * This method is executed if the "Show Record" button is hit and {@link #showedPreviousRecord} is false.
     * <p>
     * execute {@link #getPreviousGamers()} method,
     * show the previous game records stored in ArrayList of {@link #previousGamers}
     */
    @FXML
    public void showPreviousRecord(){
        if(showedPreviousRecord)
            return;

        if(!isScoreBoardReady){
            setTenApexGamersName();
            setTenApexGamersWins();
            setTenApexGamersMoves();
            isScoreBoardReady = true;
        }

        try {
            this.previousGamers = getPreviousGamers();
            for(int i = 0; i < this.previousGamers.allWinners.size() && i < 10; i++){
                this.tenApexGamersName.get(i).setText(this.previousGamers.allWinners.get(i).name);
                this.tenApexGamersWins.get(i).setText(this.previousGamers.allWinners.get(i).numWins + "");
                this.tenApexGamersMoves.get(i).setText(this.previousGamers.allWinners.get(i).allMoves);
            }
        }catch (Exception exception){
            System.out.println("No Previous Record");
        }

        showedPreviousRecord = true;
    }

    /**
     * This method will be executed after a player wins.
     * <p>
     * Show the updated game records stored in ArrayList of {@link #previousGamers}
     */
    public void showNewWholeRecords(){
        for(int i = 0; i < this.previousGamers.allWinners.size() && i < 10; i++){
            this.tenApexGamersName.get(i).setText(this.previousGamers.allWinners.get(i).name);
            this.tenApexGamersWins.get(i).setText(this.previousGamers.allWinners.get(i).numWins + "");
            this.tenApexGamersMoves.get(i).setText(this.previousGamers.allWinners.get(i).allMoves);
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
    public void doPersistentRecord(Circle player){
        String name = player.equals(playerOne) ? playerOneNameText.getText() : playerTwoNameText.getText();
        int moves = player.equals(playerOne) ? this.game.gamer1.moves : this.game.gamer2.moves;
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

    /**
     * Clear the history of game score board and reset the info of game score board
     * <p>
     * Step1: execute {@link #doClearRecord()} method
     * <p>
     * Step2: reset the info of game score board
     * @param event Hit the "Clear Record" Button
     */
    @FXML
    void clearRecord(ActionEvent event) {
        doClearRecord();

        if(!isScoreBoardReady){
            setTenApexGamersName();
            setTenApexGamersWins();
            setTenApexGamersMoves();
            isScoreBoardReady = true;
        }
        for(int i = 0; i < 10; i++){
            this.tenApexGamersName.get(i).setText("<>");
            this.tenApexGamersWins.get(i).setText("<>");
            this.tenApexGamersMoves.get(i).setText("<>");
        }
    }

}
