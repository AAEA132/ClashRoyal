package Controller;

import Model.SoundPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The controller of Main page.
 */
public class MainPageController {
    @FXML private Button profileButton;
    @FXML private Button battleDeckButton;
    @FXML private Button battleHistoryButton;
    @FXML private Button trainingCampButton;
    @FXML private Button logoutButton;

    /**
     * Action handler.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML void actionHandler(ActionEvent event) throws IOException {
        if (event.getSource() == trainingCampButton) {
            startTrainingCamp();
        }
        else if (event.getSource() == battleDeckButton){
            battleDeck();
        }
        else if (event.getSource() == profileButton){
            profile();
        }
        else if (event.getSource() == logoutButton){
            logout();
        }
    }
    private void logout() {
        try {
            Stage stage;
            Parent root;
            stage = (Stage) profileButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../View/loginpage.fxml"));
            SoundPlayer.stop("main");
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
            stage = (Stage) profileButton.getScene().getWindow();
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

    /**
     * Start training camp.
     *
     * @throws IOException the io exception
     */
    protected void startTrainingCamp() throws IOException {
        Stage stage;
        stage = (Stage) trainingCampButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/trainingcamppage.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        SoundPlayer.stop("main");
//        SoundPlayer.play("battle");
        GameController controller = loader.getController();
        System.out.println(controller.getBoardHeight());
        stage.setScene(new Scene(root, 500, 750));
        stage.show();
        root.requestFocus();
    }
}
