package Engine;

import ECS.Collider;
import ECS.Transform;
import Game.GameObjects.GameObject;
import Input.Input;
import RenderEngine.RenderEngine;
import Game.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import Game.Camera;

import java.util.List;

public class GameEngine extends Engine {

    public static List<GameObject> gameObjects;
    private AnimationTimer gameLoop;
    private Input input;
    private Camera camera;
    private RenderEngine renderEngine;
    private Game game;

    public GameEngine(Game game) {

        this.game = game;
        this.renderEngine = new RenderEngine();
        this.input = new Input();
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
                        renderEngine.render();
                       // RenderEngine.render(gameObjectImage, gameObject.getComponent(Transform.class));
                    }

                }

                input.update();
                detectCollision();

                camera.follow();

            }

        };

        this.gameLoop.start();

    }

    private void updateGameObjects() {

        for (int i = 0; i < gameObjects.size(); i++) {

            GameObject gameObject = gameObjects.get(i);

            if (gameObject.isActive()) {

                gameObject.update();
            }
        }
    }

    private void detectCollision() {

        for (int i = 0; i < Collider.colliders.size(); i++) {

            Collider collider = Collider.colliders.get(i);

            for (int j = 0; j < Collider.colliders.size(); j++) {

                if (collider == Collider.colliders.get(j)) {
                    continue;
                }

                if (collider.checkCollision(Collider.colliders.get(j))) {

                    collider.getGameObject().onCollisionEnter(Collider.colliders.get(j).getGameObject(), collider.getCollision());
                }
            }

        }
    }

    @Override
    public void stop() {

        gameObjects.clear();
        this.gameLoop.stop();
    }

    public void restartInput() {
        this.input = new Input();
    }

    public void setCamera(Camera camera) {

        this.camera = camera;
    }

    public Camera getCamera() {

        return this.camera;
    }

    public Input getInput() {
        return this.input;
    }
}