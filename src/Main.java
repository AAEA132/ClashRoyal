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
        scene.getStylesheets().add("View/Styles.css");
        primaryStage.setTitle("Clash Royale");
        primaryStage.setResizable (false);
        primaryStage.setScene(scene);
//        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
