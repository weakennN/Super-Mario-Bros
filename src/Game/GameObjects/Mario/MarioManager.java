package Game.GameObjects.Mario;

import ECS.Animator.Animation.Frame.PositionFrame;
import ECS.Animator.Animation.FrameAnimation;
import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.SprtieRenderer.Sprite;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Event.EventListener;
import Game.Animator.GlobalAnimations;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Game.Common.SpriteSheetContainer;
import Game.GameObjects.*;
import Game.Score.ScoreKeeper;
import Engine.GameEngine;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import Input.Input;
import mikera.vectorz.Vector2;

public class MarioManager {

    private Mario mario;

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

    public void decreaseMario() {
        this.mario.setBigMario(false);
        this.mario.setBreakable(false);
        this.mario.setNormal(true);
        GlobalAnimations.marioDecreasingAnimation(mario);
        Collider collider = mario.getComponent(Collider.class);
        collider.resize(GlobalVariables.defaultMarioColliderX, GlobalVariables.defaultMarioColliderY);
    }

    public void setMarioAnimationAfterJump() {
        AnimationController marioAnimationController = this.mario.getComponent(ECS.Animator.Animator.class).getAnimationController();

        if (this.mario.isJumping()) {
            if (this.mario.isBigMario()) {
                this.afterJumpDirection("bigMarioRunning",
                        SpriteSheetContainer.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(0), "bigMarioRunning", marioAnimationController);
            } else if (this.mario.isNormal()) {
                this.afterJumpDirection("marioRunning", SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(0)
                        , "marioRunning", marioAnimationController);
            } else if (this.mario.isFireMario()) {
                this.afterJumpDirection("fireMarioRunning",
                        SpriteSheetContainer.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(0), "fireMarioRunning", marioAnimationController);
            }
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
        this.mario.removeComponent(Rigidbody.class);
        Input.lock();
        SoundManager.playSound(Sounds.marioLosesLifeSound);
        Collider collider = this.mario.getComponent(Collider.class);
        Collider.removeCollider(collider);
        this.mario.removeComponent(Collider.class);
        this.mario.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(6));

        FrameAnimation marioDead = new FrameAnimation(mario, false, 300, new PositionFrame(0, this.mario.getComponent(Transform.class).getPos(),
                new Vector2(this.mario.getComponent(Transform.class).getPos().x, this.mario.getComponent(Transform.class).getPos().y - 100), mario.getComponent(Transform.class))
                , new PositionFrame(50, this.mario.getComponent(Transform.class).getPos()
                , new Vector2(this.mario.getComponent(Transform.class).getPos().x, this.mario.getComponent(Transform.class).getPos().y + 300), mario.getComponent(Transform.class)));

        marioDead.getEvent().subscribe(new EventListener<GameObject>() {
            @Override
            public void invoke(GameObject arg) {
                Mario mario = (Mario) arg;
                ScoreKeeper.decreaseLives();
                mario.setDead(true);
            }
        });
        this.mario.getComponent(Animator.class).getAnimationController().createAnimation("marioDead", marioDead);
        this.mario.getComponent(Animator.class).getAnimationController().playAnimation("marioDead");
    }

    private void afterJumpDirection(String animation, Sprite sprite, String animation2, AnimationController marioAnimationController) {
        mario.setJumping(false);
        mario.setOnGround(true);

        if (mario.getRigidbody().getVel().x < 0) {
            marioAnimationController.playAnimation(animation);
            if (this.mario.getComponent(Transform.class).getScale().x > 0) {
                this.mario.getComponent(Transform.class).getScale().x *= -1;
            }
        } else if (this.mario.getRigidbody().getVel().x == 0) {
            marioAnimationController.stop();
            this.mario.getComponent(SpriteRenderer.class).setSprite(sprite);
        } else {
            marioAnimationController.playAnimation(animation2);
            if (this.mario.getComponent(Transform.class).getScale().x < 0) {
                this.mario.getComponent(Transform.class).getScale().x *= -1;
            }
        }

        if (MarioDir.marioJumpingRight) {
            MarioDir.marioJumpingRight = false;
        } else {
            MarioDir.marioJumpingLeft = false;
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
            GlobalAnimations.goombaDeadAnimation(goomba);
        }
    }

    public void powerUpWithMushroom() {
        SoundManager.playSound(Sounds.superMarioGrowingSound);
        Input.lock();
        this.mario.getRigidbody().getVel().x = 0;
        this.mario.getRigidbody().getVel().y = 0;
        this.mario.getRigidbody().getAcc().y = 0;
        this.mario.getRigidbody().getAcc().x = 0;

        this.mario.getComponent(Animator.class).getAnimationController().getAnimation("marioGrowing").getEvent().subscribe(new EventListener<GameObject>() {
            @Override
            public void invoke(GameObject arg) {
                Input.unlock();
            }
        });
        this.mario.getComponent(Animator.class).getAnimationController().playAnimation("marioGrowing");
        this.mario.getComponent(Collider.class).resize(GlobalVariables.defaultBigMarioColliderSizeX, GlobalVariables.defaultBigMarioColliderSizeY);
        this.mario.setBigMario(true);
        this.mario.setBreakable(true);
        this.mario.setNormal(false);
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
        mushroom.destroy();
        ScoreKeeper.incrementScore(1000);

        mushroom = null;
    }
}
