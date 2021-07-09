package Input.KeyEvents;

import Game.Animator.Animator;
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
        if (mario.isNormal()) {
            mario.changeImage(Animator.marioIdleFacingLeft);
        } else if (mario.isBigMario()) {
            mario.changeImage(Animator.bigMarioFacingLeft);
        } else if (mario.isFireMario()) {
            mario.changeImage(Animator.fireMarioFacingLeft);
        }
        MarioDir.marioRunningLeft = false;
        MarioDir.marioIdleFacingLeft = true;
        mario.getRigidbody().getAcc().x = 0;
        mario.getRigidbody().getAcc().y = 0;
    }
}
