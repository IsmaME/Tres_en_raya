import jdk.jshell.spi.ExecutionControl;

public class Main {
    public static void main(String[] args) {
        TUI tui = new TUI();
        Joc game = new Joc();
        
        int op = tui.showMenu();

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
                break;
        }
    }

    private static void newGame(Joc game, TUI tui) {
        game.newGame();
        boolean runing_game = true;
        boolean player_win = false;
        boolean boarfilled = false;
        boolean bad_coords;

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
                //if coords are like -1,2 or 2,-1 we cant play because out of bounds we need check it to
                else if (play_cords[0] == -1 && play_cords[1] != -1 || play_cords[1] == -1 && play_cords[0] != -1) {
                    tui.outOfBounds();
                    bad_coords = true;
                } else if (game.getBoard()[play_cords[0]][play_cords[1]] != 0) {
                    tui.already_played();
                    bad_coords = true;
                }
                //run when all coords are correct
                else {
                    player_win = game.winning_play(play_cords[0],play_cords[1]);
                    game.play(play_cords[0],play_cords[1]);
                    boarfilled = game.board_filled();
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

    private static void loadGame() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }

    private static void settings() {
        TUI tui = new TUI();
        Joc joc = new Joc();

        int opt = tui.board_settings();

        if (opt == 1) {

        } else if (opt == 2) {
            tui.showMenu();
        }
    }

    private static void exit() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
}