package Game.GameObjects.Mario;

import Game.Animator.Animator;
import Game.CollisionInfo.Collision;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Engine.GameEngine;
import Game.GameObjects.GameObject;
import Game.GameObjects.Goomba;
import Game.GameObjects.Mushroom;
import ECS.Position;
import Game.Score.ScoreKeeper;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import javafx.scene.image.Image;

public class Mario extends GameObject {

    private MarioManager marioManager;
    private boolean normal;
    private boolean bigMario;
    private boolean isDead;
    private boolean jumping;
    private boolean immune;
    private boolean onGround;
    private boolean fireMario;
    private boolean isFalling;

    public Mario(Position position, String tag) {

        super(position, tag);

        this.normal = true;
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

        return this.bigMario;
    }

    public boolean isJumping() {

        return this.jumping;
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

                    this.marioManager.marioDead();
                } else {

                    this.immune = true;
                    this.marioManager.decreaseMario();
                }
            }


        } else if (collision.getHitDirection().y == 1) {

            if (!this.immune) {

                this.getRigidbody().getVel().y *= -0.3;
                SoundManager.playSound(Sounds.goombaDeadSound);
                ScoreKeeper.incrementScore(100);
                goomba.removeComponent(GlobalVariables.rigidbodyTag);
                Collider.removeCollider((Collider) goomba.getComponent(GlobalVariables.colliderTag));
                goomba.removeComponent(GlobalVariables.colliderTag);
                Animator.goombaDeadAnimation(goomba);
            }
        }
    }

    private void marioAndMushroomCollision(Mushroom mushroom) {

        this.getMarioManager().powerUpMarioWithMushroom();
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

    public boolean isNormal() {

        return this.normal;
    }

    public void setNormal(boolean b) {

        this.normal = b;
    }

}
