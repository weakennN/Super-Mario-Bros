package Rigidbody;

import mikera.vectorz.Vector2;

public class Position {

    private Vector2 pos;

    public Position(double x, double y) {

        this.pos = new Vector2(x, y);
    }

    public Vector2 getPos() {

        return this.pos;
    }
}
