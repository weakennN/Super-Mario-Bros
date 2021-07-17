package Game.GameObjects;

import Game.Collision.Collision;

public class Mountain extends GameObject{

    public Mountain(String tag) {
        super(tag);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }
}
