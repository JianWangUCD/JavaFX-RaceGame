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

import static com.example.comp20300javafxproject.Game.NOT_READY;
import static com.example.comp20300javafxproject.Game.PLAYERONE_DICE;
import static com.example.comp20300javafxproject.Game.PLAYERONE_MOVE;
import static com.example.comp20300javafxproject.Game.PLAYERONE_CHOOSING;
import static com.example.comp20300javafxproject.Game.PLAYERTWO_DICE;
import static com.example.comp20300javafxproject.Game.PLAYERTWO_MOVE;
import static com.example.comp20300javafxproject.Game.PLAYERTWO_CHOOSING;
import static com.example.comp20300javafxproject.Game.SOMEONE_WON;


/**
 * The Controller of JavaFx Project
 */
public class ArchitectureController {
    private Game game;

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
     * initialize the 3 ArrayList that stores the 30 Labels of the scoreboard,
     * initialize the game
     */
    public ArchitectureController(){
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
     * Initialize {@link Game#state} to the very beginning of the game - {@link Game#NOT_READY}
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
        //this.winner = new Gamer();

        game.state = NOT_READY;
        readyText.setText("please enter names to start game");
    }

    /**
     * Update the number of moving squares for different players
     * @param player player 1 or player 2
     */
    public void updatePlayerMoves(Circle player){
        if(player.equals(playerOne)){
            //this.playerOneMoves++;
            //game.gamer1.increaseMoves();
            playerOneMoveRecordNums.setText(Integer.toString(this.game.gamer1.moves));
        }
        else {
            //this.playerTwoMoves++;
            //game.gamer2.increaseMoves();
            playerTwoMoveRecordNums.setText(Integer.toString(this.game.gamer2.moves));
        }
    }


    /**
     * Player 1 throws a die to obtain the direction of movement.
     * <p>
     * Step1: Player 1 throws the dice. If the result is not `miss`, the state is updated to {@link Game#PLAYERONE_MOVE}.
     * <p>
     * Step2: If the result is `miss`
     * <p>
     * - If player 2 is stuck in the tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * <p>
     * - Otherwise, the state is updated to {@link Game#PLAYERTWO_DICE}.
     * @param event Hit the "Direction" button of player 1
     */
    @FXML
    void playerOneDir(ActionEvent event) {
        if(game.state != PLAYERONE_DICE)
            return;
        game.playerOneDir();

        if(game.state == PLAYERONE_MOVE)
            playerOneDirInfo.setText(playerOneNameText.getText() + " should go " + game.dirDiceOne.dir);
        else if(game.state == PLAYERONE_DICE)
            setPlayerTurn(playerOne);
        else if(game.state == PLAYERTWO_DICE){
            playerOneDirInfo.setText(playerOneNameText.getText() + " skips this turn");
            playerOneMoveInfo.setText("do not move");
            setPlayerTurn(playerTwo);
        }
    }

    /**
     * This method is executed if the "Move" button of player 1 is hit and the state is {@link Game#PLAYERONE_MOVE}.
     * <p>
     * Step1: Player 1 gets the "ideal" number of squares moved as determined by dice,
     * execute {@link Game#move(Gamer, DirDice, MoveDice)} method.
     * <p>
     * Step2: check current state
     * <p>
     * - {@link Game#SOMEONE_WON}: will execute {@link Game#doPersistentRecord(Gamer)} method in {@link Game#playerOneMoveSecondly()},
     * Gamer param is PlayerOne.
     * execute {@link #showNewWholeRecords()} method. execute {@link #initAfterFinish()} method.
     * Set the state to {@link Game#NOT_READY}
     * <p>
     * - {@link Game#PLAYERONE_CHOOSING}: update some info and wait for the "Left"/"Right"/"Miss" button to be hit
     * <p>
     * - else: If player 1 was stuck in tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Move" button of player 1
     */
    @FXML
    void playerOneMove(ActionEvent event) {
        if(!game.playerOneMoveFirstly())
            return;
        playerOneMoveInfo.setText("Number of squares to move: " + game.moveDiceOne.move);

        int i;
        int idealMoves = game.moveDiceOne.move; //
        for(i = 0; i < idealMoves; i++){
            game.move(game.gamer1, game.dirDiceOne, game.moveDiceOne);
            if(game.state == PLAYERONE_CHOOSING)
                break;
            if(game.state == PLAYERTWO_DICE)
                break;

            playerOne.setLayoutY(game.gamer1.layoutY);
            updatePlayerMoves(playerOne); //playertwo这里要用playertwo
            if(game.state == SOMEONE_WON){
                String winner = playerOneNameText.getText(); //playertwo这里要用playertwo
                winnerText.setText(winner + " win!");
                break; //
            }
        }
        //紧接着的两个if是不是保留一个即可？此时应该保留if(game.playerOneStuck)？
        if(game.playerOneStuck){
            playerOne.setLayoutY(game.gamer1.layoutY);
            playerOneDirInfo.setText(playerOneNameText.getText() + " was stuck");
            playerOneMoveInfo.setText("do not move");
            updatePlayerMoves(playerOne);
        }
//        if(game.playerTwoStuck){
//            playerTwo.setLayoutY(game.gamer2.layoutY);
//            playerTwoDirInfo.setText(playerTwoNameText.getText() + " was stuck");
//            playerTwoMoveInfo.setText("do not move");
//            updatePlayerMoves(playerTwo);
//        }
        if(i == idealMoves){ // idealMoves合适吧？
            game.state = PLAYERTWO_DICE;
            setPlayerTurn(playerTwo);
        }


        game.playerOneMoveSecondly();
        if(game.state == NOT_READY){
            lastGameWinnerInfo.setText(playerOneNameText.getText() + " won, moved " +
                    game.gamer1.moves + " squares.");
            showNewWholeRecords(); // 可能需要改动，函数内部改动？
            initAfterFinish();
        }
        else if(game.state == PLAYERONE_CHOOSING)
            playerOneChoice.setText("Please make a choice" + " (moves: " + game.moveDiceOne.move +")");
        else {
            if(game.state == PLAYERONE_DICE)
                setPlayerTurn(playerOne);
            if(game.state == PLAYERTWO_DICE)
                setPlayerTurn(playerTwo);
        }
    }

    /**
     * This method is executed if the "Left" button of player 1 is hit and the state is {@link Game#PLAYERONE_CHOOSING}.
     * <p>
     * Step1: execute {@link Game#moveLeftOrRight(Gamer, DirDice, MoveDice, int)} method, Gamer param is PlayerOne.
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Left" button of player 1
     */
    @FXML
    void playerOneLeft(ActionEvent event) {
        if(game.state == PLAYERONE_CHOOSING){

            for(int i = 0; i < game.moveDiceOne.move; i++){
                if(!game.moveLeftOrRight(game.gamer1, game.dirDiceOne, game.moveDiceOne, -1))
                    break;
                else {
                    playerOne.setLayoutX(game.gamer1.layoutX); // playertwo的话这里是playertwo
                    updatePlayerMoves(playerOne); // playertwo的话这里是playertwo
                }
            }
            game.state = PLAYERTWO_DICE; // playertwo的话这里是PLAYERONE_DICE

            playerOneChoice.setText("<None>");
            if(game.playerTwoStuck){
                game.state = PLAYERONE_DICE;
                game.playerTwoStuck = false;
                setPlayerTurn(playerOne);
            }
            else{
                game.state = PLAYERTWO_DICE;
                setPlayerTurn(playerTwo);
            }
        }
    }

    /**
     * This method is executed if the "Miss" button of player 1 is hit and the state is {@link Game#PLAYERONE_CHOOSING}.
     * <p>
     * Step1: update state to {@link Game#PLAYERTWO_DICE}
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Miss" button of player 1
     */
    @FXML
    void playerOneMiss(ActionEvent event) {
        if(game.state == PLAYERONE_CHOOSING){
            game.state = PLAYERTWO_DICE;
            playerOneChoice.setText("<None>");
            if(game.playerTwoStuck){
                game.state = PLAYERONE_DICE;
                game.playerTwoStuck = false;
                setPlayerTurn(playerOne);
            }
            else{
                game.state = PLAYERTWO_DICE;
                setPlayerTurn(playerTwo);
            }
        }
    }

    /**
     * This method is executed if the "Right" button of player 1 is hit and the state is {@link Game#PLAYERONE_CHOOSING}.
     * <p>
     * Step1: execute {@link Game#moveLeftOrRight(Gamer, DirDice, MoveDice, int)} method, Gamer param is PlayerOne.
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Right" Button of player 1
     */
    @FXML
    void playerOneRight(ActionEvent event) {
        if(game.state == PLAYERONE_CHOOSING){

            for(int i = 0; i < game.moveDiceOne.move; i++){
                if(!game.moveLeftOrRight(game.gamer1, game.dirDiceOne, game.moveDiceOne, 1))
                    break;
                else {
                    playerOne.setLayoutX(game.gamer1.layoutX); // playertwo的话这里是playertwo
                    updatePlayerMoves(playerOne);  // playertwo的话这里是playertwo
                }
            }
            game.state = PLAYERTWO_DICE; // playertwo的话这里是PLAYERONE_DICE

            playerOneChoice.setText("<None>");
            if(game.playerTwoStuck){
                game.state = PLAYERONE_DICE;
                game.playerTwoStuck = false;
                setPlayerTurn(playerOne);
            }
            else{
                game.state = PLAYERTWO_DICE;
                setPlayerTurn(playerTwo);
            }
        }
    }

    /**
     * Player 2 throws a die to obtain the direction of movement.
     * <p>
     * Step1: Player 2 throws the dice. If the result is not `miss`, the state is updated to {@link Game#PLAYERTWO_MOVE}.
     * <p>
     * Step2: If the result is `miss`
     * <p>
     * - If player 1 is stuck in the tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * <p>
     * - Otherwise, the state is updated to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Direction" Button of player 2
     */
    @FXML
    void playerTwoDir(ActionEvent event) {
        if(game.state != PLAYERTWO_DICE)
            return;
        game.playerTwoDir();

        if(game.state == PLAYERTWO_MOVE)
            playerTwoDirInfo.setText(playerTwoNameText.getText() + " should go " + game.dirDiceTwo.dir);
        else if(game.state == PLAYERTWO_DICE)
            setPlayerTurn(playerTwo);
        else if(game.state == PLAYERONE_DICE){
            playerTwoDirInfo.setText(playerTwoNameText.getText() + " skips this turn");
            playerTwoMoveInfo.setText("do not move");
            setPlayerTurn(playerOne);
        }
    }

    /**
     * This method is executed if the "Move" button of player 2 is hit and the state is {@link Game#PLAYERTWO_MOVE}.
     * <p>
     * Step1: Player 2 gets the "ideal" number of squares moved as determined by dice,
     * execute {@link Game#move(Gamer, DirDice, MoveDice)} method.
     * <p>
     * Step2: check current state
     * <p>
     * - {@link Game#SOMEONE_WON}: execute {@link Game#doPersistentRecord(Gamer)} method in {@link Game#playerTwoMoveSecondly()},
     * Gamer param is PlayerTwo.
     * execute {@link #showNewWholeRecords()} method. execute {@link #initAfterFinish()} method.
     * Set the state to {@link Game#NOT_READY}
     * <p>
     * - {@link Game#PLAYERONE_CHOOSING}: update some info and wait for the "Left"/"Right"/"Miss" button to be hit
     * <p>
     * - else: If player 1 was stuck in tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Move" Button of player 2
     */
    @FXML
    void PlayerTwoMove(ActionEvent event) {
        if(!game.playerTwoMoveFirstly())
            return;
        playerTwoMoveInfo.setText("Number of squares to move: " + game.moveDiceTwo.move);

        int i;
        int idealMoves = game.moveDiceTwo.move;
        for(i = 0; i < idealMoves; i++){
            game.move(game.gamer2, game.dirDiceTwo, game.moveDiceTwo);
            if(game.state == PLAYERTWO_CHOOSING)
                break;
            if(game.state == PLAYERONE_DICE)
                break;

            playerTwo.setLayoutY(game.gamer2.layoutY);
            updatePlayerMoves(playerTwo); //playertwo这里要用playertwo
            if(game.state == SOMEONE_WON){
                String winner = playerTwoNameText.getText(); //playertwo这里要用playertwo
                winnerText.setText(winner + " win!");
                break; //
            }
        }
        //--->>> 紧接着的两个if是不是保留一个即可？此时应该保留if(game.playerTwoStuck)？
//        if(game.playerOneStuck){
//            playerOne.setLayoutY(game.gamer1.layoutY);
//            playerOneDirInfo.setText(playerOneNameText.getText() + " was stuck");
//            playerOneMoveInfo.setText("do not move");
//            updatePlayerMoves(playerOne);
//        }
        if(game.playerTwoStuck){
            playerTwo.setLayoutY(game.gamer2.layoutY);
            playerTwoDirInfo.setText(playerTwoNameText.getText() + " was stuck");
            playerTwoMoveInfo.setText("do not move");
            updatePlayerMoves(playerTwo);
        }
        if(i == idealMoves){ // idealMoves合适吧？
            game.state = PLAYERONE_DICE;
            setPlayerTurn(playerOne);
        }


        game.playerTwoMoveSecondly();
        if(game.state == NOT_READY){
            lastGameWinnerInfo.setText(playerTwoNameText.getText() + " won, moved " +
                    game.gamer2.moves + " squares.");
            showNewWholeRecords(); // 可能需要改动，函数内部改动？
            initAfterFinish();
        }
        else if(game.state == PLAYERTWO_CHOOSING)
            playerTwoChoice.setText("Please make a choice" + " (moves: " + game.moveDiceTwo.move +")");
        else {
            if(game.state == PLAYERTWO_DICE)
                setPlayerTurn(playerTwo);
            if(game.state == PLAYERONE_DICE)
                setPlayerTurn(playerOne);
        }
    }

    /**
     * This method is executed if the "Left" button of player 2 is hit and the state is {@link Game#PLAYERTWO_CHOOSING}.
     * <p>
     * Step1: execute {@link Game#moveLeftOrRight(Gamer, DirDice, MoveDice, int)} method, Gamer param is PlayerTwo.
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Left" button of player 2
     */
    @FXML
    void playerTwoLeft(ActionEvent event) {
        if(game.state == PLAYERTWO_CHOOSING){

            for(int i = 0; i < game.moveDiceTwo.move; i++){
                if(!game.moveLeftOrRight(game.gamer2, game.dirDiceTwo, game.moveDiceTwo, -1))
                    break;
                else {
                    playerTwo.setLayoutX(game.gamer2.layoutX); // playertwo的话这里是playertwo
                    updatePlayerMoves(playerTwo); // playertwo的话这里是playertwo
                }
            }
            game.state = PLAYERONE_DICE; // playertwo的话这里是PLAYERONE_DICE

            playerTwoChoice.setText("<None>");
            if(game.playerOneStuck){
                game.state = PLAYERTWO_DICE;
                game.playerOneStuck = false;
                setPlayerTurn(playerTwo);
            }
            else{
                game.state = PLAYERONE_DICE;
                setPlayerTurn(playerOne);
            }
        }
    }

    /**
     * This method is executed if the "Miss" button of player 2 is hit and the state is {@link Game#PLAYERTWO_CHOOSING}.
     * <p>
     * Step1: update state to {@link Game#PLAYERONE_DICE}
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Miss" button of player 2
     */
    @FXML
    void playerTwoMiss(ActionEvent event) {
        if(game.state == PLAYERTWO_CHOOSING){
            game.state = PLAYERONE_DICE;
            playerTwoChoice.setText("<None>");
            if(game.playerOneStuck){
                game.state = PLAYERTWO_DICE;
                game.playerOneStuck = false;
                setPlayerTurn(playerTwo);
            }
            else{
                game.state = PLAYERONE_DICE;
                setPlayerTurn(playerOne);
            }
        }
    }

    /**
     * This method is executed if the "Right" button of player 2 is hit and the state is {@link Game#PLAYERTWO_CHOOSING}.
     * <p>
     * Step1: execute {@link Game#moveLeftOrRight(Gamer, DirDice, MoveDice, int)} method, Gamer param is PlayerTwo.
     * <p>
     * Step2: If player 1 was stuck in tar pit, update state to {@link Game#PLAYERTWO_DICE}.
     * If player 2 was stuck in tar pit, update state to {@link Game#PLAYERONE_DICE}.
     * @param event Hit the "Right" Button of player 2
     */
    @FXML
    void playerTwoRight(ActionEvent event) {
        if(game.state == PLAYERTWO_CHOOSING){

            for(int i = 0; i < game.moveDiceTwo.move; i++){
                if(!game.moveLeftOrRight(game.gamer2, game.dirDiceTwo, game.moveDiceTwo, 1))
                    break;
                else {
                    playerTwo.setLayoutX(game.gamer2.layoutX); // playertwo的话这里是playertwo
                    updatePlayerMoves(playerTwo);  // playertwo的话这里是playertwo
                }
            }
            game.state = PLAYERONE_DICE; // playertwo的话这里是PLAYERONE_DICE

            playerTwoChoice.setText("<None>");
            if(game.playerOneStuck){
                game.state = PLAYERTWO_DICE;
                game.playerOneStuck = false;
                setPlayerTurn(playerTwo);
            }
            else{
                game.state = PLAYERONE_DICE;
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
    void startGame(ActionEvent event) { //此函数原版未写在注释里，可查阅原版project
        if(game.state != NOT_READY)
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
            Gamers copyPreviousGamers = game.previousGamers;
            game = new Game(); // 如果只是moves和wins需要重置，可以这样，如果有下次游戏不会重置的内容，就要改一下
            game.previousGamers = copyPreviousGamers;
            game.gamer1.setName(playerOneNameInput.getText());
            game.gamer2.setName(playerTwoNameInput.getText());
            lastGameWinnerInfo.setText("");
            playerOne.setLayoutX(100);
            playerOne.setLayoutY(675);
            playerTwo.setLayoutX(700);
            playerTwo.setLayoutY(675);
            game.state = PLAYERONE_DICE;
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
     * execute {@link Game#getPreviousGamers()} method,
     * show the previous game records stored in ArrayList of {@link Game#previousGamers}
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
            game.previousGamers = game.getPreviousGamers();
            //this.previousGamers = getPreviousGamers();
            for(int i = 0; i < game.previousGamers.allWinners.size() && i < 10; i++){
                this.tenApexGamersName.get(i).setText(game.previousGamers.allWinners.get(i).name);
                this.tenApexGamersWins.get(i).setText(game.previousGamers.allWinners.get(i).numWins + "");
                this.tenApexGamersMoves.get(i).setText(game.previousGamers.allWinners.get(i).allMoves);
            }
        }catch (Exception exception){
            System.out.println("No Previous Record");
        }

        showedPreviousRecord = true;
    }

    /**
     * This method will be executed after a player wins.
     * <p>
     * Show the updated game records stored in ArrayList of {@link Game#previousGamers}
     */
    public void showNewWholeRecords(){
        for(int i = 0; i < game.previousGamers.allWinners.size() && i < 10; i++){
            this.tenApexGamersName.get(i).setText(game.previousGamers.allWinners.get(i).name);
            this.tenApexGamersWins.get(i).setText(game.previousGamers.allWinners.get(i).numWins + "");
            this.tenApexGamersMoves.get(i).setText(game.previousGamers.allWinners.get(i).allMoves);
        }
    }

    /**
     * Clear the history of game score board and reset the info of game score board
     * <p>
     * Step1: execute {@link Game#doClearRecord()} method
     * <p>
     * Step2: reset the info of game score board
     * @param event Hit the "Clear Record" Button
     */
    @FXML
    void clearRecord(ActionEvent event) {
        game.doClearRecord();

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
