package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * The type Game wizard.
 */
public class GameWizard extends GameTroop{
    /**
     * Instantiates a new Game wizard.
     *
     * @param characterLocation the character location
     * @param imageView         the image view
     * @param gameModel         the game model
     */
    public GameWizard(Point2D characterLocation, ImageView imageView, GameModel gameModel) {
        super(characterLocation, imageView, gameModel, GameCharacterSpeed.MEDIUM);
    }
}
