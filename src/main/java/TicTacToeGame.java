import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * 25.08.2019 14:00
 *
 * @author Edward
 */
public class TicTacToeGame {


    private final String[] cells = new String[9];
    private int emptySquaresLeft;


    public String[] getCells() {
        return cells;
    }

    public int getEmptySquaresLeft() {
        return emptySquaresLeft;
    }

    public void startNewGame() {
        emptySquaresLeft = 9;
        for (int i = 0; i < cells.length; i++) {
            cells[i] = "";
        }
    }


    public void markCell(int cellIndex, String player) {
        switch (player) {
            case "user":
                cells[cellIndex] = "X";
                break;
            case "pc":
                cells[cellIndex] = "O";
                break;
        }
        emptySquaresLeft--;
    }


    public void userMove() {
        int numberOfCellFromConsole=-1;
        while (numberOfCellFromConsole==-1) {
            numberOfCellFromConsole = getNumberOfCellFromConsole(new InputStreamReader(System.in));
        }
        markCell(numberOfCellFromConsole, "user");
    }

    public void gameProcess() {
        startNewGame();
        String winner;
        System.out.println("Choose number of cell from 0 to 9 and enter its number");
        do {
            userMove();
            winner = lookForWinner();
            if (!"".equals(winner)) {
                endTheGame();
                break;
            } else {
                computerMove();
                winner = lookForWinner();
                if (!"".equals(winner)) {
                    endTheGame();
                    break;
                }

            }
            drawCells();
        } while (emptySquaresLeft > 0);
        drawCells();

        String result = getResult(winner);
        if (result != null) {
            System.out.println(result);
        }
    }

    private void drawCells() {
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

    private void endTheGame() {
        System.out.println("Game over!");
    }

    public String lookForWinner() {
        String theWinner = "";


        if (!cells[0].equals("") && cells[0].equals(cells[1]) && cells[0].equals(cells[2])) {
            theWinner = cells[0];
        } else if (!cells[3].equals("") && cells[3].equals(cells[4]) && cells[3].equals(cells[5])) {
            theWinner = cells[3];
        } else if (!cells[6].equals("") && cells[6].equals(cells[7]) && cells[6].equals(cells[8])) {
            theWinner = cells[6];
        } else if (!cells[0].equals("") && cells[0].equals(cells[3]) && cells[0].equals(cells[6])) {
            theWinner = cells[0];
        } else if (!cells[1].equals("") && cells[1].equals(cells[4]) && cells[1].equals(cells[7])) {
            theWinner = cells[1];
        } else if (!cells[2].equals("") && cells[2].equals(cells[5]) && cells[2].equals(cells[8])) {
            theWinner = cells[2];
        } else if (!cells[0].equals("") && cells[0].equals(cells[4]) && cells[0].equals(cells[8])) {
            theWinner = cells[0];
        } else if (!cells[2].equals("") && cells[2].equals(cells[4]) && cells[2].equals(cells[6])) {
            theWinner = cells[2];
        }
        if (emptySquaresLeft == 0 && theWinner.equals("")) {
            return "T";
        }

        return theWinner;
    }

    public void computerMove() {
        int selectedSquare;
        selectedSquare = getRandomSquare();

        markCell(selectedSquare, "pc");

    }

    private int getRandomSquare() {
        boolean gotEmptySquare = false;

        int selectedSquare;
        Random random = new Random();

        do {
            selectedSquare = random.nextInt(9);

            if (cells[selectedSquare].equals("")) {
                gotEmptySquare = true;
            }
        } while (!gotEmptySquare);

        return selectedSquare;
    }

    public int getNumberOfCellFromConsole(InputStreamReader inputStreamReader){
        BufferedReader reader = new BufferedReader(inputStreamReader);
        try {
           int n = Integer.parseInt(reader.readLine());
            if (n >= 0 && n < cells.length && cells[n].equals("")) {
                return n;
            }
            System.out.println("Choose free cell and enter its number");
        } catch (NumberFormatException e) {
            System.out.println("Please enter the number");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getResult(String winner) {
        switch (winner) {
            case "X":
                return "You win!";
            case "O":
                return "You loose!";
            case "T":
                return "Tie!";
            default:
                return null;
        }
    }


}
