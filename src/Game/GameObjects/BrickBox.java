package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import ECS.Rigidbody;
import Game.GameObjects.Mario.Mario;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import javafx.scene.image.Image;

public class BrickBox extends GameObject {

    public BrickBox(String tag) {

        super(tag);
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

        super.changeImage(Animator.brickBox);
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

                    SoundManager.playSound(Sounds.bumpSound);
                }

            }

        } else if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {

            Collisions.defaultHorizontalCollision(this, other, collision);
        }
    }
}
