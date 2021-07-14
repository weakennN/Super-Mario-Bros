package ECS.SprtieRenderer;

import java.util.ArrayList;
import java.util.List;

public class SortingLayer {

    private String sortingLayer;
    private List<SpriteRenderer> spriteRenderers;
    private int value;

    public SortingLayer(String sortingLayer) {
        this.sortingLayer = sortingLayer;
        this.spriteRenderers = new ArrayList<>();
        this.value = SortingLayersContainer.sortingLayers.size();
    }

    public void addSpriteRenderer(SpriteRenderer spriteRenderer) {
        this.spriteRenderers.add(spriteRenderer);
    }

    public void removeSpriteRenderer(SpriteRenderer spriteRenderer) {
        this.spriteRenderers.remove(spriteRenderer);
    }

    public String getSortingLayerName() {
        return this.sortingLayer;
    }

    public List<SpriteRenderer> getSpriteRenderers() {
        return this.spriteRenderers;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
