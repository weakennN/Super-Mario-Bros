package Game.GameObjects;

import ECS.Rigidbody;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;

public class Block extends GameObject {

    public Block(String tag) {
        super(tag);
    }

    @Override
    public void update() {
        super.updateComponents();
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        Rigidbody rigidbody = other.getComponent(Rigidbody.class);

        if (rigidbody == null) {
            return;
        }

        if (collision.getHitDirection().y == -1) {
            Collisions.defaultOnGroundCollision(this, other, collision);
        } else if (collision.getHitDirection().y == 1) {
            rigidbody.getVel().y = 1;
            SoundManager.playSound(Sounds.bumpSound);
        } else if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {
            Collisions.defaultHorizontalCollision(this, other, collision);
        }

    }

}
