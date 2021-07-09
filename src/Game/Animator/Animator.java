package Game.Animator;

import ECS.Transform;
import ECS.Rigidbody;
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

    public final static String marioRunningRight = "/Game/Animator/ObjectsAnimations/MarioRunningRightDir.gif";
    public final static String marioRunningLeft = "/Game/Animator/ObjectsAnimations/MarioRunningLeftDir.gif";
    public final static String bigMarioRunningRight = "/Game/Animator/ObjectsAnimations/BigMarioRunningRightDir.gif";
    public final static String bigMarioRunningLeft = "/Game/Animator/ObjectsAnimations/BigMarioRunningLeftDir.gif";
    public final static String marioIdleFacingRight = "/Game/Animator/ObjectsAnimations/MarioIdleFacingRight.png";
    public final static String marioIdleFacingLeft = "/Game/Animator/ObjectsAnimations/MarioIdleFacingLeft.png";
    public final static String goomba = "/Game/Animator/ObjectsAnimations/goombaEnemy.gif";
    public final static String superMushroom = "/Game/Animator/ObjectsAnimations/superMushroom.png";
    public final static String marioGrowing = "/Game/Animator/ObjectsAnimations/MarioGrowing.gif";
    public final static String background = "/Game/Animator/ObjectsAnimations/background.png";
    public final static String bigMarioFacingRight = "/Game/Animator/ObjectsAnimations/bigMarioFacingRight.png";
    public final static String bigMarioFacingLeft = "/Game/Animator/ObjectsAnimations/bigMarioFacingLeft.png";
    public final static String brickBox = "/Game/Animator/ObjectsAnimations/box.png";
    public final static String marioDead = "/Game/Animator/ObjectsAnimations/marioDead.png";
    public final static String ground = "/Game/Animator/ObjectsAnimations/ground.png";
    public final static String coin = "/Game/Animator/ObjectsAnimations/coin.gif";
    public final static String itemBox = "/Game/Animator/ObjectsAnimations/itemBox.gif";
    public final static String emptyItemBox = "/Game/Animator/ObjectsAnimations/emptyItemBox.png";
    public final static String marioJumpingRight = "/Game/Animator/ObjectsAnimations/marioJumpingRight.png";
    public final static String marioJumpingLeft = "/Game/Animator/ObjectsAnimations/marioJumpingLeft.png";
    public final static String pipe = "/Game/Animator/ObjectsAnimations/pipe.png";
    public final static String bigMarioJumpingRight = "/Game/Animator/ObjectsAnimations/bigMarioJumpingRight.png";
    public final static String bigMarioJumpingLeft = "/Game/Animator/ObjectsAnimations/bigMarioJumpingLeft.png";
    public final static String koopaFacingRight = "/Game/Animator/ObjectsAnimations/koopaFacingRight.png";
    public final static String koopaFacingLeft = "/Game/Animator/ObjectsAnimations/koopaFacingLeft.png";
    public final static String flower = "/Game/Animator/ObjectsAnimations/flower.png";
    public final static String explosive = "/Game/Animator/ObjectsAnimations/explosive.gif";
    public final static String castle = "/Game/Animator/ObjectsAnimations/castle.png";
    public final static String cloud = "/Game/Animator/ObjectsAnimations/cloud.png";
    public final static String goombaDead = "/Game/Animator/ObjectsAnimations/goombaDead.png";
    public final static String fireMarioFacingRight = "/Game/Animator/ObjectsAnimations/fireMarioFacingRight.png";
    public final static String fireMarioFacingLeft = "/Game/Animator/ObjectsAnimations/fireMarioFacingLeft.png";
    public final static String fireMarioRunningRight = "/Game/Animator/ObjectsAnimations/fireMarioRunningRight.gif";
    public final static String fireMarioRunningLeft = "/Game/Animator/ObjectsAnimations/fireMarioRunningLeft.gif";
    public final static String fireMarioJumpingRight = "/Game/Animator/ObjectsAnimations/fireMarioJumpingRight.png";
    public final static String fireMarioJumpingLeft = "/Game/Animator/ObjectsAnimations/fireMarioJumpingLeft.png";
    public final static String koopasShell = "/Game/Animator/ObjectsAnimations/koopasShell.png";
    public final static String gameOver = "/Game/Animator/ObjectsAnimations/gameOver.jpg";
    public final static String block = "/Game/Animator/ObjectsAnimations/block.png";

    public static void marioGrowingAnimation(Mario mario, MarioManager marioManager) {

        mario.changeImage(Animator.marioGrowing);

        Input.lock();
        /*Designer.scene.setOnKeyPressed(null);
        Designer.scene.setOnKeyReleased(null);

         */

        animator = new AnimationTimer() {

            private int currentTime = 0;

            @Override
            public void handle(long l) {


                if (currentTime++ == 175) {

                    mario.initializeActions(mario.getComponent(Rigidbody.class));
                    Input.unlock();
                    mario.changeImage(Animator.bigMarioFacingRight);
                    marioManager.growMario();
                    this.currentTime = 0;
                    this.stop();
                    return;
                }

            }
        };

        animator.start();
    }

    public static void marioDeadAnimation(MarioManager marioManager) {

        SoundManager.playSound(Sounds.marioLosesLifeSound);

        Designer.scene.setOnKeyPressed(null);
        Designer.scene.setOnKeyReleased(null);

        marioManager.getMario().changeImage(Animator.marioDead);

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

                    mario.changeImage(Animator.marioIdleFacingRight);
                    mario.setImmune(false);
                    this.stop();
                }

            }

        };

        animator.start();
    }

    public static void marioGettingCoinFromItemBoxAnimation(GameObject gameObject) {

        double x = gameObject.getComponent(Transform.class).getPos().x;
        double y = gameObject.getComponent(Transform.class).getPos().y;

        double[] arr = new double[2];
        arr[0] = x;
        arr[1] = y - 50;

        animator = new AnimationTimer() {

            private int currentTime = 0;

            @Override
            public void handle(long l) {

                RenderEngine.renderImage(Animator.coin, arr[0], arr[1]--);

                if (this.currentTime++ == 110) {

                    this.stop();
                }
            }
        };


        animator.start();

    }

    public static void goombaDeadAnimation(Goomba goomba) {

        goomba.changeImage(Animator.goombaDead);

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
