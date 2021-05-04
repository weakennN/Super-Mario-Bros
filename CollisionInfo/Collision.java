package CollisionInfo;

import GameObjects.GameObject;
import mikera.vectorz.Vector2;

public class Collision {

    private GameObject hit;
    private Vector2 hitDirection;


    public Vector2 getHitDirection() {

        return this.hitDirection;
    }

    public void setHitDirection(Vector2 hitDirection) {

        this.hitDirection = hitDirection;
    }

}
