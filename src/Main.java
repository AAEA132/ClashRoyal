import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/loginpage.fxml"));
        Image icon = new Image("Photos/clashRoyaleIconPic.png");
        primaryStage.getIcons().add(icon);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("Clash Royale");
        primaryStage.setResizable (false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
