package com.pokemopoly;

import com.pokemopoly.ui.NumberPlayerSelectUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Game game;

    @Override
    public void start(Stage primaryStage) {
        game = new Game();
        NumberPlayerSelectUI numberPlayerSelectUI = new NumberPlayerSelectUI(game, primaryStage);
        primaryStage.setTitle("Pokemon Monopoly - Setup");
        primaryStage.setScene(numberPlayerSelectUI.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}