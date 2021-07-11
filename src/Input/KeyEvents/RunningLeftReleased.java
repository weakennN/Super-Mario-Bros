package Input.KeyEvents;

import ECS.Animator.Animator;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.Game;
import Game.GameObjects.Mario.Mario;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import javafx.scene.input.KeyCode;

public class RunningLeftReleased extends KeyEvent {

    public RunningLeftReleased(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();

        mario.getRigidbody().getVel().x = 0;
        if (!mario.isJumping()) {
            if (mario.isNormal()) {
                mario.getComponent(Animator.class).getAnimationController().stop();
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(13));
            } else if (mario.isBigMario()) {
                mario.getComponent(Animator.class).getAnimationController().stop();
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(20));
            } else if (mario.isFireMario()) {
                mario.getComponent(Animator.class).getAnimationController().stop();
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(20));
            }
        }

        MarioDir.marioRunningLeft = false;
        MarioDir.marioIdleFacingLeft = true;
        mario.getRigidbody().getAcc().x = 0;
        mario.getRigidbody().getAcc().y = 0;

    }
}
