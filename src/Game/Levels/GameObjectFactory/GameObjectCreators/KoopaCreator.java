package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Animator.AnimationController;
import Engine.ECS.Animator.Animator;
import Engine.ECS.Animator.Animation.SpriteAnimation;
import Engine.ECS.Collider;
import Engine.ECS.Rigidbody;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Renderer.SprtieRenderer.SpriteSheet;
import Engine.ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import Game.GameObjects.Koopa;
import Game.Levels.Level;
import Util.AssetPool;

public class KoopaCreator extends GameObjectCreator {

    public KoopaCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Koopa koopa = new Koopa(GlobalVariables.koopaTag);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), koopa);
        koopa.addComponent(new Rigidbody(koopa, transform));
        koopa.addComponent(new Collider(koopa,
                GlobalVariables.defaultColliderSizeX, 75, transform));
        koopa.addComponent(new SpriteRenderer(koopa));
        SpriteSheet spriteSheet = AssetPool.getSpriteSheet(GlobalVariables.KOOPA_SPRITE_SHEET_KEY);
        AnimationController animationController = new AnimationController();
        animationController.createAnimation("koopaAnimation", new SpriteAnimation(koopa, true, 20, spriteSheet.getSprites().get(0), spriteSheet.getSprites().get(1)));
        koopa.addComponent(new Animator(koopa, animationController));
        return koopa;
    }
}
