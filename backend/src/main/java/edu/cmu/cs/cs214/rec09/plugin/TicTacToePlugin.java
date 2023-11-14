package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;

/**
 * An example Rock Paper Scissors game plug-in.
 */
public class TicTacToePlugin implements GamePlugin<String> {

    private static final String GAME_NAME = "TicTacToe";

    private static final int WIDTH = TicTacToe.SIZE;
    private static final int HEIGHT =TicTacToe.SIZE;

    private TicTacToe game;
    private static final String BLANK_SQUARE_STRING = "";
    private boolean moved;

    private GameFramework framework;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                framework.setSquare(i, j, BLANK_SQUARE_STRING);
            }
        }
    }

    @Override
    public void onNewMove() {
        moved = false;
     }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return moved == true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        framework.setSquare(x, y, game.currentPlayer().name());
        game.play(x, y);
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        return game.winner().name() + " is winner!";
    }

    @Override
    public void onGameClosed() { } // Nothing to do here.

    @Override
    public String currentPlayer() {
        return game.currentPlayer().name();
    }
}
