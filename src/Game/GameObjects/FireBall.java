package Game.GameObjects;

import Engine.ECS.Animator.Animator;
import Engine.ECS.Transform;
import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import Engine.ECS.Collider;
import Engine.ECS.Rigidbody;

public class FireBall extends GameObject {

    public FireBall(String tag) {
        super(tag);
    }

    @Override
    public void update() {
        super.updateComponents();
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (other.getTag().equals(GlobalVariables.brickBoxTag) || other.getTag().equals(GlobalVariables.groundTag)
                || other.getTag().equals(GlobalVariables.pipeTag) || other.getTag().equals(GlobalVariables.itemBoxTag) || other.getTag().equals(GlobalVariables.TILE_MAP_TAG)) {
            if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1
                    || collision.getHitDirection().y == -1) {
                super.getComponent(Collider.class).setActive(false);
                super.getComponent(Animator.class).getAnimationController().stop();
                super.getComponent(Rigidbody.class).setActive(false);
                super.getComponent(Animator.class).getAnimationController().playAnimation("fireBallExplosion");

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
