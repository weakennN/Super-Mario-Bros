package Engine.ECS;

import Game.GameObjects.GameObject;
import mikera.vectorz.Vector2;

public class Transform extends Component {

    private Vector2 pos;
    private Vector2 scale;

    public Transform(Vector2 pos, GameObject gameObject) {
        super(gameObject);

        this.pos = pos;
        this.scale = new Vector2(1, 1);
    }

    public Transform(GameObject gameObject) {
        this(new Vector2(0, 0), gameObject);
    }

    public Transform(Vector2 pos) {
        this(pos, null);
    }

    public Vector2 getPos() {
        return this.pos;
    }

    public Vector2 getScale() {
        return this.scale;
    }

    public void setScale(Vector2 scale) {
        Collider collider = super.getGameObject().getComponent(Collider.class);
        collider.resize(collider.getSize().x * Math.abs(scale.x) / this.scale.x, collider.getSize().y * Math.abs(scale.y) / this.scale.y);
        this.scale = scale;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }
}
