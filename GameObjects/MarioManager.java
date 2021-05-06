package GameObjects;

import Animator.Animator;
import Common.GlobalVariables;
import Components.Collider;

public class MarioManager {

    private Mario mario;

    public MarioManager(Mario mario) {

        this.mario = mario;
    }

    public void powerUpMarioWithMushroom(Mario mario) {

        mario.getRigidbody().getVel().x = 0;
        mario.getRigidbody().getVel().y = 0;
        mario.getRigidbody().getAcc().y = 0;
        mario.getRigidbody().getAcc().x = 0;

        Animator.marioGrowingAnimation(mario);
        mario.setBigMario(true);
    }

    public void growMario() {

        Collider collider = (Collider) mario.getComponent(GlobalVariables.colliderTag);
        collider.resize(GlobalVariables.defaultBigMarioColliderSize, GlobalVariables.defaultBigMarioColliderSize);
    }

    public void decreaseMario() {

        mario.setBigMario(false);
        Animator.marioDecreasingAnimation(mario);
        Collider collider = (Collider) mario.getComponent(GlobalVariables.colliderTag);
        collider.resize(GlobalVariables.defaultColliderSize, GlobalVariables.defaultColliderSize);

    }
}
