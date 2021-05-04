package GameObjects;

import CollisionInfo.Collision;
import Components.Component;
import Rigidbody.Position;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

    private Position position;
    private Image currentAnimation;
    private String tag;
    List<Component> components;

    public GameObject(Position position, String tag) {

        this.position = position;
        this.currentAnimation = null;
        this.tag = tag;
        this.components = new ArrayList<>();
    }

    public abstract void update();

    public abstract Image render();

    public abstract void start();

    public Position getPosition() {

        return this.position;
    }

    public abstract void onCollisionEnter(GameObject other, Collision collision);

    public void changeImage(String image) {

        try {
            this.currentAnimation = new Image(new FileInputStream(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

    protected void updateComponents() {

        try {

            for (Component c : this.components) {

                c.update();
            }
        }catch (Exception e){

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
}
