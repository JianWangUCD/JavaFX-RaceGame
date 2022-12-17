package com.example.comp20300javafxproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamerTest {

    private static Gamer gamer1;

    @BeforeAll
    static void setUp(){
        gamer1 = new Gamer();
        gamer1.setName("Jian Wang");
        gamer1.setNumWins(1);
        gamer1.setMoves(22);
    }

    @Test
    void testGamerInstance(){
        assertEquals("Jian Wang", gamer1.name);
        assertEquals(1, gamer1.numWins);
        assertEquals(22, gamer1.moves);
    }
}

class GamersTest {
    private Gamer gamer1 = new Gamer();
    private Gamer gamer2 = new Gamer();
    private Gamer gamer3 = new Gamer();
    private Gamer gamer4 = new Gamer();
    private Gamer gamer5 = new Gamer();

    @Test
    void testMergeNewRecord(){
        gamer1.setName("AA");
        gamer1.setNumWins(1);
        gamer1.setMoves(20);
        gamer1.setAllMoves();

        gamer2.setName("BB");
        gamer2.setNumWins(1);
        gamer2.setMoves(17);
        gamer2.setAllMoves();

        gamer4.setName("AA");
        gamer4.setNumWins(1);
        gamer4.setMoves(26);
        gamer4.setAllMoves();

        Gamers allGamers = new Gamers();
        allGamers.allWinners.add(gamer1);
        allGamers.allWinners.add(gamer2);

        allGamers.mergeNewRecord(gamer4);
        assertEquals("AA", allGamers.allWinners.get(0).name);
        assertEquals(2, allGamers.allWinners.get(0).numWins);
        assertEquals("20 | 26", allGamers.allWinners.get(0).allMoves);
    }

    @Test
    void testSortAllRecords(){
        gamer1.setName("AA");
        gamer1.setNumWins(1);
        gamer1.setMoves(20);
        gamer1.setAllMoves();

        gamer2.setName("BB");
        gamer2.setNumWins(1);
        gamer2.setMoves(17);
        gamer2.setAllMoves();

        gamer4.setName("AA");
        gamer4.setNumWins(1);
        gamer4.setMoves(26);
        gamer4.setAllMoves();

        gamer3.setName("Jian Wang");
        gamer3.setNumWins(6);
        gamer5.setName("Riki");
        gamer5.setNumWins(3);

        Gamers allGamers = new Gamers();
        allGamers.allWinners.add(gamer1);
        allGamers.allWinners.add(gamer2);

        allGamers.mergeNewRecord(gamer4);
        allGamers.mergeNewRecord(gamer3);
        allGamers.mergeNewRecord(gamer5);
        allGamers.sortAllRecords();
        assertEquals("Jian Wang", allGamers.allWinners.get(0).name);
        assertEquals("Riki", allGamers.allWinners.get(1).name);
        assertEquals("AA", allGamers.allWinners.get(2).name);
        assertEquals("BB", allGamers.allWinners.get(3).name);
    }
}