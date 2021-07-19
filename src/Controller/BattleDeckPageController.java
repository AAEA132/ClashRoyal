package Controller;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class BattleDeckPageController {
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
        private ImageView archerCard;

        @FXML
        private ImageView arrowCard;

        @FXML
        private ImageView babyDragonCard;

        @FXML
        private ImageView barbariansCard;

        @FXML
        private ImageView cannonCard;

        @FXML
        private ImageView fireballCard;

        @FXML
        private ImageView giantCard;

        @FXML
        private ImageView infernoTowerCard;

        @FXML
        private ImageView miniPEKKACard;

        @FXML
        private ImageView wizardCard;

        @FXML
        private ImageView valkyrieCard;

        @FXML
        private ImageView rageCard;

        @FXML
        private Label Source;

        @FXML
        private Label  Target;

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
                        imageView.setImage(image);

                } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                }
                event.consume();
        }
        @FXML
        protected void onDragDetectedHandler(MouseEvent event){
                ImageView imageView = (ImageView) event.getSource();
                Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putImage(imageView.getImage());
                dragboard.setContent(clipboardContent);
                event.consume();
        }

}