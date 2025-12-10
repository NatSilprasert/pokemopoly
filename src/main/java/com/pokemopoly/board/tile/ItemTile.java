package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.ui.cards.ItemCardUI;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ItemTile extends Tile {

    private final StackPane rootPane;
    private final Consumer<Void> endTurnCallback;

    public ItemTile(String name, int index, StackPane rootPane, Consumer<Void> endTurnCallback) {
        super(name, index);
        this.rootPane = rootPane;
        this.endTurnCallback = endTurnCallback;
    }

    public void onLand(Player player, Game game) {
        ItemCard newItem = game.getDeckManager().drawItem();
        Hand hand = player.getHand();

        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 20;");
        overlay.setMaxWidth(800);

        javafx.scene.control.Label label = new javafx.scene.control.Label("You got a new item!");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        ItemCardUI newItemUI = new ItemCardUI(newItem);
        overlay.getChildren().addAll(label, newItemUI);

        Button keepBtn = new Button("Keep");
        keepBtn.setOnAction(e -> {
            if (!hand.isFull()) {
                hand.add(newItem);
                System.out.println(newItem.getName() + " has been added to the hand!");
                rootPane.getChildren().remove(overlay);
                if (endTurnCallback != null) endTurnCallback.accept(null);
            } else {
                rootPane.getChildren().remove(overlay);
                showFullHandOverlay(player, newItem);
            }
        });

        overlay.getChildren().add(keepBtn);
        StackPane.setAlignment(overlay, Pos.CENTER);
        rootPane.getChildren().add(overlay);
    }

    private void showFullHandOverlay(Player player, ItemCard newItem) {
        Hand hand = player.getHand();
        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 20;");
        overlay.setMaxWidth(800);

        javafx.scene.control.Label fullLabel = new javafx.scene.control.Label("Your hand is full! Choose action:");
        fullLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 14px;");
        overlay.getChildren().add(fullLabel);

        int capacity = hand.getCapacity();
        GridPane handGrid = new GridPane();
        handGrid.setHgap(10);
        handGrid.setVgap(10);
        handGrid.setAlignment(Pos.CENTER);

        int cols = capacity == 6 ? 3 : capacity;
        int rows = capacity == 6 ? 2 : 1;

        List<ItemCardUI> cardUIs = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            ItemCardUI cardUI = new ItemCardUI(hand.getItems().get(i));
            cardUI.setSize(2);

            int idx = i;
            cardUI.setOnMouseClicked(e -> {
                handGrid.getChildren().forEach(node -> node.setStyle(""));
                cardUI.setStyle("-fx-border-color: red; -fx-border-width: 2;");
                overlay.setUserData(idx);
            });

            int row = i / cols;
            int col = i % cols;
            handGrid.add(cardUI, col, row);
            cardUIs.add(cardUI);
        }

        overlay.getChildren().add(handGrid);

        HBox btnBox = new HBox(10);
        btnBox.setAlignment(Pos.CENTER);

        Button discardBtn = new Button("Discard New Item");
        discardBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);

            System.out.println(newItem.getName() + " has been discarded!");
            if (endTurnCallback != null) endTurnCallback.accept(null);
        });

        Button swapBtn = new Button("Swap with Selected");
        swapBtn.setOnAction(e -> {
            Object selectedIdxObj = overlay.getUserData();
            if (selectedIdxObj != null) {
                int selectedIdx = (int) selectedIdxObj;

                System.out.println(player.getHand().getItems().get(selectedIdx).getName() + " has been discarded!");
                System.out.println(newItem.getName() + " has been added to the hand!");

                hand.setItem(selectedIdx, newItem);
                rootPane.getChildren().remove(overlay);

                if (endTurnCallback != null) endTurnCallback.accept(null);
            }
        });

        btnBox.getChildren().addAll(discardBtn, swapBtn);
        overlay.getChildren().add(btnBox);

        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }

}