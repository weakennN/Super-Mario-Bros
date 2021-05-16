package Game.GameObjects.Mario;

import Game.Animator.Animator;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Game.Score.ScoreKeeper;
import UIEngine.Designer;
import Engine.GameEngine;
import Game.GameObjects.Explosive;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import javafx.scene.input.KeyCode;
import ECS.Position;

public class MarioManager {

    private Mario mario;

    public MarioManager(Mario mario) {

        this.mario = mario;
    }

    public void powerUpMarioWithMushroom() {

        mario.getRigidbody().getVel().x = 0;
        mario.getRigidbody().getVel().y = 0;
        mario.getRigidbody().getAcc().y = 0;
        mario.getRigidbody().getAcc().x = 0;

        Animator.marioGrowingAnimation(mario, this);
        mario.setBigMario(true);
        this.mario.setNormal(false);
    }

    public void growMario() {

        Collider collider = (Collider) mario.getComponent(GlobalVariables.colliderTag);
        collider.resize(GlobalVariables.defaultBigMarioColliderSizeX, GlobalVariables.defaultBigMarioColliderSizeY);
    }

    public Mario getMario() {

        return this.mario;
    }

    public void setMarioDead(boolean b) {

        this.mario.setDead(b);
    }

    public void decreaseMario() {

        mario.setBigMario(false);
        this.mario.setNormal(true);
        Animator.marioDecreasingAnimation(mario);
        Collider collider = (Collider) mario.getComponent(GlobalVariables.colliderTag);
        collider.resize(GlobalVariables.defaultMarioColliderX, GlobalVariables.defaultMarioColliderY);

    }


    public void initializeActions(Rigidbody rigidbody) {

        Designer.scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.D) {

                if (!MarioDir.marioRunningRight) {

                    if (!MarioDir.marioJumpingLeft) {
                        rigidbody.getVel().x = 2.5;
                    } else {

                        rigidbody.getVel().x = 0.7;
                    }
                    if (this.mario.isNormal()) {

                        mario.changeImage(Animator.marioRunningRight);
                    } else if (this.mario.isBigMario()) {

                        mario.changeImage(Animator.bigMarioRunningRight);
                    } else if (this.mario.isFireMario()) {

                        mario.changeImage(Animator.fireMarioRunningRight);
                    }

                    MarioDir.marioIdleFacingLeft = false;
                    MarioDir.marioRunningRight = true;
                }

            } else if (e.getCode() == KeyCode.A) {


                if (!MarioDir.marioRunningLeft) {

                    if (!MarioDir.marioJumpingRight) {

                        rigidbody.getVel().x = -2.5;
                    } else {

                        rigidbody.getVel().x = -0.7;
                    }

                    if (this.mario.isNormal()) {

                        this.mario.changeImage(Animator.marioRunningLeft);
                    } else if (this.mario.isBigMario()) {

                        this.mario.changeImage(Animator.bigMarioRunningLeft);
                    } else if (this.mario.isFireMario()) {

                        this.mario.changeImage(Animator.fireMarioRunningLeft);
                    }

                    MarioDir.marioIdleFacingRight = false;
                    MarioDir.marioRunningLeft = true;
                }

            } else if (e.getCode() == KeyCode.W) {

                if (!this.mario.isJumping()) {
                    rigidbody.getVel().y = -3.6;
                    SoundManager.playSound(Sounds.marioJumpingSound);

                    if (MarioDir.marioIdleFacingRight || MarioDir.marioRunningRight) {

                        if (this.mario.isBigMario()) {

                            this.mario.changeImage(Animator.bigMarioJumpingRight);
                        } else if (this.mario.isNormal()) {

                            this.mario.changeImage(Animator.marioJumpingRight);
                        } else if (this.mario.isFireMario()) {

                            this.mario.changeImage(Animator.fireMarioJumpingRight);
                        }

                        MarioDir.marioJumpingRight = true;
                    } else {

                        if (this.mario.isBigMario()) {

                            this.mario.changeImage(Animator.bigMarioJumpingLeft);
                        } else if (this.mario.isNormal()) {

                            mario.changeImage(Animator.marioJumpingLeft);
                        } else if (this.mario.isFireMario()) {

                            this.mario.changeImage(Animator.fireMarioJumpingLeft);
                        }

                        MarioDir.marioJumpingLeft = true;
                    }

                    this.mario.setOnGround(false);
                    this.mario.setJumping(true);
                }

            } else if (e.getCode() == KeyCode.SHIFT) {

                if (this.mario.isFireMario()) {

                    this.shootFireBall();
                }
            }
        });

        Designer.scene.setOnKeyReleased(e -> {

            if (e.getCode() == KeyCode.A) {

                rigidbody.getVel().x = 0;
                if (this.mario.isNormal()) {

                    this.mario.changeImage(Animator.marioIdleFacingLeft);
                } else if (this.mario.isBigMario()) {

                    this.mario.changeImage(Animator.bigMarioFacingLeft);
                } else if (this.mario.isFireMario()) {

                    this.mario.changeImage(Animator.fireMarioFacingLeft);
                }
                MarioDir.marioRunningLeft = false;
                MarioDir.marioIdleFacingLeft = true;
                rigidbody.getAcc().x = 0;
                rigidbody.getAcc().y = 0;
            } else if (e.getCode() == KeyCode.D) {

                rigidbody.getVel().x = 0;
                if (this.mario.isNormal()) {

                    this.mario.changeImage(Animator.marioIdleFacingRight);
                } else if (this.mario.isBigMario()) {

                    this.mario.changeImage(Animator.bigMarioFacingRight);
                } else if (this.mario.isFireMario()) {

                    this.mario.changeImage(Animator.fireMarioFacingRight);
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
            } else if (this.mario.isNormal()) {

                if (mario.getRigidbody().getVel().x > 0) {

                    this.mario.changeImage(Animator.marioRunningRight);
                } else if (mario.getRigidbody().getVel().x == 0) {

                    this.mario.changeImage(Animator.marioIdleFacingRight);
                } else {

                    this.mario.changeImage(Animator.marioRunningLeft);
                }
            } else if (this.mario.isFireMario()) {

                if (mario.getRigidbody().getVel().x > 0) {

                    this.mario.changeImage(Animator.fireMarioRunningRight);
                } else if (mario.getRigidbody().getVel().x == 0) {

                    this.mario.changeImage(Animator.fireMarioFacingRight);
                } else {

                    this.mario.changeImage(Animator.fireMarioRunningLeft);
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

            } else if (this.mario.isNormal()) {

                if (mario.getRigidbody().getVel().x < 0) {

                    this.mario.changeImage(Animator.marioRunningLeft);

                } else if (this.mario.getRigidbody().getVel().x == 0) {

                    this.mario.changeImage(Animator.marioIdleFacingLeft);
                } else {

                    this.mario.changeImage(Animator.marioRunningRight);
                }

            } else if (this.mario.isFireMario()) {

                if (mario.getRigidbody().getVel().x < 0) {

                    this.mario.changeImage(Animator.fireMarioRunningLeft);

                } else if (this.mario.getRigidbody().getVel().x == 0) {

                    this.mario.changeImage(Animator.fireMarioFacingLeft);
                } else {

                    this.mario.changeImage(Animator.fireMarioRunningRight);
                }
            }

            MarioDir.marioJumpingLeft = false;
        }

    }

    public void shootFireBall() {

        if (MarioDir.marioIdleFacingRight || MarioDir.marioRunningRight
                || MarioDir.marioJumpingRight) {

            this.createExplosive(this.mario.getPosition().getPos().x, this.mario.getPosition().getPos().y, 1);
        } else {

            this.createExplosive(this.mario.getPosition().getPos().x, this.mario.getPosition().getPos().y, -1);
        }
    }

    private void createExplosive(double xPos, double yPos, double xVel) {

        Position position = new Position(xPos, yPos);
        Explosive explosive = new Explosive(position, GlobalVariables.explosiveTag);
        explosive.addComponent(new Rigidbody(GlobalVariables.rigidbodyTag,
                position, explosive));
        explosive.addComponent(new Collider(GlobalVariables.colliderTag, position,
                35, 35, explosive));
        Rigidbody rigidbody = (Rigidbody) explosive.getComponent(GlobalVariables.rigidbodyTag);
        rigidbody.getVel().x = xVel;
        rigidbody.getVel().y = 1;
        GameEngine.gameObjects.add(explosive);

    }

    public void marioDead() {

        this.mario.getRigidbody().getVel().x = 0;
        this.mario.getRigidbody().getVel().y = 1;
        ScoreKeeper.decreaseLives();
        Collider collider = (Collider) this.mario.getComponent(GlobalVariables.colliderTag);
        Collider.removeCollider(collider);
        this.mario.removeComponent(GlobalVariables.colliderTag);
        Animator.marioDeadAnimation(this);
    }

}