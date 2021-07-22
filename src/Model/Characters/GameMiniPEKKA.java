package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * The type Game mini pekka.
 */
public class GameMiniPEKKA extends GameTroop{
    /**
     * Instantiates a new Game mini pekka.
     *
     * @param characterLocation the character location
     * @param imageView         the image view
     * @param gameModel         the game model
     */
    public GameMiniPEKKA(Point2D characterLocation, ImageView imageView, GameModel gameModel) {
        super(characterLocation, imageView, gameModel, GameCharacterSpeed.FAST);
    }
}
