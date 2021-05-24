package ECS;

import Game.GameObjects.GameObject;
import mikera.vectorz.Vector2;

public class Rigidbody extends Component {

    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;
    private Vector2 gravity;
    private boolean applyGravity;

    public Rigidbody(String tag, Position position, GameObject gameObject) {
        super(tag, gameObject);

        this.pos = position.getPos();
        this.vel = new Vector2();
        this.acc = new Vector2();
        this.gravity = new Vector2(0, 0.001);
        this.applyGravity = true;

    }

    @Override
    public void update() {

        this.physicsUpdate();
    }

    public Vector2 getVel() {

        return vel;
    }

    public Vector2 getAcc() {

        return acc;
    }

    private void physicsUpdate() {

        this.applyForce(this.gravity);
        this.vel.add(this.acc);
        this.pos.add(this.vel);
    }

    private void applyForce(Vector2 force) {

        this.acc.add(force);
    }

    public Vector2 getPos() {

        return this.pos;
    }

}
