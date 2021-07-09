package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import javafx.scene.image.Image;

public class Cloud extends GameObject {

    public Cloud(String tag) {

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

        super.changeImage(Animator.cloud);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }
}
