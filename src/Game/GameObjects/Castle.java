package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import javafx.scene.image.Image;

public class Castle extends GameObject {

    public Castle(String tag) {
        super(tag);

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
