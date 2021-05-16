package Game.GameObjects;

import Game.Animator.Animator;
import Game.CollisionInfo.Collision;
import Game.CollisionInfo.Collisions;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Engine.GameEngine;
import ECS.Position;
import Game.Score.ScoreKeeper;
import javafx.scene.image.Image;

public class ItemBox extends GameObject {

    private GameObject gameObject;
    private boolean isEmpty;

    public ItemBox(Position position, String tag, GameObject gameObject) {
        super(position, tag);

        this.gameObject = gameObject;
        this.isEmpty = false;
    }

    @Override
    public void start() {

        super.changeImage(Animator.itemBox);
    }

    @Override
    public void update() {

        super.updateComponents();
    }

    @Override
    public Image render() {

        return super.getCurrentAnimation();
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

        if (collision.getHitDirection().y == 1) {

            if (other.getTag().equals(GlobalVariables.marioTag) && !isEmpty) {

                if (this.gameObject.getTag().equals(GlobalVariables.mushroomTag)) {

                    this.gameObject.addComponent(new Rigidbody(GlobalVariables.rigidbodyTag,
                            this.gameObject.getPosition(), this.gameObject));
                    this.gameObject.addComponent(new Collider(GlobalVariables.colliderTag, this.gameObject.getPosition(),
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, this.gameObject));

                    GameEngine.gameObjects.add(this.gameObject);

                    super.changeImage(Animator.emptyItemBox);
                } else if (this.gameObject.getTag().equals(GlobalVariables.coinTag)) {

                    Animator.marioGettingCoinFromItemBoxAnimation(this);
                    ScoreKeeper.coins++;
                    super.changeImage(Animator.emptyItemBox);

                } else if (this.gameObject.getTag().equals(GlobalVariables.fireFlowerTag)) {

                    this.gameObject.addComponent(new Collider(GlobalVariables.colliderTag, this.gameObject.getPosition(),
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, this.gameObject));

                    GameEngine.gameObjects.add(this.gameObject);
                    super.changeImage(Animator.emptyItemBox);
                }

                this.isEmpty = true;
            }

            Rigidbody rigidbody = (Rigidbody) other.getComponent(GlobalVariables.rigidbodyTag);
            rigidbody.getVel().y = 1;

        } else if (collision.getHitDirection().y == -1) {

            Collisions.defaultOnGroundCollision(this, other, collision);

        } else if (collision.getHitDirection().x == 1 || collision.getHitDirection().x == -1) {

            Collisions.defaultHorizontalCollision(this, other, collision);

        }
    }
}
