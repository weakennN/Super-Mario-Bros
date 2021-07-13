package ECS.Animator.Animation.Frame;

import ECS.Transform;
import mikera.vectorz.Vector2;

public class PositionFrame extends Frame {

    private Vector2 startPos;
    private Vector2 endPos;
    private Transform target;

    private double xValue;
    private double yValue;

    public PositionFrame(double start, Vector2 startPos, Vector2 endPos, Transform transform) {
        super(start);

        this.startPos = startPos;
        this.endPos = endPos;
        this.target = transform;
    }

    @Override
    public void play() {
        this.target.getPos().x += this.xValue;
        this.target.getPos().y += this.yValue;
    }

    @Override
    public void onStart() {
        this.xValue = (this.endPos.x - this.startPos.x) / super.getValue();
        this.yValue = (this.endPos.y - this.startPos.y) / super.getValue();
    }
}
