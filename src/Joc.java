import jdk.jshell.spi.ExecutionControl;

public class Joc {
    private char[][] board;
    private short turn;

    public void newGame() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method newGame not implemented");
    }
    public void play(short row, short column) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method play not implemented");
    }
    public boolean winning_play(short row,short column) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method wining_play not implemented");
    }
}
