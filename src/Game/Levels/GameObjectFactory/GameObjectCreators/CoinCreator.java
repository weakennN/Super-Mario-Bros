package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.*;
import ECS.Animator.Animation.Frame.PositionFrame;
import ECS.Animator.Animation.FrameAnimation;
import ECS.Animator.Animation.SpriteAnimation;
import ECS.Rigidbody;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.SprtieRenderer.SpriteSheet;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.GameObjects.Coin;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import mikera.vectorz.Vector2;

import java.util.List;

public class CoinCreator extends GameObjectCreator {

    public CoinCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Coin coin = new Coin(GlobalVariables.coinTag);
        Transform transform = super.createTransform(Double.parseDouble(params[0]), Double.parseDouble(params[1]), coin);
        coin.addComponent(transform);
        SpriteSheet coinSpriteSheet = SpriteSheetContainer.getSpriteSheet(GlobalVariables.COIN_SPRITE_SHEET_KEY);
        coin.addComponent(new Rigidbody(coin, false));
        AnimationController coinAnimationController = new AnimationController();
        coinAnimationController.createAnimation("coinSpriteAnimation", new SpriteAnimation(List.of(coinSpriteSheet.getSprites().get(0),
                coinSpriteSheet.getSprites().get(1), coinSpriteSheet.getSprites().get(2), coinSpriteSheet.getSprites().get(3)), coin, true, 10));

        coin.addComponent(new SpriteRenderer(coin));

        PositionFrame positionFrame = new PositionFrame(51, 100
                , new Vector2(transform.getPos().x, transform.getPos().y - 250), new Vector2(transform.getPos().x, transform.getPos().y), coin.getComponent(Transform.class));
        coinAnimationController.createAnimation("coinAnimation", new FrameAnimation(coin, false, List.of(new PositionFrame(0, 50
                , new Vector2(transform.getPos().x, transform.getPos().y), new Vector2(transform.getPos().x, transform.getPos().y - 250), coin.getComponent(Transform.class)), positionFrame), 100));

        coin.addComponent(new Animator(coin, coinAnimationController));

        return coin;
    }
}
