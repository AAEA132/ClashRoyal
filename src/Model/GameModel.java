package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameModel {


    public enum CellValue {
        EMPTY, LIGHT_GRASS, DARK_GRASS, ROAD, RIVER, BRIDGE
    };

    private int rowCount;
    private int columnCount;
    private CellValue[][] grid;
    private static boolean gameOver;
    private static boolean youWon;


    public GameModel() {
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

        grid = new CellValue[rowCount][columnCount];
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
                grid[row][column] = thisValue;
                column++;
            }
            row++;
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


    /**
     * @param row
     * @param column
     * @return the Cell Value of cell (row, column)
     */
    public CellValue getCellValue(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }
    public static boolean isYouWon() {
        return youWon;
    }

}
