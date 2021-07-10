package ECS.SprtieRenderer;

import ECS.*;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;

public class SpriteRenderer extends Component {

    private Sprite sprite;
    private int orderInLayer;
    private SortingLayer sortingLayer;

    public SpriteRenderer(GameObject gameObject, Sprite sprite) {
        this(gameObject, SortingLayersContainer.getSortingLayerByName(GlobalVariables.DEFAULT_SORTING_LAYER));

        this.sprite = sprite;
    }

    public SpriteRenderer(GameObject gameObject, SortingLayer sortingLayer) {
        super(gameObject);

        this.orderInLayer = 0;
        this.setSortingLayer(sortingLayer);
    }

    public SpriteRenderer(GameObject gameObject) {
        this(gameObject, SortingLayersContainer.getSortingLayerByName(GlobalVariables.DEFAULT_SORTING_LAYER));
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
