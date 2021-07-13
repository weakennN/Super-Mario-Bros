package ECS.Animator.Animation;

import Event.Event;
import Game.GameObjects.GameObject;
import javafx.animation.AnimationTimer;

public abstract class Animation {

    private AnimationTimer animationTimer;
    private GameObject gameObject;
    private boolean active;
    private boolean repeat;
    private Event<GameObject> event;

    public Animation(GameObject gameObject, boolean repeat) {

        this.gameObject = gameObject;
        this.repeat = repeat;
        this.active = false;
        this.event = new Event<GameObject>();
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
            this.active = false;
            this.animationTimer.stop();
        }
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Event<GameObject> getEvent() {
        return this.event;
    }
}
