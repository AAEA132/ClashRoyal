package View;

import Model.Characters.GameCharacter;
import Model.GameModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import Model.GameModel.CellValue;

import java.util.ArrayList;

/**
 * The type Game view.
 */
public class GameView extends Group{
    /**
     * The constant CELL_WIDTH.
     */
    public final static double CELL_WIDTH = 19.0;
    private int rowCount;
    private int columnCount;
    private ImageView[][] cellViews;
    private Image darkGrassImage;
    private Image lightGrassImage;
    private Image riverImage;
    private Image bridgeImage;
    private Image roadImage;
    private Image botTower;
    private Image userTower;
    private Image botKing;
    private Image userKing;

    /**
     * Initializes the values of the image instance variables from files
     */
    public GameView() {
        this.bridgeImage = new Image(getClass().getResourceAsStream("/Photos/bridge.png"));
        this.riverImage = new Image(getClass().getResourceAsStream("/Photos/river.png"));
        this.roadImage = new Image(getClass().getResourceAsStream("/Photos/road.png"));
        this.darkGrassImage = new Image(getClass().getResourceAsStream("/Photos/darkGrass.png"));
        this.lightGrassImage = new Image(getClass().getResourceAsStream("/Photos/lightGrass.png"));
        this.botKing = new Image(getClass().getResourceAsStream("/Photos/assets/red_tower_linkgy.png"));
        this.userKing = new Image(getClass().getResourceAsStream("/Photos/assets/blueTower.png"));
        this.botTower = new Image(getClass().getResourceAsStream("/Photos/assets/red.png"));
        this.userTower = new Image(getClass().getResourceAsStream("/Photos/assets/blue.png"));
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

    /**
     * Updates the view to reflect the state of the model
     *
     * @param gameModel the game model
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
                else if (value == CellValue.BOT_TOWER) {
                    cellViews[column][row].setImage(roadImage);
                    ImageView imageView = new ImageView(botTower);
                    imageView.setX(cellViews[column][row].getX()-40);
                    imageView.setY(cellViews[column][row].getY()-40);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    imageView.setVisible(true);
                    this.getChildren().add(imageView);
                }
                else if (value == CellValue.USER_TOWER) {
                    cellViews[column][row].setImage(roadImage);
                    ImageView imageView = new ImageView(userTower);
                    imageView.setX(cellViews[column][row].getX()-40);
                    imageView.setY(cellViews[column][row].getY()-40);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    imageView.setVisible(true);
                    this.getChildren().add(imageView);
                }
                else if (value == CellValue.USER_KING) {
                    cellViews[column][row].setImage(roadImage);
                    ImageView imageView = new ImageView(userKing);
                    imageView.setX(cellViews[column][row].getX()-70);
                    imageView.setY(cellViews[column][row].getY()-60);
                    imageView.setFitWidth(180);
                    imageView.setFitHeight(180);
                    imageView.setVisible(true);
                    this.getChildren().add(imageView);
                }
                else if (value == CellValue.BOT_KING) {
                    cellViews[column][row].setImage(roadImage);
                    ImageView imageView = new ImageView(botKing);
                    imageView.setX(cellViews[column][row].getX()-70);
                    imageView.setY(cellViews[column][row].getY()-60);
                    imageView.setFitWidth(180);
                    imageView.setFitHeight(180);
                    imageView.setVisible(true);
                    this.getChildren().add(imageView);
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
            }
        }
    }

    /**
     * Gets row count.
     *
     * @return the row count
     */
    public int getRowCount() {
        return this.rowCount;
    }

    /**
     * Sets row count.
     *
     * @param rowCount the row count
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.initializeGrid();
    }

    /**
     * Gets column count.
     *
     * @return the column count
     */
    public int getColumnCount() {
        return this.columnCount;
    }

    /**
     * Sets column count.
     *
     * @param columnCount the column count
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        this.initializeGrid();
    }
}
