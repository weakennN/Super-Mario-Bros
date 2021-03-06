package Game.GameObjects;

import Engine.ECS.Animator.Animator;
import Engine.ECS.Collider;
import Engine.ECS.Renderer.SprtieRenderer.Sprite;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Transform;
import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import Engine.ECS.Rigidbody;
import Game.GameObjects.Mario.Mario;
import Game.Score.ScoreKeeper;
import Util.AssetPool;

public class Koopa extends GameObject {

    private boolean isTransformed;
    private boolean shellMoving;

    public Koopa(String tag) {
        super(tag);

        this.isTransformed = false;
        this.shellMoving = false;
    }

    @Override
    public void update() {
        Rigidbody rigidbody = super.getComponent(Rigidbody.class);
        if (!this.isTransformed) {
            if (rigidbody.getVel().x > 0) {
                if (super.getComponent(Transform.class).getScale().x >= 0) {
                    super.getComponent(Transform.class).getScale().x *= -1;
                }
            } else {
                if (super.getComponent(Transform.class).getScale().x < 0) {
                    super.getComponent(Transform.class).getScale().x *= -1;
                }
            }
        }
        super.updateComponents();
    }

    @Override
    public void start() {
        Rigidbody rigidbody = super.getComponent(Rigidbody.class);
        rigidbody.getVel().x = -1;
        super.getComponent(Animator.class).getAnimationController().playAnimation("koopaAnimation");
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (other.getTag().equals(GlobalVariables.marioTag) && collision.getHitDirection().y == -1) {

            Rigidbody rigidbody = other.getComponent(Rigidbody.class);
            Rigidbody rigidbody1 = super.getComponent(Rigidbody.class);

            if (!this.isTransformed) {
                rigidbody1.getVel().x = 0;
                this.isTransformed = true;
                super.getComponent(Collider.class).resize(50, 50);
                super.getComponent(Transform.class).getPos().y += 50;
                super.getComponent(Animator.class).getAnimationController().stop();
                super.getComponent(SpriteRenderer.class).setSprite(new Sprite(AssetPool.getTexture("KoopaShell")));
            } else {
                if (rigidbody1.getVel().x >= 0) {
                    super.getComponent(Transform.class).getPos().x += 50;
                    rigidbody1.getVel().x = 4.5;
                    this.shellMoving = true;
                } else {
                    rigidbody1.getVel().x = 0;
                    this.shellMoving = false;
                }
            }

            rigidbody.getVel().y *= -0.3;

        } else if ((other.getTag().equals(GlobalVariables.goombaTag)
                || other.getTag().equals(GlobalVariables.marioTag)) &&
                (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) && this.shellMoving) {

            if (other.getTag().equals(GlobalVariables.marioTag)) {
                Mario mario = (Mario) other;
                mario.getMarioManager().checkMarioDead();
            } else if (this.isTransformed && this.shellMoving) {
                other.destroy();
                ScoreKeeper.incrementScore(100);
            }

        } else if (other.getTag().equals(GlobalVariables.marioTag) &&
                (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) && !this.shellMoving) {
            Mario mario = (Mario) other;
            mario.getMarioManager().checkMarioDead();
        }
    }

    public boolean isTransformed() {
        return this.isTransformed;
    }
}
