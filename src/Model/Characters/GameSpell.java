package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * The type Game spell.
 */
public class GameSpell extends GameCharacter{
    /**
     * Instantiates a new Game spell.
     *
     * @param characterLocation the character location
     * @param imageView         the image view
     * @param gameModel         the game model
     */
    public GameSpell(Point2D characterLocation, ImageView imageView, GameModel gameModel) {
        super(characterLocation, imageView, gameModel, GameCharacterSpeed.FAST);
    }
    @Override
    public void moveCharacter() {
    }
}
