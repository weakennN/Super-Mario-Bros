package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Common.GlobalVariables;
import GameEngine.GameEngine;
import Rigidbody.Position;
import Score.ScoreKeeper;
import javafx.scene.image.Image;

public class Coin extends GameObject {

    public Coin(Position position, String tag) {
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

        super.changeImage(Animator.coin);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.marioTag)) {

            ScoreKeeper.coins++;
            //remove components
            this.destroy();
        }
    }
}
