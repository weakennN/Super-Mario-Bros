package RenderEngine;

import Designer.Designer;
import Rigidbody.Position;
import javafx.scene.control.Label;
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

    public static void showScore(int score, int coins, int world,
                                 int level, int time, int lives) {

        Designer.score.setText(score + "");
        Designer.score.setLayoutX(Designer.scoreLabel.getLayoutX() + (Designer.scoreLabel.getFont().getSize()));
        Designer.score.setLayoutY(50);

        Designer.coins.setText(coins + "");
        Designer.coins.setLayoutX(Designer.coinsLabel.getLayoutX() + (Designer.coinsLabel.getFont().getSize()));
        Designer.coins.setLayoutY(50);

        Designer.world.setText(world + "-" + level);
        Designer.world.setLayoutX(Designer.worldLabel.getLayoutX() + (Designer.worldLabel.getFont().getSize()));
        Designer.world.setLayoutY(50);

        Designer.time.setText(time + "");
        Designer.time.setLayoutX(Designer.timeLabel.getLayoutX() + (Designer.timeLabel.getFont().getSize()));
        Designer.time.setLayoutY(50);

        Designer.lives.setText(lives + "");
        Designer.lives.setLayoutX(Designer.livesLabel.getLayoutX() + (Designer.livesLabel.getFont().getSize()));
        Designer.lives.setLayoutY(50);

    }
}
