package ECS.SprtieRenderer;

import ECS.*;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;

import java.util.List;

public class SpriteRenderer extends Component {

    private SpriteSheet spriteSheet;
    private Sprite sprite;
    private int orderInLayer;
    private SortingLayer sortingLayer;

    public SpriteRenderer(GameObject gameObject, SpriteSheet spriteSheet) {
        this(gameObject, spriteSheet, SortingLayersContainer.getSortingLayerByName(GlobalVariables.DEFAULT_SORTING_LAYER));

    }

    public SpriteRenderer(GameObject gameObject, Sprite sprite) {
        this(gameObject, null, SortingLayersContainer.getSortingLayerByName(GlobalVariables.DEFAULT_SORTING_LAYER));

        this.sprite = sprite;
    }

    public SpriteRenderer(GameObject gameObject, SpriteSheet spriteSheet, SortingLayer sortingLayer) {
        super(gameObject);

        this.spriteSheet = spriteSheet;
        this.orderInLayer = 0;
        this.setSortingLayer(sortingLayer);
    }

    public SpriteSheet getSpriteSheet() {
        return this.spriteSheet;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public List<Sprite> getSprites() {
        return this.spriteSheet.getSprites();
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public void setSortingLayer(SortingLayer sortingLayer) {

        if (this.sortingLayer != null){
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
