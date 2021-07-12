package ECS.Animator.Animation.Frame;

import ECS.Transform;
import mikera.vectorz.Vector2;

public class ScaleFrame extends Frame {

    private Vector2 scale;
    private Transform target;

    private double xValue;
    private double yValue;

    public ScaleFrame(double start, double end, Vector2 scale, Transform transform) {
        super(start, end);
        this.scale = scale;
        this.target = transform;
    }

    @Override
    public void play() {
        this.target.getScale().x += this.xValue;
        this.target.getScale().y += this.yValue;
    }

    @Override
    public void onStart(){
        double value = super.getEnd() - super.getStart();

        this.xValue = (this.scale.x - this.target.getScale().x) / value;
        this.yValue = (this.scale.y - this.target.getScale().y) / value;
    }

    @Override
    public void onEnd() {
       //this.target.setScale(this.scale);
    }
}
