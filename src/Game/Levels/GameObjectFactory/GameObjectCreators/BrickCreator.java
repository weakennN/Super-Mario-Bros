package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Animator.Animation.Frame.PositionFrame;
import Engine.ECS.Animator.Animation.FrameAnimation;
import Engine.ECS.Animator.AnimationController;
import Engine.ECS.Animator.Animator;
import Engine.ECS.Collider;
import Engine.ECS.Renderer.SprtieRenderer.Sprite;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.BrickBox;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import Util.AssetPool;
import mikera.vectorz.Vector2;

public class BrickCreator extends GameObjectCreator {

    public BrickCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        BrickBox brickBox = new BrickBox(GlobalVariables.brickBoxTag);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), brickBox);
        brickBox.addComponent(new Collider(brickBox,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
        brickBox.addComponent(new SpriteRenderer(brickBox, new Sprite(AssetPool.getTexture("BrickBox"))));

        AnimationController brickController = new AnimationController();
        brickController.createAnimation("bump", new FrameAnimation(brickBox, false, 30, new PositionFrame(0,
                brickBox.getComponent(Transform.class).getPos(),
                new Vector2(brickBox.getComponent(Transform.class).getPos().x, brickBox.getComponent(Transform.class).getPos().y - 20), brickBox.getComponent(Transform.class))
                , new PositionFrame(15, new Vector2(transform.getPos().x, transform.getPos().y - 20), new Vector2(transform.getPos().x, transform.getPos().y), transform)));
        brickBox.addComponent(new Animator(brickBox, brickController));

        return brickBox;
    }
}
