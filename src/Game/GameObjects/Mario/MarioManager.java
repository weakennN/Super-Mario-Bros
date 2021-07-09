package Game.GameObjects.Mario;

import ECS.Transform;
import Game.Animator.Animator;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Game.GameObjects.Flower;
import Game.GameObjects.Goomba;
import Game.GameObjects.Mushroom;
import Game.Score.ScoreKeeper;
import UIEngine.Designer;
import Engine.GameEngine;
import Game.GameObjects.Explosive;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import javafx.scene.input.KeyCode;

public class MarioManager {

    private Mario mario;
    private boolean hasInput;

    public boolean isHasInput() {
        return hasInput;
    }

    public void setHasInput(boolean b) {
        this.hasInput = b;
    }

    public MarioManager(Mario mario) {
        this.mario = mario;
    }

    public void growMario() {
        Collider collider = mario.getComponent(Collider.class);
        collider.resize(GlobalVariables.defaultBigMarioColliderSizeX, GlobalVariables.defaultBigMarioColliderSizeY);
    }

    public Mario getMario() {
        return this.mario;
    }

    public void setMarioDead(boolean b) {
        this.mario.setDead(b);
    }

    public void decreaseMario() {
        this.mario.setBigMario(false);
        this.mario.setBreakable(false);
        this.mario.setNormal(true);
        Animator.marioDecreasingAnimation(mario);
        Collider collider = mario.getComponent(Collider.class);
        collider.resize(GlobalVariables.defaultMarioColliderX, GlobalVariables.defaultMarioColliderY);

    }

    public void initializeActions(Rigidbody rigidbody) {

        /*Designer.scene.setOnKeyPressed(e -> {

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
                    this.hasInput = true;
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
                    this.hasInput = true;
                }

            } else if (e.getCode() == KeyCode.W) {

                if (!this.mario.isJumping()) {
                    rigidbody.getVel().y = -3.6;

                    if (this.mario.isNormal()) {
                        SoundManager.playSound(Sounds.marioJumpingSound);
                    } else {
                        SoundManager.playSound(Sounds.bigMarioJumpingSound);
                    }

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

                this.hasInput = false;
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
                this.hasInput = false;
            }
        });

         */
    }

    public void setMarioAnimationAfterJump() {

        if (MarioDir.marioJumpingRight) {

            if (this.mario.isBigMario()) {
                this.afterJumpDirection(Animator.bigMarioRunningLeft, Animator.bigMarioFacingRight, Animator.bigMarioRunningRight);
            } else if (this.mario.isNormal()) {
                this.afterJumpDirection(Animator.marioRunningLeft, Animator.marioIdleFacingRight, Animator.marioRunningRight);
            } else if (this.mario.isFireMario()) {
                this.afterJumpDirection(Animator.fireMarioRunningLeft, Animator.fireMarioFacingRight, Animator.fireMarioRunningRight);
            }

            MarioDir.marioJumpingRight = false;

        } else if (MarioDir.marioJumpingLeft) {

            if (this.mario.isBigMario()) {
                this.afterJumpDirection(Animator.bigMarioRunningLeft, Animator.bigMarioFacingLeft, Animator.bigMarioRunningRight);
            } else if (this.mario.isNormal()) {
                this.afterJumpDirection(Animator.marioRunningLeft, Animator.marioIdleFacingLeft, Animator.marioRunningRight);
            } else if (this.mario.isFireMario()) {
                this.afterJumpDirection(Animator.fireMarioRunningLeft, Animator.fireMarioFacingLeft, Animator.fireMarioRunningRight);
            }

            MarioDir.marioJumpingLeft = false;

        }

    }

    public void shootFireBall() {

        if (MarioDir.marioIdleFacingRight || MarioDir.marioRunningRight
                || MarioDir.marioJumpingRight) {

            this.createExplosive(this.mario.getComponent(Transform.class).getPos().x + 20, this.mario.getComponent(Transform.class).getPos().y + 25, 2);
        } else {

            this.createExplosive(this.mario.getComponent(Transform.class).getPos().x, this.mario.getComponent(Transform.class).getPos().y + 25, -2);
        }
    }

    private void createExplosive(double xPos, double yPos, double xVel) {

        Explosive explosive = new Explosive(GlobalVariables.explosiveTag);
        explosive.addComponent(new Rigidbody(explosive));
        explosive.addComponent(new Collider(explosive,
                25, 25, explosive.getComponent(Transform.class)));
        Rigidbody rigidbody = explosive.getComponent(Rigidbody.class);
        rigidbody.getVel().x = xVel;
        rigidbody.getVel().y = 1;
        GameEngine.gameObjects.add(explosive);

    }

    public void marioDead() {

        this.mario.getRigidbody().getVel().x = 0;
        this.mario.getRigidbody().getVel().y = 1;
        Collider collider = this.mario.getComponent(Collider.class);
        Collider.removeCollider(collider);
        this.mario.removeComponent(Collider.class);
        Animator.marioDeadAnimation(this);
    }

    private void afterJumpDirection(String animation1, String animation2, String animation3) {

        if (mario.getRigidbody().getVel().x < 0) {
            this.mario.changeImage(animation1);
        } else if (this.mario.getRigidbody().getVel().x == 0) {
            this.mario.changeImage(animation2);
        } else {
            this.mario.changeImage(animation3);
        }

    }

    public void checkMarioDead() {

        if (!this.mario.isImmune()) {

            if (!this.mario.isBigMario() && !this.mario.isFireMario()) {
                this.marioDead();
            } else {

                this.mario.setImmune(true);
                this.mario.setBigMario(false);
                this.mario.setFireMario(false);
                this.decreaseMario();
            }
        }
    }

    public void killGoomba(Goomba goomba) {

        if (!this.mario.isImmune()) {

            this.mario.getRigidbody().getVel().y *= -0.3;
            SoundManager.playSound(Sounds.goombaDeadSound);
            ScoreKeeper.incrementScore(100);
            goomba.removeComponent(Rigidbody.class);
            Collider.removeCollider(goomba.getComponent(Collider.class));
            goomba.removeComponent(Collider.class);
            Animator.goombaDeadAnimation(goomba);
        }
    }

    public void powerUpWithMushroom() {

        SoundManager.playSound(Sounds.superMarioGrowingSound);

        mario.getRigidbody().getVel().x = 0;
        mario.getRigidbody().getVel().y = 0;
        mario.getRigidbody().getAcc().y = 0;
        mario.getRigidbody().getAcc().x = 0;

        Animator.marioGrowingAnimation(mario, mario.getMarioManager());
        mario.setBigMario(true);
        mario.setBreakable(true);
        mario.setNormal(false);

    }

    public void powerUpWithFireFireFlower(Flower fireFlower) {

        this.mario.setFireMario(true);
        this.mario.setNormal(false);
        this.mario.setBreakable(true);
        this.growMario();
        fireFlower.destroy();
    }

    public void marioPowerUpWithMushroom(Mushroom mushroom) {

        this.powerUpWithMushroom();
        GameEngine.gameObjects.remove(mushroom);
        Collider.removeCollider(mushroom.getComponent(Collider.class));
        mushroom.removeComponent(Collider.class);
        mushroom.removeComponent(Rigidbody.class);
        ScoreKeeper.incrementScore(1000);

        mushroom = null;
    }
}
