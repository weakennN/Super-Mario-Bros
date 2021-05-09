package GameEngine;

import Components.Collider;
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
                    camera.follow();
                    gameObject.update();
                    Image gameObjectImage = gameObject.render();
                    RenderEngine.render(gameObjectImage, gameObject.getPosition());
                }

                if (mario.isDead() || ScoreKeeper.time.getSeconds() == 0) {

                    // restart
                    restartLevel();
                    this.stop();
                }


            }

        };

        this.gameLoop.start();

    }

    private void startGameObjects() {

        for (GameObject gm : gameObjects) {

            gm.start();
        }
    }

    public void restartLevel() {

        while (!gameObjects.isEmpty()) {

            GameObject gameObject = gameObjects.get(0);
            System.out.println(Collider.colliders.size());
            System.out.println(gameObjects.size());
            gameObject.destroy();
        }


        this.mario = null;
        this.world.getCurrentLevel().initLevel();
        gameObjects = this.world.getCurrentLevel().getGameObjects();
        this.mario = this.world.getCurrentLevel().getMario();
        this.camera.setMario(this.mario);
        ScoreKeeper.restartTimer();
        this.start();
    }


}
