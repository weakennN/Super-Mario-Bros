package Game;

import Engine.ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import Engine.ECS.Renderer.SprtieRenderer.SpriteSheet;
import Engine.ECS.Transform;
import Game.GameResources.GlobalAnimations;
import Game.Common.GlobalVariables;
import Game.SoundEffects.Sounds;
import Engine.Input.Input;
import Input.InputHandler;
import UIEngine.Designer;
import Engine.GameEngine.GameEngine;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mario.Mario;
import Game.Levels.Overworld;
import Game.Levels.World;
import Game.Score.ScoreKeeper;
import Game.SoundEffects.SoundManager;
import Util.AssetPool;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class SuperMarioBros extends Game {

    private List<GameObject> gameObjects;
    private GameEngine engine;
    private InputHandler inputHandler;
    private World world;
    private Mario mario;

    public SuperMarioBros() {
        AssetPool.addSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET, new SpriteSheet(GlobalAnimations.MARIO_SPRITE_SHEET, 50, 51, 0));
        AssetPool.addSpriteSheet(GlobalVariables.GOOMBA_SPRITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.GOOMBA_SPRITE_SHEET, 50, 50, 0));
        AssetPool.addSpriteSheet(GlobalVariables.ITEM_BOX_SPITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.ITEM_BOX_SPRITE_SHEET, 50, 50, 0));
        AssetPool.addSpriteSheet(GlobalVariables.KOOPA_SPRITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.KOOPA_SPRITE_SHEET, 50, 75, 0));
        AssetPool.addSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.BIG_MARIO_SPRITE_SHEET, 45, 90, 0));
        AssetPool.addSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.FIRE_MARIO_SPRITE_SHEET, 45, 90, 0));
        AssetPool.addSpriteSheet(GlobalVariables.COIN_SPRITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.COIN_SPRITE_SHEET, 50, 47, 0));
        AssetPool.addSpriteSheet(GlobalVariables.FIRE_BALL_SPRITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.FIREBALL_SPRITE_SHEET, 25, 25, 0));
        AssetPool.addSpriteSheet(GlobalVariables.FIRE_BALL_EXPLOSION_SPRITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.FIREBALL_EXPLOSION_SPRITE_SHEET, 35, 40, 0));
        AssetPool.addSpriteSheet(GlobalVariables.FLOWER_SPRITE_SHEET_KEY, new SpriteSheet(GlobalAnimations.FLOWER_SPRITE_SHEET, 50, 50, 0));
        AssetPool.addTexture("BrickBox", new Image(GlobalAnimations.BRICK_SPRITE));
        AssetPool.addTexture("Block", new Image(GlobalAnimations.BLOCK_SPRITE));
        AssetPool.addTexture("Mushroom", new Image(GlobalAnimations.MUSHROOM_SPRITE));
        AssetPool.addTexture("Pipe", new Image(GlobalAnimations.PIPE_SPRITE));
        AssetPool.addTexture("Ground", new Image(GlobalAnimations.GROUND_SPRITE));
        AssetPool.addTexture("BushS", new Image(GlobalAnimations.BUSH_SPRITE_S));
        AssetPool.addTexture("BushM", new Image(GlobalAnimations.BUSH_SPRITE_M));
        AssetPool.addTexture("BushL", new Image(GlobalAnimations.BUSH_SPRITE_L));
        AssetPool.addTexture("MountainS", new Image(GlobalAnimations.MOUNTAIN_SPITE_S));
        AssetPool.addTexture("MountainM", new Image(GlobalAnimations.MOUNTAIN_SPITE_M));
        AssetPool.addTexture("KoopaShell", new Image(GlobalAnimations.KOOPA_SHELL_SPRITE));
        SortingLayersContainer.initContainer();
    }

    @Override
    public void start() {
        this.engine = new GameEngine(this, Designer.gc, Designer.scene);
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
        this.engine.getRenderEngine().getGc().setFill(Color.valueOf("42a7f5"));
        this.engine.getRenderEngine().getGc().fillRect(0, 0, this.world.getBackGround().getSizeX(), this.world.getBackGround().getSizeY());
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
                    !gm.getTag().equals(GlobalVariables.TILE_MAP_TAG) &&
                    !gm.getTag().equals("deathWall") &&
                    !gm.getTag().equals(GlobalVariables.groundTag) && gm.getComponent(Transform.class).getPos().x + 1100 < this.mario.getComponent(Transform.class).getPos().x) {
                gm.destroy();
            }
        }

        if (ScoreKeeper.lives == 0) {
            this.gaveOver();
            return;
        }

        if (this.mario.isDead() || ScoreKeeper.time.getSeconds() == 0) {
            ScoreKeeper.decreaseLives();
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
        this.engine.getRenderEngine().render(new Image(GlobalAnimations.GAME_OVER), this.engine.getCamera().getPosition().getPos().x - 960
                , 0, 1920, 1080);

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
                    Input.unlock();
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
