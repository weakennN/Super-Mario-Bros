package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import ECS.Renderer.SprtieRenderer.Sprite;
import ECS.Renderer.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.GameObjects.Bush;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import Util.AssetPool;

public class BushCreator extends GameObjectCreator {

    public BushCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Bush bush = new Bush("bush");
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), bush);
        bush.addComponent(transform);
        Sprite sprite = new Sprite(AssetPool.getTexture("BushS"));
        if (params[3].equals("m")) {
            sprite = new Sprite(AssetPool.getTexture("BushM"));
        } else if (params[3].equals("l")) {
            sprite = new Sprite(AssetPool.getTexture("BushL"));
        }
        bush.addComponent(new SpriteRenderer(bush, sprite, SortingLayersContainer.getSortingLayerByName("background")));
        return bush;
    }
}
