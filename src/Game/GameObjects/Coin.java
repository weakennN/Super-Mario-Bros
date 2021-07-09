package Game.GameObjects;

import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import Game.Score.ScoreKeeper;
import javafx.scene.image.Image;

public class Coin extends GameObject {

    public Coin(String tag) {
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

        super.changeImage(Animator.coin);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.marioTag)) {

            ScoreKeeper.coins++;
            this.destroy();
        }

    }

}
