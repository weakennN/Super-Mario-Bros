package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import javafx.scene.image.Image;

public class Ground extends GameObject {

    private boolean visible;

    public Ground(String tag, boolean visible) {

        super(tag);

        this.visible = visible;
    }

    @Override
    public void update() {

        super.updateComponents();
    }

    @Override
    public Image render() {

        if (visible) {
            return super.getCurrentAnimation();
        }

        return null;
    }

    @Override
    public void start() {

        super.changeImage(Animator.ground);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (collision.getHitDirection().y == -1) {

            Collisions.defaultOnGroundCollision(this, other, collision);
        } else if (other.getTag().equals(GlobalVariables.marioTag)
                && (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1)) {

            Collisions.defaultHorizontalCollision(this, other, collision);
        }

    }
}
