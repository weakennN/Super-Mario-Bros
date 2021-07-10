package Input.KeyEvents;

import ECS.Animator.Animator;
import ECS.SprtieRenderer.SpriteRenderer;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.Game;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import Game.GameObjects.Mario.Mario;
import javafx.scene.input.KeyCode;

public class RunningRightReleased extends KeyEvent {

    public RunningRightReleased(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {

        Mario mario = ((SuperMarioBros) game).getMario();

        mario.getRigidbody().getVel().x = 0;

        if (!mario.isJumping()) {
            if (mario.isNormal()) {
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(14));
                mario.getComponent(ECS.Animator.Animator.class).getAnimationController().stop();
            } else if (mario.isBigMario()) {
                mario.getComponent(Animator.class).getAnimationController().stop();
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(21));
            } else if (mario.isFireMario()) {
                mario.getComponent(Animator.class).getAnimationController().stop();
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(21));
            }
        }
        MarioDir.marioIdleFacingRight = true;
        MarioDir.marioRunningRight = false;
        mario.getRigidbody().getAcc().x = 0;
        mario.getRigidbody().getAcc().y = 0;

    }
}
