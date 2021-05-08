package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import CollisionInfo.Collisions;
import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import Helpers.MarioDir;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Ground extends GameObject {

    public Ground(Position position, String tag) {

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

        super.changeImage(Animator.ground);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (collision.getHitDirection().y == - 1) {

            Collisions.defaultOnGroundCollision(this, other, collision);
        }

    }
}
