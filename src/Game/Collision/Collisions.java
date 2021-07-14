package Game.Collision;

import ECS.Transform;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mario.Mario;

public class Collisions {

    public static void defaultOnGroundCollision(GameObject first, GameObject second, Collision collision) {

        Rigidbody rigidbody = second.getComponent(Rigidbody.class);

        if (rigidbody == null) {
            return;
        }

        if (collision.getHitDirection().y == -1) {
            Collider collider = second.getComponent(Collider.class);
            second.getComponent(Transform.class).getPos().y = first.getComponent(Transform.class).getPos().y - collider.getSize().y;
            rigidbody.getAcc().y = 0;
            rigidbody.getVel().y = 0;

            if (second.getTag().equals(GlobalVariables.marioTag)) {
                Mario mario = (Mario) second;
                mario.getMarioManager().setMarioAnimationAfterJump();
            }
        }
    }

    public static void defaultHorizontalCollision(GameObject first, GameObject second, Collision collision) {

        Collider collider = second.getComponent(Collider.class);

        if (collider == null) {
            return;
        }

        if (collision.getHitDirection().x == 1) {
            second.getComponent(Transform.class).getPos().x = first.getComponent(Transform.class).getPos().x - collider.getSize().x;
            checkFromEnemy(second);
        } else if (collision.getHitDirection().x == -1) {
            Collider collider1 = first.getComponent(Collider.class);
            second.getComponent(Transform.class).getPos().x = first.getComponent(Transform.class).getPos().x + collider1.getSize().x;
            checkFromEnemy(second);
        }

    }

    private static void checkFromEnemy(GameObject gameObject) {

        if (gameObject.getTag().equals(GlobalVariables.goombaTag)
                || gameObject.getTag().equals(GlobalVariables.mushroomTag) ||
                gameObject.getTag().equals(GlobalVariables.koopaTag)) {

            if (gameObject.getTag().equals(GlobalVariables.koopaTag)){
                gameObject.getComponent(Transform.class).getScale().x *= -1;
            }

            Rigidbody rigidbody = gameObject.getComponent(Rigidbody.class);
            rigidbody.getVel().x *= -1;
        }
    }
}
