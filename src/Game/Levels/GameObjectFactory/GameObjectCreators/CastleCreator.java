package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Renderer.SprtieRenderer.Sprite;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Transform;
import Game.GameResources.GlobalAnimations;
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
        castle.addComponent(new SpriteRenderer(castle, new Sprite(new Image(GlobalAnimations.CASTLE_SPRITE))));

        return castle;
    }
}
