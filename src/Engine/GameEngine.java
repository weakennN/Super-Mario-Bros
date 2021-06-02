package Engine;

import Game.GameObjects.GameObject;
import RenderEngine.RenderEngine;
import Game.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import Game.Camera;

import java.util.List;

public class GameEngine extends Engine {

    public static List<GameObject> gameObjects;
    private AnimationTimer gameLoop;
    private Camera camera;
    private Game game;

    public GameEngine(Game game) {

        this.game = game;
    }

    @Override
    public void start() {

        this.gameLoop = new AnimationTimer() {

            @Override
            public void handle(long l) {

                game.update();

                for (int i = 0; i < gameObjects.size(); i++) {

                    GameObject gameObject = gameObjects.get(i);

                    if (gameObject == null) {

                        continue;
                    }

                    if (gameObject.isActive()) {

                        gameObject.update();
                        Image gameObjectImage = gameObject.render();
                        RenderEngine.render(gameObjectImage, gameObject.getPosition());
                    }

                }

                camera.follow();

            }

        };

        this.gameLoop.start();

    }

    @Override
    public void stop() {

        gameObjects.clear();
        this.gameLoop.stop();
    }

    public void setCamera(Camera camera) {

        this.camera = camera;
    }

    public Camera getCamera() {

        return this.camera;
    }

}
