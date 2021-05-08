package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import CollisionInfo.Collisions;
import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import Rigidbody.Position;
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

            Collider collider = (Collider) other.getComponent(GlobalVariables.colliderTag);

            if (collision.getHitDirection().x == 1) {

                other.getPosition().getPos().x = this.getPosition().getPos().x - collider.getSize().x;

             /*  if (other.getTag().equals(GlobalVariables.marioTag)){

                    Mario mario = (Mario) other;
                    if (mario.isOnGround()){

                        mario.changeImage(Animator.marioIdleFacingRight);
                    }
                }

              */

            } else {

                // other.getPosition().getPos().x += 10;

                other.getPosition().getPos().x = this.getPosition().getPos().x + collider.getSize().x;
            }


            // TODO: fix this and fix the reposition of the game object after he hits the top of the brick box collider
            // TODO: Fix this method

        }

    }


}
