package Engine.ECS.Animator.Animation;

import Engine.ECS.Animator.Animation.Frame.Frame;
import Game.GameObjects.GameObject;
import javafx.animation.AnimationTimer;

import java.util.Arrays;
import java.util.List;

public class FrameAnimation extends Animation {

    private List<Frame> frames;
    private Frame currentPlaying;
    private double time;

    public FrameAnimation(GameObject gameObject, boolean repeat, double time, Frame... frames) {
        super(gameObject, repeat);

        this.frames = Arrays.asList(frames);
        this.time = time;
    }

    @Override
    public void play() {
        AnimationTimer animationTimer = super.getAnimationTimer();

        animationTimer = new AnimationTimer() {

            private int passedTime = 0;

            @Override
            public void handle(long l) {

                if (this.passedTime >= time && !getRepeat()) {
                    getAnimationFinish().invokeAll(getGameObject());
                    this.stop();
                    return;
                } else if (this.passedTime >= time) {
                    this.passedTime = 0;
                }

                for (int i = 0; i < frames.size(); i++) {
                    Frame frame = frames.get(i);

                    if (frame.getStart() == this.passedTime) {
                        if (frame.getStart() == this.passedTime) {
                            currentPlaying = frame;
                            double value = 0;
                            if (frames.size() - 1 != i) {
                                value = frames.get(i + 1).getStart();
                            } else {
                                value = time;
                            }
                            currentPlaying.setValue(Math.abs(currentPlaying.getStart() - value));
                            frame.onStart();
                        }
                    }
                }

                if (currentPlaying != null) {
                    currentPlaying.play();
                }

                this.passedTime++;
            }
        };

        animationTimer.start();
        super.setAnimationTimer(animationTimer);
    }
}
