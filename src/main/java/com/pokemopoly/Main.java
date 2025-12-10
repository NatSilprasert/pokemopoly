package com.pokemopoly;

import com.pokemopoly.ui.MusicManager;
import com.pokemopoly.ui.NumberPlayerSelectUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    private MusicManager musicManager;

    @Override
    public void start(Stage primaryStage) {
        musicManager = new MusicManager();
        musicManager.playMusicForScene("title");

        Game game = new Game(musicManager);

        NumberPlayerSelectUI numberPlayerSelectUI = new NumberPlayerSelectUI(game, primaryStage, musicManager);
        primaryStage.setTitle("Pokemon Monopoly - Setup");
        primaryStage.setScene(numberPlayerSelectUI.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}