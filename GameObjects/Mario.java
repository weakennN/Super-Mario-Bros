package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Rigidbody;
import Designer.Designer;
import GameEngine.GameEngine;
import GameObjects.AI.Goomba;
import Helpers.MarioDir;
import Rigidbody.Position;
import Rigidbody.RigidBody;
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
      /*  super.getRigidbody().setCollider(new MarioCollider(position, 53, 53));
        super.getRigidbody().setGameObject(this);
        super.getRigidbody().setGravity(true);

       */
        this.powerUp = new PowerUp();
        this.bigMario = false;
        super.changeImage(Animator.marioIdleFacingRight);
        this.isDead = false;
        this.jumping = false;

    }

    @Override
    public void update() {

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

        if (other.getTag().equals("Mushroom")) {

            this.marioAndMushroomCollision((Mushroom) other);
        } else if (other.getTag().equals("Goomba")) {

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
            Animator.marioDeadAnimation(this);

        } else if (collision.getHitDirection().y == -1) {

            GameEngine.gameObjects.remove(goomba);
            RigidBody.rigidBodies.remove(goomba.getRigidbody());
            goomba = null;
        }
    }

    private void marioAndMushroomCollision(Mushroom mushroom) {

        this.getPowerUp().powerUpMarioWithMushroom(this);
        GameEngine.gameObjects.remove(mushroom);
        RigidBody.rigidBodies.remove(mushroom.getRigidbody());
        mushroom = null;

    }
}
