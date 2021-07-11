package Game.GameObjects;

import Game.Collision.Collision;
import Game.Collision.Collisions;

public class Pipe extends GameObject {

    public Pipe(String tag) {
        super(tag);
    }

    @Override
    public void update() {

        super.updateComponents();
    }

    @Override
    public void start() {

    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (collision.getHitDirection().y == -1) {
            Collisions.defaultOnGroundCollision(this, other, collision);
        } else if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {
            Collisions.defaultHorizontalCollision(this, other, collision);
        }

    }
}
