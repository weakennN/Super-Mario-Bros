package Designer;

import GameObjects.Mario;
import mikera.vectorz.Vector2;

public class Camera {

    private double sceneWidth;
    private Vector2 pos;
    private Mario mario;

    public Camera(Mario mario) {

        this.pos = new Vector2(0, 0);
        this.mario = mario;
        this.sceneWidth = Designer.scene.getWidth();
        Designer.gc.translate(this.pos.x, this.pos.y);
    }

    public void follow() {

        if (this.mario.getRigidbody().getPos().x >= sceneWidth / 2) {
            System.out.println(this.sceneWidth);
            this.sceneWidth += 150;
            this.pos.x -= 5;
            Designer.gc.translate(this.pos.x, this.pos.y);
        }

    }
}
