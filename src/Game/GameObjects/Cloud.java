package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import ECS.Position;
import javafx.scene.image.Image;

public class Cloud extends GameObject {

    public Cloud(Position position, String tag) {

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

        super.changeImage(Animator.cloud);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }
}
