package Controller;

import Model.GameModel;
import View.GameView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    @FXML private GameView gameView;
    final private static double FRAMES_PER_SECOND = 5.0;
    private Timer timer;
    private boolean paused;
    private static final String mapFile = "src/Map/map.txt";
    private GameModel gameModel;

    public GameController() {
        this.paused = false;
    }
    /**
     * Initialize and update the model and view from the first txt file and starts the timer.
     */
    public void initialize() {
        gameModel = new GameModel();
        update();
//        pacManModel = new PacManModel();
//        update(PacManModel.Direction.NONE);
        startTimer();
    }

    /**
     * Schedules the model to update based on the timer.
     */
    private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        update();
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    /**
     * Steps the PacManModel, updates the view, updates score and level, displays Game Over/You Won, and instructions of how to play
//     * @param direction the most recently inputted direction for PacMan to move in
     */
    private void update() {
        this.gameView.update(gameModel);
//        this.pacManModel.step(direction);
//        this.pacManView.update(pacManModel);!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        this.scoreLabel.setText(String.format("Score: %d", this.pacManModel.getScore()));
//        this.levelLabel.setText(String.format("Level: %d", this.pacManModel.getLevel()));
//        if (pacManModel.isGameOver()) {
//            this.gameOverLabel.setText(String.format("GAME OVER"));
//            pause();
//        }
//        if (pacManModel.isYouWon()) {
//            this.gameOverLabel.setText(String.format("YOU WON!"));
//        }
//        //when PacMan is in ghostEatingMode, count down the ghostEatingModeCounter to reset ghostEatingMode to false when the counter is 0
//        if (pacManModel.isGhostEatingMode()) {
//            ghostEatingModeCounter--;
//        }
//        if (ghostEatingModeCounter == 0 && pacManModel.isGhostEatingMode()) {
//            pacManModel.setGhostEatingMode(false);
//        }
    }
    public static String getMapFile()
    {
        return mapFile;
    }

    public double getBoardWidth() {
        return GameView.CELL_WIDTH * this.gameView.getColumnCount();
    }

    public double getBoardHeight() {
        return GameView.CELL_WIDTH * this.gameView.getRowCount();
    }
}
