package Game.Levels.GameObjectFactory.GameObjectCreators;


import ECS.Animator.Animation;
import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.Collider;
import ECS.Rigidbody;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.SprtieRenderer.SpriteSheet;
import ECS.Transform;
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

        mario.addComponent(new SpriteRenderer(mario, marioSpriteSheet.getSprites().get(14)));

        AnimationController animationController = new AnimationController();
        animationController.createAnimation("marioRunningRight", new Animation(List.of(marioSpriteSheet.getSprites().get(15),
                marioSpriteSheet.getSprites().get(16), marioSpriteSheet.getSprites().get(17)), 20, mario));
        animationController.createAnimation("marioRunningLeft", new Animation(List.of(marioSpriteSheet.getSprites().get(12),
                marioSpriteSheet.getSprites().get(11), marioSpriteSheet.getSprites().get(10)), 20, mario));
        animationController.createAnimation("bigMarioRunningRight", new Animation(List.of(bigMarioSpriteSheet.getSprites().get(22)
                , bigMarioSpriteSheet.getSprites().get(23), bigMarioSpriteSheet.getSprites().get(24)), 20, mario));
        animationController.createAnimation("bigMarioRunningLeft", new Animation(List.of(bigMarioSpriteSheet.getSprites().get(19), bigMarioSpriteSheet.getSprites().get(18)
                , bigMarioSpriteSheet.getSprites().get(17)), 20, mario));
        animationController.createAnimation("fireMarioRunningRight", new Animation(List.of(fireMarioSpriteSheet.getSprites().get(22)
                , fireMarioSpriteSheet.getSprites().get(23), fireMarioSpriteSheet.getSprites().get(24)), 20, mario));
        animationController.createAnimation("fireMarioRunningLeft", new Animation(List.of(fireMarioSpriteSheet.getSprites().get(19)
                , fireMarioSpriteSheet.getSprites().get(18), fireMarioSpriteSheet.getSprites().get(17)), 20, mario));

        mario.addComponent(new Animator(mario, animationController));
        Transform cameraPos = new Transform(new Vector2(960, 0));
        super.getLevel().setMario(mario);

        super.getLevel().setCamera(new Camera(mario, cameraPos));

        return mario;
    }
}
