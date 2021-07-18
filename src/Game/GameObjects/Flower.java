package Game.GameObjects;

import Engine.ECS.Animator.Animator;
import Game.Collision.Collision;
import Game.Common.GlobalVariables;
import Game.GameObjects.Mario.Mario;

public class Flower extends GameObject {

    public Flower(String tag) {
        super(tag);
    }

    @Override
    public void start() {
        super.getComponent(Animator.class).getAnimationController().playAnimation("default");
    }

    @Override
    public void update() {
        super.updateComponents();
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (other.getTag().equals(GlobalVariables.marioTag)) {
            Mario mario = (Mario) other;
            mario.getMarioManager().powerUpWithFireFireFlower(this);
        }
    }
}
