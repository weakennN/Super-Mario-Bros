package GameEngine;

import Designer.Designer;
import GameObjects.GameObject;
import Levels.Overworld;
import Levels.World;
import RenderEngine.RenderEngine;
import SoundEffects.SoundManager;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import Designer.Camera;

import java.util.List;

public class GameEngine {

    public static List<GameObject> gameObjects;
    private AnimationTimer gameLoop;
    private Camera camera;
    private World world;

    public GameEngine() {

        this.world = new Overworld();
        gameObjects = this.world.getCurrentLevel().getGameObjects();
        this.camera = this.world.getCurrentLevel().getCamera();
        SoundManager.playMarioBackgroundTheme();
        // only the background is getting translated by the gc fix this.
        // TODO: fix the marioGrowingAnimation
    }

    public void start() {

        this.startGameObjects();

        this.gameLoop = new AnimationTimer() {

            @Override
            public void handle(long l) {

                Designer.gc.drawImage(world.getBackGround(), 0, 0, 3392, 500);

                for (int i = 0; i < gameObjects.size(); i++) {

                    GameObject gameObject = gameObjects.get(i);

                    camera.follow();
                    gameObject.update();
                    Image gameObjectImage = gameObject.render();
                    RenderEngine.render(gameObjectImage, gameObject.getPosition());
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

}
