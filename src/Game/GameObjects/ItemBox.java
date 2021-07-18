package Game.GameObjects;

import Engine.ECS.Animator.Animation.Frame.PositionFrame;
import Engine.ECS.Animator.Animation.FrameAnimation;
import Engine.ECS.Animator.Animator;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Transform;
import Event.EventListener;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import Engine.ECS.Collider;
import Engine.ECS.Rigidbody;
import Engine.GameEngine.GameEngine;
import Game.Levels.GameObjectFactory.GameObjectFactory;
import Game.Score.ScoreKeeper;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import Util.AssetPool;
import mikera.vectorz.Vector2;

public class ItemBox extends GameObject {

    private String gameObjectCreator;
    private boolean isEmpty;

    public ItemBox(String tag, String gameObjectCreator) {
        super(tag);
        this.gameObjectCreator = gameObjectCreator;
        this.isEmpty = false;
    }

    @Override
    public void start() {
        super.getComponent(Animator.class).getAnimationController().playAnimation("itemBoxAnimation");
    }

    @Override
    public void update() {
        super.updateComponents();
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if(other.getTag().equals(GlobalVariables.invisibleWallTag)){
          return;
        }

        if (collision.getHitDirection().y == 1) {

            if (other.getTag().equals(GlobalVariables.marioTag) && !isEmpty) {

                if (this.gameObjectCreator.equals("Mushroom")) {

                    String[] params = {super.getComponent(Transform.class).getPos().x + "", super.getComponent(Transform.class).getPos().y + ""};
                    Mushroom mushroom = (Mushroom) GameObjectFactory.create(params, "Mushroom");

                    this.createItemAnimation(mushroom, "mushroomAnimation");

                    SoundManager.playSound(Sounds.itemBlockSound);
                } else if (this.gameObjectCreator.equals("Coin")) {

                    String[] params = {super.getComponent(Transform.class).getPos().x + "", super.getComponent(Transform.class).getPos().y - 50 + ""};
                    Coin coin = (Coin) GameObjectFactory.create(params, this.gameObjectCreator);
                    coin.getComponent(Animator.class).getAnimationController().playAnimation("coinSpriteAnimation");
                    coin.getComponent(Animator.class).getAnimationController().playAnimation("coinAnimation");
                    coin.setActive(true);
                    GameEngine.gameObjects.add(coin);
                    SoundManager.playSound(Sounds.coinSound);
                    ScoreKeeper.coins++;
                } else if (this.gameObjectCreator.equals("Flower")) {
                    Flower flower = (Flower) GameObjectFactory.create(new String[]{super.getComponent(Transform.class).getPos().x + ""
                            , super.getComponent(Transform.class).getPos().y + ""}, "Flower");

                    this.createItemAnimation(flower, "flowerAnimation");
                    SoundManager.playSound(Sounds.itemBlockSound);
                }

                this.isEmpty = true;
                super.getComponent(Animator.class).getAnimationController().stop();
                super.getComponent(SpriteRenderer.class).setSprite(AssetPool.getSpriteSheet(GlobalVariables.ITEM_BOX_SPITE_SHEET_KEY).getSprites().get(3));
                super.getComponent(Animator.class).getAnimationController().stop();
                super.getComponent(Animator.class).getAnimationController().playAnimation("bump");
            } else {
                SoundManager.playSound(Sounds.bumpSound);
            }

            Rigidbody rigidbody = other.getComponent(Rigidbody.class);
            rigidbody.getVel().y = 1;

        } else if (collision.getHitDirection().y == -1) {
            Collisions.defaultOnGroundCollision(this, other, collision);
        } else if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {
            Collisions.defaultHorizontalCollision(this, other, collision);
        }
    }

    private void createItemAnimation(GameObject gameObject, String animation) {
        gameObject.getComponent(Animator.class).getAnimationController().createAnimation(animation, new FrameAnimation(
                gameObject,
                false,
                175,
                new PositionFrame(0, gameObject.getComponent(Transform.class).getPos(), new Vector2(gameObject.getComponent(Transform.class).getPos().x,
                        gameObject.getComponent(Transform.class).getPos().y - 50), gameObject.getComponent(Transform.class))));
        gameObject.getComponent(Rigidbody.class).setActive(false);
        gameObject.getComponent(Collider.class).setActive(false);
        gameObject.getComponent(Animator.class).getAnimationController().getAnimation(animation).getAnimationFinish().subscribe(new EventListener<GameObject>() {
            @Override
            public void invoke(GameObject arg) {
                arg.getComponent(Rigidbody.class).setActive(true);
                arg.getComponent(Collider.class).setActive(true);
            }
        });

        super.getComponent(Animator.class).getAnimationController().getAnimation("bump").getAnimationFinish().subscribe(new EventListener<GameObject>() {
            @Override
            public void invoke(GameObject arg) {
                GameEngine.gameObjects.add(gameObject);
                gameObject.getComponent(Animator.class).getAnimationController().playAnimation(animation);

            }
        });
    }

    public String getGameObjectCreator() {
        return this.gameObjectCreator;
    }
}
