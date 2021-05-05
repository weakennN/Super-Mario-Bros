package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class ItemBox extends GameObject {

    GameObject gameObject;

    public ItemBox(Position position, String tag) {
        super(position, tag);


    }

    @Override
    public void start() {

        super.changeImage(Animator.itemBox);
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
    public void onCollisionEnter(GameObject other, Collision collision) {


    }
}
