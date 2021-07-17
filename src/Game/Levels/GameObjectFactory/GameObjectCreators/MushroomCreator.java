package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Animator.AnimationController;
import ECS.Animator.Animator;
import ECS.Collider;
import ECS.Rigidbody;
import ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import ECS.Renderer.SprtieRenderer.Sprite;
import ECS.Renderer.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mushroom;
import Game.Levels.Level;
import Util.AssetPool;

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
        mushroom.addComponent(new SpriteRenderer(mushroom, new Sprite(AssetPool.getTexture("Mushroom")), SortingLayersContainer.getSortingLayerByName("item")));
        mushroom.addComponent(new Animator(mushroom, new AnimationController()));
        return mushroom;
    }
}
