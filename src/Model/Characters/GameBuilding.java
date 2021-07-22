package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class GameBuilding extends GameCharacter{
    public GameBuilding(Point2D characterLocation, ImageView imageView, GameModel gameModel) {
        super(characterLocation, imageView, gameModel,GameCharacterSpeed.FAST);
    }
    @Override
    public void moveCharacter() {
    }
}
