package Game;

import Game.Animator.Animator;
import Game.SoundEffects.Sounds;
import RenderEngine.RenderEngine;
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
    private World world;
    private Mario mario;

    public SuperMarioBros() {


    }

    @Override
    public void start() {

        this.engine = new GameEngine(this);
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

        for (GameObject gm : this.gameObjects) {

            if ((gm.getPosition().getPos().x >= this.mario.getPosition().getPos().x
                    || gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x)
                    && gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x + 2000
                    && !gm.isActive()) {

                gm.start();
                gm.setActive(true);
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

    }

    private void restartLevel() {

        this.destroyGameObjects();

        this.engine.getCamera().resetCamera();
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

        RenderEngine.renderBackGround(Animator.gameOver);
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
}
