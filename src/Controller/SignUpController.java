package Controller;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.net.URL;
import java.sql.Statement;
import java.sql.Connection;

public class SignUpController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button loginLink;

    @FXML
    private TextField rePassField;

    @FXML
    private Label massageLable;

    @FXML
    protected void actionHandler(ActionEvent event) throws Exception{
        if (event.getSource() == loginLink)
            LoginPressed(event);
        else if (event.getSource() == signUpButton)
            SignUpPressed(event);
    }
    @FXML
    protected void SignUpPressed(ActionEvent event) {
        if (usernameField.getText().isBlank() == false && passField.getText().isBlank() == false && rePassField.getText().isBlank() == false){
            registerUser();
        }
        else {
            massageLable.setText("Pleas enter your username & password! Don't Leave any field blank");
        }
    }
    @FXML
    protected void LoginPressed(ActionEvent event) {
        try {
            Stage stage;
            Parent root;
            stage = (Stage) loginLink.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../View/loginpage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

//    protected void closedButtonPressed(ActionEvent event){
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
//    }
//    @FXML
//    protected void actionHandler(ActionEvent event) throws Exception{
//     if (event.getSource () == signUpButton) {
////           Client client = connect();
//            Thread.sleep(1000);
////            User user = client.getResult();
////            if (user != null) {
//         if (true){
//              Stage stage;
//             stage = (Stage) usernameField.getScene().getWindow();
//             FXMLLoader loader = new FXMLLoader();
//             loader.setLocation(getClass().getResource("../View/chat.fxml"));
//             try {
//                    loader.load();
//                } catch (IOException e) {
//                 e.printStackTrace();
////                    Logger.getLogger(ChatCon.class.getName()).log(Level.SEVERE, null, e);
//                }
//
////             ChatCon chatController = loader.getController();
////                chatController.setCurrentUser(user);
//
//            Parent root = loader.getRoot();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//        else
//        {
//            System.out.println("--------------");
//            // warning visible
//        }
//
//    }
//    else if (event.getSource () == loginLink){
//
//        Stage stage;
//        Parent root;
//
//        stage = (Stage) loginLink.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//}
    protected void registerUser(){
        if (!passField.getText().equals(rePassField.getText())){
            massageLable.setText("Password does not match");
        }
        else {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connectDB = databaseConnection.getConnection();
            String username = usernameField.getText();
            String password = passField.getText();

            try {
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username LIKE '" + username + "'");
                if (!resultSet.next()){
                    String insertFields = "INSERT INTO users (username, password) VALUES ('";
                    String insertValues = username + "','" + password + "')";
                    String insertToRegister = insertFields + insertValues;
                    Statement statement2 = connectDB.createStatement();
                    statement2.executeUpdate(insertToRegister);
                    massageLable.setText("Signed up successfully");
                }
                else if (resultSet.getString("username").equals(username)) {
                    massageLable.setText("User with this username exists, pls choose another username");
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}
