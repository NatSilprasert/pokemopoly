package com.pokemopoly;

import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.cards.ItemCardUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class Shop extends HBox {

    private final Player player;

    // UI Components
    private VBox summaryBox;
    private Label playerCoinLabel;

    // State
    private ShopItemData selectedItem = null;

    // Inner class to hold shop item data structure
    private static class ShopItemData {
        String id;
        String name;
        int price;
        String type; // "BALL" or "ITEM"
        String description; // For ItemCardUI

        public ShopItemData(String id, String name, int price, String type, String description) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.type = type;
            this.description = description;
        }
    }

    // Concrete implementation of ItemCard for the Shop to use
    private static class SimpleItemCard extends ItemCard {
        public SimpleItemCard(String id, String name, String description) {
            super(id, name, description);
        }

        @Override
        public void activate(Game game) {
            // Logic for item activation would go here
            System.out.println("Used " + getName());
        }
    }

    public Shop(Player player) {
        this.player = player;

        // Root Styling
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #2b2b2b;"); // Dark background like the game board
        this.setPrefSize(500, 500);

        // --- LEFT SIDE ---
        VBox leftPane = new VBox(7.5);
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setPrefWidth(200);

        // 1. Top Left Logo
        HBox headerBox = new HBox(5);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        StackPane logoPane = new StackPane();
        Circle bgCircle = new Circle(25, Color.WHITE);
        Label shopText = new Label("SHOP");
        shopText.setFont(Font.font("Monospaced", FontWeight.BOLD, 18));
        logoPane.getChildren().addAll(bgCircle, shopText);

        // Player Coin Display (Added for UX to see current money)
        playerCoinLabel = new Label("Coins: " + player.getCoin());
        playerCoinLabel.setTextFill(Color.GOLD);
        playerCoinLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 18));

        headerBox.getChildren().addAll(logoPane, playerCoinLabel);

        // 2. Shop Image
        ImageView shopImageView = new ImageView();
        try {
            shopImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/shop/shop.png"))));
        } catch (Exception e) {
            // Placeholder if image missing
            shopImageView.setImage(null);
        }
        shopImageView.setFitWidth(190);
        shopImageView.setPreserveRatio(true);

        StackPane imageFrame = new StackPane(shopImageView);
        imageFrame.setStyle("-fx-border-color: white; -fx-border-width: 4; -fx-background-color: black;");
        imageFrame.setPadding(new Insets(2.5));

        // 3. Summary / Order Box (Empty initially)
        summaryBox = new VBox(5);
        summaryBox.setPrefHeight(150);
        summaryBox.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 2;");
        summaryBox.setAlignment(Pos.CENTER);
        summaryBox.setPadding(new Insets(5));

        // Initialize Empty State
        clearSummary();

        leftPane.getChildren().addAll(headerBox, imageFrame, summaryBox);

        // --- RIGHT SIDE (Item Grid) ---
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #2b2b2b; -fx-border-color: transparent;");

        GridPane itemGrid = new GridPane();
        itemGrid.setHgap(7.5);
        itemGrid.setVgap(7.5);
        itemGrid.setPadding(new Insets(5));
        itemGrid.setStyle("-fx-background-color: #2b2b2b;");

        // --- ROW 1: BALLS ---
        addItemToGrid(itemGrid, new ShopItemData("pokeball", "Pokeball", 1, "BALL", "Catch rate 1x"), 0, 0, "/shop/pokeball.png");
        addItemToGrid(itemGrid, new ShopItemData("greatball", "Greatball", 2, "BALL", "Catch rate 1.5x"), 1, 0, "/shop/greatball.png");
        addItemToGrid(itemGrid, new ShopItemData("ultraball", "UltraBall", 3, "BALL", "Catch rate 2x"), 2, 0, "/shop/ultraball.png");

        // --- ROW 2: POTIONS ---
        addItemToGrid(itemGrid, new ShopItemData("potion", "Potion", 1, "ITEM", "Restore 3 HP to one Pokémon in your team."), 0, 1, "/item/potion.png");
        addItemToGrid(itemGrid, new ShopItemData("superpotion", "Super Potion", 2, "ITEM", "Restore 5 HP to one Pokémon in your team."), 1, 1, "/item/superpotion.png");
        addItemToGrid(itemGrid, new ShopItemData("repel", "Repel", 1, "ITEM", "Move forward 2 tiles. You cannot roll dice or catch Pokémon this turn."), 2, 1, "/item/repel.png");

        // --- ROW 3: UTILITY ---
        addItemToGrid(itemGrid, new ShopItemData("superrepel", "Super Repel", 2, "ITEM", "Move forward 4 tiles. You cannot roll dice or catch Pokémon this turn."), 0, 2, "/item/superrepel.png");
        addItemToGrid(itemGrid, new ShopItemData("fullheal", "Full Heal", 2, "ITEM", "Cures all abnormal status conditions from one Pokémon in your team."), 1, 2, "/item/fullheal.png");
        addItemToGrid(itemGrid, new ShopItemData("rarecandy", "Rare Candy", 5, "ITEM", "Choose 1 Pokémon to evolve!"), 2, 2, "/item/rarecandy.png");

        // --- ROW 4: BICYCLE ---
        addItemToGrid(itemGrid, new ShopItemData("bicycle", "Bicycle", 5, "ITEM", "Roll Twice."), 0, 3, "/item/bicycle.png");

        scrollPane.setContent(itemGrid);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

        this.getChildren().addAll(leftPane, scrollPane);
    }

    private void addItemToGrid(GridPane grid, ShopItemData itemData, int col, int row, String imagePath) {
        VBox itemBox = new VBox(2.5);
        itemBox.setAlignment(Pos.CENTER);
        itemBox.setStyle("-fx-background-color: black; -fx-border-color: #555; -fx-border-width: 2;");
        itemBox.setPrefSize(70, 80);
        itemBox.setCursor(Cursor.HAND);

        // Hover Effect
        itemBox.setOnMouseEntered(e -> itemBox.setStyle("-fx-background-color: #222; -fx-border-color: white; -fx-border-width: 2;"));
        itemBox.setOnMouseExited(e -> {
            if (selectedItem != itemData)
                itemBox.setStyle("-fx-background-color: black; -fx-border-color: #555; -fx-border-width: 2;");
            else
                itemBox.setStyle("-fx-background-color: #222; -fx-border-color: gold; -fx-border-width: 3;");
        });

        // Image
        ImageView imgView = new ImageView();
        try {
            imgView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
        } catch (Exception e) {
            // Fallback
        }
        imgView.setFitWidth(40);
        imgView.setFitHeight(40);
        imgView.setPreserveRatio(true);

        // Price Tag Area
        HBox priceBox = new HBox(2.5);
        priceBox.setAlignment(Pos.CENTER);
        Circle coin = new Circle(6, Color.GOLD);
        Label priceLabel = new Label(String.valueOf(itemData.price));
        priceLabel.setTextFill(Color.WHITE);
        priceLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        priceBox.getChildren().addAll(coin, priceLabel);

        // Name (Optional, but good for UX, though prompt implies image + price mainly)
        Label nameLabel = new Label(itemData.name);
        nameLabel.setTextFill(Color.LIGHTGRAY);
        nameLabel.setFont(Font.font("Monospaced", 10));

        itemBox.getChildren().addAll(imgView, priceBox, nameLabel);

        // Click Action
        itemBox.setOnMouseClicked(e -> {
            selectedItem = itemData;
            updateSummary();
            // Reset styles for all boxes (simple way)
            for (Node n : grid.getChildren()) {
                n.setStyle("-fx-background-color: black; -fx-border-color: #555; -fx-border-width: 2;");
            }
            itemBox.setStyle("-fx-background-color: #222; -fx-border-color: gold; -fx-border-width: 3;");
        });

        grid.add(itemBox, col, row);
    }

    private void clearSummary() {
        summaryBox.getChildren().clear();
        Label emptyLabel = new Label("Select an item");
        emptyLabel.setTextFill(Color.GRAY);
        emptyLabel.setFont(Font.font("Monospaced", 16));
        summaryBox.getChildren().add(emptyLabel);
        selectedItem = null;
    }

    private void updateSummary() {
        summaryBox.getChildren().clear();

        if (selectedItem == null) {
            clearSummary();
            return;
        }

        // Construct a temporary ItemCard for display purposes
        SimpleItemCard displayCard = new SimpleItemCard(selectedItem.id, selectedItem.name, selectedItem.description);

        Node previewNode;
        String imagePath;

        // 1. กำหนด Path รูปภาพตามประเภทสินค้า
        if ("ITEM".equals(selectedItem.type)) {
            // *** ส่วนที่แก้ไข: Item ใช้ Path /item/[id].png ***
            imagePath = "/item/" + selectedItem.id + ".png";

            // Simple preview for items
            VBox itemPreview = new VBox(5);
            itemPreview.setAlignment(Pos.CENTER);

            ImageView img = new ImageView();
            try {
                img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
            } catch (Exception e) {
                System.err.println("Failed to load Item image from: " + imagePath);
            }

            img.setFitWidth(50);
            img.setPreserveRatio(true);
            Label name = new Label(selectedItem.name);
            name.setTextFill(Color.WHITE);
            name.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));

            // ⭐ NEW: Description with white border + center text
            Label desc = new Label(displayCard.getDescription());
            desc.setTextFill(Color.WHITE);
            desc.setFont(Font.font("Monospaced", FontWeight.NORMAL, 12));
            desc.setWrapText(true);
            desc.setMaxWidth(140);
            desc.setStyle("-fx-text-alignment: center;");
            desc.setAlignment(Pos.CENTER);

            // ห่อด้วยกล่องกรอบขาว
            VBox descriptionBox = new VBox(desc);
            descriptionBox.setStyle(
                    "-fx-border-color: white;" +
                            "-fx-border-width: 2;" +
                            "-fx-padding: 8;" +
                            "-fx-background-color: transparent;"
            );
            descriptionBox.setAlignment(Pos.CENTER);

            itemPreview.getChildren().addAll(img, name, descriptionBox);
            previewNode = itemPreview;
        } else {
            // Ball ยังคงใช้ Path เดิม: /shop/[id].png
            imagePath = "/shop/" + selectedItem.id + ".png";

            // Simple preview for balls
            VBox ballPreview = new VBox(5);
            ballPreview.setAlignment(Pos.CENTER);

            ImageView img = new ImageView();
            try {
                img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
            } catch (Exception e) {
                System.err.println("Failed to load Ball image from: " + imagePath);
            }

            img.setFitWidth(50);
            img.setPreserveRatio(true);
            Label name = new Label(selectedItem.name);
            name.setTextFill(Color.WHITE);
            name.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));

            ballPreview.getChildren().addAll(img, name);
            previewNode = ballPreview;
        }

        // 2. Pricing and Controls (ส่วนนี้ไม่มีการเปลี่ยนแปลง)
        HBox controlBox = new HBox(10);
        controlBox.setAlignment(Pos.CENTER);

        Label costLabel = new Label("Cost: " + selectedItem.price);
        costLabel.setTextFill(Color.GOLD);
        costLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 14));

        Button buyBtn = new Button("BUY");
        styleButton(buyBtn, Color.GREEN);

        Button clearBtn = new Button("CLEAR");
        styleButton(clearBtn, Color.RED);

        controlBox.getChildren().addAll(buyBtn, clearBtn);

        // 3. Status Message
        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Monospaced", 12));
        statusLabel.setWrapText(true);

        // Button Actions
        buyBtn.setOnAction(e -> {
            // ... (Logic การซื้อเดิม) ...
            if (player.getCoin() >= selectedItem.price) {
                boolean success = performPurchase(selectedItem);
                if (success) {
                    playerCoinLabel.setText("Coins: " + player.getCoin());
                    statusLabel.setTextFill(Color.LIGHTGREEN);
                    statusLabel.setText("Purchased " + selectedItem.name + "!");

                    if (player.getCoin() < selectedItem.price) {
                        buyBtn.setDisable(true);
                        buyBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
                    }
                } else {
                    statusLabel.setTextFill(Color.ORANGE);
                    statusLabel.setText("Inventory Full!");
                }
            } else {
                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("Not enough coins!");
            }
        });

        clearBtn.setOnAction(e -> clearSummary());

        // Check initial affordability
        if (player.getCoin() < selectedItem.price) {
            buyBtn.setDisable(true);
            buyBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Insufficient Funds");
        }

        // Add everything to summary
        VBox container = new VBox(5);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(previewNode, costLabel, controlBox, statusLabel);

        summaryBox.getChildren().add(container);
    }

    private void styleButton(Button btn, Color color) {
        String hex = String.format("#%02x%02x%02x",
                (int)(color.getRed()*255), (int)(color.getGreen()*255), (int)(color.getBlue()*255));
        btn.setStyle("-fx-background-color: " + hex + "; -fx-text-fill: white; -fx-font-family: 'Monospaced'; -fx-font-weight: bold;");
        btn.setCursor(Cursor.HAND);
    }

    private boolean performPurchase(ShopItemData item) {
        // Double check funds logic
        if (player.getCoin() < item.price) return false;

        boolean purchased = false;

        if ("BALL".equals(item.type)) {
            // Handle Balls (Stored as int counters in Player)
            switch (item.id) {
                case "pokeball":
                    player.setRedBall(player.getRedBall() + 1);
                    purchased = true;
                    break;
                case "greatball":
                    player.setGreatBall(player.getGreatBall() + 1);
                    purchased = true;
                    break;
                case "ultraball":
                    player.setHyperBall(player.getHyperBall() + 1);
                    purchased = true;
                    break;
            }
        } else {
            // Handle Items (Stored in Hand)
            // Create a new instance of the item card
            SimpleItemCard newCard = new SimpleItemCard(item.id, item.name, item.description);
            purchased = player.getHand().add(newCard); // Returns false if hand is full
        }

        if (purchased) {
            player.setCoin(player.getCoin() - item.price);
            System.out.println("Bought " + item.name + ". Remaining coins: " + player.getCoin());
        }

        return purchased;
    }


}