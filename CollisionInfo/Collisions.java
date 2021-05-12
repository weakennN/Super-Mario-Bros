package CollisionInfo;

import Common.GlobalVariables;
import Components.Collider;
import Components.Component;
import Components.Rigidbody;
import GameObjects.GameObject;
import GameObjects.Mario;

public class Collisions {

    public static void defaultOnGroundCollision(GameObject first, GameObject second, Collision collision) {

        Component component = second.getComponent(GlobalVariables.rigidbodyTag);

        if (component == null) {

            return;
        }

        Rigidbody rigidbody = (Rigidbody) component;

        if (collision.getHitDirection().y == -1) {

            Collider collider = (Collider) second.getComponent(GlobalVariables.colliderTag);
            second.getPosition().getPos().y = first.getPosition().getPos().y - collider.getSize().y;
            rigidbody.getAcc().y = 0;
            rigidbody.getVel().y = 0;

            if (second.getTag().equals(GlobalVariables.marioTag)) {

                Mario mario = (Mario) second;
                mario.setJumping(false);
                mario.setOnGround(true);
                mario.getMarioManager().setMarioAnimationAfterJump();
            }
        }


    }

    public static void defaultGoombaAndKoopaCollision(GameObject first, GameObject second, Collision collision) {

        Rigidbody rigidbody = (Rigidbody) first.getComponent(GlobalVariables.rigidbodyTag);
        Collider collider = (Collider) second.getComponent(GlobalVariables.colliderTag);

     /*   if (collision.getHitDirection().x == 1) {

            first.getPosition().getPos().x = second.getPosition().getPos().x - collider.getSize().x;
            rigidbody.getVel().x *= -1;

        } else if (collision.getHitDirection().x == -1) {

            first.getPosition().getPos().x = second.getPosition().getPos().x + collider.getSize().x;
            rigidbody.getVel().x *= -1;

        }

      */

    }

    public static void defaultHorizontalCollision(GameObject first, GameObject second, Collision collision) {

        // maybe change that so it gets the first's collider
        Component component = first.getComponent(GlobalVariables.colliderTag);

        if (component == null) {

            return;
        }

        Collider collider = (Collider) component;

        if (collision.getHitDirection().x == 1) {

            second.getPosition().getPos().x = first.getPosition().getPos().x - collider.getSize().x;

            checkFromEnemy(second);

        } else if (collision.getHitDirection().x == -1) {

            second.getPosition().getPos().x = first.getPosition().getPos().x + collider.getSize().x;

            checkFromEnemy(second);
        }

    }

    private static void checkFromEnemy(GameObject gameObject) {

        if (gameObject.getTag().equals(GlobalVariables.goombaTag)) {

            Rigidbody rigidbody = (Rigidbody) gameObject.getComponent(GlobalVariables.rigidbodyTag);
            rigidbody.getVel().x *= -1;
        }

    }

}
