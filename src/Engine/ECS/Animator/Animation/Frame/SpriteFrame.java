package Engine.ECS.Animator.Animation.Frame;

import Engine.ECS.Renderer.SprtieRenderer.Sprite;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;

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
