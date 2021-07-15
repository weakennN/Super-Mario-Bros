package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.SprtieRenderer.Sprite;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Animator.GlobalAnimations;
import Game.Common.GlobalVariables;
import Game.GameObjects.Cloud;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import javafx.scene.image.Image;

public class CloudCreator extends GameObjectCreator {

    public CloudCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Cloud cloud = new Cloud(GlobalVariables.cloudTag);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), cloud);
        cloud.addComponent(transform);
        cloud.addComponent(new SpriteRenderer(cloud,new Sprite(new Image(GlobalAnimations.CLOUD_SPRITE))));
        return cloud;
    }
}
