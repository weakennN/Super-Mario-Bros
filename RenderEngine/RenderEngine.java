package RenderEngine;

import UIEngine.Designer;
import ECS.Position;
import javafx.scene.image.Image;
import mikera.vectorz.Vector2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RenderEngine {

    public static void render(Image image, Position position) {

        Vector2 pos = position.getPos();

        Designer.gc.drawImage(image, pos.x, pos.y);
    }

    public static void showScore(int score, int coins, int world,
                                 int level, int time, int lives) {

        Designer.score.setText(score + "");
        Designer.score.setLayoutX(Designer.scoreLabel.getLayoutX() + Designer.scoreLabel.getWidth() / 2 - Designer.score.getWidth() / 2 + 5);
        Designer.score.setLayoutY(50);

        Designer.coins.setText(coins + "");
        Designer.coins.setLayoutX(Designer.coinsLabel.getLayoutX() + Designer.coinsLabel.getWidth() / 2 - Designer.coins.getWidth() / 2 + 5);
        Designer.coins.setLayoutY(50);

        Designer.world.setText(world + "-" + level);
        Designer.world.setLayoutX(Designer.worldLabel.getLayoutX() + Designer.worldLabel.getWidth() / 2 - Designer.world.getWidth() / 2 + 5);
        Designer.world.setLayoutY(50);

        Designer.time.setText(time + "");
        Designer.time.setLayoutX((Designer.timeLabel.getLayoutX() + Designer.timeLabel.getWidth() / 2 - Designer.time.getWidth() / 2) + 5);
        Designer.time.setLayoutY(50);

        Designer.lives.setText(lives + "");
        Designer.lives.setLayoutX(Designer.livesLabel.getLayoutX() + Designer.livesLabel.getWidth() / 2 - Designer.lives.getWidth() / 2 + 5);
        Designer.lives.setLayoutY(50);

    }

    public static void renderImage(String strImage, double startX, double startY
            , double endX, double endY) {

        try {
            Image image = new Image(new FileInputStream(strImage));
            Designer.gc.drawImage(image, startX, startY, endX, endY);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
