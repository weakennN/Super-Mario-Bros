package Game.GameObjects;

import ECS.Animator.Animator;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Engine.GameEngine;
import Game.Common.SpriteSheetContainer;
import Game.Levels.GameObjectFactory.GameObjectFactory;
import Game.Score.ScoreKeeper;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;

public class ItemBox extends GameObject {

    private GameObject gameObject;
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

        if (collision.getHitDirection().y == 1) {

            if (other.getTag().equals(GlobalVariables.marioTag) && !isEmpty) {

                if (this.gameObjectCreator.equals("Mushroom")) {

                    String[] params = {super.getComponent(Transform.class).getPos().x + "", super.getComponent(Transform.class).getPos().y - 50 + ""};
                    Mushroom mushroom = (Mushroom) GameObjectFactory.create(params, "Mushroom");

                    GameEngine.gameObjects.add(mushroom);
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
                } else if (this.gameObject.getTag().equals(GlobalVariables.fireFlowerTag)) {
                    this.gameObject.addComponent(new Collider(this.gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, this.gameObject.getComponent(Transform.class)));
                    GameEngine.gameObjects.add(this.gameObject);
                    SoundManager.playSound(Sounds.itemBlockSound);
                }

                this.isEmpty = true;
                super.getComponent(Animator.class).getAnimationController().stop();
                super.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.ITEM_BOX_SPITE_SHEET_KEY).getSprites().get(3));
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
}
