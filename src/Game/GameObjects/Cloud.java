package Game.GameObjects;

import Game.Collision.Collision;

public class Cloud extends GameObject {

    public Cloud(String tag) {
        super(tag);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
    }
}
