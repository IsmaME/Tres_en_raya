import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;

public class TestJoc {
    @org.junit.jupiter.api.Test
    void newGame() {

    }

    @org.junit.jupiter.api.Test
    void play() throws ExecutionControl.NotImplementedException {
        Joc game1 = new Joc();
        Joc game2 = new Joc();

        char[][] board_player1 = new char[3][3];
        char[][] board_player2 = new char[3][3];

        short row = 0;
        short column = 0;

        //fill the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == row && j == column){
                    board_player1[i][j] = 'X';
                    board_player2[i][j] = 'X';
                } else if (i == row && j == column + (short)1) {
                    board_player2[i][j] = 'O';
                }
            }
        }

        //test player1
        game1.play(row, column);
        Assertions.assertEquals(board_player1[row][column], game1.getBoard()[row][column]);

        //test player2
        game2.play(row, column);
        column = (short)1;
        game2.play(row,column);
        Assertions.assertEquals(board_player2[row][column], game2.getBoard()[row][column]);
    }

}
