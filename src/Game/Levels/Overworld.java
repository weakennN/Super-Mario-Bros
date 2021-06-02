package Game.Levels;

import Game.Animator.Animator;

public class Overworld extends World {

    public Overworld() {

        super();

        super.setBackGround(Animator.background, 13440, 1080);

        super.addLevel(new Level1());
    }
}
