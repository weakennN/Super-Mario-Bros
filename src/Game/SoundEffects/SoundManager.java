package Game.SoundEffects;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class SoundManager {

    private static Media media;
    private static MediaPlayer mediaPlayer;
    private static AudioClip audioClip;

    public static void playMarioBackgroundTheme() {

        File file = new File(Sounds.superMarioBackgroundMusic);

        media = new Media(Paths.get(file.getAbsolutePath()).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.04);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
    }

    public static void playSound(String sound) {

        File file = new File(sound);

        audioClip = new AudioClip(file.toURI().toString());
        audioClip.setVolume(0.05);
        audioClip.play();
    }

    public static void stop() {

        mediaPlayer.stop();
        audioClip.stop();
    }

}
