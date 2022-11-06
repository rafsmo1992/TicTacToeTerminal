package com.tictactoe;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserInterface {

    public String[] introduction(boolean firstGame, String[] oldPlayerNames) {
        PlayerInput playerInput = new PlayerInput();
        if (!firstGame) {
            System.out.println("Would you like to change the player name? y/n");
            String changePlayers = playerInput.getPlayerInput();
            if (!changePlayers.equalsIgnoreCase("y")) return oldPlayerNames;
        }
        System.out.println("If you want to play against the computer, type \"AI\" or \"AAI\" to play against the advanced computer as opponent's name");
        System.out.println("\nEnter the player1 name: ");
        String player1Name = playerInput.getPlayerInput();
        while (true) {
            System.out.println("Enter the player2 name: ");
            String player2Name = playerInput.getPlayerInput();
            if (!player1Name.equals(player2Name)) return new String[]{player1Name, player2Name};
            System.out.println("Name already exists");
        }
    }

    public BoardSettings chooseGameMode(boolean firstGame, BoardSettings oldBoard) {
        PlayerInput playerInput = new PlayerInput();
        if (!firstGame) {
            System.out.println("Change the game mode? y/n");
            String changeMode = playerInput.getPlayerInput();
            if (!changeMode.equalsIgnoreCase("y")) {
                if (oldBoard.getClass().equals(Board10.class)) return new Board10();
                return new Board3();
            }
        }
        System.out.println("Press Enter for standard 3x3 game\nor enter value 10 for 10x10 game ");
        String mode = playerInput.getPlayerInput();
        if (mode.equals("10")) {
            return new Board10();
        }
        return new Board3();
    }

    public boolean playAgain() {
        PlayerInput playerInput = new PlayerInput();
        System.out.println("Play again? y/n");
        String reply = playerInput.getPlayerInput();
        return !reply.equalsIgnoreCase("n");
    }

    public int[] getMove(String player, String[][] board) throws InvalidFieldCoordinatesException {
        PlayerInput playerInput = new PlayerInput();
        System.out.println(player + ", turn\nType the row number\n " +
                " type 0 if you want to exit");
        int row = playerInput.getPlayerInt();
        if (row == 0) {
            if (saveAndExit()) return new int[]{-1, -1};
        }
        System.out.println(player + ", turn\nType the column number");
        int column = playerInput.getPlayerInt();
        if (row < 1 || column < 1 || row > board.length || column > board.length) throw new InvalidFieldCoordinatesException("Wrong coordinates");
        return new int[]{row - 1, column - 1};
    }

    public void displayBoard(String[][] board){
        String rowToDisplay = "      columns";
        String rowToDisplay1 = "      ";
        for (int n = 1; n < board[0].length + 1; n++){
            //rowToDisplay += " col ";
            rowToDisplay1 += " " + n;
        }
        System.out.println(rowToDisplay);
        System.out.println(rowToDisplay1);
        int rowNumber = 0;
        for (String[] row: board) {
            rowNumber++;
            rowToDisplay = "row";
            if (rowNumber <= 9) rowToDisplay += " ";
            rowToDisplay += rowNumber + " |";
            for (String field : row) {
                rowToDisplay += field + "|";
            }
            System.out.println(rowToDisplay);
        }
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public void displayStatistics(Results resultsManager) {
        PlayerInput playerInput = new PlayerInput();
        System.out.println("\nShow the game statistics y/n");
        String showStatistics = playerInput.getPlayerInput();
        if (showStatistics.equalsIgnoreCase("y")) {
            boolean show = true;
            while (show) {
                System.out.println("\nGame statistics:\n\n 1 - wins against players" +
                        "\n 2 - wins against AAI opponent\n 3 - wins against AI opponent" +
                        "\n 4 - losses against other players\n 5 - losses AAI opponent" +
                        "\n 6 - loses against AI opponent\n 7 - all rankings" +
                        "\n 8 - quit the game statistics");
                String choice = playerInput.getPlayerInput();
                switch (choice) {
                    case "1":
                        System.out.println("\n Wins against other players");
                        displayRanking(resultsManager.getWinsAgainstPlayers());
                        break;
                    case "2":
                        System.out.println("\n Wins against the AAI opponent");
                        displayRanking(resultsManager.getWinsAgainstAdvancedComputer());
                        break;
                    case "3":
                        System.out.println("\n Wins against the AI opponent");
                        displayRanking(resultsManager.getWinsAgainstComputer());
                        break;
                    case "4":
                        System.out.println("\n Losses against other players");
                        displayRanking(resultsManager.getLossesAgainstPlayers());
                        break;
                    case "5":
                        System.out.println("\n Losses against the AAI opponent");
                        displayRanking(resultsManager.getLossesAgainstAdvancedComputer());
                        break;
                    case "6":
                        System.out.println("\n Losses against the AI opponent");
                        displayRanking(resultsManager.getLossesAgainstComputer());
                        break;
                    case "7":
                        System.out.println("\n Wins against other players");
                        displayRanking(resultsManager.getWinsAgainstPlayers());
                        System.out.println("\n Wins against the AAI opponent");
                        displayRanking(resultsManager.getWinsAgainstAdvancedComputer());
                        System.out.println("\n Wins against the AI opponent");
                        displayRanking(resultsManager.getWinsAgainstComputer());
                        System.out.println("\n Losses against other players");
                        displayRanking(resultsManager.getLossesAgainstPlayers());
                        System.out.println("\n Losses against the AAI opponent");
                        displayRanking(resultsManager.getLossesAgainstAdvancedComputer());
                        System.out.println("\n Losses against the AI opponent");
                        displayRanking(resultsManager.getLossesAgainstComputer());
                        break;
                    case "10":
                        System.out.println("Are you sure to reset all statistics? y/n");
                        String reset = playerInput.getPlayerInput();
                        if (reset.equalsIgnoreCase("y")) resultsManager.resetStatistics();
                        break;
                    default:
                        show = false;
                }
            }
        }
    }

    public void displayRanking(Map<String, Integer> ranking) {
        List<String> rankingList = ranking.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Entry -> (Entry.getValue() + "        " + Entry.getKey()))
                .toList();
        Collections.reverse(rankingList);
        for (String result: rankingList) {
            System.out.println(result);
        }
    }

    public boolean saveAndExit() {
        PlayerInput playerInput = new PlayerInput();
        System.out.println("Do you want to quit? y/n");
        String reply = playerInput.getPlayerInput();
        return reply.equalsIgnoreCase("y");
    }
}
