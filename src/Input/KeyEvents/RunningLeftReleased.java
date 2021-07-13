package Input.KeyEvents;

import Game.Game;
import Game.GameObjects.Mario.Mario;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import javafx.scene.input.KeyCode;

public class RunningLeftReleased extends RunningReleased {

    public RunningLeftReleased(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();

        mario.getRigidbody().getVel().x = 0;

        super.release(mario);

        MarioDir.marioRunningLeft = false;
        MarioDir.marioIdleFacingLeft = true;
    }
}
