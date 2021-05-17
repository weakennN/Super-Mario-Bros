package Game.GameObjects;

import Game.Animator.Animator;
import Game.CollisionInfo.Collision;
import Game.Common.GlobalVariables;
import ECS.Rigidbody;
import ECS.Position;
import Game.GameObjects.Mario.Mario;
import Game.Score.ScoreKeeper;
import javafx.scene.image.Image;

public class Koopa extends GameObject {

    private boolean isTransformed;
    private boolean shellMoving;

    public Koopa(Position position, String tag) {
        super(position, tag);

        this.isTransformed = false;
        this.shellMoving = false;
    }

    @Override
    public void update() {

        Rigidbody rigidbody = (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);

        if (!this.isTransformed) {

            if (rigidbody.getVel().x > 0) {

                super.changeImage(Animator.koopaFacingRight);
            } else {

                super.changeImage(Animator.koopaFacingLeft);
            }

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
        rigidbody.getVel().x = -1;
        super.changeImage(Animator.koopaFacingLeft);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.marioTag) && collision.getHitDirection().y == -1) {

            Rigidbody rigidbody = (Rigidbody) other.getComponent(GlobalVariables.rigidbodyTag);
            Rigidbody rigidbody1 = (Rigidbody) this.getComponent(GlobalVariables.rigidbodyTag);

            if (!this.isTransformed) {

                rigidbody1.getVel().x = 0;
                super.changeImage(Animator.koopasShell);
                this.isTransformed = true;

            } else {

                if (rigidbody1.getVel().x >= 0) {

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

            // TODO: fix this

            if (other.getTag().equals(GlobalVariables.marioTag)) {

                Mario mario = (Mario) other;
                mario.getMarioManager().marioDead();
            } else if (this.isTransformed) {

                other.destroy();
                ScoreKeeper.incrementScore(100);
            }

        }

    }

    public boolean isTransformed() {

        return this.isTransformed;
    }

}
