package Levels;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class World {

    private Image backGround;
    private List<Level> levels;
    private int currentLevel;

    public World() {

        this.levels = new ArrayList<>();
    }

    protected void setBackGround(Image backGround) {

        this.backGround = backGround;
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

}
