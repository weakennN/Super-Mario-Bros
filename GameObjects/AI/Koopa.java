package GameObjects.AI;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Components.Rigidbody;
import GameObjects.GameObject;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Koopa extends GameObject {

    private boolean isTransformed;

    public Koopa(Position position, String tag) {
        super(position, tag);

        this.isTransformed = false;
    }

    @Override
    public void update() {

        // TODO: make this method and add koopa's shell

        Rigidbody rigidbody = (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);

        if (rigidbody.getVel().x > 0){

            super.changeImage(Animator.koopaFacingRight);
        }else {

            super.changeImage(Animator.koopaFacingLeft);
        }

        super.updateComponents();
    }

    @Override
    public Image render() {

        return super.getCurrentAnimation();
    }

    @Override
    public void start() {

        Rigidbody rigidbody = (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);
        rigidbody.getVel().x = -1;
        super.changeImage(Animator.koopaFacingLeft);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.marioTag) && collision.getHitDirection().y == -1) {

            this.isTransformed = true;
            this.destroy();
            // change koopa's form and physics
        }

    }

    public boolean isTransformed() {

        return this.isTransformed;
    }

}
