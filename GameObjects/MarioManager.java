package GameObjects;

import Animator.Animator;
import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import Designer.Designer;
import Helpers.MarioDir;
import SoundEffects.SoundManager;
import SoundEffects.Sounds;
import javafx.scene.input.KeyCode;

public class MarioManager {

    private Mario mario;
    private boolean[] keyActions;
    private boolean[] keyReleased;

    public MarioManager(Mario mario) {

        this.mario = mario;
        this.keyActions = new boolean[3];
        this.keyReleased = new boolean[3];
    }

    // TODO: create changeImage method here so it change mario image
    // TODO: bring back the boolean keyOnPressed and KeyReleased arrays

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

    public void initializeActions(Rigidbody rigidbody) {

        Designer.scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.D) {

                // TODO: try to fix this
                // TODO: add a big mario jumping animation
                // theres is a bug in mario running right animation
                if (!MarioDir.marioRunningRight) {

                    if (!MarioDir.marioJumpingLeft) {
                        rigidbody.getVel().x = 3.5;
                    } else {

                        rigidbody.getVel().x = 0.5;
                    }
                    if (!mario.isBigMario()) {
                        mario.changeImage(Animator.marioRunningRight);

                    } else {
                        mario.changeImage(Animator.bigMarioRunningRight);

                    }

                    MarioDir.marioRunningRight = true;
                }

            } else if (e.getCode() == KeyCode.A) {


                if (!MarioDir.marioRunningLeft) {

                    if (!MarioDir.marioJumpingRight) {

                        rigidbody.getVel().x = -3.5;
                    } else {

                        rigidbody.getVel().x = -0.5;
                    }

                    if (!mario.isBigMario()) {
                        this.mario.changeImage(Animator.marioRunningLeft);

                    } else {
                        this.mario.changeImage(Animator.bigMarioRunningLeft);

                    }

                    MarioDir.marioRunningLeft = true;
                }

            } else if (e.getCode() == KeyCode.W) {

                if (!this.mario.isJumping()) {
                    rigidbody.getVel().y = -3.8;
                    SoundManager.playSound(Sounds.marioJumpingSound);

                    if (rigidbody.getVel().x >= 0) {

                        if (this.mario.isBigMario()) {

                            this.mario.changeImage(Animator.bigMarioJumpingRight);
                        } else {

                            mario.changeImage(Animator.marioJumpingRight);
                        }

                        MarioDir.marioJumpingRight = true;
                    } else {

                        if (this.mario.isBigMario()) {

                            this.mario.changeImage(Animator.bigMarioJumpingLeft);
                        } else {

                            mario.changeImage(Animator.marioJumpingLeft);
                        }
                        MarioDir.marioJumpingLeft = true;
                    }

                    this.mario.setOnGround(false);
                    this.mario.setJumping(true);
                }

            }
        });

        Designer.scene.setOnKeyReleased(e -> {

            if (e.getCode() == KeyCode.A) {

                rigidbody.getVel().x = 0;
                if (!this.mario.isBigMario()) {
                    this.mario.changeImage(Animator.marioIdleFacingLeft);
                } else if (this.mario.isBigMario()) {
                    this.mario.changeImage(Animator.bigMarioFacingLeft);
                }
                MarioDir.marioRunningLeft = false;
                MarioDir.marioIdleFacingLeft = true;
                rigidbody.getAcc().x = 0;
                rigidbody.getAcc().y = 0;
            } else if (e.getCode() == KeyCode.D) {

                rigidbody.getVel().x = 0;
                if (!this.mario.isBigMario()) {
                    this.mario.changeImage(Animator.marioIdleFacingRight);
                } else if (this.mario.isBigMario()) {
                    this.mario.changeImage(Animator.bigMarioFacingRight);
                }
                MarioDir.marioIdleFacingRight = true;
                MarioDir.marioRunningRight = false;
                rigidbody.getAcc().x = 0;
                rigidbody.getAcc().y = 0;
            }
        });
    }

    public void setMarioAnimationAfterJump() {

        if (MarioDir.marioJumpingRight) {

            if (this.mario.isBigMario()) {

                if (mario.getRigidbody().getVel().x > 0) {

                    this.mario.changeImage(Animator.bigMarioRunningRight);
                } else if (this.mario.getRigidbody().getVel().x == 0) {

                    this.mario.changeImage(Animator.bigMarioFacingRight);
                } else {

                    this.mario.changeImage(Animator.bigMarioRunningLeft);
                }
            } else {
// TODO: make a method for these if checks because there is a lot of copy paste
                if (mario.getRigidbody().getVel().x > 0) {

                    this.mario.changeImage(Animator.marioRunningRight);
                } else if (mario.getRigidbody().getVel().x == 0) {

                    this.mario.changeImage(Animator.marioIdleFacingRight);
                } else {

                    this.mario.changeImage(Animator.marioRunningLeft);
                }
            }

            MarioDir.marioJumpingRight = false;
        } else if (MarioDir.marioJumpingLeft) {

            if (this.mario.isBigMario()) {

                if (mario.getRigidbody().getVel().x < 0) {

                    this.mario.changeImage(Animator.bigMarioRunningLeft);

                } else if (this.mario.getRigidbody().getVel().x == 0) {

                    this.mario.changeImage(Animator.bigMarioFacingLeft);
                } else {

                    this.mario.changeImage(Animator.bigMarioRunningRight);
                }

            } else {

                if (mario.getRigidbody().getVel().x < 0) {

                    this.mario.changeImage(Animator.marioRunningLeft);

                } else if (this.mario.getRigidbody().getVel().x == 0) {

                    this.mario.changeImage(Animator.marioIdleFacingLeft);
                } else {

                    this.mario.changeImage(Animator.marioRunningRight);
                }

            }

            MarioDir.marioJumpingLeft = false;
        }
    }

}
