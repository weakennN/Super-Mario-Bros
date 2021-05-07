package GameObjects.AI;

import CollisionInfo.Collision;
import Common.GlobalVariables;
import GameObjects.GameObject;
import Rigidbody.Position;
import javafx.scene.image.Image;

public class Koopa extends GameObject {

    public Koopa(Position position, String tag) {
        super(position, tag);
    }

    @Override
    public void update() {

        super.updateComponents();
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

        if (other.getTag().equals(GlobalVariables.marioTag) && collision.getHitDirection().y == -1) {


        } else if (other.getTag().equals(GlobalVariables.brickBoxTag)) {

            if (collision.getHitDirection().x == 1) {

            } else if (collision.getHitDirection().x == -1) {


            }
        }
    }
}
