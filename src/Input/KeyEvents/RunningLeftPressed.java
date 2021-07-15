package Input.KeyEvents;

import ECS.Transform;
import Game.Game;
import Game.GameObjects.Mario.Mario;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import javafx.scene.input.KeyCode;

public class RunningLeftPressed extends RunningPressed {

    public RunningLeftPressed(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {

        Mario mario = ((SuperMarioBros) game).getMario();

        if (!MarioDir.marioRunningLeft) {
            if (!MarioDir.marioJumpingRight) {
                mario.getRigidbody().getVel().x = -2.5;
            } else {
                mario.getRigidbody().getVel().x = -0.7;
            }

            super.run(mario);

            MarioDir.marioIdleFacingRight = false;
            MarioDir.marioRunningLeft = true;
            if (mario.getComponent(Transform.class).getScale().x > 0 && !MarioDir.marioJumpingRight) {
                mario.getComponent(Transform.class).getScale().x *= -1;
            }
        }
    }
}
