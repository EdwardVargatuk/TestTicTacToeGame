package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 02.09.2019 22:38
 *
 * @author Edward
 */
class WinnerSearcherTest {

    private WinnerSearcher winnerSearcher;
    private TicTacToeGame ticTacToeGame;
    
    @BeforeEach
    void setUp() {
        winnerSearcher = new WinnerSearcher();
        ticTacToeGame = new TicTacToeGame();
    }

    @Test
    public void shouldFindWinnerInCaseFirstRow() {
        String expected = "X";
        WinnerSearcher winnerSearcher = new WinnerSearcher();
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);

    }

    @Test
    public void shouldFindWinnerInCaseSecondRow() {
        String expected = "O";
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(5, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseThirdRow() {
        String expected = "X";
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(7, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseFirstColumn() {
        String expected = "X";
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseSecondColumn() {
        String expected = "O";
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(7, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseThirdColumn() {
        String expected = "X";
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(5, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseFromTopLeftToBottomRightDiagonal() {
        String expected = "O";
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerInCaseFromTopRightToBottomLeftDiagonal() {
        String expected = "O";
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAnyWinnerInCaseNotTheSameMarkedCellsOneAfterAnother() {
        String expected = "T";
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(7, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(5, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(ticTacToeGame);

        assertEquals(expected, actual);
    }
}