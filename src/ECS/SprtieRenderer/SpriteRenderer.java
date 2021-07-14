package ECS.SprtieRenderer;

import ECS.*;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;

public class SpriteRenderer extends Component {

    private Sprite sprite;
    private int orderInLayer;
    private SortingLayer sortingLayer;

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
        super(gameObject);
        this.sprite = sprite;
        this.sortingLayer = sortingLayer;
        this.sortingLayer.addSpriteRenderer(this);
        this.orderInLayer = 0;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSortingLayer(SortingLayer sortingLayer) {

        if (this.sortingLayer != null) {
            this.sortingLayer.removeSpriteRenderer(this);
        }

        this.sortingLayer = sortingLayer;
        this.sortingLayer.addSpriteRenderer(this);
    }

    public SortingLayer getSortingLayer() {
        return this.sortingLayer;
    }

    public void setOrderInLayer(int orderInLayer) {
        this.orderInLayer = orderInLayer;
    }
}
