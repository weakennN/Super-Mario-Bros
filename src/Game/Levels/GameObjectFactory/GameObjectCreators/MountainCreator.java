package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import Engine.ECS.Renderer.SprtieRenderer.Sprite;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Transform;
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

        Sprite sprite = new Sprite(AssetPool.getTexture("MountainS"));
        if (params[3].equals("m")) {
            sprite = new Sprite(AssetPool.getTexture("MountainM"));
        }
        mountain.addComponent(new SpriteRenderer(mountain, sprite, SortingLayersContainer.getSortingLayerByName("background")));
        return mountain;
    }
}
