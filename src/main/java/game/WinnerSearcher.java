package game;

/**
 * 01.09.2019 21:03
 *
 * @author Edward
 */
public class WinnerSearcher {

    public String lookForWinner(TicTacToeGame ticTacToeGame) {

        String[] cells = ticTacToeGame.getCells();
        int emptySquaresLeft = ticTacToeGame.getEmptySquaresLeft();
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

}
