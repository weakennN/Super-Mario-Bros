package Input.KeyEvents;

import Game.Game;
import Game.SuperMarioBros;
import Game.GameObjects.Mario.Mario;
import javafx.scene.input.KeyCode;

public class ShootFireBall extends KeyEvent {

    public ShootFireBall(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();
        if (mario.isFireMario()) {
            mario.getMarioManager().shootFireBall();
        }
    }
}
