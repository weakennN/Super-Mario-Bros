package Game.GameObjects;

import ECS.Transform;
import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;

public class Explosive extends GameObject {

    public Explosive(String tag) {
        super(tag);
    }

    @Override
    public void update() {
        super.updateComponents();
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (other.getTag().equals(GlobalVariables.brickBoxTag) || other.getTag().equals(GlobalVariables.groundTag)
                || other.getTag().equals(GlobalVariables.pipeTag) || other.getTag().equals(GlobalVariables.itemBoxTag)) {
            if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1
                    || collision.getHitDirection().y == -1) {
                this.destroy();
            } else {
                Collider collider = other.getComponent(Collider.class);
                super.getComponent(Transform.class).getPos().y = super.getComponent(Transform.class).getPos().y - collider.getSize().y;
                Rigidbody rigidbody = super.getComponent(Rigidbody.class);

                if (rigidbody.getVel().x >= 0) {
                    rigidbody.getVel().x = 3.5;
                } else {
                    rigidbody.getVel().x = -3.5;
                }
                rigidbody.getVel().y = -1.5;
            }

        } else if (other.getTag().equals(GlobalVariables.goombaTag) || other.getTag().equals(GlobalVariables.koopaTag)) {
            other.destroy();
        }
    }
}
