package ECS.Animator.Animation.Frame;

import ECS.SprtieRenderer.Sprite;
import ECS.SprtieRenderer.SpriteRenderer;

public class SpriteFrame extends Frame {

    private Sprite sprite;
    private SpriteRenderer spriteRenderer;

    public SpriteFrame(double start, Sprite sprite, SpriteRenderer spriteRenderer) {
        super(start);

        this.sprite = sprite;
        this.spriteRenderer = spriteRenderer;
    }

    @Override
    public void play() {
        this.spriteRenderer.setSprite(this.sprite);
    }
}
