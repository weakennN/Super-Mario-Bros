package ECS.Renderer;

import ECS.Component;
import ECS.Renderer.SprtieRenderer.SortingLayer;
import Game.GameObjects.GameObject;

public abstract class Renderer extends Component {

    private int orderInLayer;
    private SortingLayer sortingLayer;

    public Renderer(GameObject gameObject, int orderInLayer, SortingLayer sortingLayer) {
        super(gameObject);
        this.orderInLayer = orderInLayer;
        this.sortingLayer = sortingLayer;
        this.sortingLayer.addRenderer(this);
    }

    public abstract void render();

    public int getOrderInLayer() {
        return this.orderInLayer;
    }

    public SortingLayer getSortingLayer() {
        return this.sortingLayer;
    }

    public void setOrderInLayer(int orderInLayer) {
        this.orderInLayer = orderInLayer;
    }

    public void setSortingLayer(SortingLayer sortingLayer) {
        if (this.sortingLayer != null) {
            this.sortingLayer.removeRenderer(this);
        }

        this.sortingLayer = sortingLayer;
        this.sortingLayer.addRenderer(this);
    }
}
