import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TUI {
    private Scanner sc = new Scanner(System.in);

    public int showMenu(){

        System.out.print(
                """
                        Benvingut a Tic Tac Toe!
                        [1] Nova partida
                        [2] Carregar partida
                        [3] Configuració
                        [4] Sortir
                        """);

        int op = 0;
        boolean control = true;

        while (control) {
            op = sc.nextInt();
            if (op <= 0 || op > 4) {
                System.out.println("Opcio invalida!");
                System.out.print(
                        """
                                [1] Nova partida
                                [2] Carregar partida
                                [3] Configuració
                                [4] Sortir
                                """);
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
        String repeatedLine = "----";

        //show the board
        for (int i = 0; i < original_board.length; i++) {
            for (int j = 0; j < original_board.length; j++) {
                if (j == original_board.length -1){
                    if (original_board[i][j] != 0) {
                        System.out.print( " " + original_board[i][j] + " ");
                    } else {
                        System.out.print( " " + " " + " ");
                    }
                }else {
                    if (original_board[i][j] != 0){
                        System.out.print(" " + original_board[i][j] + " |");
                    } else {
                        System.out.print(" " + " " + " |");
                    }
                }
            }
            System.out.println();
            if (i != original_board.length -1){
                System.out.println(repeatedLine.repeat(original_board.length));
            }
        }
        System.out.println("Introdueix la teva jugada indicant primer la columna i després la fila ('-1,-1' per desar partida)");
    }

    public short[] pickUpPlay() {
        short[] coord = new short[2];

        //saving row cord in cord[0] and saving column in cord[1]
        System.out.print("fila: ");
        coord[0] = sc.nextShort();
        System.out.println();
        System.out.print("columna: ");
        coord[1] = sc.nextShort();
        System.out.println();

        return coord;
    }
    public void endOfGame(short guanyador) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Por hacer.");
    }

    public void outOfBounds(){
        System.out.println("ERROR!! Las cordenades introduides no son correctes");
        System.out.println("Torna a introduir les coordenades:");
    }

    public void already_played() {
        System.out.println("No pots jugar en aquesta casella perque no es buida!");
        System.out.println("Torna a introduir les coordenades:");
    }

    public void win_message(short turn) {
        System.out.println(turn == 1 ? "Ha guanyat el jugador1!" : "Ha guanyat el jugador2!");
    }

    public void draw_message() {
        System.out.println("La partida ha quedat en empat!");
    }

    public int board_settings() {

        int opt = 0;

        System.out.println("""
                Selecciona la opcio que vulguis:
                [1] Mida del taulell
                [2] Tornar enrere""");

        switch (sc.nextInt()) {
            case 1:
                opt = 1;
                break;
            case 2:
                opt = 2;
                break;
            default:
                System.out.println("Opcio incorrecta!");
                break;
        }
        return opt;
    }

    public short board_size() {
        short opt = 0;
        short size;
        boolean bad_size = true;

        do {
            System.out.println("Quina mida vols al teu taulell?\n" +
                    "(minim 3x3 i maxim 10x10)");

            size = sc.nextShort();

            if (size < 3 || size > 10) {
                System.out.println("""
                        Mida del taulell incorrecta!
                        Introdueix dades correctes
                        """);
            } else {
                opt = size;
                bad_size = false;
            }


        } while (bad_size);

        return opt;
    }

    public void savedMessage(){
        System.out.println("Partida guardada amb exit!");
        System.out.println("Tornant al menu. \n\n");
    }

    public int showSaveList(List<String> saveList){
        short index = 1;
        boolean askForPosition = true;
        int numSave;

        System.out.println("-------------------------");
        for (String save : saveList){
            System.out.println(index + ". -> " + save);
            index++;
        }
        System.out.println("-------------------------");

        do {
            System.out.print("Introdueix la partida que vols carregar (has de posar el numero del index)--> ");
            numSave = this.sc.nextInt();
            System.out.println();

            if (numSave > saveList.size() || numSave <= 0){
                askForPosition = true;
                System.out.println("Introdueix un valor correcte.");
                System.out.println();
            }
            else {
                askForPosition = false;
            }
        } while (askForPosition);

        return numSave;
    }

    public void exit_program() {
        System.out.println("Adeu!");
    }
}