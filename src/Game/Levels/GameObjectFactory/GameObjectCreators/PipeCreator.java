package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Collider;
import Engine.ECS.Renderer.SprtieRenderer.Sprite;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Engine.ECS.Transform;
import Game.GameResources.GlobalAnimations;
import Game.Common.GlobalVariables;
import Game.GameObjects.GameObject;
import Game.GameObjects.Pipe;
import Game.Levels.Level;
import javafx.scene.image.Image;
import mikera.vectorz.Vector2;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PipeCreator extends GameObjectCreator {

    public PipeCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        Pipe pipe = new Pipe(GlobalVariables.pipeTag);
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), pipe);
        pipe.addComponent(new SpriteRenderer(pipe, new Sprite(new Image(GlobalAnimations.PIPE_SPRITE))));

        pipe.addComponent(new Collider(pipe, 100, 100, transform));

        List<Double> randomScales = List.of(1.4, 1.6, 1.2);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double scale = randomScales.get(random.nextInt(0, randomScales.size()));
        transform.setScale(new Vector2(1, scale));
        transform.getPos().y = transform.getPos().y - (pipe.getComponent(Collider.class).getSize().y - 100);

        return pipe;
    }
}
