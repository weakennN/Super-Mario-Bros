package ECS;

import Game.GameObjects.GameObject;

public abstract class Component {

    private GameObject gameObject;
    private String tag;

    protected Component(String tag, GameObject gameObject) {

        this.gameObject = gameObject;
        this.tag = tag;
    }

    public abstract void update();

    public String getTag() {

        return this.tag;
    }

    public GameObject getGameObject() {

        return this.gameObject;
    }
}
