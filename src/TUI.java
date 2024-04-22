import java.util.Scanner;

public class TUI {
    void showMenu(){
        Scanner sc = new Scanner(System.in);

        boolean control = true;

        while (control) {
            System.out.printf(
                    "Benvingut a Tic Tac Toe!\n" +
                            "[1] Nova partida\n" +
                            "[2] Carregar partida\n" +
                            "[3] Configuració\n" +
                            "[4] Sortir\n");

            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.printf("Has seleccionat: Nova partida!\n");
                    newGame();
                    break;
                case 2:
                    System.out.printf("Has seleccionat: Carregar partida!\n");
                    loadGame();
                    break;
                case 3:
                    System.out.printf("Has seleccionat: Configuració\n");
                    settings();
                    break;
                case 4:
                    System.out.printf("Has seleccionat: Sortir\n");
                    exit();
                    control = false;
                    break;
                default:
                    System.out.printf("Numero invàlid!\n");
            }
        }

    }
    private void newGame(){}
    private void loadGame(){}
    private void settings(){}
    private void exit(){}
}