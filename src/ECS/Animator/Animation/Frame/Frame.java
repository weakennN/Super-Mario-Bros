package ECS.Animator.Animation.Frame;

public abstract class Frame {

    private double start;

    private double value;

    public Frame(double start) {
        this.start = start;
    }

    public abstract void play();

    public void onEnd() {
    }

    public void onStart() {
    }

    public double getStart() {
        return this.start;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
