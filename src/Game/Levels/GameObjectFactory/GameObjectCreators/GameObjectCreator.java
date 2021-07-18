package Game.Levels.GameObjectFactory.GameObjectCreators;

import ECS.Transform;
import Game.GameObjects.GameObject;
import Game.Levels.Level;
import mikera.vectorz.Vector2;

public abstract class GameObjectCreator {

    private Level level;

    public GameObjectCreator(Level level) {
        this.level = level;
    }

    public abstract GameObject create(String[] params);

    public Level getLevel() {
        return this.level;
    }

    protected Transform createTransform(double x, double y, GameObject gameObject) {
        Transform transform = new Transform(new Vector2(x, y), gameObject);
        gameObject.addComponent(transform);
        return transform;
    }
}
