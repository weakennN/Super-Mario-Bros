package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.*;
import ECS.Animator.Animation.Frame.PositionFrame;
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

        animationController.createAnimation("fireMarioRunning",new SpriteAnimation(mario,true,20,fireMarioSpriteSheet.getSprites().get(1),
                fireMarioSpriteSheet.getSprites().get(2),fireMarioSpriteSheet.getSprites().get(3)));

        SpriteAnimation marioGrowing = new SpriteAnimation(mario, false, 10, marioSpriteSheet.getSprites().get(0), bigMarioSpriteSheet.getSprites().get(15),
                marioSpriteSheet.getSprites().get(0), bigMarioSpriteSheet.getSprites().get(15), marioSpriteSheet.getSprites().get(0),
                bigMarioSpriteSheet.getSprites().get(15), bigMarioSpriteSheet.getSprites().get(0), marioSpriteSheet.getSprites().get(0), bigMarioSpriteSheet.getSprites().get(15)
                , bigMarioSpriteSheet.getSprites().get(0));

        animationController.createAnimation("marioGrowing", marioGrowing);

        mario.addComponent(new Animator(mario, animationController));
        Transform cameraPos = new Transform(new Vector2(960, 0));
        super.getLevel().setMario(mario);

        super.getLevel().setCamera(new Camera(mario, cameraPos));

        return mario;
    }
}
