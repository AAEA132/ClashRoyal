package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * The type Game giant.
 */
public class GameGiant extends GameTroop{

    /**
     * Instantiates a new Game giant.
     *
     * @param characterLocation the character location
     * @param imageView         the image view
     * @param gameModel         the game model
     */
    public GameGiant(Point2D characterLocation, ImageView imageView, GameModel gameModel) {
        super(characterLocation, imageView, gameModel, GameCharacterSpeed.SLOW);
    }
}
