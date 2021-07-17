package Game.GameObjects;

import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import Game.GameObjects.Mario.Mario;

public class DeathWall extends GameObject {

    public DeathWall(String tag) {
        super(tag);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.marioTag)) {
            Mario mario = (Mario) other;
            mario.setDead(true);
        }
    }
}
