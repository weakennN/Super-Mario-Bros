package Engine.Input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class Input {

    private List<KeyCode> keyPressed;
    private List<KeyCode> keyReleased;
    private static boolean locked = false;

    public Input(Scene scene) {
        this.keyPressed = new ArrayList<>();
        this.keyReleased = new ArrayList<>();
        this.init(scene);
    }

    private void init(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (!locked) {
                keyPressed.add(e.getCode());
            }
        });

        scene.setOnKeyReleased(e -> {
            if (!locked) {
                keyReleased.add(e.getCode());
            }
        });
    }

    public void update() {
        this.keyPressed.clear();
        this.keyReleased.clear();
    }

    public boolean isPressed(KeyCode key) {
        return this.keyPressed.contains(key);
    }

    public boolean isReleased(KeyCode key) {
        return this.keyReleased.contains(key);
    }

    public KeyCode getKeyPressed() {
        if (this.keyPressed.size() < 1 || locked) {
            return null;
        }

        return this.keyPressed.get(this.keyPressed.size() - 1);
    }

    public KeyCode getKeyReleased() {
        if (this.keyReleased.size() < 1 || locked) {
            return null;
        }

        return this.keyReleased.get(this.keyReleased.size() - 1);
    }

    public static void lock() {
        locked = true;
    }

    public static void unlock() {
        locked = false;
    }
}
