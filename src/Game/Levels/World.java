package Game.Levels;

import java.util.ArrayList;
import java.util.List;

public abstract class World {

    private WorldBackground backGround;
    private List<Level> levels;
    private int currentLevel;

    public World() {
        this.levels = new ArrayList<>();
        this.currentLevel = 0;
    }

    protected void setBackGround(double sizeX, double sizeY) {
        this.backGround = new WorldBackground(sizeX, sizeY);
    }

    public Level getCurrentLevel() {
        return this.levels.get(this.currentLevel);
    }

    protected void addLevel(Level level) {
        this.levels.add(level);
    }

    public void finishedLevel() {
        this.currentLevel++;
    }

    public WorldBackground getBackGround() {
        return this.backGround;
    }
}
