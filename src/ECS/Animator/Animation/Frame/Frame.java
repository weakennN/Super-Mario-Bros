package ECS.Animator.Animation.Frame;

public abstract class Frame {

    private double start;
    private double end;

    public Frame(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public abstract void play();

    public void onEnd() {
    }

    public void onStart() {
    }

    public double getStart() {
        return this.start;
    }

    public double getEnd() {
        return this.end;
    }
}
