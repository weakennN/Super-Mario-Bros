package Score;

import RenderEngine.RenderEngine;

public class ScoreKeeper {

    public static int score = 0;
    public static int coins = 0;
    public static int lives = 3;
    public static int world = 1;
    public static int level = 1;
    public static Time time = new Time();

    // create init method that inits everything
    public static void updateScore() {

        RenderEngine.showScore(ScoreKeeper.score, ScoreKeeper.coins, world, level, time.getSeconds(), lives);
    }

    public static void decreaseLives() {

        lives--;
    }

    public static void incrementScore(int score_) {

        score += score_;
    }

    public static void incrementCoins() {

        coins++;
    }

    public static void restartTimer() {

        time = new Time();
    }
}
