package Components;

import CollisionInfo.Collision;
import Designer.Designer;
import GameObjects.GameObject;
import mikera.vectorz.Vector2;
import Rigidbody.Position;

import java.util.ArrayList;
import java.util.List;

public class Collider extends Component {

    private GameObject gameObject;
    private Vector2 pos;
    private Vector2 size;
    private Vector2 center;
    private Vector2 halfSize;
    private Collision collision;

    public static List<Collider> colliders = new ArrayList<>();

    public Collider(String tag, Position position, double sizeX,
                    double sizeY, GameObject gameObject) {
        super(tag);

        this.gameObject = gameObject;
        this.pos = position.getPos();
        this.size = new Vector2(sizeX, sizeY);
        this.halfSize = new Vector2(sizeX / 2, sizeY / 2);
        this.collision = new Collision();
        this.calculateCenter();
        colliders.add(this);
    }

    //TODO: make a resize method that changes the size the center and the half size of a collider
    @Override
    public void update() {

        for (Collider c : colliders) {

            if (c == this) {

                continue;
            }

            if (this.checkCollision(c)) {

                this.gameObject.onCollisionEnter(c.gameObject, this.collision);
            }
        }

    }

    public boolean checkCollision(Collider collider) {

        this.calculateCenter();
        collider.calculateCenter();
        this.collision.setHitDirection(new Vector2(1, 0));

        double dx = this.center.x - collider.getCenter().x;
        double dy = this.center.y - collider.getCenter().y;

        double combinedHalfWidths = this.halfSize.x + collider.getHalfSize().x;
        double combinedHalfHeights = this.halfSize.y + collider.getHalfSize().y;

        if (Math.abs(dx) <= combinedHalfWidths) {

            if (Math.abs(dy) <= combinedHalfHeights) {

                if (dy > 0) {

                    if (dy > Math.abs(dx)) {

                        this.collision.setHitDirection(new Vector2(0, 1));
                    } else {

                        this.collision.setHitDirection(new Vector2(1, 0));
                    }

                } else if (dy < 0) {

                    if (-dy > Math.abs(dx)) {

                        this.collision.setHitDirection(new Vector2(0, -1));
                    } else {

                        this.collision.setHitDirection(new Vector2(-1, 0));
                    }
                }

                return true;
            }
        }

        return false;
    }

    protected Vector2 getCenter() {

        return this.center;
    }

    protected Vector2 getHalfSize() {

        return this.halfSize;
    }

    protected void calculateCenter() {

        this.center = new Vector2(this.pos.x + this.halfSize.x, this.pos.y + this.halfSize.y);
    }

    public static void removeCollider(Collider collider) {

        colliders.remove(collider);
    }

    public void resize(double sizeX, double sizeY) {

        this.size = new Vector2(sizeX, sizeY);
        this.halfSize = new Vector2(sizeX / 2, sizeY / 2);
        this.calculateCenter();
    }

    public Vector2 getSize(){

        return this.size;
    }


  /*  public GameObject getGameObject() {

        return this.gameObject;
    }

   */
}
