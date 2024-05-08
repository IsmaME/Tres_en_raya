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
                //settings();
                break;
            case 4:
                //exit();
                break;
        }
    }


    private static void newGame(Joc game, TUI tui) {
        game.newGame();
        boolean runing_game = true;
        boolean win = false;
        boolean bad_coords = false;

        while (runing_game){
            short player_turn = game.getTurn();

            tui.showBoard(game.getBoard(), game.getTurn());

            //pick up coords, if coords are correct we call play method from Joc class
            do {
                //pick coords
                short[] play_coords = tui.pickUpPlay();

                //veryfy we passing the correct coords and make sure we dont have out of bounds
                if( game.getBoard().length < play_coords[0] || game.getBoard()[0].length < play_coords[1] || play_coords[0] < -1 || play_coords[1] < -1){
                    tui.outOfBounds();
                    bad_coords = true;
                }
                else {
                    win = game.winning_play(play_coords[0],play_coords[1]);
                    game.play(play_coords[0],play_coords[1]);
                    bad_coords = false;
                }
            } while (bad_coords);


            //check if someone win or draw


        }
    }
    private static void loadGame() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
    private static void settings() {
        TUI tui = new TUI();
    }
    private static void exit() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
}