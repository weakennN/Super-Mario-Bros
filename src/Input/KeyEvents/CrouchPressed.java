package Input.KeyEvents;

import Engine.ECS.Animator.Animator;
import Engine.ECS.Renderer.SprtieRenderer.SpriteRenderer;
import Game.Common.GlobalVariables;
import Game.Game;
import Game.SuperMarioBros;
import Game.GameObjects.Mario.Mario;
import Util.AssetPool;
import javafx.scene.input.KeyCode;

public class CrouchPressed extends KeyEvent {

    public CrouchPressed(KeyCode key) {
        super(key);
    }

    @Override
    public void run(Game game) {
        Mario mario = ((SuperMarioBros) game).getMario();

        if (!mario.isJumping()) {
            mario.getComponent(Animator.class).getAnimationController().stop();
            if (mario.isBigMario()) {
                mario.getComponent(SpriteRenderer.class).setSprite(AssetPool.getSpriteSheet(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY).getSprites().get(6));
            } else if (mario.isFireMario()) {
                mario.getComponent(SpriteRenderer.class).setSprite(AssetPool.getSpriteSheet(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY).getSprites().get(6));
            }
            mario.getRigidbody().getVel().x = 0;
            mario.getRigidbody().getVel().y = 0;
            mario.setCrouch(true);
        }
    }
}
