package Animator;

import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import Designer.Designer;
import GameObjects.Mario;
import SoundEffects.SoundManager;
import SoundEffects.Sounds;
import javafx.animation.AnimationTimer;

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

    public static void marioDeadAnimation() {

        SoundManager.playSound(Sounds.marioLosesLifeSound);

        Designer.scene.setOnKeyPressed(null);
        Designer.scene.setOnKeyReleased(null);

        animator = new AnimationTimer() {

            int currentTime = 0;

            @Override
            public void handle(long l) {

                if (currentTime++ == 300) {

                    // restart mario at default position
                    this.stop();
                    return;
                }

            }
        };

        animator.start();
    }
}
