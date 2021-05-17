package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import ECS.Position;
import javafx.scene.image.Image;

public class Explosive extends GameObject {

    public Explosive(Position position, String tag) {
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

        super.changeImage(Animator.explosive);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.pipeTag)){

            System.out.println();
        }

        if (other.getTag().equals(GlobalVariables.brickBoxTag) || other.getTag().equals(GlobalVariables.groundTag)
                || other.getTag().equals(GlobalVariables.pipeTag) || other.getTag().equals(GlobalVariables.itemBoxTag)) {

            if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1
                    || collision.getHitDirection().y == -1) {

                this.destroy();
            } else {


                Collider collider = (Collider) other.getComponent(GlobalVariables.colliderTag);
                this.getPosition().getPos().y = this.getPosition().getPos().y - collider.getSize().y;
                Rigidbody rigidbody = (Rigidbody) super.getComponent(GlobalVariables.rigidbodyTag);

                if (rigidbody.getVel().x >= 0) {

                    rigidbody.getVel().x = 3.5;
                } else {

                    rigidbody.getVel().x = -3.5;
                }

                rigidbody.getVel().y = -1.5;
            }

        } else if (other.getTag().equals(GlobalVariables.goombaTag) || other.getTag().equals(GlobalVariables.koopaTag)) {

            other.destroy();
        }
    }
}
