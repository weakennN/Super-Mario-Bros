package Input.KeyEvents;

import ECS.Animator.Animator;
import Game.GameObjects.Mario.Mario;
import javafx.scene.input.KeyCode;

public abstract class RunningPressed extends KeyEvent{
    public RunningPressed(KeyCode key) {
        super(key);
    }

    public void run(Mario mario){
        if (!mario.isJumping()) {
            mario.getComponent(Animator.class).getAnimationController().stop();
            if (mario.isNormal()) {
                mario.getComponent(Animator.class).getAnimationController().playAnimation("marioRunning");
            } else if (mario.isBigMario()) {
                mario.getComponent(Animator.class).getAnimationController().playAnimation("bigMarioRunning");
            } else if (mario.isFireMario()) {
                mario.getComponent(Animator.class).getAnimationController().playAnimation("fireMarioRunning");
            }
        }
    }
}
