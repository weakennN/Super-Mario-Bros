package Game.GameObjects;

import ECS.Position;
import ECS.Rigidbody;
import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import javafx.scene.image.Image;

public class Block extends GameObject {

    public Block(Position position, String tag) {
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

        super.changeImage(Animator.block);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        Rigidbody rigidbody = (Rigidbody) other.getComponent(GlobalVariables.rigidbodyTag);

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
