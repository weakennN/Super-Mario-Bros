package Designer;

import Animator.Animator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Designer {

    public static Scene scene;
    public static Canvas canvas;
    public static GraphicsContext gc;
    public static Group root;
    public static Image background;
    public static ImageView im;

    public static Scene initializeNodes() {

        canvas = new Canvas(1920, 1080);
        gc = canvas.getGraphicsContext2D();
        try {

            background = new Image(new FileInputStream(Animator.background));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      //  gc.translate(2000,0);
        root = new Group();
        root.getChildren().add(canvas);

        //TODO: add a imageView for the background
        scene = new Scene(root,1920,1080);


        return scene;
    }

}
