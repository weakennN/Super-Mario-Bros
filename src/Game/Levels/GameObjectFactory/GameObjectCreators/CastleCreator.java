package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Renderer.SprtieRenderer.Sprite;
import ECS.Renderer.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.Animator.GlobalAnimations;
import Game.Common.GlobalVariables;
import Game.GameObjects.Castle;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import javafx.scene.image.Image;

public class CastleCreator extends GameObjectCreator {

    public CastleCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Castle castle = new Castle(GlobalVariables.castleTag);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), castle);
        castle.addComponent(transform);
        castle.addComponent(new SpriteRenderer(castle, new Sprite(new Image(GlobalAnimations.CASTLE_SPRITE))));

        return castle;
    }
}
