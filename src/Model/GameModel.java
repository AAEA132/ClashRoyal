package Model;

import Model.Characters.GameCharacter;
import javafx.geometry.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Game model.
 */
public class GameModel {
    private Point2D leftFirstDestinationUser = new Point2D(3,17);
    private Point2D rightFirstDestinationUser = new Point2D(14, 17);
    private Point2D leftSecondDestinationUser = new Point2D(3,5);
    private Point2D rightSecondDestinationUser = new Point2D(14, 5);
    private Point2D leftThirdDestinationUser = new Point2D(8, 0);
    private Point2D rightThirdDestinationUser = new Point2D(9, 0);

    private Point2D leftFirstDestinationBot = new Point2D(3,14);
    private Point2D rightFirstDestinationBot= new Point2D(14, 14);
    private Point2D leftSecondDestinationBot = new Point2D(3,26);
    private Point2D rightSecondDestinationBot = new Point2D(14, 26);
    private Point2D leftThirdDestinationBot = new Point2D(8, 31);
    private Point2D rightThirdDestinationBot = new Point2D(9, 31);

    private ArrayList<GameCharacter> gameCharacters;
    private ArrayList<GameCharacter> gameCharactersRight;
    private ArrayList<GameCharacter> gameCharactersLeft;
    private ArrayList<GameCharacter> gameCharactersRightUser;
    private ArrayList<GameCharacter> gameCharactersLeftUser;
    private ArrayList<GameCharacter> gameCharactersRightBot;
    private ArrayList<GameCharacter> gameCharactersLeftBot;

    /**
     * The enum Cell value.
     */
    public enum CellValue {
        /**
         * Empty cell value.
         */
        EMPTY,
        /**
         * Light grass cell value.
         */
        LIGHT_GRASS,
        /**
         * Dark grass cell value.
         */
        DARK_GRASS,
        /**
         * Road cell value.
         */
        ROAD,
        /**
         * River cell value.
         */
        RIVER,
        /**
         * Bridge cell value.
         */
        BRIDGE,
        USER_TOWER,
        BOT_TOWER,
        BOT_KING,
        USER_KING
    };

    private int rowCount;
    private int columnCount;
    private CellValue[][] grid;
    private boolean[][] userPossibleCardDrop;
    private boolean[][] botPossibleCardDrop;
    private static boolean gameOver;
    private static boolean youWon;

    /**
     * Instantiates a new Game model.
     */
    public GameModel() {
        gameCharacters = new ArrayList<>();
        gameCharactersRight = new ArrayList<>();
        gameCharactersLeft = new ArrayList<>();
        gameCharactersRightUser = new ArrayList<>();
        gameCharactersLeftUser = new ArrayList<>();
        gameCharactersRightBot = new ArrayList<>();
        gameCharactersLeftBot = new ArrayList<>();
        this.startNewGame();
    }
    private void startNewGame() {
        gameOver = false;
        youWon = false;
        initializeLevel(Controller.GameController.getMapFile());
    }

    /**
     * Configure the grid CellValues based on the txt file
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
                    case "E":
                        thisValue = CellValue.BOT_TOWER;
                        break;
                    case "T":
                        thisValue = CellValue.USER_TOWER;
                        break;
                    case "K":
                        thisValue = CellValue.USER_KING;
                        break;
                    case "A":
                        thisValue = CellValue.BOT_KING;
                        break;
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
        for (int columnU = 0; columnU < columnCount; columnU++) {
            for (int rowU = 0; rowU < rowCount; rowU++) {
                if (rowU<=16)
                    userPossibleCardDrop[columnU][rowU] = false;
                else
                    userPossibleCardDrop[columnU][rowU] = true;
            }
        }
        botPossibleCardDrop = new boolean[columnCount][rowCount];
        for (int columnB = 0; columnB < columnCount; columnB++) {
            for (int rowB = 0; rowB < rowCount; rowB++) {
                if (rowB>14)
                    botPossibleCardDrop[columnB][rowB] = false;
                else
                    botPossibleCardDrop[columnB][rowB] = true;
            }
        }
    }

    /**
     * Move characters.
     */
    public void moveCharacters() {
        for (GameCharacter gameCharacter:gameCharacters) {
            gameCharacter.moveCharacter();
            if (gameCharactersRight.contains(gameCharacter)){
                if (gameCharactersRightUser.contains(gameCharacter)){
                    if (gameCharacter.getDestination().equals(rightFirstDestinationUser)){
                        if (gameCharacter.getCharacterLocation().equals(rightFirstDestinationUser)){
                            gameCharacter.setDestination(rightSecondDestinationUser);
                        }
                    }
                    else if (gameCharacter.getDestination().equals(rightSecondDestinationUser)){
                        if (gameCharacter.getCharacterLocation().equals(rightSecondDestinationUser)){
                            gameCharacter.setDestination(rightThirdDestinationUser);
                        }
                    }
                }
                else if (gameCharactersRightBot.contains(gameCharacter)){
                    if (gameCharacter.getDestination().equals(rightFirstDestinationBot)){
                        if (gameCharacter.getCharacterLocation().equals(rightFirstDestinationBot)){
                            gameCharacter.setDestination(rightSecondDestinationBot);
                        }
                    }
                    else if (gameCharacter.getDestination().equals(rightSecondDestinationBot)){
                        if (gameCharacter.getCharacterLocation().equals(rightSecondDestinationBot)){
                            gameCharacter.setDestination(rightThirdDestinationBot);
                        }
                    }
                }
            }
            else if (gameCharactersLeft.contains(gameCharacter)){
                if (gameCharactersLeftUser.contains(gameCharacter)){
                    if (gameCharacter.getDestination().equals(leftFirstDestinationUser)){
                        if (gameCharacter.getCharacterLocation().equals(leftFirstDestinationUser)){
                            gameCharacter.setDestination(leftSecondDestinationUser);
                        }
                    }
                    else if (gameCharacter.getDestination().equals(leftSecondDestinationUser)){
                        if (gameCharacter.getCharacterLocation().equals(leftSecondDestinationUser)){
                            gameCharacter.setDestination(leftThirdDestinationUser);
                        }
                    }
                }
                else if (gameCharactersLeftBot.contains(gameCharacter)){
                    if (gameCharacter.getDestination().equals(leftFirstDestinationBot)){
                        if (gameCharacter.getCharacterLocation().equals(leftFirstDestinationBot)){
                            gameCharacter.setDestination(leftSecondDestinationBot);
                        }
                    }
                    else if (gameCharacter.getDestination().equals(leftSecondDestinationBot)){
                        if (gameCharacter.getCharacterLocation().equals(leftSecondDestinationBot)){
                            gameCharacter.setDestination(leftThirdDestinationBot);
                        }
                    }
                }
            }
        }
    }

    /**
     * Add to right user.
     *
     * @param gameCharacter the game character
     */
    public void addToRightUser(GameCharacter gameCharacter){
        gameCharacter.setDestination(rightFirstDestinationUser);
        gameCharactersRightUser.add(gameCharacter);
        gameCharactersRight.add(gameCharacter);
        gameCharacters.add(gameCharacter);
    }

    /**
     * Add to left user.
     *
     * @param gameCharacter the game character
     */
    public void addToLeftUser(GameCharacter gameCharacter){
        gameCharacter.setDestination(leftFirstDestinationUser);
        gameCharactersLeftUser.add(gameCharacter);
        gameCharactersLeft.add(gameCharacter);
        gameCharacters.add(gameCharacter);
    }

    /**
     * Add to right bot.
     *
     * @param gameCharacter the game character
     */
    public void addToRightBot(GameCharacter gameCharacter){
        gameCharacter.setDestination(rightFirstDestinationBot);
        gameCharactersRightBot.add(gameCharacter);
        gameCharactersRight.add(gameCharacter);
        gameCharacters.add(gameCharacter);
    }

    /**
     * Add to left bot.
     *
     * @param gameCharacter the game character
     */
    public void addToLeftBot(GameCharacter gameCharacter){
        gameCharacter.setDestination(leftFirstDestinationBot);
        gameCharactersLeftBot.add(gameCharacter);
        gameCharactersLeft.add(gameCharacter);
        gameCharacters.add(gameCharacter);
    }

    /**
     * Gets cell value.
     *
     * @param column the column
     * @param row    the row
     * @return the Cell Value of cell (row, column)
     */
    public CellValue getCellValue(int column, int row) {
        assert column >= 0 && column < this.grid.length && row >= 0 && row < this.grid[0].length;
        return this.grid[column][row];
    }

    /**
     * Is you won boolean.
     *
     * @return the boolean
     */
    public static boolean isYouWon() {
        return youWon;
    }

    /**
     * Is move able boolean.
     *
     * @param column the column
     * @param row    the row
     * @return the boolean
     */
    public boolean isMoveAble(int column, int row){
        if (getCellValue(column,row) == CellValue.RIVER)
            return false;
        return true;
    }

    /**
     * Gets game characters.
     *
     * @return the game characters
     */
    public ArrayList<GameCharacter> getGameCharacters() {
        return gameCharacters;
    }

    /**
     * Is droppable user boolean.
     *
     * @param column the column
     * @param row    the row
     * @return the boolean
     */
    public boolean isDroppableUser(int column, int row){
        return userPossibleCardDrop[column][row];
    }

    /**
     * Is droppable bot boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isDroppableBot(int x, int y) {
        return botPossibleCardDrop[x][y];
    }
}
