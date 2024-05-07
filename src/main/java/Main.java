import jdk.jshell.spi.ExecutionControl;

public class Main {
    public static void main(String[] args) {
        TUI tui = new TUI();
        Joc game = new Joc();
        
        int op = tui.showMenu();

        switch (op) {
            case 1:
                System.out.printf("Has seleccionat: Nova partida!\n");
                newGame(game, tui);
                break;
            case 2:
                System.out.printf("Has seleccionat: Carregar partida!\n");
                //loadGame();
                break;
            case 3:
                System.out.printf("Has seleccionat: Configuració\n");
                //settings();
                break;
            case 4:
                System.out.printf("Has seleccionat: Sortir\n");
                //exit();
                break;
        }
    }


    private static void newGame(Joc game, TUI tui) {
        game.newGame();
        boolean runing_game = true;
        boolean win = false;

        while (runing_game){
            short player_turn = game.getTurn();

            tui.showBoard(game.getBoard(), game.getTurn());
            System.out.println("Introdueix la teva jugada indicant primer la columna i després la fila ('-1,-1' per desar partida)");
            short[] play_coords = tui.pickUpPlay();

            //veryfy we passing the correct coords
            if( game.getBoard().length < play_coords[0] || game.getBoard()[0].length < play_coords[1]){
                System.out.println("Las cordenades introduides no son correctes");
            }
            else {
                win = game.winning_play(play_coords[0],play_coords[1]);
                game.play(play_coords[0],play_coords[1]);
            }

            //check if someone win or draw
            if(win){
                switch (player_turn) {
                    case 1:
                        System.out.println("EL JUGADOR1 HA GUANYAT!!!");
                        runing_game = false;
                        break;
                    case 2:
                        System.out.println("EL JUGADOR2 HA GUANYAT!!!");
                        runing_game = false;
                        break;
                }
            } else{

            }

        }
    }
    private static void loadGame() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
    private static void settings() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
    private static void exit() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
}