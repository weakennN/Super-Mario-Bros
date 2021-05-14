package GameEngine;

import Designer.Designer;
import GameObjects.GameObject;
import GameObjects.Mario;
import Levels.Overworld;
import Levels.World;
import Score.ScoreKeeper;
import SoundEffects.SoundManager;

import java.util.List;

public class SuperMarioBros extends Game {

    private List<GameObject> gameObjects;
    private GameEngine engine;
    private World world;
    private Mario mario;

    public SuperMarioBros() {

        this.engine = new GameEngine(this);
        this.world = new Overworld();
        this.mario = this.world.getCurrentLevel().getMario();
        SoundManager.playMarioBackgroundTheme();
    }

    @Override
    public void start() {

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

        for (GameObject gm : this.gameObjects) {

            if ((gm.getPosition().getPos().x >= this.mario.getPosition().getPos().x
                    || gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x)
                    && gm.getPosition().getPos().x <= this.mario.getPosition().getPos().x + 2000
                    && !gm.isActive()) {

                gm.start();
                gm.setActive(true);
            }

        }

        if (this.mario.isDead()) {

            this.restartLevel();
        }

    }

    private void restartLevel() {

        while (!this.gameObjects.isEmpty()) {

            GameObject gm = this.gameObjects.get(0);
            gm.destroy();
        }

        this.engine.getCamera().resetCamera();
        this.mario = null;
        this.world.getCurrentLevel().initLevel();
        this.gameObjects = this.world.getCurrentLevel().getGameObjects();
        this.mario = this.world.getCurrentLevel().getMario();
        this.engine.setCamera(this.world.getCurrentLevel().getCamera());
        this.engine.getCamera().setMario(this.mario);
        ScoreKeeper.restartScore();
        ScoreKeeper.restartTimer();

    }

    public void stop(){

        this.engine.stop();
    }
}
