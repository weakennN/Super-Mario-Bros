package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Rigidbody;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Ground extends GameObject {

    public Ground(Position position, String tag) {

        super(position, tag);

        /*super.getRigidbody().setGameObject(this);
        super.getRigidbody().setCollider(new GroundCollider(position, 10, 1080));

         */
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

        Rigidbody rigidbody = (Rigidbody) other.getComponent(GlobalVariables.rigidbodyTag);

        if (rigidbody != null) {

            rigidbody.getAcc().y = 0;
            rigidbody.getVel().y = 0;
            other.getPosition().getPos().y = this.getPosition().getPos().y - 50;

        }

    }
}
