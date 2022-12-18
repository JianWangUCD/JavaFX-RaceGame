# COMP20300 JavaFX Project - Simon’s Race

## Student Name: 

Jian Wang

## Student ID: 

21213011

## How to run the game

### Step 1: Clone / Copy this project to your PC

### Step 2: Run the Project

Note that there are three images in the project, Fence.png, Fire.png and TarPit.png. Since their paths in the fxml file are absolute paths on my computer, you may need to change their paths to display the images at the start of the game.

### Step 3: Click the `Start` button or `Show Record` button

**Note**: Please enter the names of Player 1 and Player 2 **before** clicking the `Start` button, they must be **non-empty** and **not full spaces**.

`Show Record` button: show previous game records on the scoreboard (PS: You can press the button at any time during the game (before a new winner appears) to show previous records)

`Start` button: Enter two names and start the game.

### Step 4: Read the following information

**Note**: The turn information is displayed in the user interface as "--<player1/2 name>'s turn--". If it is **player one**'s turn, it will be **BLUE** text, if it is **player two**'s turn, it will be **RED** text.

Player 1 is a blue circle in the game. In the user interface, Player 1's name is also in blue. Player 2 is a red circle in the game. In the user interface, Player 2's name is also in red.

In the user interface, the left side is the operation area for player 1 and the right side is the operation area for player 2.

There are 5 types of obstacles in the game: the edge of board, another player, fire, tar pits, and fence.

Fire is a common obstacle, the player can not enter the fire, need to go around the way forward. 

**Tar pits** are accessible obstacles. If a player passes through a tar pit, he or she will be stuck. **This player will skip the next turn**.

A fence is an obstacle that covers two square spaces, the player can not enter the fence, need to go around the way forward.

#### The usual flow of the game is
① Click the `Direction` button on the left to get the direction of player 1's movement

② Click the `Move` button on the left to get the "ideal" number of squares moved by player 1

③ When stopped by an obstacle, select one of the buttons `Left`,`Right`,`Miss` below.

`Left` - move to the left until player 1 stops in front of any obstacle

`Right` - move to the right until player 1 stops in front of any obstacle

`Miss` - Skip this turn

④ Click the `Direction` button on the right to get the direction of player 2's movement

⑤ Click the `Move` button on the right to get the "ideal" number of squares for player 2

⑥ When stopped by an obstacle, select one of the buttons `Left`,`Right`,`Miss` below.

`Left` - move to the left until player 2 stops in front of any obstacle

`Right` - move to the right until player 2 stops in front of any obstacle

`Miss` - Skip this turn

⑦ Repeat all the above steps until a player enters the "Finish Area". Announce this player as the winner.

### Step 5: [Player One's Turn] Follow the text prompts on the window to play

### Step 6: [Player Two's Turn] Follow the text prompts on the window to play

### Step 7: Repeat step 5 or 6 until a player wins

### Step 8: The scoreboard will immediately update and show the new full records

The scoreboard shows records of the top ten players, who are listed in descending order of number of games won.

### Step 9: Start from step 3 and play in order until you want to quit the game



## Unit tests

### GameTest

`Game` class is the core logic class of the project. There are 25 tests in GameTest.java.

sampleTest - A simple test of throwing dice to detect whether the state of the game is normal or not.

test 1 - player 1 goes forward and moves 2 squares

test 2 - player 2 goes forward and moves 3 squares

test 3 - player 1 meets fire

test 4 - player 1 meets fire and move right

test 5 - player 1 meets fire and move left, the left side is the left edge of board

test 6 - player 1 meets fire and then choose to miss this turn

test 7 - player 2 meets fire

test 8 - player 2 meets fire and move left

test 9 - player 2 meets fire and move right, the right side is the right edge of board

test 10 - player 2 meets fire and then choose to miss this turn

test 11 - player 1 meets tar pit

test 12 - player 2 meets tar pit

test 13 - player 1 meets player 2

test 14 - player 2 meets player 1

test 15 - player 1 meets the edge of board

test 16 - player 2 meets the edge of board

test 17 - player 1 meets fence

test 18 - player 2 meets fence

test 19 - The player 1 enters the finish area

test 20 - The player 2 enters the finish area

test 21 - player 1 wins (no need to test player 2 wins, the logic is same)

test 22 - Persistent Record

test 23 - Clear the record

test 24 - New winners are recorded persistently

### GamerTest

testGamerInstance - test for creating new instance

### GamersTest

testMergeNewRecord - test for merging new winner in total record

testSortAllRecords - test for sorting the total records by number of wins of winners

### DirDiceTest

roll - test for rolling the dice to get the direction of movement

### MoveDiceTest

roll - test for rolling the dice to get the ideal number of squares moved
