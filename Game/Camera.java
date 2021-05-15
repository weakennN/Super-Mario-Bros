package Game;

import Game.GameObjects.Mario.Mario;
import ECS.Position;
import UIEngine.Designer;
import mikera.vectorz.Vector2;

public class Camera {

    private Position position;
    private Vector2 vel;
    private Mario mario;

    public Camera(Mario mario, Position position) {

        this.position = position;
        this.vel = new Vector2(0, 0);
        this.mario = mario;
        Designer.gc.translate(0, 0);

    }

    public void follow() {

        if (this.mario.getPosition().getPos().x > this.position.getPos().x) {

            this.position.getPos().x = this.mario.getPosition().getPos().x;

            this.vel.x = this.mario.getRigidbody().getVel().x;

            Designer.gc.translate(-this.vel.x, this.vel.y);
        }

    }

    public void setMario(Mario mario) {

        this.mario = mario;
    }

    public Position getPosition() {

        return this.position;
    }

    public void resetCamera() {

        Designer.gc.translate(this.position.getPos().x - 960, 0);
    }

}
