package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import ECS.Rigidbody;
import javafx.scene.image.Image;

public class Mushroom extends GameObject {

    public Mushroom(String tag) {
        super(tag);

    }

    @Override
    public void update() {

        super.updateComponents();
    }

    @Override
    public void start() {

        Rigidbody rigidbody = this.getRigidbody();
        rigidbody.getVel().x = 1;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }

    public Rigidbody getRigidbody() {

        return super.getComponent(Rigidbody.class);
    }
}
