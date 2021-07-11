package ECS;

import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import mikera.vectorz.Vector2;

public class Rigidbody extends Component {

    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;
    private boolean applyGravity = true;
    private int time = 0;

    public Rigidbody(GameObject gameObject) {

        super(gameObject);

        this.pos = gameObject.getComponent(Transform.class).getPos();
        this.vel = new Vector2();
        this.acc = new Vector2();
    }

    public Rigidbody(GameObject gameObject, Transform transform) {

        super(gameObject);

        this.pos = transform.getPos();
        this.vel = new Vector2();
        this.acc = new Vector2();
    }

    public Rigidbody(GameObject gameObject, boolean applyGravity) {

        this(gameObject);
        this.applyGravity = applyGravity;
    }

    @Override
    public void update() {

        this.physicsUpdate();
    }

    private void physicsUpdate() {

        if (this.applyGravity) {
            this.applyForce(new Vector2(0, 0.00125));
        }

        this.vel.add(this.acc);
        this.pos.add(this.vel);

    }

    private void applyForce(Vector2 force) {
        this.acc.add(force);
    }

    public Vector2 getVel() {
        return this.vel;
    }

    public Vector2 getAcc() {
        return this.acc;
    }

    public Vector2 getPos() {
        return this.pos;
    }
}
