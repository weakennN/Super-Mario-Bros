package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.Animation.Frame.PositionFrame;
import ECS.Animator.Animation.FrameAnimation;
import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.Animator.Animation.SpriteAnimation;
import ECS.Collider;
import ECS.Renderer.SprtieRenderer.SpriteRenderer;
import ECS.Renderer.SprtieRenderer.SpriteSheet;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import Game.GameObjects.ItemBox;
import Game.Levels.Level;
import Util.AssetPool;
import mikera.vectorz.Vector2;

public class ItemBoxCreator extends GameObjectCreator {

    public ItemBoxCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {

        ItemBox itemBox = new ItemBox(GlobalVariables.itemBoxTag, params[3]);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), itemBox);
        itemBox.addComponent(new Collider(itemBox,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
        itemBox.addComponent(new SpriteRenderer(itemBox));
        SpriteSheet itemBoxSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.ITEM_BOX_SPITE_SHEET_KEY);

        AnimationController animationController = new AnimationController();
        animationController.createAnimation("itemBoxAnimation", new SpriteAnimation(itemBox, true, 26, itemBoxSpriteSheet.getSprites().get(0), itemBoxSpriteSheet.getSprites().get(2)
                , itemBoxSpriteSheet.getSprites().get(1)));

        animationController.createAnimation("bump", new FrameAnimation(itemBox, false, 30, new PositionFrame(0,
                itemBox.getComponent(Transform.class).getPos(),
                new Vector2(itemBox.getComponent(Transform.class).getPos().x, itemBox.getComponent(Transform.class).getPos().y - 20), itemBox.getComponent(Transform.class))
                , new PositionFrame(15, new Vector2(transform.getPos().x, transform.getPos().y - 20), new Vector2(transform.getPos().x, transform.getPos().y), transform)));
        itemBox.addComponent(new Animator(itemBox, animationController));

        return itemBox;
    }
}
