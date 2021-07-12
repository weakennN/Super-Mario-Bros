package ECS.Animator.Animation;

import ECS.Animator.Animation.Frame.Frame;
import Game.GameObjects.GameObject;
import javafx.animation.AnimationTimer;

import java.util.List;

public class FrameAnimation extends Animation {

    private List<Frame> frames;
    private double time;

    public FrameAnimation(GameObject gameObject, boolean repeat, List<Frame> frames, double time) {
        super(gameObject, repeat);

        this.frames = frames;
        this.time = time;
    }

    @Override
    public void play() {

        AnimationTimer animationTimer = super.getAnimationTimer();

        animationTimer = new AnimationTimer() {

            private int passedTime = 0;

            @Override
            public void handle(long l) {

                for (Frame frame : frames) {
                    if (frame.getStart() <= this.passedTime && this.passedTime <= frame.getEnd()) {
                        if (frame.getStart() == this.passedTime) {
                            frame.onStart();
                        }
                        frame.play();
                        if (frame.getEnd() == this.passedTime) {
                            frame.onEnd();
                        }
                    }
                }

                if (this.passedTime >= time && !getRepeat()) {
                    this.stop();
                    return;
                } else if (this.passedTime >= time) {
                    this.passedTime = 0;
                }

                this.passedTime++;
            }
        };

        animationTimer.start();
        super.setAnimationTimer(animationTimer);
    }
}
