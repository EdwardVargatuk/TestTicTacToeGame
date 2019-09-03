package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

/**
 * 25.08.2019 14:03
 *
 * @author Edward
 */
class TicTacToeGameTest {

    private TicTacToeGame ticTacToeGame;

    @BeforeEach
    void setUp() {
        ticTacToeGame = new TicTacToeGame();
    }


    @Test
    public void isAllCellsHaveEmptyStringInNewGameTable() {
        String expected = "";

        String[] cells = ticTacToeGame.getCells();

        for (String cell : cells) {

            assertEquals(expected, cell);
        }
    }

    @Test
    public void shouldGameTableHaveCorrectSize() {
        int expectedSize = 9;

        String[] cells = ticTacToeGame.getCells();

        assertEquals(expectedSize, cells.length);
    }

    @Test
    public void shouldCountOfEmptySquaresInNewGameMatchTableSize() {
        int expectedSize = 9;

        int actualEmptySquaresLeft = ticTacToeGame.getEmptySquaresLeft();

        assertEquals(expectedSize, actualEmptySquaresLeft);
    }

    @Test
    public void shouldPlayerSetSymbolX() {
        String expected = "X";

        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_USER);
        String[] cells = ticTacToeGame.getCells();
        String actual = cells[4];

        assertEquals(expected, actual);
    }

    @Test
    public void shouldComputerSetSymbolO() {
        String expected = "O";

        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        String[] cells = ticTacToeGame.getCells();
        String actual = cells[4];

        assertEquals(expected, actual);
    }

    @Test
    public void shouldPCmakeStepsInRandomCell() {
        int expectedEmptySquares = 6;
        int expectedCountStingO = 3;
        int countStingO = 0;

        ticTacToeGame.computerMove();
        ticTacToeGame.computerMove();
        ticTacToeGame.computerMove();

        String[] cells = ticTacToeGame.getCells();
        for (String cell : cells) {
            if (cell.equals("O")) {
                countStingO++;
            }
        }
        assertEquals(expectedEmptySquares, ticTacToeGame.getEmptySquaresLeft());
        assertEquals(expectedCountStingO, countStingO);
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    public void doesUserCanEnterCorrectValuesOfNumberOfCell(int testInts) {
        String expectedString = "ok";
        String[] cells = ticTacToeGame.getCells();

        String actualString = ticTacToeGame.validateUserInput(String.valueOf(testInts), cells);

        assertEquals(expectedString, actualString);
    }


    @ParameterizedTest
    @ValueSource(strings = {"a", "Y", "?", "ЮЮ", "9", "11"})
    public void doesUserCanEnterAnySymbolExceptNumbersFromZeroToEight(String strings) {
        String expectedString = "Please enter number of cell from 0 to 8";
        String[] cells = ticTacToeGame.getCells();

        String actualString = ticTacToeGame.validateUserInput(strings, cells);

        assertEquals(expectedString, actualString);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 8})
    public void doesUserCanEnterOnlyNumberOfFreeCells(int testInts) {
        String expectedString = "Choose free cell and enter its number";
        String[] cells = ticTacToeGame.getCells();

        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_COMPUTER);
        String actualString = ticTacToeGame.validateUserInput(String.valueOf(testInts), cells);

        assertEquals(expectedString, actualString);
    }


    @Test
    public void shouldGetResultWhenGameOver() {
        String expectedCase1 = "You win!";
        String expectedCase2 = "You loose!";
        String expectedCase3 = "Tie!";

        String actualCase1 = ticTacToeGame.getResult("X");
        String actualCase2 = ticTacToeGame.getResult("O");
        String actualCase3 = ticTacToeGame.getResult("T");
        String actualCase4 = ticTacToeGame.getResult("x");
        assertEquals(expectedCase1, actualCase1);
        assertEquals(expectedCase2, actualCase2);
        assertEquals(expectedCase3, actualCase3);
        assertNull(actualCase4);
    }

}