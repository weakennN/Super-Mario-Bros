package Input;

import Engine.Input.Input;
import Game.Game;
import Input.KeyEvents.*;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {

    private Map<KeyCode, KeyEvent> pressedEvents;
    private Map<KeyCode, KeyEvent> releasedEvents;
    private Input input;
    private Game game;

    public InputHandler(Game game, Input input) {
        this.game = game;
        this.input = input;
        this.pressedEvents = new HashMap<>();
        this.releasedEvents = new HashMap<>();
        this.pressedEvents.put(KeyCode.D, new RunningRightPressed(KeyCode.D));
        this.pressedEvents.put(KeyCode.W, new Jump(KeyCode.W));
        this.pressedEvents.put(KeyCode.A, new RunningLeftPressed(KeyCode.A));
        this.pressedEvents.put(KeyCode.SHIFT, new ShootFireBall(KeyCode.SHIFT));
        this.pressedEvents.put(KeyCode.S, new CrouchPressed(KeyCode.S));
        this.releasedEvents.put(KeyCode.D, new RunningRightReleased(KeyCode.D));
        this.releasedEvents.put(KeyCode.A, new RunningLeftReleased(KeyCode.A));
        this.releasedEvents.put(KeyCode.S, new CrouchReleased(KeyCode.S));
    }

    public void update() {
        KeyCode pressed = this.input.getKeyPressed();
        if (pressed != null) {
            if (this.pressedEvents.containsKey(pressed)) {
                this.pressedEvents.get(pressed).run(this.game);
            }
        }

        KeyCode released = this.input.getKeyReleased();
        if (released != null) {
            if (this.releasedEvents.containsKey(released)) {
                this.releasedEvents.get(released).run(this.game);
            }
        }
    }
}
