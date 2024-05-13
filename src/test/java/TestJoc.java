import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        board[row][column] = 'O';

        //make the player2 play
        game.play(row,column);
        //test
        Assertions.assertArrayEquals(board,game.getBoard());
    }

    @ParameterizedTest
    @CsvSource({"0,0","0,1","0,2","0,3","0,4","0,5","0,6","0,7","0,8","0,9",
            "1,0","1,1","1,2","1,3","1,4","1,5","1,6","1,7","1,8","1,9",
            "2,0","2,1","2,2","2,3","2,4","2,5","2,6","2,7","2,8","2,9",
            "3,0","3,1","3,2","3,3","3,4","3,5","3,6","3,7","3,8","3,9",
            "4,0","4,1","4,2","4,3","4,4","4,5","4,6","4,7","4,8","4,9",
            "5,0","5,1","5,2","5,3","5,4","5,5","5,6","5,7","5,8","5,9",
            "6,0","6,1","6,2","6,3","6,4","6,5","6,6","6,7","6,8","6,9",
            "7,0","7,1","7,2","7,3","7,4","7,5","7,6","7,7","7,8","7,9",
            "8,0","8,1","8,2","8,3","8,4","8,5","8,6","8,7","8,8","8,9",
            "9,0","9,1","9,2","9,3","9,4","9,5","9,6","9,7","9,8","9,9"})
    void winning_play_empty_board(short row,short column) {
        Joc game = new Joc();

        game.newGame();
        game.new_board((short)10);
        Assertions.assertFalse(game.winning_play(row, column));

    }

    @ParameterizedTest
    @CsvSource({"0,0","0,1","0,2","0,3","0,4","0,5","0,6","0,7","0,8","0,9",
            "1,0","1,1","1,2","1,3","1,4","1,5","1,6","1,7","1,8","1,9",
            "2,0","2,1","2,2","2,3","2,4","2,5","2,6","2,7","2,8","2,9",
            "3,0","3,1","3,2","3,3","3,4","3,5","3,6","3,7","3,8","3,9",
            "4,0","4,1","4,2","4,3","4,4","4,5","4,6","4,7","4,8","4,9",
            "5,0","5,1","5,2","5,3","5,4","5,5","5,6","5,7","5,8","5,9",
            "6,0","6,1","6,2","6,3","6,4","6,5","6,6","6,7","6,8","6,9",
            "7,0","7,1","7,2","7,3","7,4","7,5","7,6","7,7","7,8","7,9",
            "8,0","8,1","8,2","8,3","8,4","8,5","8,6","8,7","8,8","8,9",
            "9,0","9,1","9,2","9,3","9,4","9,5","9,6","9,7","9,8","9,9"})
    void winning_play_one_position(short row,short column) {
        Joc game = new Joc();

        game.newGame();
        game.new_board((short)10);
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
    @ParameterizedTest
    @CsvSource({"10"})
    void new_board_settings(short settings) {
        Joc game = new Joc();

        int actual = game.new_board_settings(settings);

        Assertions.assertEquals(settings, actual);
    }

    //save
    @org.junit.jupiter.api.Test
    void saveGame_folderCreated() throws IOException {
        Joc game = new Joc();
        File folder = new File("savedgames");

        folder.delete();
        game.newGame();
        game.saveGame();

        Assertions.assertTrue(folder.exists());
        Assertions.assertEquals("savedgames", folder.getName());
    }

    @ParameterizedTest
    @CsvSource({"2"})
    void saveGame_fileCreated(int quantity) throws InterruptedException, IOException {
        Joc game = new Joc();
        File folder = new File("savedgames");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        folder.delete();

        for (int i = 0; i < quantity; i++) {
            game.newGame();
            game.saveGame();
            Thread.sleep(1000);
        }
        files = folder.listFiles();
        assert files != null;
        Assertions.assertEquals(quantity,files.length);
    }

    @org.junit.jupiter.api.Test
    void saveGame_fileContent() throws IOException {
        Joc game = new Joc();
        File folder = new File("savedgames");
        File[] files = folder.listFiles();

        //clear folder content
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        //delete folder
        folder.delete();

        //crate and play
        game.newGame();
        game.play((short)0,(short)0);
        game.play((short)1,(short)1);

        //save game
        game.saveGame();

        File[] save = folder.listFiles();

        assert save != null;
        File saveFile = new File("" + save[0]);
        try {
            Scanner scSave = new Scanner(saveFile);

            Assertions.assertEquals(game.getTurn(),scSave.nextShort());
            Assertions.assertEquals(game.getBoard().length,scSave.nextShort());
            scSave.nextLine();

            for (int i = 0; i < game.getBoard().length; i++) {
                //transform string to array and transform this string array to char array
                String[] stringline = scSave.nextLine().split(",");
                char[] charLine = new char[game.getBoard().length];
                for (int j = 0; j < game.getBoard().length; j++) {
                    charLine[j] = stringline[j].charAt(0);
                    if (charLine[j] == '-'){
                        charLine[j] = 0;
                    }
                }
                //check in each row of board
                Assertions.assertArrayEquals(game.getBoard()[i],charLine);
            }

        } catch (FileNotFoundException e) {
           Assertions.fail();
        }
    }

    @ParameterizedTest
    @CsvSource({"2","4"})
    void saveList(int quantity) throws InterruptedException, IOException {
        Joc game = new Joc();
        File folder = new File("savedgames");
        List<String> txtFiles = new ArrayList<>();
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        folder.delete();



        for (int i = 0; i < quantity; i++) {
            game.newGame();
            game.saveGame();
            Thread.sleep(1000);
        }


        if (folder.exists()) {
            files = folder.listFiles();
            //filter all .txt files and save list in txtFiles
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    txtFiles.add(file.getName());
                }
            }
        }
        //assert num files created are equals
        Assertions.assertEquals(quantity, game.saveList().size());
        //assert files have diferent name
        String lastValue = "";
        for (int i = 0; i < quantity; i++) {

            if (i == 0) {
                lastValue = txtFiles.get(i);
            }
            else{
                Assertions.assertNotEquals(lastValue, game.saveList().get(i));
                lastValue = txtFiles.get(i);
            }
        }
    }
    @ParameterizedTest
    @CsvSource({"0,0,1,0","0,0,0,0"})
    void loadGame(short player1Row,short player1Column,short player2Row,short player2Column) throws IOException {
        Joc game = new Joc();
        File folder = new File("savedgames");
        File[] files = folder.listFiles();

        //clear folder content
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        //delete folder
        folder.delete();

        //crate and play
        game.newGame();

        //load with player1 turn
        game.play(player1Row,player1Column);
        char[][] savedBoard = game.getBoard();
        game.saveGame();
        game.loadGame(1);

        Assertions.assertEquals(2, game.getTurn());
        Assertions.assertArrayEquals(savedBoard, game.getBoard());

        //load with player2 turn
        game.play(player2Row,player2Column);
        savedBoard = game.getBoard();
        game.saveGame();
        game.loadGame(1);

        Assertions.assertEquals(1, game.getTurn());
        Assertions.assertArrayEquals(savedBoard, game.getBoard());
    }

}
