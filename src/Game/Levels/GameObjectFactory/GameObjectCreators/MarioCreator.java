package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.*;
import ECS.Animator.Animation.Frame.ScaleFrame;
import ECS.Animator.Animation.FrameAnimation;
import ECS.Animator.Animation.SpriteAnimation;
import ECS.Collider;
import ECS.Rigidbody;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.SprtieRenderer.SpriteSheet;
import ECS.Transform;
import Event.EventListener;
import Game.Camera;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mario.Mario;
import Game.Levels.Level;
import mikera.vectorz.Vector2;

import java.util.List;

public class MarioCreator extends GameObjectCreator {

    public MarioCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Mario mario = new Mario(GlobalVariables.marioTag);

        double x = Double.parseDouble(params[1]);
        double y = Double.parseDouble(params[2]);

        Transform transform = new Transform(new Vector2(x, y), mario);
        mario.addComponent(new Rigidbody(mario, transform));
        mario.addComponent(new Collider(mario,
                GlobalVariables.defaultMarioColliderX, GlobalVariables.defaultMarioColliderY, transform));
        mario.addComponent(transform);

        SpriteSheet marioSpriteSheet = SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET);
        SpriteSheet bigMarioSpriteSheet = SpriteSheetContainer.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY);
        SpriteSheet fireMarioSpriteSheet = SpriteSheetContainer.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY);

        mario.addComponent(new SpriteRenderer(mario, marioSpriteSheet.getSprites().get(0)));
        AnimationController animationController = new AnimationController();

        animationController.createAnimation("marioRunning", new SpriteAnimation(mario, true, 20, marioSpriteSheet.getSprites().get(1)
                , marioSpriteSheet.getSprites().get(2), marioSpriteSheet.getSprites().get(3)));

        animationController.createAnimation("bigMarioRunning", new SpriteAnimation(mario, true, 20, bigMarioSpriteSheet.getSprites().get(1), bigMarioSpriteSheet.getSprites().get(2),
                bigMarioSpriteSheet.getSprites().get(3)));

       /* animationController.createAnimation("bigMarioRunningRight", new SpriteAnimation(mario, true, 20, bigMarioSpriteSheet.getSprites().get(22)
                , bigMarioSpriteSheet.getSprites().get(23), bigMarioSpriteSheet.getSprites().get(24)));
        animationController.createAnimation("bigMarioRunningLeft", new SpriteAnimation(mario, true, 20, bigMarioSpriteSheet.getSprites().get(19), bigMarioSpriteSheet.getSprites().get(18)
                , bigMarioSpriteSheet.getSprites().get(17)));
        */

        animationController.createAnimation("fireMarioRunningRight", new SpriteAnimation(mario, true, 20, fireMarioSpriteSheet.getSprites().get(22)
                , fireMarioSpriteSheet.getSprites().get(23), fireMarioSpriteSheet.getSprites().get(24)));
        animationController.createAnimation("fireMarioRunningLeft", new SpriteAnimation(mario, true, 20, fireMarioSpriteSheet.getSprites().get(19)
                , fireMarioSpriteSheet.getSprites().get(18), fireMarioSpriteSheet.getSprites().get(17)));

        FrameAnimation marioGrowing = new FrameAnimation(mario, false, 150, new ScaleFrame(0, new Vector2(1, 1.8), mario.getComponent(Transform.class)));
        animationController.createAnimation("marioGrowing", marioGrowing);

        mario.addComponent(new Animator(mario, animationController));
        Transform cameraPos = new Transform(new Vector2(960, 0));
        super.getLevel().setMario(mario);

        super.getLevel().setCamera(new Camera(mario, cameraPos));

        return mario;
    }
}
