package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Collider;
import ECS.Rigidbody;
import ECS.SprtieRenderer.Sprite;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Animator.GlobalAnimations;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mushroom;
import Game.Levels.Level;
import javafx.scene.image.Image;

public class MushroomCreator extends GameObjectCreator {

    public MushroomCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Mushroom mushroom = new Mushroom(GlobalVariables.mushroomTag);
        Transform transform = super.createTransform(Double.parseDouble(params[0]), Double.parseDouble(params[1]), mushroom);
        mushroom.addComponent(transform);
        mushroom.addComponent(new Rigidbody(mushroom, transform));
        mushroom.addComponent(new Collider(mushroom,
                GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
        mushroom.addComponent(transform);
        mushroom.addComponent(new SpriteRenderer(mushroom, new Sprite(new Image(GlobalAnimations.MUSHROOM_SPRITE))));
        return mushroom;
    }
}
