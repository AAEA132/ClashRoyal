package Controller;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BattleDeckPageController {

        private String srcId;
        private String targetId;


        @FXML
        private ImageView card1;

        @FXML
        private ImageView card2;

        @FXML
        private ImageView card3;

        @FXML
        private ImageView card4;

        @FXML
        private ImageView card5;

        @FXML
        private ImageView card6;

        @FXML
        private ImageView card7;

        @FXML
        private ImageView card8;


        @FXML
        private ImageView archerCard; //1

        @FXML
        private ImageView arrowCard; //2

        @FXML
        private ImageView babyDragonCard;//3

        @FXML
        private ImageView barbariansCard; //4

        @FXML
        private ImageView cannonCard; //5

        @FXML
        private ImageView fireballCard; //6

        @FXML
        private ImageView giantCard; //7

        @FXML
        private ImageView infernoTowerCard; //8

        @FXML
        private ImageView miniPEKKACard; //9

        @FXML
        private ImageView wizardCard; //10

        @FXML
        private ImageView valkyrieCard; //11

        @FXML
        private ImageView rageCard; //12

        @FXML
        private Label Source;

        @FXML
        private Label  Target;

        public void initialize(){
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connectDB = databaseConnection.getConnection();
//                String insertFields = "UPDATE users SET "+ targetId + " = " + id + " WHERE username = '" + User.username + "'";
//                String insertValues = id + "')";
//                String insertToRegister = insertFields + insertValues;
                try {
                        Statement statement = connectDB.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username LIKE '" + User.username + "'" );
//                        statement.executeUpdate(insertFields);
//                        Source.setText("Updated");
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
//                        resultSet.next();
//                        User.cards[0] = resultSet.getInt("card1");
//                        User.cards[1] = resultSet.getInt("card2");
//                        User.cards[2] = resultSet.getInt("card3");
//                        User.cards[3] = resultSet.getInt("card4");
//                        User.cards[4] = resultSet.getInt("card5");
//                        User.cards[5] = resultSet.getInt("card6");
//                        User.cards[6] = resultSet.getInt("card7");
//                        User.cards[7] = resultSet.getInt("card8");
//                        ImageView card = new ImageView();
//                        for (int i = 0; i < 8; i++) {
//                                for (int j = 0; j < 8; j++) {
//                                        String cardI = "card" + (j+1);
//                                        if (card1.getId().equals(cardI))
//                                                card = card1;
//                                        if (card2.getId().equals(cardI))
//                                                card = card2;
//                                        if (card3.getId().equals(cardI))
//                                                card = card3;
//                                        if (card4.getId().equals(cardI))
//                                                card = card4;
//                                        if (card5.getId().equals(cardI))
//                                                card = card5;
//                                        if (card6.getId().equals(cardI))
//                                                card = card6;
//                                        if (card7.getId().equals(cardI))
//                                                card = card7;
//                                        if (card8.getId().equals(cardI))
//                                                card = card8;
//                                        if (User.cards[i] == 1)
//                                                card.setImage(archerCard.getImage());
//                                        if (User.cards[i] == 2)
//                                                card.setImage(arrowCard.getImage());
//                                        if (User.cards[i] == 3)
//                                                card.setImage(babyDragonCard.getImage());
//                                        if (User.cards[i] == 4)
//                                                card.setImage(barbariansCard.getImage());
//                                        if (User.cards[i] == 5)
//                                                card.setImage(cannonCard.getImage());
//                                        if (User.cards[i] == 6)
//                                                card.setImage(fireballCard.getImage());
//                                        if (User.cards[i] == 7)
//                                                card.setImage(giantCard.getImage());
//                                        if (User.cards[i] == 8)
//                                                card.setImage(infernoTowerCard.getImage());
//                                        if (User.cards[i] == 9)
//                                                card.setImage(miniPEKKACard.getImage());
//                                        if (User.cards[i] == 10)
//                                                card.setImage(wizardCard.getImage());
//                                        if (User.cards[i] == 11)
//                                                card.setImage(valkyrieCard.getImage());
//                                        if (User.cards[i] == 12)
//                                                card.setImage(rageCard.getImage());
//                                }
//                        }
                }catch (Exception e){
                        e.printStackTrace();
                        e.getCause();
                }
        }

        @FXML
        void onDragDetectedHandlerText(MouseEvent event) {
                Dragboard dragboard = Source.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(Source.getText());
                dragboard.setContent(clipboardContent);
                event.consume();
        }

        @FXML
        void dragOverHandlerText(DragEvent event) {
                if (event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
        }

        @FXML
        void dragDroppedHandlerText(DragEvent event) {
                Target.setText(event.getDragboard().getString());
                event.consume();
        }
        @FXML
        protected void dragOverHandler(DragEvent event) {
                if (event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
        }
        @FXML
        protected void dragDroppedHandler(DragEvent event){
//                List<File> files = event.getDragboard().getFiles();
                try {
//                        Image image = new Image(new FileInputStream(files.get(0)));
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
                        if (dragSrc==0)
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

        private void update(String srcId, String targetId) {
                int id = getId(srcId);
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connectDB = databaseConnection.getConnection();
                String insertFields = "UPDATE users SET "+ targetId + " = " + id + " WHERE username = '" + User.username + "'";
//                String insertValues = id + "')";
//                String insertToRegister = insertFields + insertValues;
                try {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(insertFields);
                        User.cards[getTID(targetId)] = id;
//                        Source.setText("Updated");
                        Source.setText(srcId);
                        Target.setText(targetId);
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