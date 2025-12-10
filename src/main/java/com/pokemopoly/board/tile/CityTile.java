package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.MusicManager;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.cards.ItemCardUI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
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

        // ---------------------
        // 1. Heal all Pokémon
        // ---------------------
        player.getTeam().forEach(p -> p.setHp(p.getMaxHp()));
        System.out.println(player.getName() + " healed all Pokemon!");

        // ---------------------
        // 2. Open SHOP overlay
        // ---------------------
        musicManager.fadeOutCurrent(1, () -> musicManager.playMusicForScene("pokemoncenter"));
        showShopOverlay(player, game);
    }


    // ====================
    // SHOP OVERLAY
    // ====================
    private void showShopOverlay(Player player, Game game) {

        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 20;");


        Label label = new Label("Welcome to the City Shop");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        // ตัว Shop จริง ๆ (เป็น HBox ตามคลาส Shop)
        HBox shopUI = new HBox();
        shopUI.getChildren().add(new com.pokemopoly.Shop(player));
        shopUI.setAlignment(Pos.CENTER);

        // ปุ่ม Exit หลังซื้อเสร็จ
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




    private void showFullHandOverlay(Player player, ItemCard newItem, VBox shopOverlay) {

        Hand hand = player.getHand();

        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 20;");
        overlay.setMaxWidth(800);

        Label fullLabel = new Label("Your hand is full! Choose action:");
        fullLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-font-weight: bold;");
        overlay.getChildren().add(fullLabel);

        int capacity = hand.getCapacity();

        GridPane handGrid = new GridPane();
        handGrid.setHgap(10);
        handGrid.setVgap(10);
        handGrid.setAlignment(Pos.CENTER);

        int cols = capacity == 6 ? 3 : capacity;
        int rows = capacity == 6 ? 2 : 1;

        for (int i = 0; i < capacity; i++) {
            ItemCardUI cardUI = new ItemCardUI(hand.getItems().get(i));
            cardUI.setSize(2);

            int idx = i;
            cardUI.setOnMouseClicked(e -> {
                handGrid.getChildren().forEach(node -> node.setStyle(""));
                cardUI.setStyle("-fx-border-color: red; -fx-border-width: 2;");
                overlay.setUserData(idx);
            });

            int col = i % cols;
            int row = i / cols;
            handGrid.add(cardUI, col, row);
        }

        overlay.getChildren().add(handGrid);

        HBox btnBox = new HBox(10);
        btnBox.setAlignment(Pos.CENTER);

        // ปุ่ม discard ของใหม่
        Button discardBtn = new Button("Discard New Item");
        discardBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            System.out.println("Discarded: " + newItem.getName());
        });

        // ปุ่ม Swap
        Button swapBtn = new Button("Swap Selected");
        swapBtn.setOnAction(e -> {
            Object idxObj = overlay.getUserData();
            if (idxObj != null) {
                int idx = (int) idxObj;
                ItemCard old = hand.getItems().get(idx);
                System.out.println("Swapped out: " + old.getName());
                System.out.println("Added: " + newItem.getName());
                hand.setItem(idx, newItem);

                rootPane.getChildren().remove(overlay);
            }
        });

        btnBox.getChildren().addAll(discardBtn, swapBtn);
        overlay.getChildren().add(btnBox);

        // วาง overlay ข้างบน shopOverlay
        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }
}
