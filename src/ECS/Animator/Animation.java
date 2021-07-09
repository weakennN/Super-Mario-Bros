package ECS.Animator;

import ECS.SprtieRenderer.Sprite;
import ECS.SprtieRenderer.SpriteRenderer;
import Game.GameObjects.GameObject;
import javafx.animation.AnimationTimer;

import java.util.List;

public class Animation {

    private List<Sprite> sprites;
    private int time;
    private AnimationTimer animationTimer;
    private GameObject gameObject;
    private int i = 0;
    private boolean repeat;

    public Animation(List<Sprite> sprites, int time, GameObject gameObject) {

        this.sprites = sprites;
        this.time = time;
        this.gameObject = gameObject;
    }

    public void play() {

        if (this.animationTimer != null) {

            this.animationTimer.stop();
            this.i = 0;
        }

        this.gameObject.getComponent(SpriteRenderer.class).setSprite(this.sprites.get(0));

        this.animationTimer = new AnimationTimer() {

            private int passedTime = 0;

            @Override
            public void handle(long l) {

                if (passedTime++ >= time) {

                    i++;

                    if (i >= sprites.size()) {
                        i = 0;
                    }

                    if (gameObject.getComponent(SpriteRenderer.class) != null) {
                        gameObject.getComponent(SpriteRenderer.class).setSprite(sprites.get(i));
                    }

                    passedTime = 0;

                }

            }
        };

        this.animationTimer.start();
    }

    public void stop() {
        this.animationTimer.stop();
    }
}
