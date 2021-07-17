package Game.GameObjects;

import Game.Collision.Collision;

public class Bush extends GameObject{

    public Bush(String tag) {
        super(tag);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
    }
}
