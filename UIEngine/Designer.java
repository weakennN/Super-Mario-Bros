package UIEngine;

import Game.Animator.Animator;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Designer {

    public static Scene scene;
    public static Canvas canvas;
    public static GraphicsContext gc;
    public static Pane root;
    public static Image background;
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

            font = Font.loadFont(new FileInputStream("C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Game\\Animator\\Font\\defaultFont.ttf"), 50);
            background = new Image(new FileInputStream(Animator.background));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gc.setFont(font);
        gc.setFill(Color.WHITE);
        /*gc.fillText("WASD keys to move" + "\n"
                + "Shift to fire", 200, 500);

         */
        root = new Pane();
        root.getChildren().add(canvas);


        initLabels();

        //TODO: add a imageView for the background
        scene = new Scene(root, 1920, 1080);

        return scene;
    }

    private static void initLabels() {

        scoreLabel = new Label("Game/Score");
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

        double x = 100;

        for (Label label : labels) {

            label.setLayoutX(x);
            label.setFont(font);
            root.getChildren().add(label);
            x += 400;

        }

        score.setFont(font);
        coins.setFont(font);
        world.setFont(font);
        time.setFont(font);
        lives.setFont(font);

        root.getChildren().addAll(score, coins, world, time, lives);
    }

}
