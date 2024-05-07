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

        int op = 0;
        boolean control = true;

        while (control) {
            op = sc.nextInt();
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
    public void showBoard(char[][] original_board, short torn) {
        //show player turn
        System.out.println(torn == 1? "Torn: Jugador1" : "Torn: Jugador2");
        System.out.println(" ");

        //show the board
        for (int i = 0; i < original_board.length; i++) {
            for (int j = 0; j < original_board[0].length; j++) {
                System.out.print(original_board[i][j]);
            }
            System.out.println("");
        }
    }
    public short[] pickUpPlay() {
        short[] coord = new short[2];

        //saving column cord in coord[0] and saving row in coord[1]
        System.out.print("columna: ");
        coord[0] = sc.nextShort();
        System.out.println();
        System.out.print("fila: ");
        coord[1] = sc.nextShort();
        System.out.println();

        return coord;
    }
    public void endOfGame(short guanyador) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }
}