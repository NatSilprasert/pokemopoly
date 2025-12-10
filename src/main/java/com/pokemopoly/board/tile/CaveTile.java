
package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.ui.MusicManager;
import com.pokemopoly.board.Tile;
import com.pokemopoly.player.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CaveTile extends Tile {

    private final StackPane rootPane;
    private final Runnable endTurnCallback;
    private final MusicManager musicManager;

    public CaveTile(String name, int index, StackPane rootPane, Runnable endTurnCallback, MusicManager musicManager) {
        super(name, index);
        this.rootPane = rootPane;
        this.endTurnCallback = endTurnCallback;
        this.musicManager = musicManager;
    }

    @Override
    public void onLand(Player player, Game game) {
        System.out.println(player.getName() + " landed on " + name + " and must skip next turn!");
        player.setSkipTurn(true);

        musicManager.fadeOutCurrent(1, () -> musicManager.playMusicForScene("cave"));

        VBox overlay = new VBox(15);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 30; -fx-background-radius: 12;");

        Label title = new Label("Cave Trap!");
        title.setTextFill(Color.WHITE);
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label message = new Label(player.getName() + " is trapped in the cave!\nYou must skip your next turn.");
        message.setTextFill(Color.LIGHTGRAY);
        message.setStyle("-fx-font-size: 16px;");
        message.setAlignment(Pos.CENTER);

        Button ok = new Button("OK");
        ok.setStyle("-fx-font-size: 16px; -fx-padding: 6 12;");
        ok.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);

            if (endTurnCallback != null) endTurnCallback.run();
            musicManager.fadeOutCurrent(1, () -> musicManager.playWithFade("palletTown", true, 1.0));
        });

        overlay.getChildren().addAll(title, message, ok);
        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }
}