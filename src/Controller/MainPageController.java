package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.sql.Statement;

public class MainPageController {
    @FXML
    private Button profileBotton;

    @FXML
    private Button battleDeckButton;

    @FXML
    private Button battleHistoryButton;

    @FXML
    private Button trainingCampButton;

    @FXML
    private Button logoutBotton;

    @FXML
    void actionHandler(ActionEvent event) throws IOException {
        if (event.getSource() == trainingCampButton) {
            startTrainingCamp();
        }
        else if (event.getSource() == battleDeckButton){
            battleDeck();
        }
        else if (event.getSource() == profileBotton){
            profile();
        }
        else if (event.getSource() == logoutBotton){
            logout();
        }
    }

    private void logout() {
        try {
            Stage stage;
            Parent root;
            stage = (Stage) profileBotton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../View/loginpage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    private void profile() {
        try {
            Stage stage;
            Parent root;
            stage = (Stage) profileBotton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../View/profilepage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    private void battleDeck() {
        try {
            Stage stage;
            Parent root;
            stage = (Stage) battleDeckButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../View/battledeckpage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    protected void startTrainingCamp() throws IOException {
        Stage stage;
//        Parent root;
        stage = (Stage) trainingCampButton.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("../View/mainpage.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();



//        Stage primaryStage = null;
        FXMLLoader loader = new FXMLLoader();
        //load login fxml
        loader.setLocation(getClass().getResource("../View/trainingcamppage.fxml"));
        loader.load();
        Parent root = loader.getRoot();
//        stage.setTitle("PacMan");
        GameController controller = loader.getController();
//        root.setOnKeyPressed(controller);
        System.out.println(controller.getBoardHeight());
        double sceneWidth = controller.getBoardWidth() + 20.0;
        double sceneHeight = controller.getBoardHeight() + 70.0;
        stage.setScene(new Scene(root, 500, 750));
        stage.show();
        root.requestFocus();
    }
}
