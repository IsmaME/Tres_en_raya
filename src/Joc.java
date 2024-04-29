import jdk.jshell.spi.ExecutionControl;

public class Joc {
    private char[][] board = new char[3][3];
    private short turn = 1;

    //Methods
    public void newGame() {
        //set the board empty
        this.board = new char[3][3];
    }
    public void play(short row, short column)  {
        //check if this element are empty
        if (this.board[row][column] != 'X' && this.board[row][column] != 'O') {
            //add piece X or O and switch the turns
            switch (this.turn) {
                case 1:
                    this.board[row][column] = 'X';
                    this.turn = 2;
                    break;
                case 2:
                    this.board[row][column] = 'O';
                    this.turn = 1;
                    break;
            }
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
