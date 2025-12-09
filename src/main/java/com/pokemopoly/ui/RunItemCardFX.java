package com.pokemopoly.ui;

import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.items.*;
import com.pokemopoly.ui.cards.ItemCardUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RunItemCardFX extends Application {

    @Override
    public void start(Stage stage) {

        ItemCard mockCard = new TrainPass();

        ItemCardUI cardUI = new ItemCardUI(mockCard);

        StackPane root = new StackPane(cardUI);
        root.setStyle("-fx-background-color: #2b2b2b;");

        Scene scene = new Scene(root, 600, 600, Color.DARKGRAY);

        stage.setTitle("Pokemopoly - Event Card Preview (JavaFX)");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}