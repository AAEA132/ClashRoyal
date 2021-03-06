package Controller;

import Model.Characters.*;
import Model.GameModel;
import Model.User;
import View.GameView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Game controller class.
 */
public class GameController {
    private String srcId;
    final private static double FRAMES_PER_SECOND = 5.0;
    private Timer timer;
    private int second;
    private int minute;
    private String ddSecond;
    private String ddMinute;
    private DecimalFormat decimalFormat = new DecimalFormat("00");
    private boolean paused;
    private static final String mapFile = "src/Map/map.txt";
    private GameModel gameModel;
    private int ready1 =0;
    private int ready2 =0;
    private int ready3 =0;
    private int ready4 =0;
    private int next =0;
    private Image archers;
    private Image arrows;
    private Image babyDragon;
    private Image barbarians;
    private Image cannon;
    private Image fireball;
    private Image giant;
    private Image infernoTower;
    private Image miniPEKKA;
    private Image rage;
    private Image valkyrie;
    private Image wizard;
    private ArrayList<Image> cards;
    private int[] botDeck;
    private BigDecimal elixirAmountDivideBy10;
    private int elixirAmountForBot;
    private int counter = 0;
    @FXML private GameView gameView;
    @FXML private ImageView nextCard;
    @FXML private ImageView readyCard4;
    @FXML private ImageView readyCard3;
    @FXML private ImageView readyCard1;
    @FXML private ImageView readyCard2;
    @FXML private ProgressBar elixirBar;
    @FXML private Label elixirLabel;
    @FXML private Label timerLabel;
    /**
     * Instantiates a new Game controller.
     * Sets images and instantiates some fields
     */
    public GameController() {
        cards = new ArrayList<>();
        botDeck = new int[8];
        elixirAmountForBot = 4;
        elixirAmountDivideBy10 = new BigDecimal(String.format("%.2f",0.0));
        this.archers = new Image(getClass().getResourceAsStream("/Photos/Card/archers.png"));
        this.arrows = new Image(getClass().getResourceAsStream("/Photos/Card/arrows.png"));
        this.babyDragon = new Image(getClass().getResourceAsStream("/Photos/Card/baby-dragon.png"));
        this.barbarians = new Image(getClass().getResourceAsStream("/Photos/Card/barbarians.png"));
        this.cannon = new Image(getClass().getResourceAsStream("/Photos/Card/cannon.png"));
        this.fireball = new Image(getClass().getResourceAsStream("/Photos/Card/fireball.png"));
        this.giant = new Image(getClass().getResourceAsStream("/Photos/Card/giant.png"));
        this.infernoTower = new Image(getClass().getResourceAsStream("/Photos/Card/inferno-tower.png"));
        this.miniPEKKA = new Image(getClass().getResourceAsStream("/Photos/Card/mini-pekka.png"));
        this.rage = new Image(getClass().getResourceAsStream("/Photos/Card/rage.png"));
        this.valkyrie = new Image(getClass().getResourceAsStream("/Photos/Card/valkyrie.png"));
        this.wizard = new Image(getClass().getResourceAsStream("/Photos/Card/wizard.png"));
        this.paused = false;
    }
    /**
     * Initialize second and minute, connects to database and sets cards field from User class to make sure its set
     * and update the model and view from the first txt file and starts the timer.
     * Also sets random cards for ready cards and next card
     */
    public void initialize() {
        second = 1;
        minute = 3;
        elixirBar.setStyle("-fx-accent: purple;");
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();
        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username LIKE '" + User.username + "'" );
            resultSet.next();
            for (int i = 0; i < 8; i++) {
                User.cards[i] = resultSet.getInt("card"+(i+1));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        ready1 = setRandomCard(readyCard1);
        ready2 = setRandomCard(readyCard2);
        ready3 = setRandomCard(readyCard3);
        ready4 = setRandomCard(readyCard4);
        next = setRandomCard(nextCard);
        gameModel = new GameModel();
        setBotBattleDeck();
        update();
        startTimer();
    }
    private int setRandomCard(ImageView imageView) {
        Random random = new Random();
        boolean p = true;
        int index;
        int id = 0;
        while (p){
            index = random.nextInt(8);
            id = User.cards[index];

            if (!(cards.contains(getImage(id)))){
                cards.add(getImage(id));
                p = false;
            }
        }
     imageView.setImage(getImage(id));
        return id;
    }
    /**
     * Schedules the model to update based on the timer.
     */
    private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(() -> update());
            }
        };
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }
    /**
     * Update method which is called repeatedly in timer; It updates timer label and elixir for both user and bot
     * First bot drops a random card, then moves all the characters and updates the view by calling its update method
     */
    private void update() {
        botCardDrop();
        if (counter < 5){
            counter++;
        }
        else if ( counter == 5) {
            if (minute > 0 && second > 0) {
                second--;
                ddSecond = decimalFormat.format(second);
                ddMinute = decimalFormat.format(minute);
                timerLabel.setText(ddMinute + ":" + ddSecond);
                if (second == 0) {
                    second = 59;
                    minute--;
                    ddSecond = decimalFormat.format(second);
                    ddMinute = decimalFormat.format(minute);
                    timerLabel.setText(ddMinute + ":" + ddSecond);
                }
            }
            if (elixirAmountForBot<10 && minute>0){
                elixirAmountForBot+=1;
            }
            else if (elixirAmountForBot<10){
                elixirAmountForBot+=2;
            }
            if (elixirAmountDivideBy10.doubleValue() < 1 && minute>0) {
                elixirAmountDivideBy10 = new BigDecimal(String.format("%.2f",elixirAmountDivideBy10.doubleValue() + 0.1));
                elixirBar.setProgress(elixirAmountDivideBy10.doubleValue());
                elixirLabel.setText(Integer.toString((int) Math.round(elixirAmountDivideBy10.doubleValue() * 10)));
            }
            else if (elixirAmountDivideBy10.doubleValue() < 1 && minute<=0) {
                elixirAmountDivideBy10 = new BigDecimal(String.format("%.2f",elixirAmountDivideBy10.doubleValue() + 0.2));
                elixirBar.setProgress(elixirAmountDivideBy10.doubleValue());
                String s = Integer.toString((int) Math.round(elixirAmountDivideBy10.doubleValue() * 10));
                if (s.equals("11")){
                    s = "10";
                    elixirAmountDivideBy10 = new BigDecimal(String.format("%.2f",1.0));
                }
                elixirLabel.setText(s);
            }
            counter = 0;
        }
        this.gameModel.moveCharacters();
        this.gameView.update(gameModel);
    }
    /**
     * Sets a random deck for bot
     */
    private void setBotBattleDeck() {
        Random random = new Random();
        ArrayList<Integer> chooses = new ArrayList<>();
        while (chooses.size()<9){
            int id = random.nextInt(12);
            if (!chooses.contains(id)){
                chooses.add(id);
            }
        }
        for (int i = 0; i < 8; i++) {
            botDeck[i] = chooses.get(i);
        }
    }
    /**
     * Bot drops a random card and makes a new game character
     */
    private void botCardDrop() {
        Random random = new Random();
        int drop = random.nextInt(5);
        if (drop == 1) {
            drop = random.nextInt(8);
            drop = botDeck[drop];
            if (elixirAmountForBot >= getElixir(drop)) {
                boolean p = true;
                while (p) {
                    int x = random.nextInt(17);
                    int y = random.nextInt(31);

                    if (drop == 2 || drop == 6 || drop == 12){
                        if (y!=15 && y!=16){
                            elixirAmountForBot -= getElixir(drop);
                            Image image = getImage(drop);
                            ImageView imageView = new ImageView(image);
                            imageView.setId(x+"#"+y);
                            GameCharacter gameCharacter = new GameSpell(new Point2D(x, y), imageView, gameModel);
                            if (x > 8) {
                                gameModel.addToRightBot(gameCharacter);
                            } else {
                                gameModel.addToLeftBot(gameCharacter);
                            }
                            imageView.setX(x);
                            imageView.setY(y);
                            imageView.setFitHeight(30);
                            imageView.setFitWidth(30);
                            imageView.setVisible(true);
                            gameView.getChildren().add(imageView);
                            p = false;
                        }
                    }
                    else if (drop == 5 || drop == 8){
                        if (gameModel.isDroppableBot(x, y)){
                            elixirAmountForBot -= getElixir(drop);
                            Image image = getImage(drop);
                            ImageView imageView = new ImageView(image);
                            imageView.setId(x+"#"+y);

                            GameCharacter gameCharacter = new GameBuilding(new Point2D(x, y), imageView, gameModel);
                            if (x > 8) {
                                gameModel.addToRightBot(gameCharacter);
                            } else {
                                gameModel.addToLeftBot(gameCharacter);
                            }
                            imageView.setX(x);
                            imageView.setY(y);
                            imageView.setFitHeight(30);
                            imageView.setFitWidth(30);
                            imageView.setVisible(true);
                            gameView.getChildren().add(imageView);
                            p = false;
                        }
                    }
                    else if (drop==1 || drop==3 || drop==4 || drop==7 || drop==9 || drop==10 || drop==11){
                        if (gameModel.isDroppableBot(x, y)){
                            elixirAmountForBot -= getElixir(drop);

                            Image image = getImage(drop);
                            ImageView imageView = new ImageView(image);
                            imageView.setId(x+"#"+y);
                            GameCharacter gameCharacter;
                            if (drop==1){
                                 gameCharacter = new GameArcher(new Point2D(x, y), imageView, gameModel);
                            }
                            else if (drop==3){
                                gameCharacter = new GameBabyDragon(new Point2D(x, y), imageView, gameModel);
                            }
                            else if (drop==4){
                                gameCharacter = new GameBarbarian(new Point2D(x, y), imageView, gameModel);
                            }
                            else if (drop==7){
                                gameCharacter = new GameGiant(new Point2D(x, y), imageView, gameModel);
                            }
                            else if (drop==9){
                                gameCharacter = new GameMiniPEKKA(new Point2D(x, y), imageView, gameModel);
                            }
                            else if (drop==10){
                                gameCharacter = new GameWizard(new Point2D(x, y), imageView, gameModel);
                            }
                            else {
                                gameCharacter = new GameValkyrie(new Point2D(x, y), imageView, gameModel);
                            }
                            if (x > 8) {
                                gameModel.addToRightBot(gameCharacter);
                            } else {
                                gameModel.addToLeftBot(gameCharacter);
                            }
                            imageView.setX(x);
                            imageView.setY(y);
                            imageView.setFitHeight(30);
                            imageView.setFitWidth(30);
                            imageView.setVisible(true);
                            gameView.getChildren().add(imageView);
                            p = false;
                        }
                    }
                }
            }
        }
    }
    /**
     * Drag dropped handler.
     *
     * @param event the event
     */
    public void dragDroppedHandler(DragEvent event) {
        try {
            ImageView tile = (ImageView) event.getTarget();
            String[] tokens = tile.getId().split("#");
            if ((elixirAmountDivideBy10.doubleValue()*10) >= getCost(srcId) && (gameModel.isDroppableUser(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])) || ((getIntId(srcId) == 2 || getIntId(srcId) == 6 || getIntId(srcId) == 12) && (Integer.parseInt(tokens[1]) != 15 && Integer.parseInt(tokens[1]) != 16)))) {
                elixirAmountDivideBy10 = new BigDecimal(String.format("%.2f",elixirAmountDivideBy10.doubleValue() - (((double)getCost(srcId))/10)));
                elixirBar.setProgress(elixirAmountDivideBy10.doubleValue());
                elixirLabel.setText(Integer.toString((int) Math.round(elixirAmountDivideBy10.doubleValue() * 10)));
                Image image = event.getDragboard().getImage();
                ImageView imageView = new ImageView(image);
                imageView.setId(tile.getId());
                GameCharacter gameCharacter;
                if (getIntId(srcId) == 5 || getIntId(srcId) == 8){
                    gameCharacter = new GameBuilding(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                }
                else if (getIntId(srcId) == 2 || getIntId(srcId) == 6 || getIntId(srcId) == 12){
                    gameCharacter = new GameSpell(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                }
                else {
                    if (getIntId(srcId) == 1){
                        gameCharacter = new GameArcher(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                    }
                    else if (getIntId(srcId) == 3){
                        gameCharacter = new GameBabyDragon(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                    }
                    else if (getIntId(srcId) == 4){
                        gameCharacter = new GameBarbarian(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                    }
                    else if (getIntId(srcId) == 7){
                        gameCharacter = new GameGiant(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                    }
                    else if (getIntId(srcId) == 9){
                        gameCharacter = new GameMiniPEKKA(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                    }
                    else if (getIntId(srcId) == 10){
                        gameCharacter = new GameWizard(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                    }
                    else{
                        gameCharacter = new GameValkyrie(new Point2D(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])),imageView,gameModel);
                    }
                }
                if (Integer.parseInt(tokens[0]) > 8){
                    gameModel.addToRightUser(gameCharacter);
                }
                else {
                    gameModel.addToLeftUser(gameCharacter);
                }
                imageView.setX((event.getX() - 15));
                imageView.setY((event.getY()) - 15);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                imageView.setVisible(true);
                gameView.getChildren().add(imageView);

                ImageView src = getId(srcId);
                cards.remove(src.getImage());
                src.setImage(nextCard.getImage());
                if (srcId.equals(readyCard1.getId()))
                    ready1 = next;
                else if (srcId.equals(readyCard2.getId()))
                    ready2 = next;
                else if (srcId.equals(readyCard3.getId()))
                    ready3 = next;
                else if (srcId.equals(readyCard4.getId()))
                    ready4 = next;
                next = setRandomCard(nextCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        event.consume();
    }

    /**
     * Drag over handler.
     *
     * @param event the event
     */
    public void dragOverHandler(DragEvent event) {
        if (event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }
    /**
     * On drag detected handler.
     *
     * @param event the event
     */
    @FXML
    void onDragDetectedHandler(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        srcId = imageView.getId();
        Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putImage(imageView.getImage());
        dragboard.setContent(clipboardContent);
        event.consume();
    }
    /**
     * Gets map file address.
     *
     * @return the map file
     */
    public static String getMapFile() {
        return mapFile;
    }
    /**
     * Gets board width.
     *
     * @return the board width
     */
    public double getBoardWidth() {
        return GameView.CELL_WIDTH * this.gameView.getColumnCount();
    }
    /**
     * Gets board height.
     *
     * @return the board height
     */
    public double getBoardHeight() {
        return GameView.CELL_WIDTH * this.gameView.getRowCount();
    }

    private ImageView getId(String srcId) {
        if (srcId.equals(readyCard1.getId()))
            return readyCard1;
        else if (srcId.equals(readyCard2.getId()))
            return readyCard2;
        else if (srcId.equals(readyCard3.getId()))
            return readyCard3;
        else if (srcId.equals(readyCard4.getId()))
            return readyCard4;
        else
            return null;
    }
    private int getCost(String src){
        if (src.equals(readyCard1.getId()))
            return getElixir(ready1);
        else if (src.equals(readyCard2.getId()))
            return getElixir(ready2);
        else if (src.equals(readyCard3.getId()))
            return getElixir(ready3);
        else if (src.equals(readyCard4.getId()))
            return getElixir(ready4);
        else
            return 0;
    }
    private int getIntId(String src){
        if (src.equals(readyCard1.getId()))
            return ready1;
        else if (src.equals(readyCard2.getId()))
            return ready2;
        else if (src.equals(readyCard3.getId()))
            return ready3;
        else if (src.equals(readyCard4.getId()))
            return ready4;
        else
            return 0;
    }
    private int getElixir(int id) {
        if (id == 1)
            return 3;
        else if (id == 2)
            return 3;
        else if (id == 3)
            return 4;
        else if (id == 4)
            return 5;
        else if (id == 5)
            return 3;
        else if (id == 6)
            return 4;
        else if (id == 7)
            return 5;
        else if (id == 8)
            return 5;
        else if (id == 9)
            return 4;
        else if (id == 10)
            return 5;
        else if (id == 11)
            return 4;
        else if (id == 12)
            return 2;
        else
            return 0;
    }
    private Image getImage(int srcId) {
        if (srcId == 1)
            return archers;
        else if (srcId == 2)
            return arrows;
        else if (srcId == 3)
            return babyDragon;
        else if (srcId == 4)
            return barbarians;
        else if (srcId == 5)
            return cannon;
        else if (srcId == 6)
            return fireball;
        else if (srcId == 7)
            return giant;
        else if (srcId == 8)
            return infernoTower;
        else if (srcId == 9)
            return miniPEKKA;
        else if (srcId == 10)
            return wizard;
        else if (srcId == 11)
            return valkyrie;
        else if (srcId == 12)
            return rage;
        else
            return null;
    }
}
