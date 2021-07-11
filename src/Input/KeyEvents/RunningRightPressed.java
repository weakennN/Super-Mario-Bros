package Input.KeyEvents;

import ECS.Animator.Animation;
import ECS.Animator.Animator;
import ECS.Transform;
import Game.Game;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import Game.GameObjects.Mario.Mario;
import javafx.scene.input.KeyCode;

public class RunningRightPressed extends KeyEvent {

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

            if (!mario.isJumping()) {
                if (mario.isNormal()) {
                    mario.getComponent(ECS.Animator.Animator.class).getAnimationController().playAnimation("marioRunningRight");
                } else if (mario.isBigMario()) {
                    mario.getComponent(Animator.class).getAnimationController().playAnimation("bigMarioRunningRight");
                } else if (mario.isFireMario()) {
                    mario.getComponent(Animator.class).getAnimationController().playAnimation("fireMarioRunningRight");
                }

            }

            MarioDir.marioIdleFacingLeft = false;
            MarioDir.marioRunningRight = true;

        }
    }
}
