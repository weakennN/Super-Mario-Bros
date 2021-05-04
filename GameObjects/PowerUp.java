package GameObjects;

import Animator.Animator;

public class PowerUp {

    public PowerUp() {

    }

    public void powerUpMarioWithMushroom(Mario mario) {

        mario.getRigidbody().getVel().x = 0;
        mario.getRigidbody().getVel().y = 0;
        mario.getRigidbody().getAcc().y = 0;
        mario.getRigidbody().getAcc().x = 0;

        Animator.marioGrowingAnimation(mario);
        mario.setBigMario(true);
    }
}
