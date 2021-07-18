package Game;

import Engine.ECS.Rigidbody;
import Engine.ECS.Transform;
import Game.GameObjects.GameObject;
import UIEngine.Designer;
import javafx.scene.canvas.GraphicsContext;
import mikera.vectorz.Vector2;

public class Camera {

    private Transform position;
    private Vector2 vel;
    private GameObject target;
    private GraphicsContext gc;

    public Camera(Transform position) {
        this.position = position;
        this.vel = new Vector2(0, 0);
    }

    public void follow() {
        if (this.target.getComponent(Rigidbody.class) == null) {
            return;
        }

        if (this.target.getComponent(Transform.class).getPos().x > this.position.getPos().x) {
            this.position.getPos().x = this.target.getComponent(Transform.class).getPos().x;
            this.vel.x = this.target.getComponent(Rigidbody.class).getVel().x;
            this.gc.translate(-this.vel.x, this.vel.y);
        }
    }

    public Transform getPosition() {
        return this.position;
    }

    public GameObject getTarget() {
        return this.target;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void resetCamera() {
        this.gc.translate(this.position.getPos().x - 960, 0);
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
}
