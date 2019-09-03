package game;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 25.08.2019 14:00
 *
 * @author Edward
 */
public class TicTacToeGame {


    private final String[] cells = new String[9];
    private int emptySquaresLeft;
    public static final String PLAYER_USER = "user";
    public static final String PLAYER_COMPUTER = "pc";
    private final String VALIDATION_REGEX = "[0-8]";


    public String[] getCells() {
        return cells;
    }

    public int getEmptySquaresLeft() {
        return emptySquaresLeft;
    }

    public TicTacToeGame() {
        this.emptySquaresLeft = 9;
        for (int i = 0; i < cells.length; i++) {
            cells[i] = "";
        }
    }

    public void markCell(int cellIndex, String player) {
        switch (player) {
            case PLAYER_USER:
                cells[cellIndex] = "X";
                break;
            case PLAYER_COMPUTER:
                cells[cellIndex] = "O";
                break;
        }
        emptySquaresLeft--;
    }

    public void computerMove() {
        int selectedSquare;
        selectedSquare = getRandomSquare();

        markCell(selectedSquare, PLAYER_COMPUTER);

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

    public String validateUserInput(String userInput, String[] cells) {
        Pattern pattern = Pattern.compile(VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches()) {
            Integer numberOfCell = Integer.valueOf(userInput);
            if (cells[numberOfCell].equals("")) {
                return "ok";
            } else {
                return "Choose free cell and enter its number";
            }
        } else {
            return "Please enter number of cell from 0 to 8";
        }
    }

}
