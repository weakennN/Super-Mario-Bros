package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import Designer.Designer;
import GameEngine.GameEngine;
import GameObjects.AI.Goomba;
import Helpers.MarioDir;
import Rigidbody.Position;
import SoundEffects.SoundManager;
import SoundEffects.Sounds;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Mario extends GameObject {

    private PowerUp powerUp;
    private boolean bigMario;
    private boolean isDead;
    private boolean jumping;

    //TODO: crop the images.

    public Mario(Position position, String tag) {

        super(position, tag);

        this.powerUp = new PowerUp();
        this.bigMario = false;
        super.changeImage(Animator.marioIdleFacingRight);
        this.isDead = false;
        this.jumping = false;

    }

    @Override
    public void update() {

        if (bigMario){

            Collider collider = (Collider) this.getComponent(GlobalVariables.colliderTag);
            collider.resize(GlobalVariables.defaultBigMarioColliderSize, GlobalVariables.defaultBigMarioColliderSize);
        }

        super.updateComponents();

    }

    @Override
    public Image render() {

        return super.getCurrentAnimation();
    }

    @Override
    public void start() {

        Rigidbody rigidbody = (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);
        this.initializeActions(rigidbody);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.mushroomTag)) {

            this.marioAndMushroomCollision((Mushroom) other);
        } else if (other.getTag().equals(GlobalVariables.goombaTag)) {

            this.marioAndGoombaCollision((Goomba) other, collision);
        }

    }

    public PowerUp getPowerUp() {

        return this.powerUp;
    }

    public void setDead(boolean dead) {

        this.isDead = dead;
    }

    public void initializeActions(Rigidbody rigidbody) {

        Designer.scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.D) {

                rigidbody.getVel().x = 3.5;
                if (!MarioDir.marioRunningRight) {

                    if (!bigMario) {
                        super.changeImage(Animator.marioRunningRight);

                    } else {
                        super.changeImage(Animator.bigMarioRunningRight);

                    }

                    MarioDir.marioRunningRight = true;
                }

            } else if (e.getCode() == KeyCode.A) {

                rigidbody.getVel().x = -3.5;
                if (!MarioDir.marioRunningLeft) {

                    if (!bigMario) {
                        super.changeImage(Animator.marioRunningLeft);

                    } else {
                        super.changeImage(Animator.bigMarioRunningLeft);

                    }

                    MarioDir.marioRunningLeft = true;
                }

            } else if (e.getCode() == KeyCode.W) {

                if (!this.jumping) {
                    rigidbody.getVel().y = -3.8;
                    SoundManager.playSound(Sounds.marioJumpingSound);
                }
                this.jumping = true;
            }
        });

        Designer.scene.setOnKeyReleased(e -> {

            if (e.getCode() == KeyCode.A) {

                rigidbody.getVel().x = 0;
                if (!bigMario) {
                    super.changeImage(Animator.marioIdleFacingLeft);
                } else if (bigMario) {
                    super.changeImage(Animator.bigMarioFacingLeft);
                }
                MarioDir.marioRunningLeft = false;
                rigidbody.getAcc().x = 0;
                rigidbody.getAcc().y = 0;
            } else if (e.getCode() == KeyCode.D) {

                rigidbody.getVel().x = 0;
                if (!bigMario) {
                    super.changeImage(Animator.marioIdleFacingRight);
                } else if (bigMario) {
                    super.changeImage(Animator.bigMarioFacingRight);
                }
                MarioDir.marioRunningRight = false;
                rigidbody.getAcc().x = 0;
                rigidbody.getAcc().y = 0;
            }
        });
    }

    public void setBigMario(boolean b) {

        this.bigMario = b;
    }

    public void setJumping(boolean jumping) {

        this.jumping = jumping;
    }

    public Rigidbody getRigidbody() {

        return (Rigidbody) this.getComponent(GlobalVariables.rigidbodyTag);
    }

    private void marioAndGoombaCollision(Goomba goomba, Collision collision) {

        if (collision.getHitDirection().x > 0 && collision.getHitDirection().x > collision.getHitDirection().y
                || collision.getHitDirection().x < 0) {

            MarioLivesListener.decreaseLives();
            this.isDead = true;
            this.changeImage(Animator.marioDead);
            this.getRigidbody().getVel().x = 0;
            this.getRigidbody().getVel().y = 1;
            Animator.marioDeadAnimation();
            Collider collider = (Collider) this.getComponent(GlobalVariables.colliderTag);
            Collider.removeCollider(collider);
            this.removeComponent(GlobalVariables.colliderTag);

        } else if (collision.getHitDirection().y == -1) {

            GameEngine.gameObjects.remove(goomba);

            Collider.removeCollider((Collider) goomba.getComponent(GlobalVariables.colliderTag));
            goomba.removeComponent(GlobalVariables.colliderTag);

            goomba = null;
        }
    }

    private void marioAndMushroomCollision(Mushroom mushroom) {

        this.getPowerUp().powerUpMarioWithMushroom(this);
        GameEngine.gameObjects.remove(mushroom);
        Collider.removeCollider((Collider) mushroom.getComponent(GlobalVariables.colliderTag));
        mushroom.removeComponent(GlobalVariables.colliderTag);
        mushroom.removeComponent(GlobalVariables.rigidbodyTag);

        mushroom = null;

    }
}
