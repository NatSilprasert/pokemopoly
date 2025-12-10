package com.pokemopoly.ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class MusicManager {

    private MediaPlayer currentPlayer;
    private final Map<String, Media> mediaLibrary = new HashMap<>();

    public MusicManager() {
        addMusic("title", "/sound/Title Screen.mp3");
        addMusic("palletTown", "/sound/Pallet Town.mp3");
        addMusic("pokemoncenter", "/sound/Pokemon Center.mp3");
        addMusic("battle", "/sound/Final Battle! (Rival).mp3");
        addMusic("opening", "/sound/Opening Movie.mp3");
        addMusic("cave","/sound/Mt. Moon.mp3");
        addMusic("daycare", "/sound/Hurry Along.mp3");
    }

    public void addMusic(String key, String path) {
        Media media = new Media(getClass().getResource(path).toExternalForm());
        mediaLibrary.put(key, media);
    }

    public void play(String key, boolean loop) {
        stopCurrent();

        Media media = mediaLibrary.get(key);
        if (media == null) return;

        currentPlayer = new MediaPlayer(media);
        currentPlayer.setVolume(1.0);
        currentPlayer.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
        currentPlayer.play();
    }

    public void stopCurrent() {
        if (currentPlayer != null) {
            currentPlayer.stop();
            currentPlayer = null;
        }
    }

    public void fadeOutCurrent(double seconds, Runnable afterFade) {
        if (currentPlayer == null) {
            if (afterFade != null) afterFade.run();
            return;
        }

        Timeline fade = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(currentPlayer.volumeProperty(), currentPlayer.getVolume())),
                new KeyFrame(Duration.seconds(seconds),
                        new KeyValue(currentPlayer.volumeProperty(), 0))
        );

        fade.setOnFinished(e -> {
            stopCurrent();
            if (afterFade != null) afterFade.run();
        });

        fade.play();
    }

    public void playWithFade(String key, boolean loop, double fadeInSeconds) {
        Media media = mediaLibrary.get(key);
        if (media == null) return;

        currentPlayer = new MediaPlayer(media);
        currentPlayer.setVolume(0); // เริ่มจาก 0
        currentPlayer.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
        currentPlayer.play();

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(currentPlayer.volumeProperty(), 0)),
                new KeyFrame(Duration.seconds(fadeInSeconds), new KeyValue(currentPlayer.volumeProperty(), 1.0))
        );
        fadeIn.play();
    }

    public MediaPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void playMusicForScene(String sceneKey) {
        switch (sceneKey) {
            case "title" -> play("title", true);
            case "opening" -> play("opening", true);
            case "palletTown" -> play("palletTown", true);
            case "pokemoncenter" -> play("pokemoncenter", true);
            case "battle" -> play("battle", true);
            case "cave" -> play("cave", true);
            case "daycare" -> play("daycare", true);
            default -> stopCurrent();
        }
    }
}

