package Engine.GameEngine;

import Engine.ECS.Collider;
import Game.GameObjects.GameObject;
import Engine.Input.Input;
import RenderEngine.RenderEngine;
import Game.Game;
import javafx.animation.AnimationTimer;
import Game.Camera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class GameEngine {

    public static List<GameObject> gameObjects;
    private AnimationTimer gameLoop;
    private Input input;
    private Camera camera;
    private RenderEngine renderEngine;
    private Game game;

    public GameEngine(Game game, GraphicsContext gc, Scene scene) {
        this.game = game;
        this.renderEngine = new RenderEngine(gc);
        this.input = new Input(scene);
    }

    public void start() {
        this.renderEngine.setCamera(this.camera);
        this.camera.setGc(this.renderEngine.getGc());

        this.gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                renderEngine.clear();
                game.update();
                updateGameObjects();
                input.update();
                renderEngine.render();
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
            if (!collider.isActive()) {
                continue;
            }
            for (int j = 0; j < Collider.colliders.size(); j++) {
                if (collider == Collider.colliders.get(j)) {
                    continue;
                } else if (!Collider.colliders.get(j).isActive()) {
                    continue;
                }
                if (collider.checkCollision(Collider.colliders.get(j))) {
                    collider.getGameObject().onCollisionEnter(Collider.colliders.get(j).getGameObject(), collider.getCollision());
                }
            }
        }
    }

    public void stop() {
        gameObjects.clear();
        this.gameLoop.stop();
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        this.renderEngine.setCamera(camera);
        this.camera.setGc(this.renderEngine.getGc());
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Input getInput() {
        return this.input;
    }

    public RenderEngine getRenderEngine() {
        return this.renderEngine;
    }
}
