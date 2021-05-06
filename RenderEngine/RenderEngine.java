package RenderEngine;

import Designer.Designer;
import Rigidbody.Position;
import javafx.scene.image.Image;
import mikera.vectorz.Vector2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RenderEngine {

    public static void render(Image image, Position position) {

        // TODO: add a condition to check the position and see if its close to Mario so to be rendered

        Vector2 pos = position.getPos();

        Designer.gc.drawImage(image, pos.x, pos.y);
    }

    public static void renderImage(String image, double x, double y) {

        try {
            Image image1 = new Image(new FileInputStream(image));
            Designer.gc.drawImage(image1, x, y);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
