import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        TUI tui = new TUI();
        Joc game = new Joc();
        
        int op = tui.showMenu();

        do {
            switch (op) {
                case 1:
                    newGame(game, tui, false);
                    break;
                case 2:
                    loadGame(game, tui);
                    break;
                case 3:
                    settings(tui, game);
                    break;
                case 4:
                    exit(tui);
                    return;
            }
            op = tui.showMenu();

        } while (true);
    }

    private static void newGame(Joc game, TUI tui, Boolean loadedFromSaveData) throws IOException {
        if (!loadedFromSaveData){
            game.newGame();
        }
        boolean runing_game = true;
        boolean player_win = false;
        boolean boarfilled = false;
        boolean bad_coords;
        int board_row = game.getBoard().length;
        int board_column = game.getBoard()[0].length;

        while (runing_game){
            short player_turn = game.getTurn();

            tui.showBoard(game.getBoard(), game.getTurn());

            //pick up cords, if cords are correct we call play method from Joc class
            do {
                //pick coords
                short[] play_cords = tui.pickUpPlay();

                //verify we're passing the correct cords and make sure we don't have out of bounds
                if( game.getBoard().length <= play_cords[0] || game.getBoard()[0].length <= play_cords[1] || play_cords[0] < -1 || play_cords[1] < -1){
                    tui.outOfBounds();
                    bad_coords = true;
                }
                //if coords are -1,-1 program will save the game
                else if (play_cords[0] == -1 && play_cords[1] == -1) {
                    //if saved show message
                    if (game.saveGame()){
                        tui.savedMessage();
                    }
                    bad_coords = false;
                    runing_game = false;
                }
                //if coords are like -1,2 or 2,-1 we cant play because out of bounds we need check it to
                else if (play_cords[0] == -1 || play_cords[1] == -1) {
                    tui.outOfBounds();
                    bad_coords = true;
                }
                //A player can't put his piece in a cord that is already filled
                else if (game.getBoard()[play_cords[0]][play_cords[1]] != 0) {
                    tui.already_played();
                    bad_coords = true;
                }
                //run when all coords are correct
                else {
                    player_win = game.winning_play(play_cords[0],play_cords[1]);
                    game.play(play_cords[0],play_cords[1]);
                    boarfilled = game.board_filled(board_row, board_column);
                    bad_coords = false;
                }
            } while (bad_coords);

            //check if someone win or draw
            if (player_win){
                tui.win_message(player_turn);
                runing_game = false;
            } else if (boarfilled) {
                tui.draw_message();
                runing_game = false;
            }
        }
    }

    private static void loadGame(Joc game, TUI tui) throws IOException {
        //send list from joc to TUI and show it from TUI
        int position = tui.showSaveList(game.saveList());
        game.loadGame(position);
        newGame(game,tui,true);
    }

    //This method asks the player what he wants to do in the "settings"
    private static void settings(TUI tui, Joc game) throws IOException {
        short settings;
        int new_size = 0;


        int opt = tui.board_settings();

        switch (opt) {
            case 1:
            //This will get the settings and give it to the method that changes the board
            settings = tui.board_size();
            game.new_board_settings(settings);

            case 2:
                break;
        }
    }

    private static void exit(TUI tui) {
        tui.exit_program();
    }
}