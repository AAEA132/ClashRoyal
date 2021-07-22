package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * The type Game troop.
 */
public class GameTroop extends GameCharacter{
    /**
     * Instantiates a new Game troop.
     *
     * @param characterLocation the character location
     * @param imageView         the image view
     * @param gameModel         the game model
     * @param speed             the speed
     */
    public GameTroop(Point2D characterLocation, ImageView imageView, GameModel gameModel, GameCharacterSpeed speed) {
        super(characterLocation, imageView, gameModel, speed);
    }
}
