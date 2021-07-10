package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Collider;
import ECS.SprtieRenderer.Sprite;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.BrickBox;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import javafx.scene.image.Image;

public class BrickCreator extends GameObjectCreator {

    public BrickCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        BrickBox brickBox = new BrickBox(GlobalVariables.brickBoxTag);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]),brickBox);
        brickBox.addComponent(transform);
        brickBox.addComponent(new Collider(brickBox,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
        brickBox.addComponent(new SpriteRenderer(brickBox, new Sprite(new Image(Game.Animator.Animator.BRICK_SPRITE))));
        return brickBox;
    }
}
