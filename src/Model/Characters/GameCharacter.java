package Model.Characters;

import Model.GameModel;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * The type Game character.
 */
public class GameCharacter {
    /**
     * The enum Direction.
     */
    public enum Direction {
        /**
         * Up direction.
         */
        UP,
        /**
         * Down direction.
         */
        DOWN,
        /**
         * Left direction.
         */
        LEFT,
        /**
         * Right direction.
         */
        RIGHT,
        /**
         * None direction.
         */
        NONE,
        /**
         * Up right direction.
         */
        UP_RIGHT,
        /**
         * Up left direction.
         */
        UP_LEFT,
        /**
         * Down right direction.
         */
        DOWN_RIGHT,
        /**
         * Down left direction.
         */
        DOWN_LEFT
    };
    private Point2D characterLocation;
    private Point2D characterVelocity;
    private static Direction lastDirection;
    private static Direction currentDirection;
    private GameCharacterSpeed speed;
    private double speedD;
    private ImageView imageView;
    private Point2D destination;
    private GameModel gameModel;

    /**
     * Instantiates a new Game character.
     *
     * @param characterLocation the character location
     * @param imageView         the image view
     * @param gameModel         the game model
     * @param speed             the speed
     */
    public GameCharacter(Point2D characterLocation, ImageView imageView, GameModel gameModel, GameCharacterSpeed speed) {
        this.characterLocation = characterLocation;
        this.imageView = imageView;
        this.gameModel = gameModel;
        this.speed = speed;
        this.speedD = getSpeedInt(speed);
    }
    private double getSpeedInt(GameCharacterSpeed speed) {
        if (speed == GameCharacterSpeed.FAST)
            return 1;
        else if (speed == GameCharacterSpeed.MEDIUM)
            return 0.5;
        else
            return 0.25;
    }

    /**
     * Change velocity point 2 d.
     *
     * @param direction the direction
     * @return the point 2 d
     */
    public Point2D changeVelocity(Direction direction){
        if(direction == Direction.LEFT){
            return new Point2D(-speedD,0);
        }
        else if(direction == Direction.RIGHT){
            return new Point2D(speedD,0);
        }
        else if(direction == Direction.UP){
            return new Point2D(0,speedD);
        }
        else if(direction == Direction.UP_RIGHT){
            return new Point2D(speedD,speedD);
        }
        else if(direction == Direction.UP_LEFT){
            return new Point2D(-speedD,speedD);
        }
        else if(direction == Direction.DOWN){
            return new Point2D(0,-speedD);
        }
        else if(direction == Direction.DOWN_RIGHT){
            return new Point2D(speedD,-speedD);
        }
        else if(direction == Direction.DOWN_LEFT){
            return new Point2D(-speedD,-speedD);
        }
        else{
            return new Point2D(0,0);
        }
    }

    /**
     * Move character.
     */
    public void moveCharacter(){
            if (characterLocation.getY() == destination.getY()) {
                if (characterLocation.getX() > destination.getX()) {
                    characterVelocity = changeVelocity(Direction.LEFT);
                } else {
                    characterVelocity = changeVelocity(Direction.RIGHT);
                }
                Point2D potentialLocation = characterLocation.add(characterVelocity);

                if (gameModel.isMoveAble((int) potentialLocation.getX() , (int) potentialLocation.getY())) {
                    characterLocation = potentialLocation;
                }
            }
            else if (characterLocation.getX() == destination.getX()) {
                if (characterLocation.getY() > destination.getY()) {
                    characterVelocity = changeVelocity(Direction.DOWN);
                } else {
                    characterVelocity = changeVelocity(Direction.UP);
                }
                Point2D potentialLocation = characterLocation.add(characterVelocity);

                if (gameModel.isMoveAble((int) potentialLocation.getX() , (int) potentialLocation.getY())) {
                    characterLocation = potentialLocation;
                }
            }
            else if (characterLocation.getX() < destination.getX()){
                if (characterLocation.getY() > destination.getY()) {
                    characterVelocity = changeVelocity(Direction.DOWN_RIGHT);
                } else {
                    characterVelocity = changeVelocity(Direction.UP_RIGHT);
                }
                Point2D potentialLocation = characterLocation.add(characterVelocity);

                if (gameModel.isMoveAble((int) potentialLocation.getX() , (int) potentialLocation.getY())) {
                    characterLocation = potentialLocation;
                }
            }
            else if (characterLocation.getX() > destination.getX()){
                if (characterLocation.getY() > destination.getY()) {
                    characterVelocity = changeVelocity(Direction.DOWN_LEFT);
                } else {
                    characterVelocity = changeVelocity(Direction.UP_LEFT);
                }
                Point2D potentialLocation = characterLocation.add(characterVelocity);

                if (gameModel.isMoveAble((int) potentialLocation.getX() , (int) potentialLocation.getY())) {
                    characterLocation = potentialLocation;
                }
            }
        Platform.runLater(() -> imageView.setId(characterLocation.getX()+"#"+characterLocation.getY()));
    }

    /**
     * Gets destination.
     *
     * @return the destination
     */
    public Point2D getDestination() {
        return destination;
    }

    /**
     * Sets destination.
     *
     * @param destination the destination
     */
    public void setDestination(Point2D destination) {
        this.destination = destination;
    }

    /**
     * Gets current direction.
     *
     * @return the current direction
     */
    public static Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Sets current direction.
     *
     * @param direction the direction
     */
    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    /**
     * Gets last direction.
     *
     * @return the last direction
     */
    public static Direction getLastDirection() {
        return lastDirection;
    }

    /**
     * Sets last direction.
     *
     * @param direction the direction
     */
    public void setLastDirection(Direction direction) {
        lastDirection = direction;
    }

    /**
     * Gets character velocity.
     *
     * @return the character velocity
     */
    public Point2D getCharacterVelocity() {
        return characterVelocity;
    }

    /**
     * Sets character velocity.
     *
     * @param characterVelocity the character velocity
     */
    public void setCharacterVelocity(Point2D characterVelocity) {
        this.characterVelocity = characterVelocity;
    }

    /**
     * Gets character location.
     *
     * @return the character location
     */
    public Point2D getCharacterLocation() {
        return characterLocation;
    }

    /**
     * Gets image view.
     *
     * @return the image view
     */
    public ImageView getImageView() {
        return imageView;
    }
}
