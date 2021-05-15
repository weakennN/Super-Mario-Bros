package Game.GameObjects;

import Game.Animator.Animator;
import Game.CollisionInfo.Collision;
import Game.CollisionInfo.Collisions;
import Game.Common.GlobalVariables;
import ECS.Rigidbody;
import Game.GameObjects.Mario.Mario;
import ECS.Position;
import javafx.scene.image.Image;

public class BrickBox extends GameObject {

    public BrickBox(Position position, String tag) {

        super(position, tag);
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

        super.changeImage(Animator.brickBox);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        Rigidbody rigidbody = (Rigidbody) other.getComponent(GlobalVariables.rigidbodyTag);

        if (rigidbody == null) {

            return;
        }

        if (collision.getHitDirection().y == -1) {

            Collisions.defaultOnGroundCollision(this, other, collision);

        } else if (collision.getHitDirection().y == 1) {

            rigidbody.getVel().y = 1;

            if (other.getTag().equals(GlobalVariables.marioTag)) {

                Mario mario = (Mario) other;

                if (mario.isBigMario()) {

                    this.destroy();
                }

            }

        } else if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {

            Collisions.defaultHorizontalCollision(this, other, collision);
        }

    }

}
