package ECS.Animator;

import Game.GameObjects.GameObject;
import javafx.animation.AnimationTimer;

public abstract class Animation {

    private AnimationTimer animationTimer;
    private GameObject gameObject;
    private boolean repeat;

    public Animation(GameObject gameObject, boolean repeat) {

        this.gameObject = gameObject;
        this.repeat = repeat;
    }

    public abstract void play();

    public AnimationTimer getAnimationTimer() {
        return this.animationTimer;
    }

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public boolean getRepeat() {
        return this.repeat;
    }

    public void stop() {
        if (this.animationTimer != null) {
            this.animationTimer.stop();
        }
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }
}