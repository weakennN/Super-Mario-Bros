package Input.KeyEvents;

import ECS.Animator.Animator;
import ECS.SprtieRenderer.SpriteRenderer;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.Game;
import Game.GameObjects.Mario.Mario;
import Game.GameObjects.Mario.MarioDir;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import Game.SuperMarioBros;
import javafx.scene.input.KeyCode;

public class Jump extends KeyEvent {

    public Jump(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();

        if (!mario.isJumping()) {
            mario.getRigidbody().getVel().y = -3.6;

            if (mario.isNormal()) {
                SoundManager.playSound(Sounds.marioJumpingSound);
            } else {
                SoundManager.playSound(Sounds.bigMarioJumpingSound);
            }

            if (MarioDir.marioIdleFacingRight || MarioDir.marioRunningRight) {

                if (mario.isBigMario()) {
                    mario.getComponent(Animator.class).getAnimationController().stop();
                    mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(26));
                } else if (mario.isNormal()) {
                    mario.getComponent(Animator.class).getAnimationController().stop();
                    mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(19));
                } else if (mario.isFireMario()) {
                    mario.getComponent(Animator.class).getAnimationController().stop();
                    mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(26));
                }

                MarioDir.marioJumpingRight = true;
            } else {

                if (mario.isBigMario()) {
                    mario.getComponent(Animator.class).getAnimationController().stop();
                    mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(15));
                } else if (mario.isNormal()) {
                    mario.getComponent(Animator.class).getAnimationController().stop();
                    mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(8));
                } else if (mario.isFireMario()) {
                    mario.getComponent(Animator.class).getAnimationController().stop();
                    mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(15));
                }

                MarioDir.marioJumpingLeft = true;
            }

            mario.setOnGround(false);
            mario.setJumping(true);
        }
    }
}
