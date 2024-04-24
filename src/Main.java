import jdk.jshell.spi.ExecutionControl;

public class Main {
    public static void main(String[] args) {
        Joc game = new Joc();
        TUI tui = new TUI();
        int op = tui.showMenu();

        switch (op) {
            case 1:
                System.out.printf("Has seleccionat: Nova partida!\n");
                //newGame();
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
    private static void newGame() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
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