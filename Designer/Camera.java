package Designer;

import GameObjects.Mario;
import javafx.scene.PerspectiveCamera;
import mikera.vectorz.Vector2;

public class Camera {

    private double sceneWidth;
    private Vector2 pos;
    private Vector2 vel;
    private Mario mario;
    private PerspectiveCamera camera;

    public Camera(Mario mario) {

        this.pos = new Vector2(0, 0);
        this.vel = new Vector2(-5, 0);
        this.mario = mario;
        this.sceneWidth = Designer.scene.getWidth();
        Designer.gc.translate(this.pos.x, this.pos.y);

    }

    public void follow() {

        if (this.mario.getRigidbody() != null) {

            if (this.mario.getRigidbody().getPos().x >= sceneWidth / 2) {

                this.sceneWidth += 150;

                this.pos.add(this.vel);
                Designer.gc.translate(this.pos.x, this.pos.y);
            }
        }

    }

    public void setMario(Mario mario) {

        this.mario = mario;
    }

}
