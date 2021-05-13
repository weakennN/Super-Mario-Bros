package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import GameEngine.GameEngine;
import GameObjects.AI.Goomba;
import Helpers.MarioDir;
import Rigidbody.Position;
import Score.ScoreKeeper;
import SoundEffects.SoundManager;
import SoundEffects.Sounds;
import javafx.scene.image.Image;

public class Mario extends GameObject {

    private MarioManager marioManager;
    private boolean bigMario;
    private boolean isDead;
    private boolean jumping;
    private boolean immune;
    private boolean onGround;
    private boolean fireMario;
    private boolean isFalling;

    public Mario(Position position, String tag) {

        super(position, tag);

        this.bigMario = false;
        super.changeImage(Animator.marioIdleFacingRight);
        MarioDir.marioIdleFacingRight = true;
        this.isDead = false;
        this.jumping = false;
        this.immune = false;
        this.onGround = false;
        this.isFalling = false;
        this.fireMario = false;
        this.marioManager = new MarioManager(this);
    }

    @Override
    public void update() {

        if (bigMario) {

            this.marioManager.growMario();
        }

        super.updateComponents();

    }

    @Override
    public Image render() {

        return super.getCurrentAnimation();
    }

    @Override
    public void start() {

        this.initializeActions(this.getRigidbody());
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.mushroomTag)) {

            this.marioAndMushroomCollision((Mushroom) other);
        } else if (other.getTag().equals(GlobalVariables.goombaTag)) {

            this.marioAndGoombaCollision((Goomba) other, collision);
        }

    }

    public MarioManager getMarioManager() {

        return this.marioManager;
    }

    public void setDead(boolean dead) {

        this.isDead = dead;
    }

    public void initializeActions(Rigidbody rigidbody) {

        this.marioManager.initializeActions(rigidbody);
    }

    public void setBigMario(boolean b) {

        this.bigMario = b;
    }

    public boolean isFireMario() {

        return this.fireMario;
    }

    public void setFireMario(boolean b) {

        this.fireMario = b;
    }

    public void setJumping(boolean jumping) {

        this.jumping = jumping;
    }

    public Rigidbody getRigidbody() {

        return (Rigidbody) this.getComponent(GlobalVariables.rigidbodyTag);
    }

    public void setImmune(boolean b) {

        this.immune = b;
    }

    public boolean isBigMario() {
        return bigMario;
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isOnGround() {

        return this.onGround;
    }

    public boolean isDead() {

        return this.isDead;
    }

    private void marioAndGoombaCollision(Goomba goomba, Collision collision) {

        if (collision.getHitDirection().x > 0 && collision.getHitDirection().x > collision.getHitDirection().y
                || collision.getHitDirection().x < 0) {

            if (!this.immune) {

                if (!bigMario) {

                    this.getRigidbody().getVel().x = 0;
                    this.getRigidbody().getVel().y = 1;
                    ScoreKeeper.decreaseLives();
                    this.changeImage(Animator.marioDead);
                    Collider collider = (Collider) this.getComponent(GlobalVariables.colliderTag);
                    Collider.removeCollider(collider);
                    this.removeComponent(GlobalVariables.colliderTag);
                    Animator.marioDeadAnimation(this.marioManager);

                } else {

                    this.immune = true;
                    this.marioManager.decreaseMario();
                }
            }


        } else if (collision.getHitDirection().y == 1) {

            this.getRigidbody().getVel().y -= 5;
            SoundManager.playSound(Sounds.goombaDeadSound);
            ScoreKeeper.incrementScore(100);
            goomba.removeComponent(GlobalVariables.rigidbodyTag);
            Collider.removeCollider((Collider) goomba.getComponent(GlobalVariables.colliderTag));
            goomba.removeComponent(GlobalVariables.colliderTag);
            Animator.goombaDeadAnimation(goomba);
        }
    }

    private void marioAndMushroomCollision(Mushroom mushroom) {

        this.getMarioManager().powerUpMarioWithMushroom(this);
        GameEngine.gameObjects.remove(mushroom);
        Collider.removeCollider((Collider) mushroom.getComponent(GlobalVariables.colliderTag));
        mushroom.removeComponent(GlobalVariables.colliderTag);
        mushroom.removeComponent(GlobalVariables.rigidbodyTag);
        ScoreKeeper.incrementScore(1000);

        mushroom = null;

    }

    @Override
    public void changeImage(String image) {

        if (!this.isJumping()) {

            super.changeImage(image);
        }
    }

    public void setOnGround(boolean b) {

        this.onGround = b;
    }

}
