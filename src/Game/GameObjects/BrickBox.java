package Game.GameObjects;

import ECS.Animator.Animator;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import ECS.Rigidbody;
import Game.GameObjects.Mario.Mario;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;

public class BrickBox extends GameObject {

    public BrickBox(String tag) {
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
            if (other.getTag().equals(GlobalVariables.marioTag)) {
                Mario mario = (Mario) other;
                if (mario.isBreakable()) {
                    SoundManager.playSound(Sounds.blockDestructionSound);
                    this.destroy();
                } else {
                    super.getComponent(Animator.class).getAnimationController().stop();
                    super.getComponent(Animator.class).getAnimationController().playAnimation("bump");
                    SoundManager.playSound(Sounds.bumpSound);
                }

            }

        } else if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {
            Collisions.defaultHorizontalCollision(this, other, collision);
        }
    }
}
