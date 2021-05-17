package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import ECS.Rigidbody;
import ECS.Position;
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
        rigidbody.getVel().x = 1;

        super.changeImage(Animator.superMushroom);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.brickBoxTag)) {

            Collisions.defaultHorizontalCollision(other, this, collision);
        }
    }

    public Rigidbody getRigidbody() {

        return (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);
    }

}
