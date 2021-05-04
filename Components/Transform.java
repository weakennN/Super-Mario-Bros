package Components;

import mikera.vectorz.Vector2;

public class Transform {

    private Vector2 pos;

    protected Transform(Vector2 pos) {

        this.pos = pos;
    }

    public Vector2 getPos() {

        return this.pos;
    }
}
