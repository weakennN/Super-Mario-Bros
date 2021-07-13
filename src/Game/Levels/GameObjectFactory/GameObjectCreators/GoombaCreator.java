package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.Animator.Animation.SpriteAnimation;
import ECS.Collider;
import ECS.Rigidbody;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.SprtieRenderer.SpriteSheet;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.GameObjects.GameObject;
import Game.GameObjects.Goomba;
import Game.Levels.Level;

import java.util.List;

public class GoombaCreator extends GameObjectCreator {

    public GoombaCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Goomba goomba = new Goomba(GlobalVariables.goombaTag);

        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), goomba);
        goomba.addComponent(transform);

        goomba.addComponent(new Rigidbody(goomba, transform));
        goomba.addComponent(new SpriteRenderer(goomba));
        AnimationController animationController = new AnimationController();
        SpriteSheet goombaSpriteSheet = SpriteSheetContainer.getSpriteSheet(GlobalVariables.GOOMBA_SPRITE_SHEET_KEY);
        animationController.createAnimation("goombaAnimation", new SpriteAnimation(goomba, true, 20,goombaSpriteSheet.getSprites().get(0),
                goombaSpriteSheet.getSprites().get(1)));
        goomba.addComponent(new Animator(goomba, animationController));
        goomba.addComponent(new Collider(goomba,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));

        return goomba;
    }
}
