import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestJoc {
    @org.junit.jupiter.api.Test
    void newGame() {
        Joc game = new Joc();

        //create an empty board
        char[][] empty_board = new char[3][3];

        //get our board
        game.newGame();
        char[][] my_board = game.getBoard();

        //test empty board
        Assertions.assertArrayEquals(empty_board, my_board);

        //test player1 turn
        Assertions.assertEquals(1, game.getTurn());

        //test player2 turn
        Assertions.assertEquals(2, game.getTurn() + 1);
    }

    @org.junit.jupiter.api.Test
    void play() throws ExecutionControl.NotImplementedException {
        Joc game1 = new Joc();

        char[][] board = new char[3][3];

        short row = 0;
        short column = 0;

        //test player1
        board[row][column] = 'X';
        game1.play(row, column);
        Assertions.assertArrayEquals(board, game1.getBoard());

        //test player2
        board[row][column] = 'X';
        column = 1;
        board[row][column] = 'O';
        game1.play(row, column);
        Assertions.assertArrayEquals(board, game1.getBoard());
    }

}
