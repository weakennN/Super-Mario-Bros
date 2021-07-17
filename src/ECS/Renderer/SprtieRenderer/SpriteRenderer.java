package ECS.Renderer.SprtieRenderer;

import ECS.Renderer.Renderer;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import UIEngine.Designer;

public class SpriteRenderer extends Renderer {

    private Sprite sprite;

    public SpriteRenderer(GameObject gameObject, Sprite sprite) {
        this(gameObject, sprite, SortingLayersContainer.getSortingLayerByName(GlobalVariables.DEFAULT_SORTING_LAYER));
    }

    public SpriteRenderer(GameObject gameObject, SortingLayer sortingLayer) {
        this(gameObject, null, sortingLayer);
    }

    public SpriteRenderer(GameObject gameObject) {
        this(gameObject, null, SortingLayersContainer.getSortingLayerByName(GlobalVariables.DEFAULT_SORTING_LAYER));
    }

    public SpriteRenderer(GameObject gameObject, Sprite sprite, SortingLayer sortingLayer) {
        super(gameObject, 0, sortingLayer);
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void render() {
        if (super.getGameObject().isActive()) {
            Transform transform = super.getGameObject().getComponent(Transform.class);

            Designer.gc.drawImage(this.sprite.getTexture(), transform.getScale().x < 0 ?
                            transform.getPos().x + this.sprite.getTexture().getWidth()
                            : transform.getPos().x, transform.getPos().y,
                    this.sprite.getTexture().getWidth() * transform.getScale().x,
                    this.sprite.getTexture().getHeight() * transform.getScale().y);
        }
    }
}
