package Game.GameObjects;

import ECS.Transform;
import Game.Animator.Animator;
import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Rigidbody;
import Engine.GameEngine;
import Game.Score.ScoreKeeper;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import javafx.scene.image.Image;
import mikera.vectorz.Vector2;

public class ItemBox extends GameObject {

    private GameObject gameObject;
    private boolean isEmpty;

    public ItemBox(String tag, GameObject gameObject) {
        super(tag);

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

                    Transform transform = new Transform(new Vector2(super.getComponent(Transform.class).getPos().x, super.getComponent(Transform.class).getPos().y - 50), this.gameObject);
                    this.gameObject.addComponent(new Rigidbody(this.gameObject, transform));
                    this.gameObject.addComponent(new Collider(this.gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
                    this.gameObject.addComponent(transform);

                    GameEngine.gameObjects.add(this.gameObject);

                    SoundManager.playSound(Sounds.itemBlockSound);
                    super.changeImage(Animator.emptyItemBox);
                } else if (this.gameObject.getTag().equals(GlobalVariables.coinTag)) {

                    SoundManager.playSound(Sounds.coinSound);
                    Animator.marioGettingCoinFromItemBoxAnimation(this);
                    ScoreKeeper.coins++;
                    super.changeImage(Animator.emptyItemBox);

                } else if (this.gameObject.getTag().equals(GlobalVariables.fireFlowerTag)) {

                    this.gameObject.addComponent(new Collider(this.gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, this.gameObject.getComponent(Transform.class)));

                    GameEngine.gameObjects.add(this.gameObject);

                    SoundManager.playSound(Sounds.itemBlockSound);
                    super.changeImage(Animator.emptyItemBox);
                }

                this.isEmpty = true;

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
