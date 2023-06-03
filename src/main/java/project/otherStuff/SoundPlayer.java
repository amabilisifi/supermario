package project.otherStuff;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class SoundPlayer {
    private final MediaPlayer mediaPlayer;
    private static SoundPlayer mainSoundPlayer;

    public SoundPlayer(String path) {
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public void playOnRepeat() {
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public void mute() {
        mediaPlayer.setVolume(0);
    }

    public static void setMainSoundPlayer(SoundPlayer mainSoundPlayer) {
        SoundPlayer.mainSoundPlayer = mainSoundPlayer;
    }

    public static SoundPlayer getMainSoundPlayer() {
        return mainSoundPlayer;
    }
}
