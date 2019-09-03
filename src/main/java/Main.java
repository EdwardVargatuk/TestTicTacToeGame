import game.TicTacToeGame;
import game.WinnerSearcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 29.08.2019 10:02
 *
 * @author Edward
 */
public class Main {

    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        WinnerSearcher winnerSearcher = new WinnerSearcher();

        String winner;
        System.out.println("Choose number of cell from 0 to 8 and enter its number");
        do {
            int chosenByUserCell = Main.userMove(ticTacToeGame);
            ticTacToeGame.markCell(chosenByUserCell, TicTacToeGame.PLAYER_USER);
            winner = winnerSearcher.lookForWinner(ticTacToeGame);
            if (!winner.equals("")) {
                break;
            } else {
                ticTacToeGame.computerMove();
                winner = winnerSearcher.lookForWinner(ticTacToeGame);
                if (!winner.equals("")) {
                    break;
                }
            }
            Main.drawCells(ticTacToeGame.getCells());
        } while (ticTacToeGame.getEmptySquaresLeft() > 0);
        Main.drawCells(ticTacToeGame.getCells());
        System.out.println("Game over!");

        String result = ticTacToeGame.getResult(winner);
        if (result != null) {
            System.out.println(result);
        }
    }

    private static void drawCells(String[] cells) {
        System.out.println("     |     |     ");
        for (int i = 0; i < cells.length; i++) {
            if (i != 0) {
                if (i % 3 == 0) {
                    System.out.println();
                    System.out.println("_____|_____|_____");
                    System.out.println("     |     |     ");
                } else
                    System.out.print("|");
            }

            if (cells[i].equals("")) System.out.print("  " + " " + "  ");
            if (cells[i].equals("X")) System.out.print("  X  ");
            if (cells[i].equals("O")) System.out.print("  O  ");
        }
        System.out.println();
        System.out.println("     |     |     ");
    }

    private static int userMove(TicTacToeGame ticTacToeGame) {
        int numberOfCellFromConsole = -1;
        while (numberOfCellFromConsole == -1) {
            numberOfCellFromConsole = Main.getNumberOfCellFromConsole(ticTacToeGame);
        }
        return numberOfCellFromConsole;
    }

    private static int getNumberOfCellFromConsole(TicTacToeGame ticTacToeGame) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String userInput = reader.readLine();
            String validationResult = ticTacToeGame.validateUserInput(userInput, ticTacToeGame.getCells());
            if (validationResult.equals("ok")) {
                return Integer.parseInt(userInput);
            } else {
                System.out.println(validationResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
