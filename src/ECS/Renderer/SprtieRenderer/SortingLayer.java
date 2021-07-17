package ECS.Renderer.SprtieRenderer;

import ECS.Renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

public class SortingLayer {

    private String sortingLayer;
    private List<Renderer> spriteRenderers;
    private int value;

    public SortingLayer(String sortingLayer) {
        this.sortingLayer = sortingLayer;
        this.spriteRenderers = new ArrayList<>();
        this.value = SortingLayersContainer.sortingLayers.size();
    }

    public void addRenderer(Renderer renderer) {
        this.spriteRenderers.add(renderer);
    }

    public void removeRenderer(Renderer renderer) {
        this.spriteRenderers.remove(renderer);
    }

    public String getSortingLayerName() {
        return this.sortingLayer;
    }

    public List<Renderer> getRenderers() {
        return this.spriteRenderers;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
