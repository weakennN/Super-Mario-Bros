package Components;

import CollisionInfo.Collision;
import Designer.Designer;
import GameObjects.GameObject;
import mikera.vectorz.Vector2;
import Rigidbody.Position;

import java.util.ArrayList;
import java.util.List;

public class Collider extends Component {

    private Vector2 pos;
    private Vector2 size;
    private Vector2 center;
    private Vector2 halfSize;
    private Collision collision;

    public static List<Collider> colliders = new ArrayList<>();

    public Collider(String tag, Position position, double sizeX,
                    double sizeY, GameObject gameObject) {
        super(tag, gameObject);

        this.pos = position.getPos();
        this.size = new Vector2(sizeX, sizeY);
        this.halfSize = new Vector2(sizeX / 2, sizeY / 2);
        this.collision = new Collision();
      //  Designer.gc.strokeRect(this.pos.x, this.pos.y, this.size.x, this.size.y);
        this.calculateCenter();
        colliders.add(this);
    }

    @Override
    public void update() {

        for (Collider c : colliders) {

            if (c == this || c == null) {

                continue;
            }

            if (this.checkCollision(c)) {

                super.getGameObject().onCollisionEnter(c.getGameObject(), this.collision);
            }
        }

    }

    public boolean checkCollision(Collider collider) {

        this.calculateCenter();
        collider.calculateCenter();

       /* if (this.getGameObject().getTag().equals(collider.getGameObject().getTag())) {

            return false;
        }

        */

        double dx = this.center.x - collider.getCenter().x;
        double dy = this.center.y - collider.getCenter().y;

        double combinedHalfWidths = this.halfSize.x + collider.getHalfSize().x;
        double combinedHalfHeights = this.halfSize.y + collider.getHalfSize().y;

        if (Math.abs(dx) <= combinedHalfWidths && Math.abs(dy) <= combinedHalfHeights) {

            int overLapX = (int) (combinedHalfWidths - Math.abs(dx));
            int overLapY = (int) (combinedHalfHeights - Math.abs(dy));

            if (overLapX == overLapY && overLapX == 0) return false;

            if (overLapX >= overLapY) {

                if (dy > 0) {

                    this.collision.setHitDirection(new Vector2(0, -1));

                } else if (dy < 0) {

                    this.collision.setHitDirection(new Vector2(0, 1));

                }

            } else {

                if (dx < 0) {

                    this.collision.setHitDirection(new Vector2(-1, 0));

                } else if (dx > 0) {

                    this.collision.setHitDirection(new Vector2(1, 0));

                }

            }

            return true;
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

    public Vector2 getSize() {

        return this.size;
    }

}
