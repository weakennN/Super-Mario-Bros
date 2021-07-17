package Game.Levels;

public class Overworld extends World {

    public Overworld() {
        super();
        super.setBackGround(13440, 1080);
        super.addLevel(new Level1());
    }
}
