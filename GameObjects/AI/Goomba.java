package GameObjects.AI;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Rigidbody;
import GameObjects.GameObject;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Goomba extends GameObject {

    public Goomba(Position position, String tag) {

        super(position, tag);

        super.changeImage(Animator.goomba);
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
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }

    public Rigidbody getRigidbody() {

        return (Rigidbody) this.getComponent(GlobalVariables.rigidbodyTag);
    }

}
