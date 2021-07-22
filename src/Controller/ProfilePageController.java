package Controller;

import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * The controller of Profile page.
 */
public class ProfilePageController {
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
    private Double totalCost = 0.0;
    @FXML private Label username;
    @FXML private ImageView level;
    @FXML private ImageView card1;
    @FXML private ImageView card2;
    @FXML private ImageView card3;
    @FXML private ImageView card4;
    @FXML private ImageView card5;
    @FXML private ImageView card6;
    @FXML private ImageView card7;
    @FXML private ImageView card8;
    @FXML private Label avgElixirCostLabel;
    @FXML private Button backButton;
    /**
     * Action handler.
     */
    @FXML void actionHandler() {
        try {
            Stage stage;
            Parent root;
            stage = (Stage) backButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../View/mainpage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    /**
     * Instantiates a new Profile page controller.
     */
    public ProfilePageController(){
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
    }
    /**
     * Sets all battle deck image views and labels like username and avg elixir cost of deck by getting the battle deck from database
     */
    public void initialize(){
        username.setText(User.username);
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
        for (int i = 0; i < 8; i++) {
            String cardI = "card"+(i+1);
            ImageView card = null;
            if (card1.getId().equals(cardI))
                card = card1;
            else if (card2.getId().equals(cardI))
                card = card2;
            else if (card3.getId().equals(cardI))
                card = card3;
            else if (card4.getId().equals(cardI))
                card = card4;
            else if (card5.getId().equals(cardI))
                card = card5;
            else if (card6.getId().equals(cardI))
                card = card6;
            else if (card7.getId().equals(cardI))
                card = card7;
            else if (card8.getId().equals(cardI))
                card = card8;
            if (User.cards[i] == 1) {
                card.setImage(archers);
                totalCost+=3;
            }
            else if (User.cards[i] == 2) {
                card.setImage(arrows);
                totalCost+=3;
            }
            else if (User.cards[i] == 3) {
                card.setImage(babyDragon);
                totalCost+=4;
            }
            else if (User.cards[i] == 4) {
                card.setImage(barbarians);
                totalCost+=5;
            }
            else if (User.cards[i] == 5) {
                card.setImage(cannon);
                totalCost+=3;
            }
            else if (User.cards[i] == 6) {
                card.setImage(fireball);
                totalCost+=4;
            }
            else if (User.cards[i] == 7) {
                card.setImage(giant);
                totalCost+=5;
            }
            else if (User.cards[i] == 8) {
                card.setImage(infernoTower);
                totalCost+=5;
            }
            else if (User.cards[i] == 9) {
                card.setImage(miniPEKKA);
                totalCost+=4;
            }
            else if (User.cards[i] == 10) {
                card.setImage(wizard);
                totalCost+=5;
            }
            else if (User.cards[i] == 11) {
                card.setImage(valkyrie);
                totalCost+=4;
            }
            else if (User.cards[i] == 12) {
                card.setImage(rage);
                totalCost+=2;
            }
        }
        avgElixirCostLabel.setText((totalCost/8)+"");
    }
}
