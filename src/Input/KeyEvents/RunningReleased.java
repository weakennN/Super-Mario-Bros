package Input.KeyEvents;

import ECS.Animator.Animator;
import ECS.SprtieRenderer.SpriteRenderer;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.GameObjects.Mario.Mario;
import javafx.scene.input.KeyCode;

public abstract class RunningReleased extends KeyEvent{

    public RunningReleased(KeyCode key) {
        super(key);
    }

    public void release(Mario mario){
        if (!mario.isJumping()) {
            if (mario.isNormal()) {
                mario.getComponent(Animator.class).getAnimationController().stop();
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(0));
            } else if (mario.isBigMario()) {
                mario.getComponent(Animator.class).getAnimationController().stop();
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(0));
            } else if (mario.isFireMario()) {
                mario.getComponent(Animator.class).getAnimationController().stop();
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(0));
            }
        }
        mario.getRigidbody().getAcc().x = 0;
        mario.getRigidbody().getAcc().y = 0;
    }
}