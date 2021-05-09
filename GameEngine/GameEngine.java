package GameEngine;

import Designer.Designer;
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
        // TODO: fix the marioGrowingAnimation
    }

    public void start() {

        this.startGameObjects();

        this.gameLoop = new AnimationTimer() {

            @Override
            public void handle(long l) {

                ScoreKeeper.updateScore();
                Designer.gc.drawImage(world.getBackGround(), 0, 0, 5760, 1080);

                for (int i = 0; i < gameObjects.size(); i++) {

                    GameObject gameObject = gameObjects.get(i);

                    camera.follow();
                    gameObject.update();
                    Image gameObjectImage = gameObject.render();
                    RenderEngine.render(gameObjectImage, gameObject.getPosition());
                }

                if (mario.isDead()) {

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

        for (GameObject gameObject : gameObjects) {

            gameObject.destroy();
        }

        this.mario = null;
        this.world.getCurrentLevel().initLevel();
        gameObjects = this.world.getCurrentLevel().getGameObjects();
        this.mario = this.world.getCurrentLevel().getMario();
        this.camera.setMario(this.mario);
        this.start();
    }


}
