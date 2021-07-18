package Input.KeyEvents;

import Engine.ECS.Transform;
import Game.Game;
import Game.GameObjects.Mario.MarioDir;
import Game.SuperMarioBros;
import Game.GameObjects.Mario.Mario;
import javafx.scene.input.KeyCode;

public class RunningRightPressed extends RunningPressed {

    public RunningRightPressed(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();

        if (!MarioDir.marioRunningRight) {
            if (!MarioDir.marioJumpingLeft) {
                mario.getRigidbody().getVel().x = 2.5;
            } else {
                mario.getRigidbody().getVel().x = 0.7;
            }

            super.run(mario);

            MarioDir.marioIdleFacingLeft = false;
            MarioDir.marioRunningRight = true;
            if (mario.getComponent(Transform.class).getScale().x < 0 && !MarioDir.marioJumpingLeft) {
                mario.getComponent(Transform.class).getScale().x *= -1;
            }
        }
    }
}
