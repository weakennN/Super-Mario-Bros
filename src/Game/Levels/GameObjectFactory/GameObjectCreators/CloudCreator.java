package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Renderer.SprtieRenderer.Sprite;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Transform;
import Game.GameResources.GlobalAnimations;
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
        cloud.addComponent(new SpriteRenderer(cloud,new Sprite(new Image(GlobalAnimations.CLOUD_SPRITE))));
        return cloud;
    }
}
