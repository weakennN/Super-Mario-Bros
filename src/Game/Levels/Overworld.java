package Game.Levels;

import Game.Animator.GlobalAnimations;

public class Overworld extends World {

    public Overworld() {

        super();

        super.setBackGround(GlobalAnimations.background, 13440, 1080);

        super.addLevel(new Level1());
    }
}
