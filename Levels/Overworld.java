package Levels;

import Animator.Animator;

public class Overworld extends World {

    public Overworld() {

        super();

        super.setBackGround(Animator.background, 5760, 1080);

        super.addLevel(new Level1());
    }
}
