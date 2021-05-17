package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import Game.GameObjects.Mario.Mario;
import ECS.Position;
import javafx.scene.image.Image;

public class Flower extends GameObject {

    public Flower(Position position, String tag) {
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

        super.changeImage(Animator.flower);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.marioTag)) {

            Mario mario = (Mario) other;
            mario.setFireMario(true);
            this.destroy();
        }

    }

}
