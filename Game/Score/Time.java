package Game.Score;

import java.util.Timer;
import java.util.TimerTask;

public class Time {

    private Timer timer;
    private TimerTask timerTask;
    private int seconds;

    public Time() {

        this.timer = new Timer();

        this.seconds = 400;
        this.timerTask = new TimerTask() {

            @Override
            public void run() {

                seconds--;
            }

        };

        timer.scheduleAtFixedRate(timerTask, 300, 650);
    }

    public int getSeconds() {

        return this.seconds;
    }

    public Timer getTimer() {

        return this.timer;
    }

}
