package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import ECS.Rigidbody;
import javafx.scene.image.Image;

public class Goomba extends GameObject {

    public Goomba(String tag) {

        super(tag);

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

        Rigidbody rigidbody = super.getComponent(Rigidbody.class);
        rigidbody.getVel().x = -1;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if ((collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1)
                && other.getTag().equals(GlobalVariables.goombaTag)) {

            this.getRigidbody().getVel().x *= -1;
            Rigidbody rigidbody = other.getComponent(Rigidbody.class);
            rigidbody.getVel().x *= -1;
        }
    }

    public Rigidbody getRigidbody() {
        return this.getComponent(Rigidbody.class);
    }

}
