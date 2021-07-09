package Input.KeyEvents;

import Game.Game;
import javafx.scene.input.KeyCode;

public abstract class KeyEvent {

    private KeyCode key;

    public KeyEvent(KeyCode key) {
        this.key = key;
    }

    public abstract void run(Game game);
}
