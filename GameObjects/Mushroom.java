package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import CollisionInfo.Collisions;
import Common.GlobalVariables;
import Components.Rigidbody;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Mushroom extends GameObject {

    public Mushroom(Position position, String tag) {
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

        Rigidbody rigidbody = (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);
        rigidbody.getVel().x = -2;

        super.changeImage(Animator.superMushroom);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.brickBoxTag)) {

            Collisions.defaultGoombaAndKoopaCollision(this, other, collision);
        }
    }

    public Rigidbody getRigidbody() {

        return (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);
    }

}
