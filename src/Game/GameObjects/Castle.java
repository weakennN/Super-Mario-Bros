package Game.GameObjects;

import Game.Collision.Collision;

public class Castle extends GameObject {

    public Castle(String tag) {
        super(tag);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
    }
}
