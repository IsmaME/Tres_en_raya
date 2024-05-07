public class Joc {
    private char[][] board;
    private short turn;

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

        this.board[row][column] = symbol;

        //Check if there's a winning row
        if (this.board[row][0] == symbol && this.board[row][1] == symbol && this.board[row][2] == symbol) {
            this.board[row][column] = 0;
            return true;
        }

        //Check if there's a winning column
        if (this.board[0][column] == symbol && this.board[1][column] == symbol && this.board[2][column] == symbol) {
            this.board[row][column] = 0;
            return true;
        }

        //Check if there's a winning diagonal
        if ((this.board[0][0] == symbol && this.board[1][1] == symbol && this.board[2][2] == symbol)
                || (this.board[0][2] == symbol && this.board[1][1] == symbol && this.board[2][0] == symbol)) {
            this.board[row][column] = 0;
            return true;
        }

        //If all the board is fill and there's no winner return false
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.board[i][j] != 0) {
                    this.board[row][column] = 0;
                    return false;
                }
            }
        }

        //If there's no winning condition return false
        this.board[row][column] = 0;
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
