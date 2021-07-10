package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.Animation;
import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.Rigidbody;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.SprtieRenderer.SpriteSheet;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.GameObjects.Coin;
import Game.GameObjects.GameObject;
import Game.Levels.Level;

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
        coin.addComponent(new Rigidbody(coin));
        AnimationController coinAnimationController = new AnimationController();
        coinAnimationController.createAnimation("coinAnimation", new Animation(List.of(coinSpriteSheet.getSprites().get(0),
                coinSpriteSheet.getSprites().get(1), coinSpriteSheet.getSprites().get(2), coinSpriteSheet.getSprites().get(3)), 7, coin));
        coin.addComponent(new SpriteRenderer(coin));
        coin.addComponent(new Animator(coin, coinAnimationController));

        return coin;
    }
}
