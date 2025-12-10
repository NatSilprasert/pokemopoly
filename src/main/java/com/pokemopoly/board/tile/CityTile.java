package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.MusicManager;
import com.pokemopoly.ui.ShopUI;
import com.pokemopoly.board.Tile;
import com.pokemopoly.player.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.function.Consumer;

public class CityTile extends Tile {

    private final StackPane rootPane;
    private final Consumer<Void> endTurnCallback;
    private final MusicManager musicManager;

    public CityTile(String name, int index, StackPane rootPane, Consumer<Void> endTurnCallback, MusicManager musicManager) {
        super(name, index);
        this.rootPane = rootPane;
        this.endTurnCallback = endTurnCallback;
        this.musicManager = musicManager;
    }

    @Override
    public void onLand(Player player, Game game) {

        player.getTeam().forEach(p -> p.setHp(p.getMaxHp()));
        System.out.println(player.getName() + " healed all Pokemon!");

        musicManager.fadeOutCurrent(1, () -> musicManager.playMusicForScene("pokemoncenter"));
        showShopOverlay(player, game);
    }

    private void showShopOverlay(Player player, Game game) {

        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 20;");


        Label label = new Label("Welcome to the City Shop");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        HBox shopUI = new HBox();
        shopUI.getChildren().add(new ShopUI(player));
        shopUI.setAlignment(Pos.CENTER);

        Button exitBtn = new Button("EXIT SHOP");
        exitBtn.setStyle("""
                -fx-background-color: #cc0000;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-padding: 8 20;
                -fx-background-radius: 10;
                -fx-border-radius: 10;
                -fx-border-color: black;
                -fx-border-width: 2;
                """);

        exitBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            System.out.println("Exited Shop.");
            if (endTurnCallback != null) endTurnCallback.accept(null);
            musicManager.fadeOutCurrent(1, () -> musicManager.playWithFade("palletTown", true, 1.0));
        });

        overlay.getChildren().addAll(label, shopUI, exitBtn);

        StackPane.setAlignment(overlay, Pos.CENTER);
        rootPane.getChildren().add(overlay);
    }
}
