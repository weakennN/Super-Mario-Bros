package Game.GameObjects;

import Game.Animator.Animator;
import Game.CollisionInfo.Collision;
import Game.Common.GlobalVariables;
import ECS.Rigidbody;
import ECS.Position;
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
        rigidbody.getVel().x = -1;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if ((collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1)
                && other.getTag().equals(GlobalVariables.goombaTag)) {

            this.getRigidbody().getVel().x *= -1;
            Rigidbody rigidbody = (Rigidbody) other.getComponent(GlobalVariables.rigidbodyTag);
            rigidbody.getVel().x *= -1;

        }


    }

    public Rigidbody getRigidbody() {

        return (Rigidbody) this.getComponent(GlobalVariables.rigidbodyTag);
    }

}
