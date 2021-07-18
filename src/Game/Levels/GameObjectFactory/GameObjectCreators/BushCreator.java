package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import Engine.ECS.Renderer.SprtieRenderer.Sprite;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Transform;
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
