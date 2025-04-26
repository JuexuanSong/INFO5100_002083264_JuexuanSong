import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import controller.ImageController;

public class ImageManagerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            ImageController controller = new ImageController(primaryStage, root);

            Scene scene = new Scene(root, 1000, 600);
            primaryStage.setTitle("Image Management Tool");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
