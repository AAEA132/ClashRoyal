package Model;

import Model.Characters.GameCharacter;
import javafx.geometry.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameModel {
    private Point2D leftFirstDestinationUser = new Point2D(3,17);
    private Point2D rightFirstDestinationUser = new Point2D(14, 17);

    private Point2D leftSecondDestinationUser = new Point2D(3,5);
    private Point2D rightSecondDestinationUser = new Point2D(14, 5);

    private Point2D leftThirdDestinationUser = new Point2D(8, 0);
    private Point2D rightThirdDestinationUser = new Point2D(9, 0);

    private ArrayList<GameCharacter> gameCharacters;
    private ArrayList<GameCharacter> gameCharactersRight;
    private ArrayList<GameCharacter> gameCharactersLeft;

    public enum CellValue {
        EMPTY, LIGHT_GRASS, DARK_GRASS, ROAD, RIVER, BRIDGE
    };

    private int rowCount;
    private int columnCount;
    private CellValue[][] grid;
    private boolean[][] userPossibleCardDrop;
    private static boolean gameOver;
    private static boolean youWon;


    public GameModel() {
        gameCharacters = new ArrayList<>();
        gameCharactersRight = new ArrayList<>();
        gameCharactersLeft = new ArrayList<>();
        this.startNewGame();
    }

    private void startNewGame() {
        gameOver = false;
        youWon = false;
        initializeLevel(Controller.GameController.getMapFile());
    }

    /**
     * Configure the grid CellValues based on the txt file and place PacMan and ghosts at their starting locations.
     * "W" indicates a wall, "E" indicates an empty square, "B" indicates a big dot, "S" indicates
     * a small dot, "1" or "2" indicates the ghosts home, and "P" indicates Pacman's starting position.
     *
     * @param fileName txt file containing the board configuration
     */
    public void initializeLevel(String fileName) {
        rowCount = 32;
        columnCount = 18;

        File file = new File(fileName);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        grid = new CellValue[columnCount][rowCount];
        int row = 0;
//        int pacmanRow = 0;
//        int pacmanColumn = 0;
//        int ghost1Row = 0;
//        int ghost1Column = 0;
//        int ghost2Row = 0;
//        int ghost2Column = 0;
        while(sc.hasNextLine()){
            int column = 0;
            String line= sc.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()){
                String value = lineScanner.next();
                CellValue thisValue;
                switch (value) {
                    case "W":
                        thisValue = CellValue.RIVER;
                        break;
                    case "R":
                        thisValue = CellValue.ROAD;
                        break;
                    case "B":
                        thisValue = CellValue.BRIDGE;
                        break;
                    case "D":
                        thisValue = CellValue.DARK_GRASS;
                        break;
                    case "L":
                        thisValue = CellValue.LIGHT_GRASS;
                        break;
//                    case "P":
//                        thisValue = CellValue.PACMANHOME;
//                        pacmanRow = row;
//                        pacmanColumn = column;
//                        break;
                    default:
                        thisValue = CellValue.EMPTY;
                        break;
                }
                grid[column][row] = thisValue;
                column++;
            }
            row++;
        }
        userPossibleCardDrop = new boolean[columnCount][rowCount];
        for (int columnC = 0; columnC < columnCount; columnC++) {
            for (int rowC = 0; rowC < rowCount; rowC++) {
                if (rowC<=16)
                    userPossibleCardDrop[columnC][rowC] = false;
                else
                    userPossibleCardDrop[columnC][rowC] = true;
            }
        }
//        pacmanLocation = new Point2D(pacmanRow, pacmanColumn);
//        pacmanVelocity = new Point2D(0,0);
//        ghost1Location = new Point2D(ghost1Row,ghost1Column);
//        ghost1Velocity = new Point2D(-1, 0);
//        ghost2Location = new Point2D(ghost2Row,ghost2Column);
//        ghost2Velocity = new Point2D(-1, 0);
//        currentDirection = Direction.NONE;
//        lastDirection = Direction.NONE;
    }
    public void moveCharacters() {
        for (GameCharacter gameCharacter : gameCharactersLeft) {
            gameCharacter.moveCharacter();
            if (gameCharacter.getCharacterLocation().equals(leftFirstDestinationUser)){
                gameCharacter.setDestination(leftSecondDestinationUser);
            }
            else if (gameCharacter.getCharacterLocation().equals(leftSecondDestinationUser)){
                gameCharacter.setDestination(leftThirdDestinationUser);
            }
        }
        for (GameCharacter gameCharacter : gameCharactersRight) {
            gameCharacter.moveCharacter();
            if (gameCharacter.getCharacterLocation().equals(rightFirstDestinationUser)){
                gameCharacter.setDestination(rightSecondDestinationUser);
            }
            else if (gameCharacter.getCharacterLocation().equals(rightSecondDestinationUser)){
                gameCharacter.setDestination(rightThirdDestinationUser);
            }
        }
    }
    public void addToRight(GameCharacter gameCharacter){
        gameCharacter.setDestination(rightFirstDestinationUser);
        gameCharactersRight.add(gameCharacter);
        gameCharacters.add(gameCharacter);
    }
    public void addToLeft(GameCharacter gameCharacter){
        gameCharacter.setDestination(leftFirstDestinationUser);
        gameCharactersLeft.add(gameCharacter);
        gameCharacters.add(gameCharacter);
    }

    /**
     * @param row
     * @param column
     * @return the Cell Value of cell (row, column)
     */
    public CellValue getCellValue(int column, int row) {
        assert column >= 0 && column < this.grid.length && row >= 0 && row < this.grid[0].length;
        return this.grid[column][row];
    }
    public static boolean isYouWon() {
        return youWon;
    }

    public boolean isMoveAble(int column, int row){
        if (getCellValue(column,row) == CellValue.RIVER)
            return false;
        return true;
    }

    public ArrayList<GameCharacter> getGameCharacters() {
        return gameCharacters;
    }

    public boolean isDroppableUser(int column, int row){
        return userPossibleCardDrop[column][row];
    }
}
