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
            // change koopa's form and physics
        } else if (other.getTag().equals(GlobalVariables.brickBoxTag)) {

            if (collision.getHitDirection().x == 1) {

            } else if (collision.getHitDirection().x == -1) {


            }
        }
    }

    public boolean isTransformed() {

        return this.isTransformed;
    }

}
