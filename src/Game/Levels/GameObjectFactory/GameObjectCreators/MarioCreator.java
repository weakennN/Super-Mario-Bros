package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Animator.*;
import Engine.ECS.Animator.Animation.Frame.ScaleFrame;
import Engine.ECS.Animator.Animation.FrameAnimation;
import Engine.ECS.Animator.Animation.SpriteAnimation;
import Engine.ECS.Collider;
import Engine.ECS.Rigidbody;
import Engine.ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Renderer.SprtieRenderer.SpriteSheet;
import Engine.ECS.Transform;
import Event.EventListener;
import Game.Camera;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mario.Mario;
import Game.Levels.Level;
import Engine.Input.Input;
import Util.AssetPool;
import javafx.stage.Screen;
import mikera.vectorz.Vector2;

public class MarioCreator extends GameObjectCreator {

    public MarioCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Mario mario = new Mario(GlobalVariables.marioTag);

        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), mario);
        mario.addComponent(new Rigidbody(mario, transform));
        mario.addComponent(new Collider(mario,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));

        SpriteSheet marioSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET);
        SpriteSheet bigMarioSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY);
        SpriteSheet fireMarioSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY);

        mario.addComponent(new SpriteRenderer(mario, marioSpriteSheet.getSprites().get(0), SortingLayersContainer.getSortingLayerByName("player")));
        AnimationController animationController = new AnimationController();

        animationController.createAnimation("marioRunning", new SpriteAnimation(mario, true, 17, marioSpriteSheet.getSprites().get(1)
                , marioSpriteSheet.getSprites().get(2), marioSpriteSheet.getSprites().get(3)));

        animationController.createAnimation("bigMarioRunning", new SpriteAnimation(mario, true, 17, bigMarioSpriteSheet.getSprites().get(1), bigMarioSpriteSheet.getSprites().get(2),
                bigMarioSpriteSheet.getSprites().get(3)));

        animationController.createAnimation("fireMarioRunning", new SpriteAnimation(mario, true, 17, fireMarioSpriteSheet.getSprites().get(1),
                fireMarioSpriteSheet.getSprites().get(2), fireMarioSpriteSheet.getSprites().get(3)));

        SpriteAnimation marioGrowing = new SpriteAnimation(mario, false, 2, marioSpriteSheet.getSprites().get(0), bigMarioSpriteSheet.getSprites().get(15),
                marioSpriteSheet.getSprites().get(0), bigMarioSpriteSheet.getSprites().get(15), marioSpriteSheet.getSprites().get(0),
                bigMarioSpriteSheet.getSprites().get(15), bigMarioSpriteSheet.getSprites().get(0), marioSpriteSheet.getSprites().get(0), bigMarioSpriteSheet.getSprites().get(15)
                , bigMarioSpriteSheet.getSprites().get(0));

        animationController.createAnimation("marioGrowing", marioGrowing);

        animationController.createAnimation("marioDecreasing", new FrameAnimation(mario, false, 100, new ScaleFrame(0, mario.getComponent(Transform.class).getScale(), mario.getComponent(Transform.class))));

        animationController.getAnimation("marioDecreasing").getAnimationFinish().subscribe(new EventListener<GameObject>() {
            @Override
            public void invoke(GameObject arg) {
                Input.unlock();
                mario.setImmune(false);
            }
        });

        mario.addComponent(new Animator(mario, animationController));
        Transform cameraPos = new Transform(new Vector2(Screen.getPrimary().getBounds().getWidth() / 2, 0));
        super.getLevel().setMario(mario);

        super.getLevel().setCamera(new Camera(cameraPos));
        super.getLevel().getCamera().setTarget(mario);

        return mario;
    }
}
