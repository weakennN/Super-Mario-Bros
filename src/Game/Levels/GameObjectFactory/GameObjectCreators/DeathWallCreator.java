package Game.Levels.GameObjectFactory.GameObjectCreators;

import Engine.ECS.Collider;
import Engine.ECS.Transform;
import Game.GameObjects.DeathWall;
import Game.GameObjects.GameObject;
import Game.Levels.Level;

public class DeathWallCreator extends GameObjectCreator {

    public DeathWallCreator(Level level) {
        super(level);
    }

    @Override
    public GameObject create(String[] params) {
        DeathWall deathWall = new DeathWall("deathWall");
        Transform transform = super.createTransform(Double.parseDouble(params[1]), Double.parseDouble(params[2]), deathWall);
        deathWall.addComponent(new Collider(deathWall, Double.parseDouble(params[3]), Double.parseDouble(params[4]), transform));

        return deathWall;
    }
}
