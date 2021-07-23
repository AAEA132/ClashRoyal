package Controller;
import Model.SoundPlayer;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * The controller of Login page.
 */
public class LoginPageController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passField;
    @FXML private Button loginButton;
    @FXML private Button signUpLink;
    @FXML private Label loginLabel;

    /**
     * Action handler.
     *
     * @param event the event
     */
    @FXML
    protected void actionHandler (ActionEvent event) {
        if (event.getSource() == loginButton)
            loginPressed();
        else if (event.getSource() == signUpLink)
            createAccountForm();
    }

    /**
     * Login pressed.
     */
    @FXML
    protected void loginPressed() {
        if (usernameField.getText().isBlank() == false && passField.getText().isBlank() == false){
            validateLogin();
        }
        else {
            loginLabel.setText("Pleas enter your username & password");
        }
    }

    /**
     * Validates the login
     */
    private void validateLogin() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();
        String verifyLogin = "select count(1) from users where username = '" + usernameField.getText() + "' and password = '" + passField.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    loginLabel.setText("Congrats!");
                    User.username = usernameField.getText();
                    Stage stage;
                    Parent root;
                    SoundPlayer.initializeGameSounds();
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

    /**
     * Create account form.
     * Moves to sign up page
     */
    protected void createAccountForm(){
        try {
            Stage stage;
            Parent root;
            stage = (Stage) signUpLink.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../View/signuppage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
