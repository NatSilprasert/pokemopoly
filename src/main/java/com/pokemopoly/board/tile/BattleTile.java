package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.DeckManager;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;
import com.pokemopoly.ui.BattleUI;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;
import java.util.function.Consumer;

public class BattleTile extends Tile {

    private Player boss;
    private final StackPane rootPane;
    private final Consumer<Void> endTurnCallback;

    public BattleTile(String name, int index, StackPane rootPane, Consumer<Void> endTurnCallback) {
        super(name, index);
        this.rootPane = rootPane;
        this.endTurnCallback = endTurnCallback;

        if (Objects.equals(name, "Gym 1")) {
            boss = new Player("Gym 1's Leader", ProfessionType.TRAINER);
        }
        else if (Objects.equals(name, "Gym 2")) {
            boss = new Player("Gym 2's Leader", ProfessionType.TRAINER);
        }
        else if (Objects.equals(name, "Villain")) {
            boss = new Player("Villain", ProfessionType.TRAINER);
        }
    }

    @Override
    public void onLand(Player player, Game game) {

        VBox overlay = new VBox(15);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 25;");
        overlay.setMaxWidth(800);

        Label title = new Label("You have entered the Gym!");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        Label ask = new Label("Do you want to fight?");
        ask.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        Button fightBtn = new Button("Fight!");
        fightBtn.setStyle("-fx-font-size: 16px; -fx-padding: 10 25;");

        Button skipBtn = new Button("Skip");
        skipBtn.setStyle("-fx-font-size: 16px; -fx-padding: 10 25;");

        fightBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            startBattleWithFade(player, game);
        });

        skipBtn.setOnAction(e -> {
            rootPane.getChildren().remove(overlay);
            if (endTurnCallback != null) endTurnCallback.accept(null);
        });

        overlay.getChildren().addAll(title, ask, fightBtn, skipBtn);
        rootPane.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }

    private void startBattleWithFade(Player player, Game game) {

        StackPane black = new StackPane();
        black.setStyle("-fx-background-color: black;");
        black.setOpacity(0);

        rootPane.getChildren().add(black);
        StackPane.setAlignment(black, Pos.CENTER);

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(black.opacityProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(black.opacityProperty(), 1)
                )
        );

        fadeIn.setOnFinished(ev -> {

            // สุ่มโปเกมอนให้ Boss
            DeckManager deckManager = game.getDeckManager();

            if (boss.getTeam().isEmpty()) {
                boss.addPokemon(deckManager.drawPurplePokemon());
            }
            else {
                boss.setPokemon(0, deckManager.drawPurplePokemon());
            }

            // ✔ เรียก GUI BattleUI แทน battle.start()
            new BattleUI(game, rootPane, player, boss, () -> {
                endTurnCallback.accept(null);
            }).start();

            // ลบหน้าดำทิ้ง (BattleUI มี fade-in ของตัวเอง)
            rootPane.getChildren().remove(black);
        });

        fadeIn.play();
    }
}