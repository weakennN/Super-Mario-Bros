package Game.GameObjects;

import Game.Animator.Animator;
import Game.CollisionInfo.Collision;
import Game.CollisionInfo.Collisions;
import Game.Common.GlobalVariables;
import ECS.Position;
import javafx.scene.image.Image;

public class Ground extends GameObject {

    private boolean visible;

    public Ground(Position position, String tag, boolean visible) {

        super(position, tag);

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
