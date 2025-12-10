
package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class StartTile extends Tile {

    private final StackPane rootPane;
    private final Runnable endTurnCallback;

    public StartTile(String name, int index, StackPane rootPane, Runnable endTurnCallback) {
        super(name, index);
        this.rootPane = rootPane;
        this.endTurnCallback = endTurnCallback;
    }

    @Override
    public void onLand(Player player, Game game) {
        showSellPokemonUI(player, game, null);
    }

    public void walkPass(Player player, Game game, Runnable callback) {
        showSellPokemonUI(player, game, callback);
    }

    private void showSellPokemonUI(Player player, Game game, Runnable callback) {
        List<PokemonCard> team = player.getTeam();
        if (team.isEmpty()) {
            System.out.println(player.getName() + " has no Pokémon to sell.");
            if (callback != null) callback.run();
            else if (endTurnCallback != null) endTurnCallback.run();
            return;
        }

        VBox overlay = new VBox(20);
        overlay.setAlignment(Pos.CENTER);
        overlay.setPadding(new Insets(30));
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85);");
        overlay.setMaxWidth(1000);

        Label title = makeLabel("Select Pokémon to Sell", 28, Color.WHITE);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        int pokemonCount = team.size();
        int columns = (pokemonCount <= 3) ? pokemonCount : (pokemonCount + 1) / 2;
        int rows = (pokemonCount <= 3) ? 1 : 2;

        grid.setHgap(20);
        grid.setVgap(20);

        List<PokemonCard> selected = new ArrayList<>();

        Label totalLabel = makeLabel("Total: 0 Coins", 20, Color.LIGHTGREEN);

        Button confirm = new Button("Confirm Sale");
        confirm.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 18px; -fx-padding: 8 20;");
        confirm.setDisable(true);
        confirm.setOnAction(e -> {
            int totalCoins = selected.stream().mapToInt(PokemonCard::getPrice).sum();
            selected.forEach(p -> player.getTeam().remove(p));
            player.setCoin(player.getCoin() + totalCoins);

            overlay.getChildren().clear();
            Label success = makeLabel("Sale Successful! + " + totalCoins + " Coins", 28, Color.LIGHTGREEN);
            Button ok = new Button("OK");
            ok.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 20px; -fx-padding: 8 20;");
            ok.setOnAction(ev -> {
                rootPane.getChildren().remove(overlay);
                if (callback != null) callback.run();
                else if (endTurnCallback != null) endTurnCallback.run();
            });

            overlay.getChildren().addAll(success, ok);
        });

        Button cancel = new Button("Cancel");
        cancel.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 18px; -fx-padding: 8 20;");
        cancel.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            if (callback != null) callback.run();
            else if (endTurnCallback != null) endTurnCallback.run();
        });

        // ใส่ Pokémon ลง GridPane ตามจำนวนแถวและคอลัมน์
        for (int i = 0; i < pokemonCount; i++) {
            PokemonCard card = team.get(i);
            VBox cardUI = buildPokemonCard(card, pokemonCount > 3);
            int row = (pokemonCount <= 3) ? 0 : i / columns;
            int col = (pokemonCount <= 3) ? i : i % columns;
            grid.add(cardUI, col, row);

            cardUI.setOnMouseClicked(e -> {
                if (selected.contains(card)) {
                    selected.remove(card);
                    cardUI.setStyle(baseCardStyle(pokemonCount > 3));
                } else {
                    selected.add(card);
                    cardUI.setStyle(baseCardStyle(pokemonCount > 3) + "-fx-border-color: yellow; -fx-border-width: 3;");
                }
                confirm.setDisable(selected.isEmpty());
                totalLabel.setText("Total: " + selected.stream().mapToInt(PokemonCard::getPrice).sum() + " Coins");
            });
        }

        overlay.getChildren().addAll(title, grid, totalLabel, new HBox(20, confirm, cancel));
        ((HBox) overlay.getChildren().get(3)).setAlignment(Pos.CENTER);
        rootPane.getChildren().add(overlay);
    }

    private VBox buildPokemonCard(PokemonCard card, boolean shrink) {
        VBox box = new VBox(shrink ? 5 : 10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(shrink ? 5 : 10));
        box.setStyle(baseCardStyle(shrink));

        Label name = makeLabel(card.getName(), 20, Color.WHITE);
        Label stats = makeLabel("Price: " + card.getPrice() + " Coins", 16, Color.LIGHTGREEN);

        ImageView img = new ImageView(getImage(card));
        img.setFitHeight(shrink ? 80 : 120);
        img.setPreserveRatio(true);

        box.getChildren().addAll(name, img, stats);
        return box;
    }

    private String baseCardStyle(boolean shrink) {
        return "-fx-background-color: #1e1e1e;" +
                "-fx-border-color: white;" +
                (shrink ? "-fx-border-width: 1;" : "-fx-border-width: 2;") +
                "-fx-background-radius: 12;";
    }

    private Label makeLabel(String text, int size, Color color) {
        Label lb = new Label(text);
        lb.setFont(Font.font("Pixelify Sans", size));
        lb.setTextFill(color);
        return lb;
    }

    private Image getImage(PokemonCard card) {
        try {
            return new Image(getClass().getResourceAsStream("/sprites/" + card.getName().toLowerCase() + ".png"));
        } catch (Exception e) {
            return new Image(getClass().getResourceAsStream("/sprites/placeholder.png"));
        }
    }
}