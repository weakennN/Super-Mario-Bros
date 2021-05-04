package GameObjects;

import CollisionInfo.Collision;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class ItemBox extends GameObject {

    public ItemBox(Position position, String tag) {
        super(position, tag);


    }

    @Override
    public void update() {


    }

    @Override
    public Image render() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }
}
