package Game.GameObjects;

import ECS.Transform;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import javafx.scene.image.Image;

public class InvisibleWall extends GameObject {

    private Transform cameraPosition;

    public InvisibleWall(String tag, Transform cameraPosition) {
        super(tag);

        this.cameraPosition = cameraPosition;
    }

    @Override
    public void update() {

        super.getComponent(Transform.class).getPos().x = this.cameraPosition.getPos().x - 1010;
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

        if (other.getTag().equals(GlobalVariables.marioTag) &&
                (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1)) {

            Collisions.defaultHorizontalCollision(this, other, collision);
        }

    }
}
