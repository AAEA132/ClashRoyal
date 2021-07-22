package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class Card {
    private MovingDirection direction = MovingDirection.NONE,
            pastDirection = MovingDirection.NONE;
    private int cost;
    private ImageView imageView;
    private HashMap<MovingDirection,Image> cardImages = new HashMap<>();

    public Card() {
    }
    public void setCardImage(ImageView cardImage, Image cardUp, Image cardDown, Image cardLeft, Image cardRight) {
        this.imageView = cardImage;
        this.cardImages.put(MovingDirection.UP, cardUp);
        this.cardImages.put(MovingDirection.DOWN, cardDown);
        this.cardImages.put(MovingDirection.LEFT, cardLeft);
        this.cardImages.put(MovingDirection.RIGHT, cardRight);
    }
    public ImageView getImageView() {
        return imageView;
    }
    public MovingDirection getDirection() {
        return direction;
    }
//    public void setDirection(MovingDirection direction) {
//        goToNearestPlace();
//        this.direction = direction;
//        if (direction == MovingDirection.NONE)
//            return;
//        if (!isGhost)
//            imageView.setImage(pacmanImages.get(direction));
//    }
}
