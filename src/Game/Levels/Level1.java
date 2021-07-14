package Game.Levels;

import Game.TileMaps.TileMaps;

public class Level1 extends Level {

    public Level1() {
        super();
    }

    @Override
    public void initLevel() {
        super.initLevel();
        super.initLevelObjects(TileMaps.overWorldLevel1);
    }
}
