package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class BrickBox extends GameObject {

    public BrickBox(Position position, String tag, double sizeX, double sizeY) {
        super(position, tag);

    }

    public BrickBox(Position position, String tag) {

        this(position, tag, 50, 50);
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

            Collider collider = (Collider) other.getComponent(GlobalVariables.colliderTag);
            other.getPosition().getPos().y = this.getPosition().getPos().y - collider.getSize().y;
            rigidbody.getVel().y = 0;
            rigidbody.getAcc().y = 0;

            if (other.getTag().equals(GlobalVariables.marioTag)) {

                Mario mario = (Mario) other;
                mario.setJumping(false);

            }
        } else if (collision.getHitDirection().y == 1) {

            rigidbody.getVel().y = 1;

        } else if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {

            if (collision.getHitDirection().x == 1) {

                other.getPosition().getPos().x -= 10;
            } /*else {

                other.getPosition().getPos().x += 10;
            }
            */

            // TODO: fix this and fix the reposition of the game object after he hits the top of the brick box collider

            rigidbody.getVel().x = 0;

        }

    }


}
