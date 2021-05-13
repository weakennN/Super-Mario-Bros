package Animator;

import Common.GlobalVariables;
import Components.Rigidbody;
import Designer.Designer;
import GameObjects.AI.Goomba;
import GameObjects.GameObject;
import GameObjects.Mario;
import GameObjects.MarioManager;
import SoundEffects.SoundManager;
import SoundEffects.Sounds;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Animator {

    private static AnimationTimer animator;

    public final static String marioRunningRight = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\MarioRunningRightDir.gif";
    public final static String marioRunningLeft = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\MarioRunningLeftDir.gif";
    public final static String bigMarioRunningRight = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\BigMarioRunningRightDir.gif";
    public final static String bigMarioRunningLeft = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\BigMarioRunningLeftDir.gif";
    public final static String marioIdleFacingRight = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\MarioIdleFacingRight.png";
    public final static String marioIdleFacingLeft = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\MarioIdleFacingLeft.png";
    public final static String goomba = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\goombaEnemy.gif";
    public final static String superMushroom = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\superMushroom.png";
    public final static String marioGrowing = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\MarioGrowing.gif";
    public final static String background = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\background.png";
    public final static String bigMarioFacingRight = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\bigMarioFacingRight.png";
    public final static String bigMarioFacingLeft = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\bigMarioFacingLeft.png";
    public final static String brickBox = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\box.png";
    public final static String marioDead = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\marioDead.png";
    public final static String ground = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\ground.png";
    public final static String coin = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\coin.gif";
    public final static String itemBox = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\itemBox.gif";
    public final static String emptyItemBox = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\emptyItemBox.png";
    public final static String marioJumpingRight = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\marioJumpingRight.png";
    public final static String marioJumpingLeft = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\marioJumpingLeft.png";
    public final static String pipe = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\pipe.png";
    public final static String bigMarioJumpingRight = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\bigMarioJumpingRight.png";
    public final static String bigMarioJumpingLeft = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\bigMarioJumpingLeft.png";
    public final static String koopaFacingRight = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\koopaFacingRight.png";
    public final static String koopaFacingLeft = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\koopaFacingLeft.png";
    public final static String flower = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\flower.png";
    public final static String explosive = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\explosive.png";
    public final static String castle = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\castle.png";
    public final static String cloud = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\cloud.png";
    public final static String goombaDead = "C:\\Users\\PC\\IdeaProjects\\Super Mario Game\\src\\Animator\\ObjectsAnimations\\goombaDead.png";

    public static void marioGrowingAnimation(Mario mario) {

        mario.changeImage(Animator.marioGrowing);

        Designer.scene.setOnKeyPressed(null);
        Designer.scene.setOnKeyReleased(null);

        animator = new AnimationTimer() {

            private int currentTime = 0;

            @Override
            public void handle(long l) {


                if (currentTime++ == 175) {

                    mario.initializeActions((Rigidbody) mario.getComponent(GlobalVariables.rigidbodyTag));
                    mario.changeImage(Animator.bigMarioFacingRight);
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

        animator = new AnimationTimer() {

            int currentTime = 0;

            @Override
            public void handle(long l) {

                if (currentTime++ == 300) {

                    // restart mario at default position
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

        double x = gameObject.getPosition().getPos().x;
        double y = gameObject.getPosition().getPos().y;

        double[] arr = new double[2];
        arr[0] = x;
        arr[1] = y - 50;

        animator = new AnimationTimer() {

            private int currentTime = 0;

            @Override
            public void handle(long l) {

                try {
                    Designer.gc.drawImage(new Image(new FileInputStream(Animator.coin)), arr[0], arr[1]--);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


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
