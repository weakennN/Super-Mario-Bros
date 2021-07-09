package Input.KeyEvents;

import Game.Animator.Animator;
import Game.Game;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import Game.GameObjects.Mario.Mario;
import javafx.scene.input.KeyCode;

public class RunningRightPressed extends KeyEvent{

    public RunningRightPressed(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();

        if (!MarioDir.marioRunningRight) {

            if (!MarioDir.marioJumpingLeft) {
                mario.getRigidbody().getVel().x = 2.5;
            } else {
                mario.getRigidbody().getVel().x = 0.7;
            }

            if (mario.isNormal()) {
                mario.changeImage(Animator.marioRunningRight);
            } else if (mario.isBigMario()) {
                mario.changeImage(Animator.bigMarioRunningRight);
            } else if (mario.isFireMario()) {
                mario.changeImage(Animator.fireMarioRunningRight);
            }

            MarioDir.marioIdleFacingLeft = false;
            MarioDir.marioRunningRight = true;
        }
    }
}
