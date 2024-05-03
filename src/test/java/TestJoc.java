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


    @ParameterizedTest
    @CsvSource({"0,0","0,1","0,2","1,0","1,1","1,2","2,0","2,1","2,2"})
    void play_player1(short row, short column) throws ExecutionControl.NotImplementedException {
        Joc game = new Joc();
        char[][] board = new char[3][3];

        //Asign X in the indicated position
        board[row][column] = 'X';
        //make player1 play
        game.play(row,column);
        //test
        Assertions.assertArrayEquals(board,game.getBoard());
    }

    @ParameterizedTest
    @CsvSource({"0,0","0,1","0,2","1,0","1,1","1,2","2,0","2,1","2,2"})
    void play_player2(short row, short column) throws ExecutionControl.NotImplementedException {
        Joc game = new Joc();
        char[][] board = new char[3][3];

        //preparations
        //make player1 play
        game.newGame();
        game.play((short)1,(short)1);
        board[1][1] = 'X';

        //testing player2
        //Asign X in the indicated position
        if (board[row][column] != 'X'){
            board[row][column] = 'O';
        }
        //make the player2 play
        game.play(row,column);
        //test
        Assertions.assertArrayEquals(board,game.getBoard());
    }
}
