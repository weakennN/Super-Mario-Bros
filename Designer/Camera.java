package Designer;

import GameObjects.Mario;
import mikera.vectorz.Vector2;

public class Camera {

    private Vector2 pos;
    private Vector2 vel;
    private Mario mario;

    public Camera(Mario mario) {

        this.pos = new Vector2(960, 0);
        this.vel = new Vector2(-2.5, 0);
        this.mario = mario;
        Designer.gc.translate(0, 0);

    }

    public void follow() {

        if (this.mario.getPosition().getPos().x > this.pos.x) {

            this.pos.x = this.mario.getPosition().getPos().x;

            Designer.gc.translate(this.vel.x, this.vel.y);
        }

    }

    public void setMario(Mario mario) {

        this.mario = mario;
    }

}
