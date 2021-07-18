package Input.KeyEvents;

import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Game.Common.GlobalVariables;
import Game.Game;
import Game.SuperMarioBros;
import Game.GameObjects.Mario.Mario;
import Util.AssetPool;
import javafx.scene.input.KeyCode;

public class CrouchReleased extends KeyEvent {

    public CrouchReleased(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();

        if (mario.isCrouching()) {
            if (mario.isBigMario()) {
                mario.getComponent(SpriteRenderer.class).setSprite(AssetPool.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(0));
            } else if (mario.isFireMario()) {
                mario.getComponent(SpriteRenderer.class).setSprite(AssetPool.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(0));
            }
            mario.setCrouch(false);
        }
    }
}
