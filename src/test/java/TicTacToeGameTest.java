import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

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

    @Ignore
    @Test
    public void callGameProcess() {
    }

    @Test
    public void isAllCellsHaveEmptyStringInNewGameTable() {
        String expected = "";

        String[] cells = ticTacToeGame.getCells();
        ticTacToeGame.startNewGame();

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

        ticTacToeGame.startNewGame();
        int actualEmptySquaresLeft = ticTacToeGame.getEmptySquaresLeft();

        assertEquals(expectedSize, actualEmptySquaresLeft);
    }


    @Test
    public void shouldCallUserMove() {
        TicTacToeGame ticTacToeGameMock = Mockito.mock(TicTacToeGame.class);
        Mockito.when(ticTacToeGameMock.getNumberOfCellFromConsole(new InputStreamReader(System.in))).thenReturn(4);
        Mockito.when(ticTacToeGameMock.getCells()).thenReturn(new String[]{"", "", "", "", "X", "", "", "", ""});
        Mockito.doCallRealMethod().when(ticTacToeGameMock).userMove();
//        Mockito.doCallRealMethod().when(ticTacToeGameMock).markCell(4, "user");

        ticTacToeGameMock.userMove();
        String[] cells = ticTacToeGameMock.getCells();

        assertEquals("X", cells[4]);
        Mockito.verify(ticTacToeGameMock).userMove();
    }

    @Test
    public void shouldPlayerSetSymbolX() {
        String expected = "X";

        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(4, "user");
        String[] cells = ticTacToeGame.getCells();
        String actual = cells[4];

        assertEquals(expected, actual);
    }

    @Test
    public void shouldComputerSetSymbolO() {
        String expected = "O";

        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(4, "pc");
        String[] cells = ticTacToeGame.getCells();
        String actual = cells[4];

        assertEquals(expected, actual);
    }

    @Test
    public void shouldPCmakeStepsInRandomCell() {
        int expectedEmptySquares = 6;
        int expectedCountStingO = 3;
        int countStingO = 0;

        ticTacToeGame.startNewGame();
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
    public void shouldUserSetFromConsoleCorrectNumberOfCell(int testInts) {
        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(testInts).getBytes());
        InputStreamReader inputStreamReader = new InputStreamReader(in);

        ticTacToeGame.startNewGame();
        int numberOfCellFromConsole1 = ticTacToeGame.getNumberOfCellFromConsole(inputStreamReader);

        assertEquals(testInts, numberOfCellFromConsole1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "Y", "?"})
    public void shouldUserGetFromConsoleOnlyNumber(String strings) {
        ByteArrayInputStream in = new ByteArrayInputStream(strings.getBytes());
        InputStreamReader inputStreamReader = new InputStreamReader(in);

        ticTacToeGame.startNewGame();
        int numberOfCellFromConsole1 = ticTacToeGame.getNumberOfCellFromConsole(inputStreamReader);

        assertEquals(-1, numberOfCellFromConsole1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 8})
    public void shouldUserSetFromConsoleOnlyInEmptyCell(int testInts) {
        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(testInts).getBytes());
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(4, "pc");
        ticTacToeGame.markCell(0, "user");
        ticTacToeGame.markCell(8, "pc");
        int numberOfCellFromConsole1 = ticTacToeGame.getNumberOfCellFromConsole(inputStreamReader);

        assertEquals(-1, numberOfCellFromConsole1);
    }


    @Test
    public void shouldFindWinnerInCaseFirstRow() {
        String expected = "X";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(0, "user");
        ticTacToeGame.markCell(1, "user");
        ticTacToeGame.markCell(2, "user");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);

    }

    @Test
    public void shouldFindWinnerInCaseSecondRow() {
        String expected = "O";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(3, "pc");
        ticTacToeGame.markCell(4, "pc");
        ticTacToeGame.markCell(5, "pc");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseThirdRow() {
        String expected = "X";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(6, "user");
        ticTacToeGame.markCell(7, "user");
        ticTacToeGame.markCell(8, "user");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseFirstColumn() {
        String expected = "X";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(0, "user");
        ticTacToeGame.markCell(3, "user");
        ticTacToeGame.markCell(6, "user");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseSecondColumn() {
        String expected = "O";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(1, "pc");
        ticTacToeGame.markCell(4, "pc");
        ticTacToeGame.markCell(7, "pc");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseThirdColumn() {
        String expected = "X";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(2, "user");
        ticTacToeGame.markCell(5, "user");
        ticTacToeGame.markCell(8, "user");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseFromTopLeftToBottomRightDiagonal() {
        String expected = "O";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(0, "pc");
        ticTacToeGame.markCell(4, "pc");
        ticTacToeGame.markCell(8, "pc");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseFromTopRightToBottomLeftDiagonal() {
        String expected = "O";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(2, "pc");
        ticTacToeGame.markCell(4, "pc");
        ticTacToeGame.markCell(6, "pc");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAnyWinnerInCaseNotTheSameMarkedCellsOneAfterAnother() {
        String expected = "T";
        ticTacToeGame.startNewGame();
        ticTacToeGame.markCell(0, "user");
        ticTacToeGame.markCell(2, "pc");
        ticTacToeGame.markCell(1, "user");
        ticTacToeGame.markCell(3, "pc");
        ticTacToeGame.markCell(4, "user");
        ticTacToeGame.markCell(8, "pc");
        ticTacToeGame.markCell(6, "user");
        ticTacToeGame.markCell(7, "pc");
        ticTacToeGame.markCell(5, "user");

        String actual = ticTacToeGame.lookForWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetResultWhenGameOver() {
        String expectedCase1 = "You win!";
        String expectedCase2 = "You loose!";
        String expectedCase3 = "Tie!";

        ticTacToeGame.startNewGame();
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