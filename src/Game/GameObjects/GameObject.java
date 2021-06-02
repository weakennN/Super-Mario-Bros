package Game.GameObjects;

import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import ECS.Collider;
import ECS.Component;
import Engine.GameEngine;
import ECS.Position;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

    private Position position;
    private Image currentAnimation;
    private String tag;
    private boolean active;
    List<Component> components;

    public GameObject(Position position, String tag) {

        this.position = position;
        this.currentAnimation = null;
        this.tag = tag;
        this.components = new ArrayList<>();
        this.active = false;
    }

    public abstract void update();

    public abstract Image render();

    public abstract void start();

    public Position getPosition() {

        return this.position;
    }

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

    public Component getComponent(String componentTag) {

        for (Component c : this.components) {

            if (c.getTag().equals(componentTag)) {

                return c;
            }

        }

        return null;
    }

    public void removeComponent(String componentTag) {

        this.components.removeIf(c -> c.getTag().equals(componentTag));
    }

    public void destroy() {

        GameEngine.gameObjects.remove(this);
        Collider collider = (Collider) this.getComponent(GlobalVariables.colliderTag);
        Collider.removeCollider(collider);
        this.active = false;
        this.components.clear();
    }
}
