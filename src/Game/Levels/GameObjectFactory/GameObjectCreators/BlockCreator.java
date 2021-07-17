package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Collider;
import ECS.Renderer.SprtieRenderer.Sprite;
import ECS.Renderer.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.Block;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import Util.AssetPool;

public class BlockCreator extends GameObjectCreator {

    public BlockCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Block block = new Block(GlobalVariables.blockTag);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), block);
        block.addComponent(transform);
        block.addComponent(new Collider(block,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
        block.addComponent(new SpriteRenderer(block, new Sprite(AssetPool.getTexture("Block"))));

        return block;
    }
}
