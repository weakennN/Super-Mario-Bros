package Game.GameObjects;

import Game.Collision.Collision;
import ECS.Collider;
import ECS.Component;
import Engine.GameEngine;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

    private Image currentAnimation;
    private String tag;
    private boolean active;
    List<Component> components;

    public GameObject(String tag) {

        this.currentAnimation = null;
        this.tag = tag;
        this.components = new ArrayList<>();
        this.active = false;
    }

    public abstract void update();

    public abstract Image render();

    public abstract void start();

    public abstract void onCollisionEnter(GameObject other, Collision collision);

    public void changeImage(String image) {

        this.currentAnimation = new Image(image);

    }

    public Image getCurrentAnimation() {

        return this.currentAnimation;
    }

    protected void setTag(String tag) {

        this.tag = tag;
    }

    public String getTag() {

        return tag;
    }

    public void setActive(boolean b) {

        this.active = true;
    }

    public boolean isActive() {

        return this.active;
    }

    protected void updateComponents() {

        try {

            for (Component c : this.components) {

                if (c == null) {

                    continue;
                }

                c.update();
            }

        } catch (Exception ignored) {

        }

    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public <T extends Component> T getComponent(Class<T> component) {

        for (Component c : this.components) {
            if (component.isAssignableFrom(c.getClass())) {
                return component.cast(c);
            }
        }

        return null;
    }

    public <T extends Component> void removeComponent(Class<T> component) {
        this.components.removeIf(c -> component.isAssignableFrom(c.getClass()));
    }

    public void destroy() {

        GameEngine.gameObjects.remove(this);
        Collider collider = this.getComponent(Collider.class);
        Collider.removeCollider(collider);
        this.active = false;
        this.components.clear();
    }
}
