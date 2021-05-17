package Game.GameObjects;

import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import ECS.Position;
import javafx.scene.image.Image;

public class InvisibleWall extends GameObject {

    private Position cameraPosition;

    public InvisibleWall(Position position, String tag, Position cameraPosition) {
        super(position, tag);

        this.cameraPosition = cameraPosition;
    }

    @Override
    public void update() {

        super.getPosition().getPos().x = this.cameraPosition.getPos().x - 1010;
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
