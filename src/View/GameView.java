package View;

import Model.Characters.GameCharacter;
import Model.GameModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Model.GameModel.CellValue;

import java.util.ArrayList;

public class GameView extends Group{
    public final static double CELL_WIDTH = 19.0;

    private int rowCount;
    private int columnCount;
    private ImageView[][] cellViews;
    private Image darkGrassImage;
    private Image lightGrassImage;
    private Image riverImage;
    private Image bridgeImage;
    private Image roadImage;

//    private Image pacmanRightImage;
//    private Image pacmanUpImage;
//    private Image pacmanDownImage;
//    private Image pacmanLeftImage;
//    private Image ghost1Image;
//    private Image ghost2Image;
//    private Image blueGhostImage;
//    private Image wallImage;
//    private Image bigDotImage;
//    private Image smallDotImage;

    /**
     * Initializes the values of the image instance variables from files
     */
    public GameView() {
        this.bridgeImage = new Image(getClass().getResourceAsStream("/Photos/bridge.png"));
        this.riverImage = new Image(getClass().getResourceAsStream("/Photos/river.png"));
        this.roadImage = new Image(getClass().getResourceAsStream("/Photos/road.png"));
        this.darkGrassImage = new Image(getClass().getResourceAsStream("/Photos/darkGrass.png"));
        this.lightGrassImage = new Image(getClass().getResourceAsStream("/Photos/lightGrass.png"));
//        this.pacmanRightImage = new Image(getClass().getResourceAsStream("/pic/pacmanRight.gif"));
//        this.pacmanUpImage = new Image(getClass().getResourceAsStream("/pic/pacmanUp.gif"));
//        this.pacmanDownImage = new Image(getClass().getResourceAsStream("/pic/pacmanDown.gif"));
//        this.pacmanLeftImage = new Image(getClass().getResourceAsStream("/pic/pacmanLeft.gif"));
//        this.ghost1Image = new Image(getClass().getResourceAsStream("/pic/redghost.gif"));
//        this.ghost2Image = new Image(getClass().getResourceAsStream("/pic/ghost2.gif"));
//        this.blueGhostImage = new Image(getClass().getResourceAsStream("/pic/blueghost.gif"));
//        this.wallImage = new Image(getClass().getResourceAsStream("/pic/wall.png"));
//        this.bigDotImage = new Image(getClass().getResourceAsStream("/pic/whitedot.png"));
//        this.smallDotImage = new Image(getClass().getResourceAsStream("/pic/smalldot.png"));
    }
    /**
     * Constructs an empty grid of ImageViews
     */
    private void initializeGrid() {
        cellViews = new ImageView[columnCount][rowCount];
        for (int column = 0; column < columnCount; column++) {
            for (int row = 0; row < rowCount; row++) {
                String id = column+"#"+row;
                ImageView imageView = new ImageView();
                imageView.setId(id);
                imageView.setX(column * CELL_WIDTH);
                imageView.setY(row * CELL_WIDTH);
                imageView.setFitWidth(CELL_WIDTH);
                imageView.setFitHeight(CELL_WIDTH);
                cellViews[column][row] = imageView;
                this.getChildren().add(imageView);
            }
        }
    }

    /** Updates the view to reflect the state of the model
     *
     * @param gameModel
     */
    public void update(GameModel gameModel) {
        ArrayList<GameCharacter> gameCharacters = gameModel.getGameCharacters();
        //for each ImageView, set the image to correspond with the CellValue of that cell
        for (int column = 0; column < columnCount; column++){
            for (int row = 0; row < rowCount; row++){
                CellValue value = gameModel.getCellValue(column,row);
                if (value == CellValue.LIGHT_GRASS) {
                    cellViews[column][row].setImage(lightGrassImage);
                }
                else if (value == CellValue.DARK_GRASS) {
                    cellViews[column][row].setImage(darkGrassImage);
                }
                else if (value == CellValue.ROAD) {
                    cellViews[column][row].setImage(roadImage);
                }
                else if (value == CellValue.RIVER) {
                    cellViews[column][row].setImage(riverImage);
                }
                else if (value == CellValue.BRIDGE) {
                    cellViews[column][row].setImage(bridgeImage);
                }
                else {
                    cellViews[column][row].setImage(null);
                }

                if (!gameCharacters.isEmpty()) {
                    for (GameCharacter gameCharacter : gameCharacters) {
                        if (gameCharacter != null && column == gameCharacter.getCharacterLocation().getX() && row == gameCharacter.getCharacterLocation().getY()) {
                            gameCharacter.getImageView().setX(cellViews[column][row].getX());
                            gameCharacter.getImageView().setY(cellViews[column][row].getY());
                        }
                    }
                }
//                //check which direction PacMan is going in and display the corresponding image
//                if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY()) {
//                    if ((GameModel.getLastDirection() == GameModel.Direction.RIGHT || GameModel.getLastDirection() == GameModel.Direction.NONE)) {
//                        cellViews[row][column].setImage(pacmanRightImage);
//                    }
//                    else if (GameModel.getLastDirection() == GameModel.Direction.LEFT) {
//                        cellViews[row][column].setImage(pacmanLeftImage);
//                    }
//                    else if (GameModel.getLastDirection() == GameModel.Direction.UP) {
//                        cellViews[row][column].setImage(pacmanUpImage);
//                    }
//                    else if (GameModel.getLastDirection() == GameModel.Direction.DOWN) {
//                        cellViews[row][column].setImage(pacmanDownImage);
//                    }
//                }
//                Image ghostNormal;
//                Image ghostEating;
//                if (row == model.getGhost1Location().getX() && column == model.getGhost1Location().getY()) {
//                    ghostNormal = ghost1Image;
//                    ghostEating = blueGhostImage;
//                }
//                else if (row == model.getGhost2Location().getX() && column == model.getGhost2Location().getY()){
//                    ghostNormal = ghost2Image;
//                    ghostEating = blueGhostImage;
//                }
//                else
//                    continue;
//
//                //make ghosts "blink" towards the end of ghostEatingMode (display regular ghost images on alternating updates of the counter)
//                if (GameModel.isGhostEatingMode()) {
//                    if (Controller.getGhostEatingModeCounter() <= 6 && (Controller.getGhostEatingModeCounter() % 2 == 0)) {
//                        cellViews[row][column].setImage(ghostNormal);
//                    }
//                    //display blue ghosts in ghostEatingMode
//                    else {
//                        cellViews[row][column].setImage(ghostEating);
//                    }
//                }
//                //dispaly regular ghost images otherwise
//                else {
//                    cellViews[row][column].setImage(ghostNormal);
//                }
            }
        }
    }
    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.initializeGrid();
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        this.initializeGrid();
    }
}
