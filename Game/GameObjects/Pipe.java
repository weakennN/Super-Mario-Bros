package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import ECS.Position;
import javafx.scene.image.Image;

public class Pipe extends GameObject {

    public Pipe(Position position, String tag) {
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

        super.changeImage(Animator.pipe);
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
