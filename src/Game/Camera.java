package Game;

import ECS.Rigidbody;
import ECS.Transform;
import Game.GameObjects.GameObject;
import UIEngine.Designer;
import mikera.vectorz.Vector2;

public class Camera {

    private Transform position;
    private Vector2 vel;
    private GameObject target;

    public Camera(Transform position) {
        this.position = position;
        this.vel = new Vector2(0, 0);
        Designer.gc.translate(0, 0);
    }

    public void follow() {
        if (this.target.getComponent(Rigidbody.class) == null) {
            return;
        }

        if (this.target.getComponent(Transform.class).getPos().x > this.position.getPos().x) {
            this.position.getPos().x = this.target.getComponent(Transform.class).getPos().x;
            this.vel.x = this.target.getComponent(Rigidbody.class).getVel().x;
            Designer.gc.translate(-this.vel.x, this.vel.y);
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
        Designer.gc.translate(this.position.getPos().x - 960, 0);
    }
}
