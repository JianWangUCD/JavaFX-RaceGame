package com.example.comp20300javafxproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Multiple players. That is for record board
 */
public class Gamers implements Serializable {
    /**
     * All winning players
     */
    public ArrayList<Gamer> allWinners = new ArrayList<>();

    public ArrayList<Gamer> getAllWinners() {
        return allWinners;
    }

    /**
     * Find the player in the record
     *
     * @param name the player name
     * @return The index of the player in ArrayList. If not found, return -1.
     */
    public int indexOfGamer(String name) {
        for (Gamer gamer : this.allWinners) {
            if (gamer.name.equals(name))
                return this.allWinners.indexOf(gamer);
        }
        return -1;
    }

    /**
     * Put the new winner in all records. If the player has already won,
     * then the number of wins is increased by one and a new moves is recorded
     *
     * @param newWinner Gamer object of new winner
     */
    public void mergeNewRecord(Gamer newWinner) {
        int index = indexOfGamer(newWinner.name);
        if (index == -1) {
            this.allWinners.add(newWinner);
        } else {
            this.allWinners.get(index).numWins++;
            this.allWinners.get(index).allMoves += " | " + newWinner.moves;
        }
    }

    /**
     * Sort all records in descending order according to the number of wins of different players
     */
    public void sortAllRecords() {
        Collections.sort(this.allWinners);
    }
}
