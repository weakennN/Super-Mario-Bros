package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Flower extends GameObject {

    public Flower(Position position, String tag) {
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

        super.changeImage(Animator.flower);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        // TODO: make Mario able to throw explosives

        if (other.getTag().equals(GlobalVariables.marioTag)) {

            Mario mario = (Mario) other;
            mario.setFireMario(true);
        }
    }
}