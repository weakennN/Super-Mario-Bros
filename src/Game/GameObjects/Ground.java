package Game.GameObjects;

import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;

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
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (collision.getHitDirection().y == -1) {
            Collisions.defaultOnGroundCollision(this, other, collision);
        } else if (other.getTag().equals(GlobalVariables.marioTag)
                && (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1)) {
            Collisions.defaultHorizontalCollision(this, other, collision);
        }
    }
}
