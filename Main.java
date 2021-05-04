import Designer.Designer;
import GameEngine.GameEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = Designer.initializeNodes();

        GameEngine engine = new GameEngine();

        stage.setTitle("Super Mario Game");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        engine.start();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
