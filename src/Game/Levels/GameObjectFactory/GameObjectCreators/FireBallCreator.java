package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Animator.Animation.SpriteAnimation;
import Engine.ECS.Animator.AnimationController;
import Engine.ECS.Animator.Animator;
import Engine.ECS.Collider;
import Engine.ECS.Rigidbody;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Renderer.SprtieRenderer.SpriteSheet;
import Engine.ECS.Transform;
import Event.EventListener;
import Game.Common.GlobalVariables;
import Game.GameObjects.FireBall;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import Util.AssetPool;

public class FireBallCreator extends GameObjectCreator {

    public FireBallCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        FireBall fireBall = new FireBall(GlobalVariables.explosiveTag);

        Transform transform = super.createTransform(Double.parseDouble(params[0]), Double.parseDouble(params[1]), fireBall);
        fireBall.addComponent(new Rigidbody(fireBall));
        fireBall.addComponent(new Collider(fireBall, 25, 25, transform));
        SpriteSheet fireBallSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.FIRE_BALL_SPRITE_SHEET_KEY);
        SpriteSheet fireBallExplosionSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.FIRE_BALL_EXPLOSION_SPRITE_SHEET_KEY);
        fireBall.addComponent(new SpriteRenderer(fireBall, fireBallSpriteSheet.getSprites().get(0)));
        AnimationController animationController = new AnimationController();
        animationController.createAnimation("fireBallAnimation", new SpriteAnimation(fireBall, true, 10, fireBallSpriteSheet.getSprites().get(0)
                , fireBallSpriteSheet.getSprites().get(1), fireBallSpriteSheet.getSprites().get(2), fireBallSpriteSheet.getSprites().get(3)));
        fireBall.addComponent(new Animator(fireBall, animationController));
        animationController.createAnimation("fireBallExplosion", new SpriteAnimation(fireBall, false, 10, fireBallExplosionSpriteSheet.getSprites().get(0),
                fireBallExplosionSpriteSheet.getSprites().get(1), fireBallExplosionSpriteSheet.getSprites().get(2)));

        animationController.getAnimation("fireBallExplosion").getAnimationFinish().subscribe(new EventListener<GameObject>() {
            @Override
            public void invoke(GameObject arg) {
                fireBall.destroy();
            }
        });

        fireBall.getComponent(Rigidbody.class).getVel().x = Double.parseDouble(params[2]);
        fireBall.getComponent(Rigidbody.class).getVel().y = 3;

        return fireBall;
    }
}
