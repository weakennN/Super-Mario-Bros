package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Animator.Animation.SpriteAnimation;
import Engine.ECS.Animator.AnimationController;
import Engine.ECS.Animator.Animator;
import Engine.ECS.Collider;
import Engine.ECS.Rigidbody;
import Engine.ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Renderer.SprtieRenderer.SpriteSheet;
import Engine.ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.Flower;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import Util.AssetPool;

public class FlowerCreator extends GameObjectCreator {

    public FlowerCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Flower flower = new Flower(GlobalVariables.fireFlowerTag);
        Transform transform = super.createTransform(Double.parseDouble(params[0]), Double.parseDouble(params[1]), flower);
        flower.addComponent(new Rigidbody(flower, false));
        flower.addComponent(new Collider(flower, GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
        SpriteSheet flowerSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.FLOWER_SPRITE_SHEET_KEY);
        flower.addComponent(new SpriteRenderer(flower, flowerSpriteSheet.getSprites().get(0), SortingLayersContainer.getSortingLayerByName("item")));
        AnimationController animationController = new AnimationController();
        animationController.createAnimation("default", new SpriteAnimation(flower, true, 10, flowerSpriteSheet.getSprites().get(0),
                flowerSpriteSheet.getSprites().get(1), flowerSpriteSheet.getSprites().get(2), flowerSpriteSheet.getSprites().get(3)));
        flower.addComponent(new Animator(flower, animationController));
        return flower;
    }
}
