package Rigidbody;

import GameObjects.GameObject;
import mikera.vectorz.Vector2;

import java.util.ArrayList;
import java.util.List;

public class RigidBody {

    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;
    private boolean applyGravity;

   // private Collider collider;
    private GameObject gameObject;
    //make sure to always apply gravity
    public static List<RigidBody> rigidBodies = new ArrayList<>();

    public RigidBody(Position position) {

        this.pos = position.getPos();
        this.vel = new Vector2();
        this.acc = new Vector2();
        rigidBodies.add(this);
        this.applyGravity = false;
    }

    public void physicsUpdate() {

        if (this.applyGravity) {

            this.applyGravity();
        }

        // this.applyDragForce();
        this.vel.add(this.acc);
        this.pos.add(this.vel);

        for (int i = 0; i < rigidBodies.size(); i++) {

            RigidBody currentRB = rigidBodies.get(i);

            if (currentRB == this) {

                continue;
            }

          /* if (currentRB.collider.checkCollision(this.collider)) {

                currentRB.collider.onCollision(this.gameObject, currentRB.getGameObject());
            }

           */
        }
    }

    public Vector2 getPos() {

        return this.pos;
    }

    public Vector2 getVel() {

        return this.vel;
    }

    /*public void setCollider(Collider collider) {

      this.collider = collider;
    }

     */

    public GameObject getGameObject() {

        return this.gameObject;
    }

    public void setGameObject(GameObject gameObject) {

        this.gameObject = gameObject;
    }

    public void setGravity(boolean b) {

        this.applyGravity = b;
    }

    private void applyForce(Vector2 force) {

        this.acc.add(force);
    }

    public Vector2 getAcc() {

        return this.acc;
    }

    public void applyDragForce() {

        Vector2 drag = new Vector2(this.vel.x, this.vel.y);
        drag.normalise();
        double c = -0.0001;
        double speed = this.vel.magnitude();

        drag.multiply(c * speed * speed);

        this.applyForce(drag);
    }

    public void applyGravity() {

        this.applyForce(new Vector2(0, 0.001));
    }

}
