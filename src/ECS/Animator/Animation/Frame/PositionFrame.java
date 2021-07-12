package ECS.Animator.Animation.Frame;

import ECS.Transform;
import mikera.vectorz.Vector2;

public class PositionFrame extends Frame {

    private Vector2 startPos;
    private Vector2 endPos;
    private Transform target;

    private double xValue;
    private double yValue;

    public PositionFrame(double start, double end, Vector2 startPos, Vector2 endPos, Transform transform) {
        super(start, end);

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
        double value = super.getEnd() - super.getStart();

        this.xValue = (this.endPos.x - this.startPos.x) / value;
        this.yValue = (this.endPos.y - this.startPos.y) / value;
    }
}
