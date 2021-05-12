package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Explosive extends GameObject {

    public Explosive(Position position, String tag) {
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

        super.changeImage(Animator.explosive);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.brickBoxTag) || other.getTag().equals(GlobalVariables.groundTag)) {

            if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {

                // destroy explosiveAnimation
                this.destroy();
            } else {

                // bounce up
                Collider collider = (Collider) other.getComponent(GlobalVariables.colliderTag);
                this.getPosition().getPos().y = this.getPosition().getPos().y - collider.getSize().y;
                Rigidbody rigidbody = (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);

                if (rigidbody.getVel().x >= 0) {

                    rigidbody.getVel().x = 3.5;
                } else {

                    rigidbody.getVel().x = -3.5;
                }

                rigidbody.getVel().y = -3.5;
            }

        } else if (other.getTag().equals(GlobalVariables.goombaTag) || other.getTag().equals(GlobalVariables.koopaTag)) {

            // destroy them
            other.destroy();
        }
    }
}
