package com.tictactoe;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTestSuite {
    @Nested
    class testBoard3{
        @Test
        void testAddMove() throws SpaceTakenException {
            //Given
            Board3 board3 = new Board3();

            //When
            board3.addMove(0, 0, "X");
            board3.addMove(1, 1, "O");

            //Then
            assertAll(
                    () -> assertDoesNotThrow(() -> board3.addMove(0, 2, "X")),
                    () -> assertThrows(SpaceTakenException.class, () -> board3.addMove(0, 2, "O")),
                    () -> assertEquals("X", board3.getBoard()[0][2])
            );
        }
        @Test
        void testVictoryHorizontal() throws SpaceTakenException{
            //Given
            Board3 board3 = new Board3();
            board3.addMove(2, 0, "X");
            board3.addMove(1, 1, "O");
            board3.addMove(2, 1, "X");
            board3.addMove(0, 0, "O");
            board3.addMove(2, 2, "X");

            //When
            boolean victoryX = board3.victory(2, 2, "X");
            boolean victoryO = board3.victory(0, 0, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)
            );
        }
        @Test
        void testVictoryVertical() throws SpaceTakenException{
            //Given
            Board3 board3 = new Board3();
            board3.addMove(2, 0, "X");
            board3.addMove(1, 1, "O");
            board3.addMove(2, 2, "X");
            board3.addMove(0, 1, "O");
            board3.addMove(0, 2, "X");
            board3.addMove(2, 1, "O");

            //When
            boolean victoryX = board3.victory(0, 2, "X");
            boolean victoryO = board3.victory(2, 1, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryX),
                    () -> assertTrue(victoryO)

            );
        }
        @Test
        void testVictoryDiagonalLeft() throws SpaceTakenException{
            //Given
            Board3 board3 = new Board3();
            board3.addMove(0, 0, "X");
            board3.addMove(0, 1, "O");
            board3.addMove(2, 2, "X");
            board3.addMove(0, 2, "O");
            board3.addMove(1, 1, "X");

            //When
            boolean victoryX = board3.victory(1, 1, "X");
            boolean victoryO = board3.victory(0, 2, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)

            );
        }
        @Test
        void testVictoryDiagonalRight() throws SpaceTakenException{
            //Given
            Board3 board3 = new Board3();
            board3.addMove(0, 0, "X");
            board3.addMove(1, 1, "O");
            board3.addMove(1, 0, "X");
            board3.addMove(0, 2, "O");
            board3.addMove(2, 2, "X");
            board3.addMove(2, 0, "O");

            //When
            boolean victoryX = board3.victory(0, 2, "X");
            boolean victoryO = board3.victory(2, 1, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryX),
                    () -> assertTrue(victoryO)

            );
        }
    }
    @Nested
    class testBigBoard{
        @Test
        void testAddMove() throws SpaceTakenException{
            //Given
            Board10 board10 = new Board10();

            //When
            board10.addMove(5, 5, "X");
            board10.addMove(5, 6, "O");
            board10.addMove(6, 5, "X");
            board10.addMove(6, 6, "O");
            board10.addMove(6, 7, "X");
            board10.addMove(6, 4, "O");
            board10.addMove(6, 3, "X");
            board10.addMove(7, 4, "O");

            //Then
            assertAll(
                    () -> assertDoesNotThrow(() -> board10.addMove(8, 5, "X")),
                    () -> assertThrows(SpaceTakenException.class, () -> board10.addMove(6, 7, "O")),
                    () -> assertEquals("X", board10.getBoard()[6][3]),
                    () -> assertEquals("O", board10.getBoard()[6][6])
            );
        }
        @Test
        void testVictoryHorizontal() throws SpaceTakenException{
            //Given
            Board10 board10 = new Board10();
            board10.addMove(4, 0, "X");
            board10.addMove(1, 1, "O");
            board10.addMove(4, 1, "X");
            board10.addMove(1, 0, "O");
            board10.addMove(4, 2, "X");
            board10.addMove(1, 2, "O");
            board10.addMove(4, 3, "X");
            board10.addMove(1, 3, "O");
            board10.addMove(4, 4, "X");

            //When
            boolean victoryX = board10.victory(4, 4, "X");
            boolean victoryO = board10.victory(1, 3, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)
            );
        }
        @Test
        void testVictoryVertical() throws SpaceTakenException{
            //Given
            Board10 board10 = new Board10();
            board10.addMove(4, 0, "X");
            board10.addMove(1, 1, "O");
            board10.addMove(3, 0, "X");
            board10.addMove(2, 1, "O");
            board10.addMove(2, 0, "X");
            board10.addMove(3, 1, "O");
            board10.addMove(0, 0, "X");
            board10.addMove(4, 1, "O");
            board10.addMove(5, 0, "X");
            board10.addMove(6, 1, "O");
            board10.addMove(7, 0, "X");
            board10.addMove(7, 1, "O");
            board10.addMove(1, 0, "X");

            //When
            boolean victoryX = board10.victory(1, 0, "X");
            boolean victoryO = board10.victory(7, 1, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)
            );
        }
        @Test
        void testVictoryDiagonalLeft() throws SpaceTakenException{
            //Given
            Board10 board10 = new Board10();
            board10.addMove(4, 0, "X");
            board10.addMove(5, 0, "O");
            board10.addMove(5, 1, "X");
            board10.addMove(6, 1, "O");
            board10.addMove(6, 2, "X");
            board10.addMove(7, 2, "O");
            board10.addMove(7, 3, "X");
            board10.addMove(8, 3, "O");
            board10.addMove(6, 3, "X");
            board10.addMove(8, 4, "O");
            board10.addMove(7, 4, "X");
            board10.addMove(9, 4, "O");

            //When
            boolean victoryX = board10.victory(7, 4, "X");
            boolean victoryO = board10.victory(9, 4, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryX),
                    () -> assertTrue(victoryO)
            );
        }
        @Test
        void testVictoryDiagonalRight() throws SpaceTakenException{
            //Given
            Board10 board10 = new Board10();
            board10.addMove(8, 0, "X");
            board10.addMove(5, 0, "O");
            board10.addMove(7, 1, "X");
            board10.addMove(4, 1, "O");
            board10.addMove(6, 2, "X");
            board10.addMove(3, 2, "O");
            board10.addMove(5, 3, "X");
            board10.addMove(2, 3, "O");
            board10.addMove(3, 5, "X");
            board10.addMove(0, 4, "O");
            board10.addMove(4, 4, "X");

            //When
            boolean victoryX = board10.victory(4, 4, "X");
            boolean victoryO = board10.victory(0, 4, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)
            );
        }
    }
}
