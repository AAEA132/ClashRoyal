package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
/**
 * The type Sign up controller.
 */
public class SignUpController {
    @FXML private TextField usernameField;
    @FXML private TextField passField;
    @FXML private Button signUpButton;
    @FXML private Button loginLink;
    @FXML private TextField rePassField;
    @FXML private Label massageLabel;
    /**
     * Action handler.
     *
     * @param event the event
     */
    @FXML protected void actionHandler(ActionEvent event) {
        if (event.getSource() == loginLink)
            LoginPressed();
        else if (event.getSource() == signUpButton)
            SignUpPressed();
    }
    /**
     * Sign up pressed.
     */
    @FXML
    protected void SignUpPressed() {
        if (usernameField.getText().isBlank() == false && passField.getText().isBlank() == false && rePassField.getText().isBlank() == false){
            registerUser();
        }
        else {
            massageLabel.setText("Pleas enter your username & password! Don't Leave any field blank");
        }
    }
    /**
     * Login pressed.
     */
    @FXML
    protected void LoginPressed() {
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
    /**
     * Register user.
     */
    protected void registerUser(){
        if (!passField.getText().equals(rePassField.getText())){
            massageLabel.setText("Password does not match");
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
                    massageLabel.setText("Signed up successfully");
                }
                else if (resultSet.getString("username").equals(username)) {
                    massageLabel.setText("User with this username exists, pls choose another username");
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}
