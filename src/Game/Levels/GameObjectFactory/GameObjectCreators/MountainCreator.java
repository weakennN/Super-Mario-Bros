package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import ECS.Renderer.SprtieRenderer.Sprite;
import ECS.Renderer.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mountain;
import Game.Levels.Level;
import Util.AssetPool;

public class MountainCreator extends GameObjectCreator {

    public MountainCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Mountain mountain = new Mountain("mountain");
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), mountain);
        mountain.addComponent(transform);

        Sprite sprite = new Sprite(AssetPool.getTexture("MountainS"));
        if (params[3].equals("m")) {
            sprite = new Sprite(AssetPool.getTexture("MountainM"));
        }
        mountain.addComponent(new SpriteRenderer(mountain, sprite, SortingLayersContainer.getSortingLayerByName("background")));
        return mountain;
    }
}
