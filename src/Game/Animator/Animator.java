package Game.Animator;

import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import ECS.Rigidbody;
import Game.Common.GlobalVariables;
import Game.Common.SpriteSheetContainer;
import Game.Score.ScoreKeeper;
import Input.Input;
import RenderEngine.RenderEngine;
import UIEngine.Designer;
import Game.GameObjects.Goomba;
import Game.GameObjects.GameObject;
import Game.GameObjects.Mario.Mario;
import Game.GameObjects.Mario.MarioManager;
import Game.SoundEffects.SoundManager;
import Game.SoundEffects.Sounds;
import javafx.animation.AnimationTimer;

public class Animator {

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

    public static void marioGrowingAnimation(Mario mario, MarioManager marioManager) {

        //mario.changeImage(Animator.marioGrowing);

        // Input.lock();
        /*Designer.scene.setOnKeyPressed(null);
        Designer.scene.setOnKeyReleased(null);

         */

        animator = new AnimationTimer() {

            private int currentTime = 0;
            private int finalTime = 0;

            @Override
            public void handle(long l) {


               /* if (currentTime++ == 175) {

                    mario.initializeActions(mario.getComponent(Rigidbody.class));
                    Input.unlock();
                    // mario.changeImage(Animator.bigMarioFacingRight);
                    marioManager.growMario();
                    this.currentTime = 0;
                    this.stop();
                    return;
                }

                */
                if (currentTime++ >= 10) {
                    mario.getComponent(Transform.class).getPos().x += 7;
                    currentTime = 0;
                }

                System.out.println("in");

                finalTime++;

                if (finalTime >= 175) {
                    this.stop();
                }
            }

        };


        animator.start();
    }

    public static void marioDeadAnimation(MarioManager marioManager) {

        SoundManager.playSound(Sounds.marioLosesLifeSound);

        Designer.scene.setOnKeyPressed(null);
        Designer.scene.setOnKeyReleased(null);

        marioManager.getMario().getComponent(ECS.Animator.Animator.class).getAnimationController().stop();
        marioManager.getMario().getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.MARIO_SPRITE_SHEET).getSprites().get(7));

        animator = new AnimationTimer() {

            int currentTime = 0;

            @Override
            public void handle(long l) {

                if (currentTime++ == 300) {
                    ScoreKeeper.decreaseLives();
                    marioManager.setMarioDead(true);
                    this.stop();
                    return;
                }

            }
        };

        animator.start();
    }

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

    public static void marioGettingCoinFromItemBoxAnimation(GameObject gameObject) {

        animator = new AnimationTimer() {

            private int currentTime = 0;

            @Override
            public void handle(long l) {

                if (this.currentTime++ == 125) {

                    gameObject.destroy();
                    this.stop();
                }
            }
        };


        animator.start();

    }

    public static void goombaDeadAnimation(Goomba goomba) {

        goomba.getComponent(SpriteRenderer.class).setSprite(SpriteSheetContainer.getSpriteSheet(GlobalVariables.GOOMBA_SPRITE_SHEET_KEY).getSprites().get(2));
        goomba.getComponent(ECS.Animator.Animator.class).getAnimationController().stop();

        animator = new AnimationTimer() {

            private int currentTime = 0;

            @Override
            public void handle(long l) {

                if (this.currentTime++ >= 50) {

                    goomba.destroy();
                    this.currentTime = 0;
                    this.stop();
                }
            }
        };

        animator.start();
    }
}
