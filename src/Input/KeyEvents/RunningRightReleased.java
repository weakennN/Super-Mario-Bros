package Input.KeyEvents;

import Game.Game;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import Game.GameObjects.Mario.Mario;
import javafx.scene.input.KeyCode;

public class RunningRightReleased extends RunningReleased {

    public RunningRightReleased(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {

        Mario mario = ((SuperMarioBros) game).getMario();

        mario.getRigidbody().getVel().x = 0;

        super.release(mario);

        MarioDir.marioIdleFacingRight = true;
        MarioDir.marioRunningRight = false;
    }
}
