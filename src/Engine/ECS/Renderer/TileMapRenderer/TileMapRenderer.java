package Engine.ECS.Renderer.TileMapRenderer;

import Engine.ECS.Renderer.Renderer;
import Engine.ECS.Renderer.SprtieRenderer.SortingLayer;
import Engine.ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import Game.GameObjects.GameObject;
import Game.GameObjects.TileMap.Tile;
import Game.GameObjects.TileMap.TileMap;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class TileMapRenderer extends Renderer {

    public TileMapRenderer(GameObject gameObject, int orderInLayer, SortingLayer sortingLayer) {
        super(gameObject, orderInLayer, sortingLayer);
    }

    public TileMapRenderer(GameObject gameObject) {
        super(gameObject, 0, SortingLayersContainer.getSortingLayerByName("default"));
    }

    @Override
    public void render(GraphicsContext gc) {
        List<Tile> tiles = ((TileMap) super.getGameObject()).getTiles();

        for (Tile tile : tiles) {
            gc.drawImage(tile.getSprite().getTexture()
                    , tile.getPos().x, tile.getPos().y,
                    tile.getSprite().getTexture().getWidth(),
                    tile.getSprite().getTexture().getHeight());
        }
    }
}
