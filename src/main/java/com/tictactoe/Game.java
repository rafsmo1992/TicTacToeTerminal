package com.tictactoe;

import java.io.Serializable;
import java.util.InputMismatchException;

public class Game implements Serializable {
    private int movesMade = 0;
    private int[] lastMove = null;
    private int currentPlayer = 0;


    public int playGame(String[] players, BoardSettings board) {
        UserInterface userInterface = new UserInterface();
        ComputerPlayer computerPlayer = new ComputerPlayer();
        String[] symbols = {"X", "O"};
        boolean stillPlaying = true;

        while (stillPlaying) {
            try {
                userInterface.displayBoard(board.getBoard());
                int[] move;
                if (players[currentPlayer].equalsIgnoreCase("AI")) {
                    move = computerPlayer.makeMove(board.getBoard());
                } else if (players[currentPlayer].equalsIgnoreCase("AAI")) {
                    move = computerPlayer.makeMove(board.getBoard(), lastMove, symbols[(currentPlayer + 1)%2]);
            }else {
                    move = userInterface.getMove(players[currentPlayer], board.getBoard());
                }
                if (move[0] == -1) return -4;
                board.addMove(move[0], move[1], symbols[currentPlayer]);
                userInterface.displayBoard(board.getBoard());
                movesMade ++;
                lastMove = move;
                if (board.victory(move[0], move[1], symbols[currentPlayer])) {
                    userInterface.displayMessage(players[currentPlayer] + " won the game!");
                    return currentPlayer;
                }
                if (movesMade == board.getMaxMoves()) {
                    userInterface.displayMessage("Stalemate");
                    stillPlaying = false;
                }
                currentPlayer = (currentPlayer + 1) % 2;
            }catch (InputMismatchException e){
                userInterface.displayMessage("row and column must be numbers " + e);
            }catch (InvalidFieldCoordinatesException e){
                userInterface.displayMessage(e + "\nRow and column number must be 1, 2 or 3");
            }catch (SpaceTakenException e){
                userInterface.displayMessage("Unable to make this move: " + e);

            }
        }
        return -1;
    }
}