package Designer;

import GameObjects.Mario;
import Rigidbody.Position;
import mikera.vectorz.Vector2;

public class Camera {

    private Position position;
    private Vector2 vel;
    private Mario mario;

    public Camera(Mario mario, Position position) {

        this.position = position;
        this.vel = new Vector2(-2.5, 0);
        this.mario = mario;
        Designer.gc.translate(0, 0);

    }

    // TODO: add invincible wall int the left corner of the screen

    public void follow() {

        if (this.mario.getPosition().getPos().x > this.position.getPos().x) {

            this.position.getPos().x = this.mario.getPosition().getPos().x;

            Designer.gc.translate(this.vel.x, this.vel.y);
        }

    }

    public void setMario(Mario mario) {

        this.mario = mario;
    }

    public Position getPosition() {

        return this.position;
    }

}
