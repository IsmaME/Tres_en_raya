import jdk.jshell.spi.ExecutionControl;

public class Joc {
    private char[][] board;
    private short turn = 1;

    //Methods
    public void newGame() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method newGame not implemented");
    }
    public void play(short row, short column)  {
        board = new char[3][3];

        //add pice X or O and switch the turns
        switch (turn) {
            case 1:
                board[row][column] = 'X';
                turn = 2;
                break;
            case 2:
                board[row][column] = 'O';
                turn = 1;
                break;
        }
    }
    public boolean winning_play(short row,short column) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method wining_play not implemented");
    }

    //Getters
    public char[][] getBoard() {
        return board;
    }

    public short getTurn() {
        return turn;
    }
}
