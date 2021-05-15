package Game.SoundEffects;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class SoundManager {

    private static Media media;
    private static MediaPlayer mediaPlayer;
    private static AudioClip audioClip;

    public static void playMarioBackgroundTheme() {

        media = new Media(Paths.get(Sounds.superMarioBackgroundMusic).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.04);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
    }

    public static void playSound(String sound) {

        audioClip = new AudioClip(Paths.get(sound).toUri().toString());
        audioClip.setVolume(0.04);
        audioClip.play();
    }

}
