package Game.Animator;

import Game.GameObjects.Mario.Mario;
import javafx.animation.AnimationTimer;

public class GlobalAnimations {

    private static AnimationTimer animator;

    public final static String background = "/Game/Animator/ObjectsAnimations/background.png";
    public final static String gameOver = "/Game/Animator/ObjectsAnimations/gameOver.jpg";
    public final static String BRICK_SPRITE = "/resources/images/brick_sprite.png";
    public final static String BLOCK_SPRITE = "/resources/images/block_sprite.png";
    public final static String MUSHROOM_SPRITE = "/resources/images/mushroom_sprite.png";
    public final static String PIPE_SPRITE = "/resources/images/pipe_sprite.png";
    public final static String MARIO_SPRITE_SHEET = "mario_sprite_sheet.png";
    public final static String GOOMBA_SPRITE_SHEET = "goomba_sprite_sheet.png";
    public final static String ITEM_BOX_SPRITE_SHEET = "item_box_sprite_sheet.png";
    public final static String KOOPA_SPRITE_SHEET = "koopa_sprite_sheet.png";
    public final static String BIG_MARIO_SPRITE_SHEET = "big_mario_sprite_sheet.png";
    public final static String FIRE_MARIO_SPRITE_SHEET = "fire_mario_sprite_sheet.png";
    public final static String COIN_SPRITE_SHEET = "coin_sprite_sheet.png";

    public static void marioDecreasingAnimation(Mario mario) {

        animator = new AnimationTimer() {

            private int currentTime = 0;

            @Override
            public void handle(long l) {
                if (this.currentTime++ >= 175) {
                    mario.setImmune(false);
                    this.stop();
                }
            }
        };
        animator.start();
    }
}
