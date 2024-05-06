public class Joc {
    private char[][] board = new char[3][3];
    private short turn = 1;

    //Methods
    public void newGame() {
        //set the board empty
        this.board = new char[3][3];
        this.turn = 1;
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
    public boolean winning_play(short row,short column) {
        char symbol;

        if (this.turn == 1) {
            symbol = 'X';
        } else {
            symbol = 'O';
        }

        //Create an empty board
        char[][] temp_board = new char[3][3];

        //Copy the elements of our board in the empty one
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp_board[i][j] = this.board[i][j];
            }
        }

        //Now we can simulate plays on the temp_board without affecting our board
        //Simulate a play on the temp_board
        temp_board[row][column] = symbol;

        //Check if there's a winning row
        if (temp_board[row][0] == symbol && temp_board[row][1] == symbol && temp_board[row][2] == symbol) {
            return true;
        }

        //Check if there's a winning column
        if (temp_board[0][column] == symbol && temp_board[1][column] == symbol && temp_board[2][column] == symbol) {
            return true;
        }

        //Check if there's a winning diagonal
        if ((temp_board[0][0] == symbol && temp_board[1][1] == symbol && temp_board[2][2] == symbol)
                || (temp_board[0][2] == symbol && temp_board[1][1] == symbol && temp_board[2][0] == symbol)) {
            return true;
        }

        //If there's no winning condition return false
        return false;
    }

    //Getters
    public char[][] getBoard() {
        return board;
    }

    public short getTurn() {
        return turn;
    }
}
