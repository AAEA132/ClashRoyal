package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class GameTroop extends GameCharacter{
    public GameTroop(Point2D characterLocation, ImageView imageView, GameModel gameModel, GameCharacterSpeed speed) {
        super(characterLocation, imageView, gameModel, speed);
    }
}
