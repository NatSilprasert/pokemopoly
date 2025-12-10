package com.pokemopoly.ui;

import com.pokemopoly.Battle;
import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.List;
import java.util.stream.Collectors;

public class BattleUI {

    private final Game game;
    private final StackPane rootPane;
    private final Player player;
    private final Player bot;
    private final Runnable onEnd;

    private StackPane battleContainer;
    private Label playerHPLabel;
    private Label botHPLabel;

    private VBox playerBox;
    private VBox enemyBox;

    private Battle battle;

    private PokemonCard playerPokemon;
    private PokemonCard botPokemon;

    public BattleUI(Game game, StackPane rootPane, Player player, Player bot, Runnable onEnd) {
        this.game = game;
        this.rootPane = rootPane;
        this.player = player;
        this.bot = bot;
        this.onEnd = onEnd;

        try {
            Font.loadFont(getClass().getResourceAsStream("/fonts/PixelifySans.ttf"), 16);
        } catch (Exception ignored) {}
    }

    public void start() {
        if (player.getTeam().isEmpty()) {
            if (onEnd != null) onEnd.run();
            return;
        }
        if (bot.getTeam().isEmpty()) {
            if (onEnd != null) onEnd.run();
            return;
        }

        botPokemon = bot.getTeam().get(0);
        battle = new Battle(game, player, bot, true);

        showPokemonSelection();
    }

    private void buildBattleUI() {

        battleContainer = new StackPane();
        battleContainer.setStyle("-fx-background-color: rgba(0,0,0,0.65);");
        battleContainer.setPadding(new Insets(30));

        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);

        HBox topRow = new HBox(40);
        topRow.setAlignment(Pos.CENTER);

        // ---------------------- ENEMY ----------------------
        enemyBox = new VBox(6);
        enemyBox.setAlignment(Pos.CENTER);
        enemyBox.setStyle(
                "-fx-background-color: #361414;" +
                        "-fx-border-color: #ff4444;" +
                        "-fx-border-width: 3;" +
                        "-fx-background-radius: 12;"
        );
        enemyBox.setPadding(new Insets(15));

        Label enemyName = makeLabel(bot.getName(), 14, Color.WHITE);
        Label enemyPokemonName = makeLabel(botPokemon.getName(), 22, Color.WHITE);

        String enemyTypeText = botPokemon.getTypes()
                .stream()
                .map(Enum::name)
                .collect(Collectors.joining(" / "));
        Label enemyType = makeLabel("Type: " + enemyTypeText, 16, Color.LIGHTGRAY);

        ImageView enemyImg = new ImageView(getPokemonImage(botPokemon));
        enemyImg.setFitHeight(150);
        enemyImg.setPreserveRatio(true);

        Label enemyStats = makeLabel(
                "ATK " + botPokemon.getPower() + "    HP " +
                        botPokemon.getHp() + " / " + botPokemon.getMaxHp(),
                20, Color.WHITE
        );
        botHPLabel = enemyStats;

        enemyBox.getChildren().addAll(enemyName, enemyPokemonName, enemyType, enemyImg, enemyStats);

        // ---------------------- PLAYER ----------------------
        playerBox = new VBox(6);
        playerBox.setAlignment(Pos.CENTER);
        playerBox.setStyle(
                "-fx-background-color: #142536;" +
                        "-fx-border-color: #33bbff;" +
                        "-fx-border-width: 3;" +
                        "-fx-background-radius: 12;"
        );
        playerBox.setPadding(new Insets(15));

        Label playerName = makeLabel(player.getName(), 14, Color.WHITE);
        Label playerPokemonName = makeLabel(playerPokemon.getName(), 22, Color.WHITE);

        String typeText = playerPokemon.getTypes()
                .stream()
                .map(Enum::name)
                .collect(Collectors.joining(" / "));
        Label playerType = makeLabel("Type: " + typeText, 16, Color.LIGHTGRAY);

        ImageView playerImg = new ImageView(getPokemonImage(playerPokemon));
        playerImg.setFitHeight(150);
        playerImg.setPreserveRatio(true);

        Label playerStats = makeLabel(
                "ATK " + playerPokemon.getPower() + "    HP " +
                        playerPokemon.getHp() + " / " + playerPokemon.getMaxHp(),
                20, Color.WHITE
        );
        playerHPLabel = playerStats;

        playerBox.getChildren().addAll(playerName, playerPokemonName, playerType, playerImg, playerStats);

        // VS
        Label vs = makeLabel("VS", 38, Color.WHITE);

        topRow.getChildren().addAll(playerBox, vs, enemyBox);

        Label message = makeLabel("Tap to roll the dice", 20, Color.WHITE);

        Button rollBtn = new Button("ROLL");
        rollBtn.setStyle(
                "-fx-font-family: 'Pixelify Sans'; -fx-font-size: 24px;" +
                        "-fx-padding: 10 25; -fx-background-color: white;"
        );
        rollBtn.setOnAction(e -> startPlayerRoll(message, rollBtn));

        root.getChildren().addAll(topRow, message, rollBtn);

        battleContainer.getChildren().add(root);
        rootPane.getChildren().add(battleContainer);
    }

    private Label makeLabel(String text, int size, Color color) {
        Label lb = new Label(text);
        lb.setFont(Font.font("Pixelify Sans", size));
        lb.setTextFill(color);
        return lb;
    }

    private void startPlayerRoll(Label msg, Button rollBtn) {

        rollBtn.setDisable(true);

        final RollDiceUI[] diceHolder = new RollDiceUI[1];

        diceHolder[0] = new RollDiceUI(playerRoll -> {
            Platform.runLater(() -> {
                rootPane.getChildren().remove(diceHolder[0].getView());
                msg.setText("You rolled " + playerRoll + " ...");
            });

            PauseTransition wait = new PauseTransition(Duration.seconds(0.7));
            wait.setOnFinished(ev -> startBotRoll(msg, rollBtn, playerRoll));
            wait.play();
        });

        rootPane.getChildren().add(diceHolder[0].getView());
        StackPane.setAlignment(diceHolder[0].getView(), Pos.CENTER);
    }

    private void startBotRoll(Label msg, Button rollBtn, int playerRoll) {

        // ดีเลย์ 1 วินาทีเหมือน Bot คิด
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished(ev -> {

            final RollDiceUI[] diceHolder = new RollDiceUI[1];
            diceHolder[0] = new RollDiceUI(botRoll -> {

                Platform.runLater(() -> {
                    rootPane.getChildren().remove(diceHolder[0].getView());
                    msg.setText("You: " + playerRoll + "   |   Enemy: " + botRoll);
                });

                PauseTransition wait = new PauseTransition(Duration.seconds(0.2));
                wait.setOnFinished(e2 -> resolveTurn(playerRoll, botRoll, msg, rollBtn));
                wait.play();

            }, true); // 👉 Bot mode

            // เพิ่ม dice UI ของ Bot ลงบนจอ
            rootPane.getChildren().add(diceHolder[0].getView());
            StackPane.setAlignment(diceHolder[0].getView(), Pos.CENTER);

            // ❌ ไม่ต้องเรียก autoRoll() อีกแล้ว
            // เพราะ RollDiceUI(isBot=true) จะ auto-roll ให้เอง
        });

        delay.play();
    }

    private void resolveTurn(int pRoll, int bRoll, Label msg, Button rollBtn) {

        if (pRoll > bRoll) {

            boolean win = battle.attack(player, bot, playerPokemon, botPokemon);
            updateHPLabels();

            playShake(enemyBox);

            if (win) {
                showEndVictory(true);
                return;
            }

        } else if (bRoll > pRoll) {

            boolean lose = battle.attack(bot, player, botPokemon, playerPokemon);
            updateHPLabels();

            playShake(playerBox);

            if (lose) {
                showEndVictory(false);
                return;
            }

        } else {
            msg.setText("Tie! Roll again.");
        }

        rollBtn.setDisable(false);
        msg.setText("Tap to roll again");
    }

    private void playShake(Region node) {
        TranslateTransition t1 = new TranslateTransition(Duration.millis(40), node);
        t1.setByX(-12);
        TranslateTransition t2 = new TranslateTransition(Duration.millis(40), node);
        t2.setByX(24);
        TranslateTransition t3 = new TranslateTransition(Duration.millis(40), node);
        t3.setByX(-12);

        SequentialTransition seq = new SequentialTransition(t1, t2, t3);
        seq.setOnFinished(e -> node.setTranslateX(0));
        seq.play();
    }

    private void showEndVictory(boolean playerWon) {
        VBox result = new VBox(12);
        result.setAlignment(Pos.CENTER);
        result.setStyle("-fx-background-color: rgba(0,0,0,0.9); -fx-padding: 20;");

        Label title = makeLabel(playerWon ? "You won the battle!" : "You lost...", 24, Color.WHITE);
        result.getChildren().add(title);

        if (playerWon) {
            HBox rewardsBox = new HBox(15);
            rewardsBox.setAlignment(Pos.CENTER);

            int coins = 0;
            String badgeName = null;

            String opponentName = bot.getName();

            if (opponentName.equals("Gym 1's Leader")) {
                if (!player.getBadges1()) {
                    player.setBadges1();
                    badgeName = "Badge1";
                    System.out.println(player.getName() + " receive Gym 1's Badge");
                } else {
                    coins = 5;
                }
            } else if (opponentName.equals("Gym 2's Leader")) {
                if (!player.getBadges2()) {
                    player.setBadges2();
                    badgeName = "Badge2";
                    System.out.println(player.getName() + " receive Gym 2's Badge");
                } else {
                    coins = 5;
                }
            } else if (opponentName.equals("Villain")) {
                coins = 10;
            }

            if (badgeName != null) {
                VBox badgeBox = new VBox(5);
                badgeBox.setAlignment(Pos.CENTER);
//                ImageView badgeImg = new ImageView(new Image(getClass().getResourceAsStream(
//                        "/badges/" + badgeName.toLowerCase() + ".png")));
//                badgeImg.setFitHeight(60);
//                badgeImg.setPreserveRatio(true);
                Label badgeLbl = makeLabel("+ " + badgeName, 20, Color.YELLOW);
                badgeBox.getChildren().addAll(badgeLbl);
                rewardsBox.getChildren().add(badgeBox);
            }

            if (coins > 0) {
                VBox coinBox = new VBox(5);
                coinBox.setAlignment(Pos.CENTER);
                Label coinLbl = makeLabel("+ " + coins + " Coins", 20, Color.LIGHTGREEN);
                coinBox.getChildren().addAll(coinLbl);
                rewardsBox.getChildren().add(coinBox);

                // เพิ่ม coin ให้ player
                player.setCoin(player.getCoin() + coins);
                System.out.println(player.getName() + " receive " + coins + " coins!");
                System.out.println(player.getName() + " has " + player.getCoin() + " coins");
            }

            result.getChildren().add(rewardsBox);
        }

        Button ok = new Button("OK");
        ok.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 20px; -fx-padding: 8 20;");
        ok.setOnAction(e -> {
            rootPane.getChildren().remove(result);
            playFadeOutAndFinish();
        });

        result.getChildren().add(ok);
        rootPane.getChildren().add(result);
        StackPane.setAlignment(result, Pos.CENTER);
    }

    private void playFadeOutAndFinish() {

        Pane black = new Pane();
        black.setStyle("-fx-background-color: black;");
        black.setOpacity(0);
        rootPane.getChildren().add(black);

        FadeTransition ft = new FadeTransition(Duration.seconds(0.45), black);
        ft.setFromValue(0);
        ft.setToValue(1.0);

        ft.setOnFinished(e -> {

            if (battleContainer != null) {
                rootPane.getChildren().remove(battleContainer);
                battleContainer = null;
            }

            rootPane.getChildren().remove(black);

            if (onEnd != null) onEnd.run();
        });

        ft.play();
    }

    private void showPokemonSelection() {

        VBox overlay = new VBox(25);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 30;");
        overlay.setMaxWidth(1100);

        Label title = makeLabel("Choose Your Pokémon", 32, Color.WHITE);

        // GRID FOR AUTO 2 ROWS
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        final PokemonCard[] selected = {null};
        int index = 0;

        List<PokemonCard> team = player.getTeam();
        boolean shrink = team.size() > 3;

        if (shrink) {
            grid.setHgap(0);
            grid.setVgap(0);
        } else {
            grid.setHgap(20);
            grid.setVgap(20);
        }

        for (PokemonCard card : team) {

            VBox cardUI = buildPokemonSelectCard(card, shrink);

            int col = index % 3;
            int row = index / 3;

            grid.add(cardUI, col, row);
            index++;

            cardUI.setOnMouseClicked(e -> {
                grid.getChildren().forEach(n -> n.setStyle(baseSelectStyle(shrink)));
                cardUI.setStyle(baseSelectStyle(shrink) +
                        "-fx-border-color: yellow; -fx-border-width: 4;");
                selected[0] = card;
            });
        }

        Button confirm = new Button("Confirm");
        confirm.setStyle(
                "-fx-font-family: 'Pixelify Sans'; -fx-font-size: 20px; -fx-padding: 12 30;"
        );

        confirm.setOnAction(e -> {
            if (selected[0] == null) return;

            playerPokemon = selected[0];
            rootPane.getChildren().remove(overlay);

            buildBattleUI();
            playFadeIn();
        });

        overlay.getChildren().addAll(title, grid, confirm);
        rootPane.getChildren().add(overlay);
    }

    private String baseSelectStyle(boolean small) {
        return "-fx-background-color: #1e1e1e;" +
                "-fx-border-color: white;" +
                (small ? "-fx-border-width: 1;" : "-fx-border-width: 2;") +
                "-fx-background-radius: 12;";
    }

    private VBox buildPokemonSelectCard(PokemonCard card, boolean shrink) {

        VBox box = new VBox(shrink ? 0 : 8);
        box.setAlignment(Pos.CENTER);
        box.setPadding(shrink ? new Insets(5) : new Insets(15));
        box.setStyle(baseSelectStyle(shrink));

        double scale = shrink ? 0.75 : 1.0;
        box.setScaleX(scale);
        box.setScaleY(scale);

        Label name = makeLabel(card.getName(), 22, Color.WHITE);

        String typeText = card.getTypes()
                .stream()
                .map(Enum::name)
                .collect(Collectors.joining(" / "));
        Label type = makeLabel("Type: " + typeText, 16, Color.LIGHTGRAY);

        ImageView img = new ImageView(getPokemonImage(card));
        img.setFitHeight(shrink ? 120 : 140);
        img.setPreserveRatio(true);

        Label stats = makeLabel(
                "ATK " + card.getPower() + "   HP " +
                        card.getHp() + "/" + card.getMaxHp(),
                18, Color.WHITE);

        box.getChildren().addAll(name, type, img, stats);

        // -------------------- ถ้า HP = 0 --------------------
        if (card.getHp() <= 0) {
            box.setOpacity(0.5); // ทำให้สีจางลง
            box.setDisable(true); // ไม่สามารถคลิกได้
        }

        return box;
    }

    private Image getPokemonImage(PokemonCard card) {
        try {
            String path = "/sprites/" + card.getName().toLowerCase() + ".png";
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            return new Image(getClass().getResourceAsStream("/sprites/placeholder.png"));
        }
    }

    private void updateHPLabels() {
        Platform.runLater(() -> {
            playerHPLabel.setText(
                    "ATK " + playerPokemon.getPower() +
                            "    HP " + playerPokemon.getHp() + " / " + playerPokemon.getMaxHp()
            );

            botHPLabel.setText(
                    "ATK " + botPokemon.getPower() +
                            "    HP " + botPokemon.getHp() + " / " + botPokemon.getMaxHp()
            );
        });
    }

    private void playFadeIn() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.4), battleContainer);
        battleContainer.setOpacity(0);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
