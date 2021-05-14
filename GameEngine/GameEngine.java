package GameEngine;

import GameObjects.GameObject;
import RenderEngine.RenderEngine;
import Score.ScoreKeeper;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import Designer.Camera;

import java.util.List;

public class GameEngine {

    public static List<GameObject> gameObjects;
    private AnimationTimer gameLoop;
    private Camera camera;
    private Game game;

    public GameEngine(Game game) {

        this.game = game;
    }

    public void start() {

        this.gameLoop = new AnimationTimer() {

            @Override
            public void handle(long l) {

                game.update();
                ScoreKeeper.updateScore();

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

    public void restartLevel() {

        while (!gameObjects.isEmpty()) {

            GameObject gameObject = gameObjects.get(0);
            gameObject.destroy();
        }

      /*  this.camera.resetCamera();
        this.mario = null;
        this.world.getCurrentLevel().initLevel();
        gameObjects = this.world.getCurrentLevel().getGameObjects();
        this.mario = this.world.getCurrentLevel().getMario();
        this.camera = this.world.getCurrentLevel().getCamera();
        this.camera.setMario(this.mario);
        ScoreKeeper.restartScore();
        ScoreKeeper.restartTimer();

       */

        this.start();

    }

    public void lateStart() {

      /*  for (GameObject gm : gameObjects) {

            if ((gm.getPosition().getPos().x >= this.mario.getPosition().getPos().x
                    || gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x)
                    && gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x + 2000
                    && !gm.isActive()) {

                gm.setActive(true);
                gm.start();
            }
        }

       */
    }

    public void stop() {

        gameObjects.clear();
        this.gameLoop.stop();
        ScoreKeeper.stopTimer();
    }

    public void setCamera(Camera camera) {

        this.camera = camera;
    }

    public Camera getCamera() {

        return this.camera;
    }

}
