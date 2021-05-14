package GameEngine;

import GameObjects.GameObject;
import GameObjects.Mario;
import Levels.Overworld;
import Levels.World;
import RenderEngine.RenderEngine;
import Score.ScoreKeeper;
import SoundEffects.SoundManager;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import Designer.Camera;

import java.util.List;

public class GameEngine {

    public static List<GameObject> gameObjects;
    private AnimationTimer gameLoop;
    private Camera camera;
    private Mario mario;
    private World world;

    public GameEngine() {

        this.world = new Overworld();
        gameObjects = this.world.getCurrentLevel().getGameObjects();
        this.camera = this.world.getCurrentLevel().getCamera();
        this.mario = this.world.getCurrentLevel().getMario();
        SoundManager.playMarioBackgroundTheme();

        // move these in the start method
    }

    public void start() {

        this.startGameObjects();

        this.gameLoop = new AnimationTimer() {

            @Override
            public void handle(long l) {

                RenderEngine.renderBackGround(world.getBackGround().getImage(),
                        world.getBackGround().getSizeX(), world.getBackGround().getSizeY());

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

                if (mario.isDead() || ScoreKeeper.time.getSeconds() == 0) {

                    this.stop();
                    restartLevel();
                }

                lateStart();
            }

        };

        this.gameLoop.start();

    }

    private void startGameObjects() {

        for (GameObject gm : gameObjects) {

            if ((gm.getPosition().getPos().x >= this.mario.getPosition().getPos().x
                    || gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x)
                    && gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x + 2000) {

                gm.setActive(true);
                gm.start();
            }

        }
    }

    public void restartLevel() {

        while (!gameObjects.isEmpty()) {

            GameObject gameObject = gameObjects.get(0);
            gameObject.destroy();
        }

        this.camera.resetCamera();
        this.mario = null;
        this.world.getCurrentLevel().initLevel();
        gameObjects = this.world.getCurrentLevel().getGameObjects();
        this.mario = this.world.getCurrentLevel().getMario();
        this.camera = this.world.getCurrentLevel().getCamera();
        this.camera.setMario(this.mario);
        ScoreKeeper.restartScore();
        ScoreKeeper.restartTimer();

        this.start();

    }

    public void lateStart() {

        for (GameObject gm : gameObjects) {

            if ((gm.getPosition().getPos().x >= this.mario.getPosition().getPos().x
                    || gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x)
                    && gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x + 2000
                    && !gm.isActive()) {

                gm.setActive(true);
                gm.start();
            }
        }
    }

    public void stop() {

        gameObjects.clear();
        this.gameLoop.stop();
        ScoreKeeper.stopTimer();
    }

}
