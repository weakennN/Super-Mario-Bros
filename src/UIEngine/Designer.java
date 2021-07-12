package UIEngine;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Designer {

    public static Scene scene;
    public static Canvas canvas;
    public static GraphicsContext gc;
    public static Pane root;
    public static Font font;
    public static Label scoreLabel;
    public static Label coinsLabel;
    public static Label worldLabel;
    public static Label timeLabel;
    public static Label livesLabel;
    public static Label score;
    public static Label coins;
    public static Label world;
    public static Label time;
    public static Label lives;

    public static Scene initializeNodes() {

        canvas = new Canvas(1920, 1080);
        gc = canvas.getGraphicsContext2D();

        try {
            File file = new File("./src/Game/Animator/Font/defaultFont.ttf");
            font = Font.loadFont(new FileInputStream(file), 33);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gc.setFont(font);
        gc.setFill(Color.WHITE);

        root = new Pane();
        root.getChildren().add(canvas);

        initLabels();

        scene = new Scene(root, 1920, 1080);

        return scene;
    }

    private static void initLabels() {

        scoreLabel = new Label("Score");
        coinsLabel = new Label("Coins");
        worldLabel = new Label("World");
        timeLabel = new Label("Time");
        livesLabel = new Label("Lives");
        score = new Label();
        coins = new Label();
        world = new Label();
        time = new Label();
        lives = new Label();

        Label[] labels = {scoreLabel, coinsLabel, worldLabel, timeLabel, livesLabel};

        double x = 90;

        for (Label label : labels) {

            label.setLayoutX(x);
            label.setFont(font);
            root.getChildren().add(label);
            x += 390;

        }

        score.setFont(font);
        coins.setFont(font);
        world.setFont(font);
        time.setFont(font);
        lives.setFont(font);

        root.getChildren().addAll(score, coins, world, time, lives);
    }

    public static void showLabels(boolean b) {

        Label[] labels = {scoreLabel, coinsLabel, worldLabel, timeLabel, livesLabel, score, coins, world, lives, time};
        for (Label label : labels) {
            label.setVisible(b);
        }
    }

    public static void updateScore(int score, int coins, int world,
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
}
