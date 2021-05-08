package CollisionInfo;

import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import GameObjects.GameObject;
import GameObjects.Mario;

public class Collisions {

    public static void defaultOnGroundCollision(GameObject first, GameObject second, Collision collision) {

        Rigidbody rigidbody = (Rigidbody) second.getComponent(GlobalVariables.rigidbodyTag);

        if (rigidbody != null) {

            if (collision.getHitDirection().y == -1) {

                Collider collider = (Collider) second.getComponent(GlobalVariables.colliderTag);
                second.getPosition().getPos().y = first.getPosition().getPos().y - collider.getSize().y;
                rigidbody.getAcc().y = 0;
                rigidbody.getVel().y = 0;
                // TODO: make this a method
                if (second.getTag().equals(GlobalVariables.marioTag)) {

                    Mario mario = (Mario) second;
                    mario.setJumping(false);
                    mario.setOnGround(true);
                    mario.getMarioManager().setMarioAnimationAfterJump();
                }
            }

        }
    }

    public static void defaultGoombaAndKoopaCollision(GameObject first, GameObject second, Collision collision) {

        Rigidbody rigidbody = (Rigidbody) first.getComponent(GlobalVariables.rigidbodyTag);
        Collider collider = (Collider) second.getComponent(GlobalVariables.colliderTag);

        if (collision.getHitDirection().x == 1) {

            first.getPosition().getPos().x += collider.getSize().x;
            rigidbody.getVel().x = 2;

        } else if (collision.getHitDirection().x == -1) {

            first.getPosition().getPos().x -= collider.getSize().x;
            rigidbody.getVel().x = -2;

        }

    }
}
