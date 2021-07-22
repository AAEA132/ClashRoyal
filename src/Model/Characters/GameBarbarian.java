package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * The type Game barbarian.
 */
public class GameBarbarian extends GameTroop{
    /**
     * Instantiates a new Game barbarian.
     *
     * @param characterLocation the character location
     * @param imageView         the image view
     * @param gameModel         the game model
     */
    public GameBarbarian(Point2D characterLocation, ImageView imageView, GameModel gameModel) {
        super(characterLocation, imageView, gameModel, GameCharacterSpeed.MEDIUM);
    }
}
