package Game.GameObjects;

import Engine.ECS.Animator.Animator;
import Engine.ECS.Renderer.Renderer;
import Game.Collision.Collision;
import Engine.ECS.Collider;
import Engine.ECS.Component;
import Engine.GameEngine.GameEngine;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

    private String tag;
    private boolean active;
    List<Component> components;

    public GameObject(String tag) {
        this.tag = tag;
        this.components = new ArrayList<>();
        this.active = false;
    }

    public void update() {

    }

    public void start() {

    }

    public abstract void onCollisionEnter(GameObject other, Collision collision);

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
        for (Component c : this.components) {
            if (c == null) {
                continue;
            }
            c.update();
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

        if (this.getComponent(Animator.class) != null) {
            if (this.getComponent(Animator.class).getAnimationController() != null) {
                this.getComponent(Animator.class).getAnimationController().stop();
            }
        }

        if (this.getComponent(Renderer.class) != null) {
            this.getComponent(Renderer.class).getSortingLayer().removeRenderer(this.getComponent(Renderer.class));
        }

        this.active = false;
        this.components.clear();
    }
}
