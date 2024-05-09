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
    void play_player1(short row, short column) {
        Joc game = new Joc();
        char[][] board = new char[3][3];

        //Asign X in the indicated position
        board[row][column] = 'X';
        //make player1 play
        game.newGame();
        game.play(row,column);
        //test
        Assertions.assertArrayEquals(board,game.getBoard());
    }

    @ParameterizedTest
    @CsvSource({"0,0","0,1","0,2","1,0","1,1","1,2","2,0","2,1","2,2"})
    void play_player2(short row, short column) {
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

    @ParameterizedTest
    @CsvSource({"0,0","0,1","0,2","1,0","1,1","1,2","2,0","2,1","2,2"})
    void winning_play_empty_board(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        Assertions.assertFalse(game.winning_play(row, column));

    }

    @ParameterizedTest
    @CsvSource({"0,0","0,1","0,2","1,0","1,1","1,2","2,0","2,1","2,2"})
    void winning_play_one_position(short row,short column) {
        Joc game = new Joc();

        game.newGame();
        game.play(row, column);

        Assertions.assertFalse(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,0"})
    void winning_play_player1_column1(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 1);
        game.play((short) 1, (short) 0);
        game.play((short) 2, (short) 1);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,1"})
    void winning_play_player1_column2(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 1);
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 1);
        game.play((short) 2, (short) 0);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,2"})
    void winning_play_player1_column3(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 2);
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 2);
        game.play((short) 2, (short) 0);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,0"})
    void winning_play_player2_column1(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 1, (short) 1);
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 2);
        game.play((short) 1, (short) 0);
        game.play((short) 2, (short) 2);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,1"})
    void winning_play_player2_column2(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 0);
        game.play((short) 0, (short) 1);
        game.play((short) 2, (short) 2);
        game.play((short) 1, (short) 1);
        game.play((short) 0, (short) 2);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,2"})
    void winning_play_player2_column3(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 0);
        game.play((short) 0, (short) 2);
        game.play((short) 1, (short) 0);
        game.play((short) 1, (short) 2);
        game.play((short) 1, (short) 1);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"0,2"})
    void winning_play_player1_row1(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 1);
        game.play((short) 0, (short) 1);
        game.play((short) 2, (short) 1);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"1,2"})
    void winning_play_player1_row2(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 1, (short) 0);
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 1);
        game.play((short) 2, (short) 0);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,2"})
    void winning_play_player1_row3(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 2, (short) 0);
        game.play((short) 0, (short) 0);
        game.play((short) 2, (short) 1);
        game.play((short) 1, (short) 0);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"0,2"})
    void winning_play_player2_row1(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 1, (short) 1);
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 2);
        game.play((short) 0, (short) 1);
        game.play((short) 2, (short) 2);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"1,2"})
    void winning_play_player2_row2(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 0);
        game.play((short) 2, (short) 2);
        game.play((short) 1, (short) 1);
        game.play((short) 0, (short) 2);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,2"})
    void winning_play_player2_row3(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 0);
        game.play((short) 2, (short) 0);
        game.play((short) 1, (short) 0);
        game.play((short) 2, (short) 1);
        game.play((short) 1, (short) 1);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,2"})
    void winning_play_player1_diagonal1(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 0);
        game.play((short) 2, (short) 0);
        game.play((short) 1, (short) 1);
        game.play((short) 1, (short) 0);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"0,2"})
    void winning_play_player1_diagonal2(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 2, (short) 0);
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 1);
        game.play((short) 0, (short) 1);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"2,2"})
    void winning_play_player2_diagonal1(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 2, (short) 0);
        game.play((short) 0, (short) 0);
        game.play((short) 2, (short) 1);
        game.play((short) 1, (short) 1);
        game.play((short) 1, (short) 0);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @ParameterizedTest
    @CsvSource({"0,2"})
    void winning_play_player2_diagonal2(short row,short column) {
        Joc game = new Joc();

        game.newGame();

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 1);
        game.play((short) 2, (short) 0);
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 1);
        game.play((short) 2, (short) 2);

        Assertions.assertTrue(game.winning_play(row, column));
    }

    @org.junit.jupiter.api.Test
    void board_filled() {
        Joc game = new Joc();

        game.newGame();

        int board_row = game.getBoard().length;
        int board_column = game.getBoard()[0].length;

        //Make a board with a winning condition for player1
        game.play((short) 0, (short) 1);
        game.play((short) 0, (short) 0);
        game.play((short) 1, (short) 0);
        game.play((short) 0, (short) 2);
        game.play((short) 1, (short) 1);
        game.play((short) 1, (short) 2);
        game.play((short) 2, (short) 0);
        game.play((short) 2, (short) 1);
        game.play((short) 2, (short) 2);

        Assertions.assertTrue(game.board_filled(board_row, board_column));
    }
}
