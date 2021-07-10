package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.Animation;
import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.Collider;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.SprtieRenderer.SpriteSheet;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.GameObjects.GameObject;
import Game.GameObjects.ItemBox;
import Game.Levels.Level;

import java.util.List;

public class ItemBoxCreator extends GameObjectCreator {

    public ItemBoxCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {

        ItemBox itemBox = new ItemBox(GlobalVariables.itemBoxTag, params[3]);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), itemBox);
        itemBox.addComponent(transform);
        itemBox.addComponent(new Collider(itemBox,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
        itemBox.addComponent(new SpriteRenderer(itemBox));
        SpriteSheet itemBoxSpriteSheet = SpriteSheetContainer.getSpriteSheet(GlobalVariables.ITEM_BOX_SPITE_SHEET_KEY);

        AnimationController animationController = new AnimationController();
        animationController.createAnimation("itemBoxAnimation", new Animation(List.of(itemBoxSpriteSheet.getSprites().get(0), itemBoxSpriteSheet.getSprites().get(2)
                , itemBoxSpriteSheet.getSprites().get(1)), 26, itemBox));
        itemBox.addComponent(new Animator(itemBox, animationController));

        return itemBox;
    }
}
