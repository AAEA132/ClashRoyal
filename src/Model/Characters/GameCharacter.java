package Model.Characters;

import Model.GameModel;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import Model.GameModel.CellValue;
import java.util.Random;

public class GameCharacter {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT
    };
    private Point2D characterLocation;
    private Point2D characterVelocity;

    private static Direction lastDirection;
    private static Direction currentDirection;

    private ImageView imageView;

    private Point2D destination;

    public Point2D getDestination() {
        return destination;
    }

    public void setDestination(Point2D destination) {
        this.destination = destination;
    }

    private GameModel gameModel;

    public GameCharacter(Point2D characterLocation, ImageView imageView, GameModel gameModel) {
        this.characterLocation = characterLocation;
        this.imageView = imageView;
        this.gameModel = gameModel;
    }

    public Direction intToDirection(int x){
        if (x == 0){
            return Direction.LEFT;
        }
        else if (x == 1){
            return Direction.RIGHT;
        }
        else if(x == 2){
            return Direction.UP;
        }
        else if(x == 3){
            return Direction.DOWN;
        }
        else if(x == 4){
            return Direction.UP_RIGHT;
        }
        else if(x == 5){
            return Direction.UP_LEFT;
        }
        else if(x == 6){
            return Direction.DOWN_RIGHT;
        }
        else if(x == 7){
            return Direction.DOWN_LEFT;
        }
        else{
            return Direction.NONE;
        }
    }

    public Point2D changeVelocity(Direction direction){
        if(direction == Direction.LEFT){
            return new Point2D(-1,0);
        }
        else if(direction == Direction.RIGHT){
            return new Point2D(1,0);
        }
        else if(direction == Direction.UP){
            return new Point2D(0,1);
        }
        else if(direction == Direction.UP_RIGHT){
            return new Point2D(1,1);
        }
        else if(direction == Direction.UP_LEFT){
            return new Point2D(-1,1);
        }
        else if(direction == Direction.DOWN){
            return new Point2D(0,-1);
        }
        else if(direction == Direction.DOWN_RIGHT){
            return new Point2D(1,-1);
        }
        else if(direction == Direction.DOWN_LEFT){
            return new Point2D(-1,-1);
        }
        else{
            return new Point2D(0,0);
        }
    }
    public static Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    public static Direction getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(Direction direction) {
        lastDirection = direction;
    }

    public Point2D getCharacterLocation() {
        return characterLocation;
    }

    public ImageView getImageView() {
        return imageView;
    }

    //    public void moveCharacter() {
//        Point2D[] character1Data = moveAGhost(ghost1Velocity, ghost1Location);
//        ghost1Velocity = ghost1Data[0];
//        ghost1Location = ghost1Data[1];
//        ghost2Velocity = ghost2Data[0];
//        ghost2Location = ghost2Data[1];
//
//    }

    public void moveCharacter(){
            //check if ghost is in PacMan's column and move towards him
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
            //check if ghost is in PacMan's row and move towards him
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
    }
}
