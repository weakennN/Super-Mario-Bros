package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Animator.AnimationController;
import Engine.ECS.Animator.Animator;
import Engine.ECS.Animator.Animation.SpriteAnimation;
import Engine.ECS.Collider;
import Engine.ECS.Rigidbody;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Renderer.SprtieRenderer.SpriteSheet;
import Engine.ECS.Transform;
import Event.EventListener;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import Game.GameObjects.Goomba;
import Game.Levels.Level;
import Util.AssetPool;

public class GoombaCreator extends GameObjectCreator {

    public GoombaCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Goomba goomba = new Goomba(GlobalVariables.goombaTag);

        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), goomba);

        goomba.addComponent(new Rigidbody(goomba, transform));
        goomba.addComponent(new SpriteRenderer(goomba));
        AnimationController animationController = new AnimationController();
        SpriteSheet goombaSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.GOOMBA_SPRITE_SHEET_KEY);
        animationController.createAnimation("goombaAnimation", new SpriteAnimation(goomba, true, 20, goombaSpriteSheet.getSprites().get(0),
                goombaSpriteSheet.getSprites().get(1)));
        goomba.addComponent(new Animator(goomba, animationController));
        goomba.addComponent(new Collider(goomba,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));

        animationController.createAnimation("goombaDead", new SpriteAnimation(goomba, false, 50, goombaSpriteSheet.getSprites().get(2)));
        animationController.getAnimation("goombaDead").getAnimationFinish().subscribe(new EventListener<GameObject>() {
            @Override
            public void invoke(GameObject arg) {
                arg.destroy();
            }
        });
        return goomba;
    }
}
