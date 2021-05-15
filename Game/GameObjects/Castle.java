package Game.GameObjects;

import Game.Animator.Animator;
import Game.CollisionInfo.Collision;
import ECS.Position;
import javafx.scene.image.Image;

public class Castle extends GameObject {

    public Castle(Position position, String tag) {
        super(position, tag);

    }

    @Override
    public void update() {

    }

    @Override
    public Image render() {

        return super.getCurrentAnimation();
    }

    @Override
    public void start() {

        super.changeImage(Animator.castle);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }
}
