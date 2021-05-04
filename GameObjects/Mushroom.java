package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Rigidbody;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Mushroom extends GameObject {

    public Mushroom(Position position, String tag) {
        super(position, tag);

    /*    super.getRigidbody().setCollider(new MushroomCollider(position, 75, 75));
        super.getRigidbody().setGameObject(this);
        super.getRigidbody().getVel().x = -2;

     */
        super.changeImage(Animator.superMushroom);
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

    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }

    public Rigidbody getRigidbody() {

        return (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);
    }

}
