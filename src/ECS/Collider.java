package ECS;

import Game.Collision.Collision;
import Game.GameObjects.GameObject;
import mikera.vectorz.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Collider extends Component {

    private Vector2 pos;
    private Vector2 size;
    private Vector2 center;
    private Vector2 halfSize;
    private boolean checked;
    private Collision collision;
    public static List<Collider> colliders = new ArrayList<>();

    public Collider(GameObject gameObject, double sizeX, double sizeY, Transform transform) {

        super(gameObject);
        this.pos = transform.getPos();
        this.size = new Vector2(sizeX, sizeY);
        this.halfSize = new Vector2(sizeX / 2, sizeY / 2);
        this.calculateCenter();
        this.checked = false;
        this.collision = new Collision();
        colliders.add(this);
    }

    public boolean checkCollision(Collider collider) {

        this.calculateCenter();
        collider.calculateCenter();

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

    public static void removeCollider(Collider collider) {
        colliders.remove(collider);
    }

    private void calculateCenter() {

        this.center = new Vector2(this.pos.x + this.halfSize.x, this.pos.y + this.halfSize.y);
    }

    public Vector2 getCenter() {

        return this.center;
    }

    public Vector2 getHalfSize() {

        return this.halfSize;
    }

    public Vector2 getSize() {

        return this.size;
    }

    public Vector2 getPos() {

        return this.pos;
    }

    public void resize(double sizeX, double sizeY) {

        this.halfSize = new Vector2(sizeX / 2, sizeY / 2);
        this.size.x = sizeX;
        this.size.y = sizeY;
        this.calculateCenter();
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean b) {
        this.checked = b;
    }

    public Collision getCollision() {
        return this.collision;
    }
}
