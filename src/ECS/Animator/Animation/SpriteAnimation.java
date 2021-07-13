package ECS.Animator.Animation;

import ECS.SprtieRenderer.Sprite;
import ECS.SprtieRenderer.SpriteRenderer;
import Game.GameObjects.GameObject;
import javafx.animation.AnimationTimer;

import java.util.Arrays;
import java.util.List;

public class SpriteAnimation extends Animation {

    private List<Sprite> sprites;
    private int time;
    private int i = 0;

    public SpriteAnimation(GameObject gameObject, boolean repeat, int time, Sprite... sprites) {
        super(gameObject, repeat);

        this.sprites = Arrays.asList(sprites);
        this.time = time;
    }

    @Override
    public void play() {

        super.setActive(true);
        AnimationTimer animationTimer = super.getAnimationTimer();

        if (animationTimer != null) {
            animationTimer.stop();
            this.i = 0;
        }

        super.getGameObject().getComponent(SpriteRenderer.class).setSprite(this.sprites.get(0));

        animationTimer = new AnimationTimer() {

            private int passedTime = 0;

            @Override
            public void handle(long l) {

                if (passedTime++ >= time) {
                    i++;
                    if (i >= sprites.size()) {
                        i = 0;
                        if (!getRepeat()){
                            this.stop();
                            getEvent().invokeAll(getGameObject());
                            return;
                        }
                    }
                    if (getGameObject().getComponent(SpriteRenderer.class) != null && isActive()) {
                        getGameObject().getComponent(SpriteRenderer.class).setSprite(sprites.get(i));
                    }
                    passedTime = 0;
                }
            }
        };

        animationTimer.start();
        super.setAnimationTimer(animationTimer);
    }
}
