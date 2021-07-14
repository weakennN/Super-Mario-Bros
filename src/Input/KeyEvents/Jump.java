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

    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();

        if (!mario.isJumping()) {
            mario.getRigidbody().getVel().y = -3.6;
            if (mario.isNormal()) {
                SoundManager.playSound(Sounds.marioJumpingSound);
            } else {
                SoundManager.playSound(Sounds.bigMarioJumpingSound);
            }

            mario.getComponent(Animator.class).getAnimationController().stop();

            if (mario.isBigMario()) {
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(5));
            } else if (mario.isNormal()) {
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(5));
            } else if (mario.isFireMario()) {
                mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(26));
            }

            if (MarioDir.marioIdleFacingRight || MarioDir.marioRunningRight) {
                MarioDir.marioJumpingRight = true;
            } else {
                MarioDir.marioJumpingLeft = true;
            }

            mario.setOnGround(false);
            mario.setJumping(true);
        }
    }
}