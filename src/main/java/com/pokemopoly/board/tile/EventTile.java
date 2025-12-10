
package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.cards.EventCardUI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.util.function.Consumer;

public class EventTile extends Tile {

    private final StackPane rootPane;
    private final Consumer<Void> endTurnCallback;

    public EventTile(String name, int index, StackPane rootPane, Consumer<Void> endTurnCallback) {
        super(name, index);
        this.rootPane = rootPane;
        this.endTurnCallback = endTurnCallback;
    }

    @Override
    public void onLand(Player player, Game game) {

        EventCard eventCard = game.getDeckManager().drawEvent();

        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 20;");
        overlay.setMaxWidth(800);

        javafx.scene.control.Label label = new javafx.scene.control.Label("You found an Event Card!");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        EventCardUI ui = new EventCardUI(eventCard);
        overlay.getChildren().addAll(label, ui);

        Button keepBtn = new Button("OK");
        keepBtn.setOnAction(e -> {
            eventCard.activate(game);
            rootPane.getChildren().remove(overlay);
            if (endTurnCallback != null) endTurnCallback.accept(null);
        });

        overlay.getChildren().add(keepBtn);
        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }
}