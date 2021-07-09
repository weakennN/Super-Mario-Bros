package Input.KeyEvents;

import Game.Animator.Animator;
import Game.Game;
import Game.GameObjects.Mario.Mario;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import javafx.scene.input.KeyCode;

public class RunningLeftPressed extends KeyEvent {

    public RunningLeftPressed(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {

        Mario mario = ((SuperMarioBros) game).getMario();

        if (!MarioDir.marioRunningLeft) {

            if (!MarioDir.marioJumpingRight) {
                mario.getRigidbody().getVel().x = -2.5;
            } else {
                mario.getRigidbody().getVel().x = -0.7;
            }

            if (mario.isNormal()) {
                mario.changeImage(Animator.marioRunningLeft);
            } else if (mario.isBigMario()) {
                mario.changeImage(Animator.bigMarioRunningLeft);
            } else if (mario.isFireMario()) {
                mario.changeImage(Animator.fireMarioRunningLeft);
            }

            MarioDir.marioIdleFacingRight = false;
            MarioDir.marioRunningLeft = true;
        }
    }
}
