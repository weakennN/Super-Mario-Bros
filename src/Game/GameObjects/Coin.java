package Game.GameObjects;

import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import Game.Score.ScoreKeeper;

public class Coin extends GameObject {

    public Coin(String tag) {
        super(tag);
    }

    @Override
    public void update() {
        super.updateComponents();
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (other.getTag().equals(GlobalVariables.marioTag)) {
            ScoreKeeper.coins++;
            this.destroy();
        }
    }
}
