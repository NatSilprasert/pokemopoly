package com.pokemopoly;

import com.pokemopoly.ui.NumberPlayerSelectUI;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {


    private MusicManager musicManager;

    @Override
    public void start(Stage primaryStage) {
        // โหลดเพลงจาก resources
        musicManager = new MusicManager();
        musicManager.playMusicForScene("title");

        NumberPlayerSelectUI numberPlayerSelectUI = new NumberPlayerSelectUI(new Game(), primaryStage, musicManager);
        primaryStage.setTitle("Pokemon Monopoly - Setup");
        primaryStage.setScene(numberPlayerSelectUI.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}