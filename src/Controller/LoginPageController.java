package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPageController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpLink;

    @FXML
    private Label loginLabel;

//    @FXML
//    protected void actionHandler(ActionEvent event) throws Exception{
//        if (event.getSource () == loginButton) {
////            Client client = connect();
//            Thread.sleep(1000);
////            User user = client.getResult();
////            if (user != null) {
//            if (true){
//                Stage stage;
//                stage = (Stage) usernameField.getScene().getWindow();
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("../View/chat.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
////                    Logger.getLogger(ChatCon.class.getName()).log(Level.SEVERE, null, e);
//                }
//
////                ChatCon chatController = loader.getController();
////                chatController.setCurrentUser(user);
//
//                Parent root = loader.getRoot();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            }
//            else
//            {
//                // warning visible
//            }
//
//        }
//        else if (event.getSource () == signUpLink){
//
//            Stage stage;
//            Parent root;
//
//            stage = (Stage) signUpLink.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("../View/signUp.fxml"));
//
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//    }

    @FXML
    protected void actionHandler (ActionEvent event) throws Exception{
        if (event.getSource() == loginButton)
            loginPressed(event);
        else if (event.getSource() == signUpLink)
            createAccountForm(event);
    }

    @FXML
    protected void loginPressed(ActionEvent event){
        if (usernameField.getText().isBlank() == false && passField.getText().isBlank() == false){
            validateLogin();
        }
        else {
            loginLabel.setText("Pleas enter your username & password");
        }
    }

    private void validateLogin() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();

        String verifyLogin = "select count(1) from user_account where username = '" + usernameField.getText() + "' and password = '" + passField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    loginLabel.setText("Congrats!");
                    Stage stage;
                    Parent root;
                    stage = (Stage) signUpLink.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("../View/mainpage.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    loginLabel.setText("Invalid Login! Try again");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    protected void createAccountForm(ActionEvent event){
        try {
            Stage stage;
            Parent root;
            stage = (Stage) signUpLink.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../View/signuppage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            Stage primaryStage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("View/signuppage.fxml"));
//            Image icon = new Image("Photos/clashRoyaleIconPic.png");
//            primaryStage.getIcons().add(icon);
//            Scene scene = new Scene(root, 400, 600);
//            scene.getStylesheets().add("View/Styles.css");
//            primaryStage.setTitle("Clash Royale");
//            primaryStage.setResizable (false);
//            primaryStage.setScene(scene);
//            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
