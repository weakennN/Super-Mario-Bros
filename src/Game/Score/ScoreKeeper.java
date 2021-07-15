package Game.Score;

import UIEngine.Designer;

public class ScoreKeeper {

    public static int score = 0;
    public static int coins = 0;
    public static int lives = 3;
    public static int world = 1;
    public static int level = 1;
    public static Time time = new Time();

    public static void updateScore() {
        Designer.updateScore(ScoreKeeper.score, ScoreKeeper.coins, world, level, time.getSeconds(), lives);
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
        time.getTimer().cancel();
        time = new Time();
    }

    public static void stopTimer() {
        time.getTimer().cancel();
    }

    public static void restartScore(){
        score = 0;
        coins = 0;
    }

    public static void restart(){
        restartScore();
        restartTimer();
        lives = 3;
        level = 1;
        world = 1;
    }
}
