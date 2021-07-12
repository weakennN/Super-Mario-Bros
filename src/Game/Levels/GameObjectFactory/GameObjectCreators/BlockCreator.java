package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Collider;
import ECS.SprtieRenderer.Sprite;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Animator.GlobalAnimations;
import Game.Common.GlobalVariables;
import Game.GameObjects.Block;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import javafx.scene.image.Image;

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
        block.addComponent(new SpriteRenderer(block, new Sprite(new Image(GlobalAnimations.BLOCK_SPRITE))));

        return block;
    }
}
