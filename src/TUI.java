import jdk.jshell.spi.ExecutionControl;

import java.util.Scanner;

public class TUI {
    private Scanner sc = new Scanner(System.in);

    public int showMenu(){

        System.out.print(
                "Benvingut a Tic Tac Toe!\n" +
                        "[1] Nova partida\n" +
                        "[2] Carregar partida\n" +
                        "[3] Configuració\n" +
                        "[4] Sortir\n");

        int op = sc.nextInt();
        boolean control = true;

        while (control) {
            if (op <= 0 || op > 4) {
                System.out.println("Opcio invalida!");
                System.out.print(
                                "[1] Nova partida\n" +
                                "[2] Carregar partida\n" +
                                "[3] Configuració\n" +
                                "[4] Sortir\n");
            } else {
                control = false;
            }
        }
        return op;
    }
    public void showBoard(char[][] taulell, short torn) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
    public short pickUpPlay() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
    public void endOfGame(short guanyador) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
}