import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Joc {
    private char[][] board = new char[3][3];
    private short turn;

    //Methods
    public void newGame() {
        this.turn = 1;
    }
    public void new_board(int size) {
        //set the board empty
        this.board = new char[size][size];
        setBoard(this.board);
    }

    public void play(short row, short column)  {
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

        //If there's no winning condition return false
        this.board[row][column] = 0;
        return false;
    }

    public boolean board_filled(int board_row, int board_column) {
        int total_cells = board_row * board_column;
        int filled_cells = 0;
        boolean opt = false;

        //If all the board is fill and there's no winner return true
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if (this.board[i][j] != 0) {
                    filled_cells++;
                }
            }
        }
        if (filled_cells == total_cells) {
            opt = true;
        }

        return opt;
    }

    public int new_board_settings(short settings) {
        String data = "";
        File board_size = new File("config");

        try {
            FileWriter size = new FileWriter(board_size);
            size.write(String.valueOf(settings));
            size.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner myReader;

        try {
            myReader = new Scanner(board_size);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
            System.out.println(data);
        }

        return Integer.parseInt(data);
    }

    public boolean save_game(){
        boolean fileCreated = false;
        //set the actual date and hour
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        //adapt date/hour in  yyyyMMdd_HHmmss format
        String adapted_date = formattedDateTime.replace("-","").replace(":", "").replace(" ", "_");

        //folder and file instanace
        File folder = new File("savedgames");
        File save_data = new File(folder,adapted_date + ".txt");

        //new folder and file instance
        //Create save folder if don't exists
        if (!folder.exists()) {
            folder.mkdir();
        }

        //Craete Save data file
        try {
            fileCreated = save_data.createNewFile();

            //wirte game data if not exists ??????????????
            FileWriter writer = new FileWriter(save_data);
            writer.write(this.turn + "\n");

            // iteration for save the board
            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board[0].length; j++) {
                    //writing each row of board in this format -,-,-
                    if (j != this.board[0].length - 1) {
                        //check null and fill with -
                        if (this.board[i][j] == 0){
                            writer.write("-" + ",");
                        } else {
                            writer.write(this.board[i][j] + ",");
                        }
                    } else {
                        //check null and fill with -
                        if (this.board[i][j] == 0){
                            writer.write("-");
                        } else {
                            writer.write(this.board[i][j] + "");
                        }
                    }
                }
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e){
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }

        return fileCreated;
    }

    //Getters
    public char[][] getBoard() {
        return board;
    }

    public short getTurn() {
        return turn;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
