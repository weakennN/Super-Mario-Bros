package ECS.Animator.Animation.Frame;

import ECS.Transform;
import mikera.vectorz.Vector2;

public class ScaleFrame extends Frame {

    private Vector2 scale;
    private Transform target;

    private double xValue;
    private double yValue;

    public ScaleFrame(double start,Vector2 scale, Transform transform) {
        super(start);
        this.scale = scale;
        this.target = transform;
    }

    @Override
    public void play() {
        this.target.setScale(new Vector2(this.target.getScale().x + this.xValue, this.target.getScale().y + this.yValue));
    }

    @Override
    public void onStart() {
        this.xValue = (this.scale.x - this.target.getScale().x) / super.getValue();
        this.yValue = (this.scale.y - this.target.getScale().y) / super.getValue();
    }
}
