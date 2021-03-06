package Engine.ECS;

import Game.GameObjects.GameObject;

public abstract class Component {

    private GameObject gameObject;
    private boolean active;

    public Component(GameObject gameObject) {

        this.gameObject = gameObject;
        this.active = true;
    }

    public void start() {
    }

    public void update() {
    }

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean b) {
        this.active = b;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
