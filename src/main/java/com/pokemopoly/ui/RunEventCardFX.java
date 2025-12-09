package com.pokemopoly.ui;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.event.LegendaryInArea;
import com.pokemopoly.ui.EventCardUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RunEventCardFX extends Application {

    @Override
    public void start(Stage stage) {

        EventCard mockCard = new LegendaryInArea();

        EventCardUI cardUI = new EventCardUI(mockCard);

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
