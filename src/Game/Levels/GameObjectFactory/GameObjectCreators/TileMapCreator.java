package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Collider;
import ECS.Renderer.SprtieRenderer.Sprite;
import ECS.Renderer.TileMapRenderer.TileMapRenderer;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import Game.GameObjects.TileMap.Tile;
import Game.GameObjects.TileMap.TileMap;
import Game.Levels.Level;
import Util.AssetPool;
import mikera.vectorz.Vector2;

public class TileMapCreator extends GameObjectCreator {

    public TileMapCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        TileMap tileMap = new TileMap(GlobalVariables.TILE_MAP_TAG);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), tileMap);
        tileMap.addComponent(transform);
        Sprite sprite = new Sprite(AssetPool.getTexture(params[4]));
        double startX = Double.parseDouble(params[5]);
        double startY = Double.parseDouble(params[6]);

        int reputationsX = Integer.parseInt(params[7]);
        int reputationsY = Integer.parseInt(params[8]);

        for (int i = 0; i < reputationsY; i++) {
            for (int j = 0; j < reputationsX; j++) {
                Tile tile = new Tile(sprite, new Vector2(startX, startY));
                tileMap.addTile(tile);
                startX += sprite.getTexture().getWidth();
            }
            startX = Double.parseDouble(params[5]);
            startY += sprite.getTexture().getHeight();
        }

        tileMap.addComponent(new TileMapRenderer(tileMap));
        tileMap.addComponent(new Collider(tileMap, reputationsX * sprite.getTexture().getWidth()
                , reputationsY * sprite.getTexture().getHeight(), transform));

        return tileMap;
    }
}
