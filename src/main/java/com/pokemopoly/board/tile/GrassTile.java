package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.ui.RollDiceUI;
import com.pokemopoly.board.GrassColor;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.DeckManager;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.cards.PokemonCardUI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.function.Consumer;

public class GrassTile extends Tile {

    private final StackPane rootPane;
    private final Consumer<Void> endTurnCallback;
    private final GrassColor color;
    private final int catchRate;

    public GrassTile(String name, int index, GrassColor color, StackPane rootPane, Consumer<Void> endTurnCallback) {
        super(name, index);
        this.color = color;
        this.catchRate = color.getCatchRate();
        this.rootPane = rootPane;
        this.endTurnCallback = endTurnCallback;
    }

    public void onLand(Player player, Game game) {
        if (player.isTeamFull() && endTurnCallback != null) {
            endTurnCallback.accept(null);
            return;
        }

        DeckManager deckManager = game.getDeckManager();
        PokemonCard pokemonCard;

        switch (catchRate) {
            case 3 -> {
                if (deckManager.isBlueEmpty()) {
                    System.out.println("Blue deck is empty! Skipping catch.");
                    endTurnCallback.accept(null);
                    return;
                }
                pokemonCard = deckManager.drawBluePokemon();
            }
            case 4 -> {
                if (deckManager.isPurpleEmpty()) {
                    System.out.println("Purple deck is empty! Skipping catch.");
                    endTurnCallback.accept(null);
                    return;
                }
                pokemonCard = deckManager.drawPurplePokemon();
            }
            case 5 -> {
                if (deckManager.isRedEmpty()) {
                    System.out.println("Red deck is empty! Skipping catch.");
                    endTurnCallback.accept(null);
                    return;
                }
                pokemonCard = deckManager.drawRedPokemon();
            }
            case 6 -> {
                if (deckManager.isCrownEmpty()) {
                    System.out.println("Crown deck is empty! Skipping catch.");
                    endTurnCallback.accept(null);
                    return;
                }
                pokemonCard = deckManager.drawCrownPokemon();
            }
            default -> {
                if (deckManager.isGreenEmpty()) {
                    System.out.println("Green deck is empty! Skipping catch.");
                    endTurnCallback.accept(null);
                    return;
                }
                pokemonCard = deckManager.drawGreenPokemon();
            }
        }

        showCatchOverlay(player, pokemonCard);
    }

    private void showCatchOverlay(Player player, PokemonCard pokemonCard) {
        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 20;");
        overlay.setMaxWidth(800);

        Label title = new Label("A wild Pokémon appeared!");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        PokemonCardUI pokemonUI = new PokemonCardUI(pokemonCard);
        overlay.getChildren().addAll(title, pokemonUI);

        HBox btnBox = new HBox(10);
        btnBox.setAlignment(Pos.CENTER);

        Button catchBtn = new Button("Catch");
        Button ignoreBtn = new Button("Ignore");

        catchBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            showBallSelectionOverlay(player, pokemonCard, catchRate);
        });

        ignoreBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            if (endTurnCallback != null) endTurnCallback.accept(null);
        });

        btnBox.getChildren().addAll(catchBtn, ignoreBtn);
        overlay.getChildren().add(btnBox);

        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }

    private void showBallSelectionOverlay(Player player, PokemonCard pokemonCard, int baseCatchRate) {
        VBox overlay = new VBox(20);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 30;");
        overlay.setMaxWidth(900);

        Label title = new Label("Select a Pokéball:");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

        Label catchRateLabel = new Label("Catch rate needed: " + baseCatchRate);
        catchRateLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 18px;");

        HBox ballBox = new HBox(30);
        ballBox.setAlignment(Pos.CENTER);

        final int[] effectiveCatchRate = {baseCatchRate};
        final int[] selectedBallType = {-1};
        final Button[] selectedBtn = {null};

        Button redBallBtn = createBallSelectionButton(player, "pokeball.png", 0, ballBox, catchRateLabel, effectiveCatchRate, selectedBallType, selectedBtn, baseCatchRate);
        Button greatBallBtn = createBallSelectionButton(player, "greatball.png", 1, ballBox, catchRateLabel, effectiveCatchRate, selectedBallType, selectedBtn, baseCatchRate);
        Button hyperBallBtn = createBallSelectionButton(player, "ultraball.png", 2, ballBox, catchRateLabel, effectiveCatchRate, selectedBallType, selectedBtn, baseCatchRate);

        ballBox.getChildren().addAll(redBallBtn, greatBallBtn, hyperBallBtn);

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle("-fx-font-size: 16px; -fx-padding: 10 20 10 20;");
        confirmBtn.setOnAction(e -> {
            if (selectedBallType[0] == -1) {
                catchRateLabel.setText("⚠ Please select a Pokéball first!");
                return;
            }

            switch (selectedBallType[0]) {
                case 0 -> player.setRedBall(player.getRedBall() - 1);
                case 1 -> player.setGreatBall(player.getGreatBall() - 1);
                case 2 -> player.setHyperBall(player.getHyperBall() - 1);
            }

            rootPane.getChildren().remove(overlay);

            final RollDiceUI[] diceUIHolder = new RollDiceUI[1];

            diceUIHolder[0] = new RollDiceUI((dice) -> {

                RollDiceUI diceUI = diceUIHolder[0];

                System.out.println("Rolled: " + dice + ", Needed: " + effectiveCatchRate[0]);

                if (dice >= effectiveCatchRate[0]) {

                    rootPane.getChildren().remove(diceUI.getView());
                    showCaughtOverlay(player, pokemonCard);

                } else {

                    rootPane.getChildren().remove(diceUI.getView());

                    showBreakFreeOverlay(player, pokemonCard, baseCatchRate);
                }
            });

            rootPane.getChildren().add(diceUIHolder[0].getView());
        });

        overlay.getChildren().addAll(title, catchRateLabel, ballBox, confirmBtn);

        if (player.getRedBall() + player.getGreatBall() + player.getHyperBall() <= 0) {
            Label noBallLabel = new Label("No Pokéballs available!");
            noBallLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            Button okBtn = new Button("OK");
            okBtn.setStyle("-fx-font-size: 16px;");
            okBtn.setOnAction(e -> {
                rootPane.getChildren().remove(overlay);
                if (endTurnCallback != null) endTurnCallback.accept(null);
            });
            overlay.getChildren().clear();
            overlay.getChildren().addAll(noBallLabel, okBtn);
        }

        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }

    private Button createBallSelectionButton(Player player, String imagePath, int typeModifier,
                                             HBox ballBox, Label catchRateLabel,
                                             int[] effectiveCatchRate, int[] selectedBallType,
                                             Button[] selectedBtn, int baseCatchRate) {
        int ballCount;
        switch (typeModifier) {
            case 0 -> ballCount = player.getRedBall();
            case 1 -> ballCount = player.getGreatBall();
            case 2 -> ballCount = player.getHyperBall();
            default -> ballCount = 0;
        }

        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);
        ImageView ballImg = new ImageView(new Image(getClass().getResourceAsStream("/shop/" + imagePath)));
        ballImg.setFitWidth(70); // ขยาย
        ballImg.setFitHeight(70);

        Label countLabel = new Label(String.valueOf(ballCount));
        countLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        box.getChildren().addAll(ballImg, countLabel);

        Button btn = new Button("", box);
        btn.setDisable(ballCount <= 0);

        btn.setOnAction(e -> {
            if (selectedBtn[0] != null) selectedBtn[0].setStyle("");
            btn.setStyle("-fx-border-color: red; -fx-border-width: 3;");
            selectedBtn[0] = btn;

            effectiveCatchRate[0] = Math.max(0, baseCatchRate - typeModifier);
            catchRateLabel.setText("Catch rate after ball modifier: " + effectiveCatchRate[0]);

            selectedBallType[0] = typeModifier;
        });

        return btn;
    }

    private void showCaughtOverlay(Player player, PokemonCard pokemonCard) {

        VBox overlay = new VBox(15);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 25;");
        overlay.setMaxWidth(800);

        Label title = new Label("🎉 You caught " + pokemonCard.getName() + "!");
        title.setStyle("-fx-text-fill: #00ff99; -fx-font-size: 24px; -fx-font-weight: bold;");

        PokemonCardUI cardUI = new PokemonCardUI(pokemonCard);

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle("-fx-font-size: 18px; -fx-padding: 10 25 10 25;");

        confirmBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);

            if (player.getTeam().size() < player.getMaxPokemon()) {
                System.out.println(pokemonCard.getName() + " is add to " + player.getName() + " team!");
                player.addPokemon(pokemonCard);
                if (endTurnCallback != null) endTurnCallback.accept(null);
            }
        });

        overlay.getChildren().addAll(title, cardUI, confirmBtn);

        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }

    private void showBreakFreeOverlay(Player player, PokemonCard pokemonCard, int baseCatchRate) {

        VBox overlay = new VBox(20);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 25;");
        overlay.setMaxWidth(800);

        Label title = new Label("The Pokémon broke free!");
        title.setStyle("-fx-text-fill: orange; -fx-font-size: 22px; -fx-font-weight: bold;");

        PokemonCardUI cardUI = new PokemonCardUI(pokemonCard);

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        Button retryBtn = new Button("Try Again");
        retryBtn.setStyle("-fx-font-size: 16px; -fx-padding: 10 20;");

        Button releaseBtn = new Button("Let It Go");
        releaseBtn.setStyle("-fx-font-size: 16px; -fx-padding: 10 20;");

        retryBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            showBallSelectionOverlay(player, pokemonCard, baseCatchRate);
        });

        releaseBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            if (endTurnCallback != null) endTurnCallback.accept(null);
        });

        buttonBox.getChildren().addAll(retryBtn, releaseBtn);

        overlay.getChildren().addAll(title, cardUI, buttonBox);

        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }

    public int getCatchRate() {
        return catchRate;
    }

    public GrassColor getColor() {
        return color;
    }
}