package Input.KeyEvents;

import Game.Animator.Animator;
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
                    mario.changeImage(Animator.bigMarioJumpingRight);
                } else if (mario.isNormal()) {
                    mario.changeImage(Animator.marioJumpingRight);
                } else if (mario.isFireMario()) {
                    mario.changeImage(Animator.fireMarioJumpingRight);
                }

                MarioDir.marioJumpingRight = true;
            } else {

                if (mario.isBigMario()) {
                    mario.changeImage(Animator.bigMarioJumpingLeft);
                } else if (mario.isNormal()) {
                    mario.changeImage(Animator.marioJumpingLeft);
                } else if (mario.isFireMario()) {
                    mario.changeImage(Animator.fireMarioJumpingLeft);
                }

                MarioDir.marioJumpingLeft = true;
            }

            mario.setOnGround(false);
            mario.setJumping(true);
        }
    }
}
