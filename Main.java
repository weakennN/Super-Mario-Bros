import UIEngine.Designer;
import Game.SuperMarioBros;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = Designer.initializeNodes();

        SuperMarioBros superMarioBros = new SuperMarioBros();

        stage.setTitle("Super Mario Bros.");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        superMarioBros.start();

        stage.setOnCloseRequest(e -> {

            superMarioBros.stop();
        });

    }

    public static void main(String[] args) {

        launch(args);
    }
}
