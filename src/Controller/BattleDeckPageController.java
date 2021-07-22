package Controller;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * The controller class for Battle deck page.
 */
public class BattleDeckPageController {

        private String srcId;
        private String targetId;

        @FXML private ImageView card1;
        @FXML private ImageView card2;
        @FXML private ImageView card3;
        @FXML private ImageView card4;
        @FXML private ImageView card5;
        @FXML private ImageView card6;
        @FXML private ImageView card7;
        @FXML private ImageView card8;
        @FXML private ImageView archerCard; //1
        @FXML private ImageView arrowCard; //2
        @FXML private ImageView babyDragonCard;//3
        @FXML private ImageView barbariansCard; //4
        @FXML private ImageView cannonCard; //5
        @FXML private ImageView fireballCard; //6
        @FXML private ImageView giantCard; //7
        @FXML private ImageView infernoTowerCard; //8
        @FXML private ImageView miniPEKKACard; //9
        @FXML private ImageView wizardCard; //10
        @FXML private ImageView valkyrieCard; //11
        @FXML private ImageView rageCard; //12
        @FXML private Button backButton;

        /**
         * Initialize method of javaFx.
         * First it does a query on database and adds previous battle deck (saved on database) to the cards field of the User class
         * then it sets the battle deck images on image views and disables the source image views which their card is present in battle deck already
         */
        public void initialize(){
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connectDB = databaseConnection.getConnection();
                try {
                        Statement statement = connectDB.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username LIKE '" + User.username + "'" );
                        resultSet.next();
                        for (int i = 0; i < 8; i++) {
                                User.cards[i] = resultSet.getInt("card"+(i+1));
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
                                        card.setImage(archerCard.getImage());
                                        archerCard.setDisable(true);
                                }
                                else if (User.cards[i] == 2) {
                                        card.setImage(arrowCard.getImage());
                                        arrowCard.setDisable(true);
                                }
                                else if (User.cards[i] == 3) {
                                        card.setImage(babyDragonCard.getImage());
                                        babyDragonCard.setDisable(true);
                                }
                                else if (User.cards[i] == 4) {
                                        card.setImage(barbariansCard.getImage());
                                        barbariansCard.setDisable(true);
                                }
                                else if (User.cards[i] == 5) {
                                        card.setImage(cannonCard.getImage());
                                        cannonCard.setDisable(true);
                                }
                                else if (User.cards[i] == 6) {
                                        card.setImage(fireballCard.getImage());
                                        fireballCard.setDisable(true);
                                }
                                else if (User.cards[i] == 7) {
                                        card.setImage(giantCard.getImage());
                                        giantCard.setDisable(true);
                                }
                                else if (User.cards[i] == 8) {
                                        card.setImage(infernoTowerCard.getImage());
                                        infernoTowerCard.setDisable(true);
                                }
                                else if (User.cards[i] == 9) {
                                        card.setImage(miniPEKKACard.getImage());
                                        miniPEKKACard.setDisable(true);
                                }
                                else if (User.cards[i] == 10) {
                                        card.setImage(wizardCard.getImage());
                                        wizardCard.setDisable(true);
                                }
                                else if (User.cards[i] == 11) {
                                        card.setImage(valkyrieCard.getImage());
                                        valkyrieCard.setDisable(true);
                                }
                                else if (User.cards[i] == 12) {
                                        card.setImage(rageCard.getImage());
                                        rageCard.setDisable(true);
                                }
                        }
                }catch (Exception e){
                        e.printStackTrace();
                        e.getCause();
                }
        }

        /**
         * Back function for backButton.
         * moves back to main page
         */
        @FXML void back() {
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
         * Drag over handler.
         *
         * @param event the event
         */
        @FXML
        protected void dragOverHandler(DragEvent event) {
                if (event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
        }

        /**
         * Drag dropped handler.
         * Sets target's image view to dropped image and disables the source, also enables the source of the previous image in that image view if
         * there is any; also updates cards field of class User and data base
         *
         * @param event the event
         */
        @FXML
        protected void dragDroppedHandler(DragEvent event){
                try {
                        Image image = event.getDragboard().getImage();
                        ImageView imageView = (ImageView) event.getTarget();

                        String cardI = imageView.getId();
                        int idTarget = getTID(cardI);
                        int idSrc = User.cards[idTarget];
                        if (idSrc==1)
                                archerCard.setDisable(false);
                        if (idSrc==2)
                                arrowCard.setDisable(false);
                        if (idSrc==3)
                                babyDragonCard.setDisable(false);
                        if (idSrc==4)
                                barbariansCard.setDisable(false);
                        if (idSrc==5)
                                cannonCard.setDisable(false);
                        if (idSrc==6)
                                fireballCard.setDisable(false);
                        if (idSrc==7)
                                giantCard.setDisable(false);
                        if (idSrc==8)
                                infernoTowerCard.setDisable(false);
                        if (idSrc==0)
                                miniPEKKACard.setDisable(false);
                        if (idSrc==10)
                                wizardCard.setDisable(false);
                        if (idSrc==11)
                                valkyrieCard.setDisable(false);
                        if (idSrc==12)
                                rageCard.setDisable(false);

                        int dragSrc = getId(srcId);
                        if (dragSrc==1)
                                archerCard.setDisable(true);
                        if (dragSrc==2)
                                arrowCard.setDisable(true);
                        if (dragSrc==3)
                                babyDragonCard.setDisable(true);
                        if (dragSrc==4)
                                barbariansCard.setDisable(true);
                        if (dragSrc==5)
                                cannonCard.setDisable(true);
                        if (dragSrc==6)
                                fireballCard.setDisable(true);
                        if (dragSrc==7)
                                giantCard.setDisable(true);
                        if (dragSrc==8)
                                infernoTowerCard.setDisable(true);
                        if (dragSrc==9)
                                miniPEKKACard.setDisable(true);
                        if (dragSrc==10)
                                wizardCard.setDisable(true);
                        if (dragSrc==11)
                                valkyrieCard.setDisable(true);
                        if (dragSrc==12)
                                rageCard.setDisable(true);

                        imageView.setImage(image);

                } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                }
                ImageView imageView = (ImageView) event.getSource();
                targetId = imageView.getId();
                update(srcId,targetId);
                event.consume();
        }

        /**
         * On drag detected handler.
         *
         * @param event the event
         */
        @FXML
        protected void onDragDetectedHandler(MouseEvent event){
                ImageView imageView = (ImageView) event.getSource();
                srcId = imageView.getId();
                Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putImage(imageView.getImage());
                dragboard.setContent(clipboardContent);
                event.consume();
        }

        /**
         * Updates database and cards field of class User
         * @param srcId fxId of the image view which was dragged from
         * @param targetId fxId of the image view which is dropped on
         */
        private void update(String srcId, String targetId) {
                int id = getId(srcId);
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connectDB = databaseConnection.getConnection();
                String insertFields = "UPDATE users SET "+ targetId + " = " + id + " WHERE username = '" + User.username + "'";
                try {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(insertFields);
                        User.cards[getTID(targetId)] = id;
                }catch (Exception e){
                        e.printStackTrace();
                        e.getCause();
                }
        }

        private int getTID(String targetId) {
                if (targetId.equals(card1.getId()))
                        return 0;
                else if (targetId.equals(card2.getId()))
                        return 1;
                else if (targetId.equals(card3.getId()))
                        return 2;
                else if (targetId.equals(card4.getId()))
                        return 3;
                else if (targetId.equals(card5.getId()))
                        return 4;
                else if (targetId.equals(card6.getId()))
                        return 5;
                else if (targetId.equals(card7.getId()))
                        return 6;
                else if (targetId.equals(card8.getId()))
                        return 7;
                else
                        return -1;
        }

        private int getId(String srcId) {
                if (srcId.equals(archerCard.getId()))
                        return 1;
                else if (srcId.equals(arrowCard.getId()))
                        return 2;
                else if (srcId.equals(babyDragonCard.getId()))
                        return 3;
                else if (srcId.equals(barbariansCard.getId()))
                        return 4;
                else if (srcId.equals(cannonCard.getId()))
                        return 5;
                else if (srcId.equals(fireballCard.getId()))
                        return 6;
                else if (srcId.equals(giantCard.getId()))
                        return 7;
                else if (srcId.equals(infernoTowerCard.getId()))
                        return 8;
                else if (srcId.equals(miniPEKKACard.getId()))
                        return 9;
                else if (srcId.equals(wizardCard.getId()))
                        return 10;
                else if (srcId.equals(valkyrieCard.getId()))
                        return 11;
                else if (srcId.equals(rageCard.getId()))
                        return 12;
                else
                        return 0;
        }
}