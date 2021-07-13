package Game;

import ECS.SprtieRenderer.SortingLayersContainer;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.SoundEffects.Sounds;
import Input.Input;
import Input.InputHandler;
import UIEngine.Designer;
import Engine.GameEngine;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mario.Mario;
import Game.Levels.Overworld;
import Game.Levels.World;
import Game.Score.ScoreKeeper;
import Game.SoundEffects.SoundManager;
import javafx.animation.AnimationTimer;

import java.util.List;

public class SuperMarioBros extends Game {

    private List<GameObject> gameObjects;
    private GameEngine engine;
    private InputHandler inputHandler;
    private World world;
    private Mario mario;

    public SuperMarioBros() {

    }

    @Override
    public void start() {

        this.engine = new GameEngine(this);
        SpriteSheetContainer.init();
        SortingLayersContainer.initContainer();
        this.inputHandler = new InputHandler(this, this.engine.getInput());
        this.world = new Overworld();
        this.world.getCurrentLevel().initLevel();
        this.mario = this.world.getCurrentLevel().getMario();
        SoundManager.playMarioBackgroundTheme();
        this.gameObjects = this.world.getCurrentLevel().getGameObjects();
        GameEngine.gameObjects = this.gameObjects;
        this.engine.setCamera(this.world.getCurrentLevel().getCamera());
        this.engine.start();
    }

    @Override
    public void render() {
        Designer.gc.drawImage(this.world.getBackGround().getImage(), 0, 0, this.world.getBackGround().getSizeX(),
                this.world.getBackGround().getSizeY());
    }

    @Override
    public void update() {

        this.render();
        ScoreKeeper.updateScore();

        for (int i = 0; i < this.gameObjects.size(); i++) {

            GameObject gm = this.gameObjects.get(i);

            if ((gm.getComponent(Transform.class).getPos().x >= this.mario.getComponent(Transform.class).getPos().x
                    || gm.getComponent(Transform.class).getPos().x <= this.mario.getComponent(Transform.class).getPos().x)
                    && gm.getComponent(Transform.class).getPos().x <= this.mario.getComponent(Transform.class).getPos().x + 2000
                    && !gm.isActive()) {

                gm.start();
                gm.setActive(true);
            }

            if (!gm.getTag().equals(GlobalVariables.marioTag) && !gm.getTag().equals(GlobalVariables.invisibleWallTag) &&
                    !gm.getTag().equals(GlobalVariables.groundTag) && gm.getComponent(Transform.class).getPos().x + 1000 < this.mario.getComponent(Transform.class).getPos().x) {
                gm.destroy();
            }
        }

        if (ScoreKeeper.lives == 0) {

            this.gaveOver();
            return;
        }

        if (this.mario.isDead() || ScoreKeeper.time.getSeconds() == 0) {

            this.restartLevel();
        }

        if (ScoreKeeper.time.getSeconds() == 100) {

            SoundManager.playSound(Sounds.timeWarningSound);
        }

        this.inputHandler.update();
    }

    private void restartLevel() {

        this.destroyGameObjects();
        Input.unlock();
        this.engine.getCamera().resetCamera();
        this.engine.restartInput();
        this.inputHandler = new InputHandler(this, this.engine.getInput());
        this.mario = null;
        this.world.getCurrentLevel().initLevel();
        this.gameObjects = this.world.getCurrentLevel().getGameObjects();
        this.mario = this.world.getCurrentLevel().getMario();
        this.engine.setCamera(this.world.getCurrentLevel().getCamera());
        ScoreKeeper.restartScore();
        ScoreKeeper.restartTimer();

    }

    public void stop() {

        ScoreKeeper.stopTimer();
        this.engine.stop();
    }

    private void gaveOver() {

        Designer.showLabels(false);
      /*  RenderEngine.renderImage(Animator.gameOver, this.engine.getCamera().getPosition().getPos().x - 960
                , 0, 1920, 1080);

       */
        this.engine.getCamera().resetCamera();
        this.world = null;
        this.mario = null;
        this.destroyGameObjects();
        this.engine.stop();
        SoundManager.stop();
        SoundManager.playSound(Sounds.gameOverSound);

        AnimationTimer animationTimer = new AnimationTimer() {

            private int time = 0;

            @Override
            public void handle(long l) {

                if (this.time++ == 350) {

                    Designer.showLabels(true);
                    ScoreKeeper.restart();
                    SuperMarioBros.this.start();
                    this.stop();
                }

            }

        };

        animationTimer.start();

    }

    private void destroyGameObjects() {

        while (!this.gameObjects.isEmpty()) {

            GameObject gm = this.gameObjects.get(0);
            gm.destroy();
        }
    }

    public Mario getMario() {
        return this.mario;
    }
}
