package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.Animation;
import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.Animator.SpriteAnimation;
import ECS.Collider;
import ECS.Rigidbody;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.SprtieRenderer.SpriteSheet;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.GameObjects.GameObject;
import Game.GameObjects.Koopa;
import Game.Levels.Level;

import java.util.List;

public class KoopaCreator extends GameObjectCreator {

    public KoopaCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Koopa koopa = new Koopa(GlobalVariables.koopaTag);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), koopa);
        koopa.addComponent(transform);
        koopa.addComponent(new Rigidbody(koopa, transform));
        koopa.addComponent(new Collider(koopa,
                GlobalVariables.defaultColliderSizeX, 75, transform));
        koopa.addComponent(new SpriteRenderer(koopa));
        SpriteSheet spriteSheet = SpriteSheetContainer.getSpriteSheet(GlobalVariables.KOOPA_SPRITE_SHEET_KEY);
        AnimationController animationController = new AnimationController();
        animationController.createAnimation("koopaAnimation", new SpriteAnimation(List.of(spriteSheet.getSprites().get(0), spriteSheet.getSprites().get(1)), koopa, true, 20));
        koopa.addComponent(new Animator(koopa, animationController));
        return koopa;
    }
}