package Input.KeyEvents;

import Game.Animator.Animator;
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
        if (mario.isNormal()) {
            mario.changeImage(Animator.marioIdleFacingRight);
        } else if (mario.isBigMario()) {
            mario.changeImage(Animator.bigMarioFacingRight);
        } else if (mario.isFireMario()) {
            mario.changeImage(Animator.fireMarioFacingRight);
        }

        MarioDir.marioIdleFacingRight = true;
        MarioDir.marioRunningRight = false;
        mario.getRigidbody().getAcc().x = 0;
        mario.getRigidbody().getAcc().y = 0;
    }
}
