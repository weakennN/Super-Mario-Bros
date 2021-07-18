package Game.GameObjects.Mario;

import ECS.Animator.Animation.Frame.PositionFrame;
import ECS.Animator.Animation.FrameAnimation;
import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.Renderer.SprtieRenderer.Sprite;
import ECS.Renderer.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Event.EventListener;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Game.GameObjects.*;
import Game.Levels.GameObjectFactory.GameObjectFactory;
import Game.Score.ScoreKeeper;
import Engine.GameEngine;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import Input.Input;
import Util.AssetPool;
import mikera.vectorz.Vector2;

public class MarioManager {

    private Mario mario;

    public MarioManager(Mario mario) {
        this.mario = mario;
    }

    public void growMario() {
        this.mario.getComponent(Collider.class).resize(GlobalVariables.defaultBigMarioColliderSizeX, GlobalVariables.defaultBigMarioColliderSizeY);
    }

    public Mario getMario() {
        return this.mario;
    }

    public void decreaseMario() {
        Input.lock();
        this.mario.getRigidbody().getVel().x = 0;
        this.mario.getRigidbody().getVel().y = 0;
        this.mario.setBigMario(false);
        this.mario.setBreakable(false);
        this.mario.setNormal(true);
        this.mario.getComponent(Animator.class).getAnimationController().stop();
        this.mario.getComponent(Animator.class).getAnimationController().playAnimation("marioDecreasing");
        Collider collider = mario.getComponent(Collider.class);
        collider.resize(GlobalVariables.defaultMarioColliderX, GlobalVariables.defaultMarioColliderY);
    }

    public void setMarioAnimationAfterJump() {
        AnimationController marioAnimationController = this.mario.getComponent(ECS.Animator.Animator.class).getAnimationController();

        if (this.mario.isJumping()) {
            if (this.mario.isBigMario()) {
                this.afterJumpDirection("bigMarioRunning",
                        AssetPool.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(0), "bigMarioRunning", marioAnimationController);
            } else if (this.mario.isNormal()) {
                this.afterJumpDirection("marioRunning", AssetPool.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(0)
                        , "marioRunning", marioAnimationController);
            } else if (this.mario.isFireMario()) {
                this.afterJumpDirection("fireMarioRunning",
                        AssetPool.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(0), "fireMarioRunning", marioAnimationController);
            }
        }
    }

    public void shootFireBall() {
        if (MarioDir.marioIdleFacingRight || MarioDir.marioRunningRight
                || MarioDir.marioJumpingRight) {
            this.createExplosive(this.mario.getComponent(Transform.class).getPos().x + 20, this.mario.getComponent(Transform.class).getPos().y + 25, 3);
        } else {
            this.createExplosive(this.mario.getComponent(Transform.class).getPos().x, this.mario.getComponent(Transform.class).getPos().y + 25, -3);
        }
    }

    private void createExplosive(double xPos, double yPos, double xVel) {
        FireBall fireBall = (FireBall) GameObjectFactory.create(new String[]{xPos + "", yPos + "", xVel + ""}, "FireBall");
        fireBall.getComponent(Animator.class).getAnimationController().stop();
        fireBall.getComponent(Animator.class).getAnimationController().playAnimation("fireBallAnimation");
        GameEngine.gameObjects.add(fireBall);
    }

    public void marioDead() {
        this.mario.removeComponent(Rigidbody.class);
        this.mario.getComponent(Animator.class).getAnimationController().stop();
        MarioDir.disableDirs();
        Input.lock();
        SoundManager.playSound(Sounds.marioLosesLifeSound);
        Collider collider = this.mario.getComponent(Collider.class);
        Collider.removeCollider(collider);
        this.mario.removeComponent(Collider.class);
        this.mario.getComponent(SpriteRenderer.class).setSprite(AssetPool.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(6));

        FrameAnimation marioDead = new FrameAnimation(mario, false, 300, new PositionFrame(0, this.mario.getComponent(Transform.class).getPos(),
                new Vector2(this.mario.getComponent(Transform.class).getPos().x, this.mario.getComponent(Transform.class).getPos().y - 100), mario.getComponent(Transform.class))
                , new PositionFrame(50, this.mario.getComponent(Transform.class).getPos()
                , new Vector2(this.mario.getComponent(Transform.class).getPos().x, this.mario.getComponent(Transform.class).getPos().y + 300), mario.getComponent(Transform.class)));

        marioDead.getAnimationFinish().subscribe(new EventListener<GameObject>() {
            @Override
            public void invoke(GameObject arg) {
                Mario mario = (Mario) arg;
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
            this.mario.getRigidbody().getVel().x = -2.5;
            if (this.mario.getComponent(Transform.class).getScale().x > 0) {
                this.mario.getComponent(Transform.class).getScale().x *= -1;
            }
        } else if (this.mario.getRigidbody().getVel().x == 0) {
            marioAnimationController.stop();
            this.mario.getComponent(SpriteRenderer.class).setSprite(sprite);
        } else {
            marioAnimationController.playAnimation(animation2);
            this.mario.getRigidbody().getVel().x = 2.5;
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
            goomba.getComponent(Animator.class).getAnimationController().stop();
            goomba.getComponent(Animator.class).getAnimationController().playAnimation("goombaDead");
        }
    }

    public void powerUpWithMushroom() {
        if (this.mario.isNormal()) {
            SoundManager.playSound(Sounds.superMarioGrowingSound);
            this.growMario();
            MarioDir.disableDirs();
            this.mario.getRigidbody().setActive(false);
            Input.lock();

            this.mario.getComponent(Animator.class).getAnimationController().getAnimation("marioGrowing").getAnimationFinish().subscribe(new EventListener<GameObject>() {
                @Override
                public void invoke(GameObject arg) {
                    mario.getRigidbody().setActive(true);
                    Input.unlock();
                }
            });

            this.mario.getComponent(Animator.class).getAnimationController().stop();
            this.mario.getComponent(Animator.class).getAnimationController().playAnimation("marioGrowing");
            this.mario.setBigMario(true);
            this.mario.setBreakable(true);
            this.mario.setNormal(false);
        }
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
