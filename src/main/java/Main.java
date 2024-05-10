import jdk.jshell.spi.ExecutionControl;

public class Main {
    public static void main(String[] args) {
        TUI tui = new TUI();
        Joc game = new Joc();
        
        int op = tui.showMenu();
        boolean control = true;

        do {
            switch (op) {
                case 1:
                    newGame(game, tui);
                    break;
                case 2:
                    //loadGame();
                    break;
                case 3:
                    settings();
                    break;
                case 4:
                    //exit();
                    control = false;
                    break;
            }
            op = tui.showMenu();

        } while (control);
    }

    private static void newGame(Joc game, TUI tui) {
        game.newGame();
        char[][] final_board = new char[3][3];
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
                    if (game.save_game()){
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
                game.setBoard(final_board);
                runing_game = false;
            } else if (boarfilled) {
                game.setBoard(final_board);
                tui.draw_message();
                runing_game = false;
            }
        }
    }

    private static void loadGame() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }

    //This method asks the player what he wants to do in the "settings"
    private static void settings() {
        TUI tui = new TUI();
        Joc game = new Joc();
        short settings;
        int new_size = 0;


        int opt = tui.board_settings();

        switch (opt) {
            case 1:
            //This will get the settings and give it to the method that changes the board
            settings = tui.board_size();
            new_size = game.new_board_settings(settings);
            game.new_board(new_size);
            case 2:
                break;
        }
    }

    private static void exit() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
}