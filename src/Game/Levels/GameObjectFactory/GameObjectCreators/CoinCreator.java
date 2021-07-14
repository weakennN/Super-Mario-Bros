package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.*;
import ECS.Animator.Animation.Frame.PositionFrame;
import ECS.Animator.Animation.FrameAnimation;
import ECS.Animator.Animation.SpriteAnimation;
import ECS.Rigidbody;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.SprtieRenderer.SpriteSheet;
import ECS.Transform;
import Event.EventListener;
import Game.Common.GlobalVariables;
import Game.GameObjects.Coin;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import Util.AssetPool;
import mikera.vectorz.Vector2;

public class CoinCreator extends GameObjectCreator {

    public CoinCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Coin coin = new Coin(GlobalVariables.coinTag);
        Transform transform = super.createTransform(Double.parseDouble(params[0]), Double.parseDouble(params[1]), coin);
        coin.addComponent(transform);
        SpriteSheet coinSpriteSheet = AssetPool.getSpriteSheet(GlobalVariables.COIN_SPRITE_SHEET_KEY);
        coin.addComponent(new Rigidbody(coin, false));
        AnimationController coinAnimationController = new AnimationController();
        coinAnimationController.createAnimation("coinSpriteAnimation", new SpriteAnimation(coin, true, 10, coinSpriteSheet.getSprites().get(0),
                coinSpriteSheet.getSprites().get(1), coinSpriteSheet.getSprites().get(2), coinSpriteSheet.getSprites().get(3)));

        coin.addComponent(new SpriteRenderer(coin));

        PositionFrame positionFrame = new PositionFrame(51
                , new Vector2(transform.getPos().x, transform.getPos().y - 250), new Vector2(transform.getPos().x, transform.getPos().y), coin.getComponent(Transform.class));
        coinAnimationController.createAnimation("coinAnimation", new FrameAnimation(coin, false, 100, new PositionFrame(0
                , new Vector2(transform.getPos().x, transform.getPos().y), new Vector2(transform.getPos().x, transform.getPos().y - 250), coin.getComponent(Transform.class)), positionFrame));

        coinAnimationController.getAnimation("coinAnimation").getAnimationFinish().subscribe(new EventListener() {
            @Override
            public void invoke(Object arg) {
                GameObject gm = (GameObject) arg;
                gm.destroy();
            }
        });
        coin.addComponent(new Animator(coin, coinAnimationController));

        return coin;
    }
}
