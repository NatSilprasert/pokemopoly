package com.pokemopoly.ui;

import com.pokemopoly.Game;
import com.pokemopoly.MusicManager;
import com.pokemopoly.board.Board;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;
import com.pokemopoly.ui.cards.ItemCardUI;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainGameUI {

    private final Game game;
    private final Stage stage;
    private final Scene scene;

    private final StackPane root;
    private final Pane playerLayer = new Pane();

    private final Color[] playerColors = {
            Color.RED, Color.DODGERBLUE, Color.GOLD, Color.LIMEGREEN
    };

    private final double[][] boardPositions = new double[40][2];

    private void initBoardPositions() {
        boardPositions[0] = new double[]{615, 515};
        boardPositions[1] = new double[]{562, 515};
        boardPositions[2] = new double[]{518, 515};
        boardPositions[3] = new double[]{475, 515};
        boardPositions[4] = new double[]{431, 515};
        boardPositions[5] = new double[]{387, 515};
        boardPositions[6] = new double[]{342, 515};
        boardPositions[7] = new double[]{299, 515};
        boardPositions[8] = new double[]{253, 515};
        boardPositions[9] = new double[]{211, 515};

        boardPositions[10] = new double[]{156, 515};
        boardPositions[11] = new double[]{156, 460};
        boardPositions[12] = new double[]{156, 418};
        boardPositions[13] = new double[]{156, 373};
        boardPositions[14] = new double[]{156, 330};
        boardPositions[15] = new double[]{156, 286};
        boardPositions[16] = new double[]{156, 242};
        boardPositions[17] = new double[]{156, 197};
        boardPositions[18] = new double[]{156, 154};
        boardPositions[19] = new double[]{156, 110};

        boardPositions[20] = new double[]{156, 52};
        boardPositions[21] = new double[]{211, 52};
        boardPositions[22] = new double[]{253, 52};
        boardPositions[23] = new double[]{299, 52};
        boardPositions[24] = new double[]{342, 52};
        boardPositions[25] = new double[]{387, 52};
        boardPositions[26] = new double[]{431, 52};
        boardPositions[27] = new double[]{475, 52};
        boardPositions[28] = new double[]{518, 52};
        boardPositions[29] = new double[]{562, 52};

        boardPositions[30] = new double[]{615, 52};
        boardPositions[31] = new double[]{615, 110};
        boardPositions[32] = new double[]{615, 154};
        boardPositions[33] = new double[]{615, 197};
        boardPositions[34] = new double[]{615, 242};
        boardPositions[35] = new double[]{615, 286};
        boardPositions[36] = new double[]{615, 330};
        boardPositions[37] = new double[]{615, 373};
        boardPositions[38] = new double[]{615, 418};
        boardPositions[39] = new double[]{615, 460};
    }

    public MainGameUI(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;

        Image boardImage = new Image(
                getClass().getResource("/main_board.png").toExternalForm()
        );

        ImageView boardView = new ImageView(boardImage);
        boardView.setPreserveRatio(true);
        boardView.setFitWidth(800);
        boardView.setFitHeight(600);

        StackPane boardLayer = new StackPane(boardView);
        boardLayer.setAlignment(Pos.CENTER);


        VBox turnOverlay = createTurnOverlay();
        turnOverlay.setVisible(false);

        Pane blackOverlay = new Pane();
        blackOverlay.setStyle("-fx-background-color: black;");
        blackOverlay.setOpacity(1.0);
        blackOverlay.setMouseTransparent(true);

        root = new StackPane(boardLayer, playerLayer, turnOverlay, blackOverlay);
        root.setAlignment(Pos.CENTER);

        blackOverlay.toFront();

        this.scene = new Scene(root, 800, 600);

        if (getClass().getResource("/global.css") != null) {
            this.scene.getStylesheets().add(
                    getClass().getResource("/global.css").toExternalForm()
            );
        }

        game.setUpGame(root, this::nextTurn);
        initBoardPositions();
        initPlayerIcons();

        Platform.runLater(() -> playIntroFade(blackOverlay, turnOverlay));
    }

    private void initPlayerIcons() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player p = game.getPlayers().get(i);

            String iconFile = getIconForProfession(p.getProfession());
            Image img = new Image(getClass().getResource(iconFile).toExternalForm());

            ImageView iv = new ImageView(img);
            iv.setFitWidth(24);
            iv.setFitHeight(24);

            StackPane iconWrapper = new StackPane(iv);
            iconWrapper.setPrefSize(14, 14);

            iconWrapper.setStyle(
                    "-fx-border-color: " + toWebColor(playerColors[i]) + ";" +
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 3;" +
                            "-fx-background-radius: 3;"
            );

            double startX = boardPositions[0][0];
            double startY = boardPositions[0][1];

            iconWrapper.setLayoutX(startX);
            iconWrapper.setLayoutY(startY);

            playerLayer.getChildren().add(iconWrapper);
        }
    }

    private String getIconForProfession(ProfessionType p) {
        return switch (p) {
            case TRAINER -> "/prof/trainer.png";
            case FISHER -> "/prof/fisher.png";
            case SCIENTIST -> "/prof/scientist.png";
            case ROCKET -> "/prof/rocket.png";
        };
    }

    private String toWebColor(Color c) {
        return String.format("rgba(%d,%d,%d,1)",
                (int) (c.getRed() * 255),
                (int) (c.getGreen() * 255),
                (int) (c.getBlue() * 255));
    }


    private VBox createTurnOverlay() {

        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.setMaxWidth(350);
        box.setMaxHeight(350);
        box.setStyle("-fx-background-color: rgba(0,0,0,0.65);");

        Text turnText = new Text("Player's Turn!");
        turnText.setFill(Color.WHITE);
        turnText.setStyle("-fx-font-size: 42px;");

        Button btnItem = new Button("Use item");
        Button btnSkip = new Button("Roll a dice");

        btnItem.setPrefWidth(220);
        btnSkip.setPrefWidth(220);

        btnItem.setOnAction(e -> {
            hideTurnOverlay(box);
            showPlayerItemOverlay();
        });

        btnSkip.setOnAction(e -> {
            hideTurnOverlay(box);
            startDiceRoll();
        });

        box.getChildren().addAll(turnText, btnItem, btnSkip);
        box.setVisible(false);

        return box;
    }


    public void showTurnOverlay(Player p) {
        if (p.isSkipTurn()) {
            nextTurn();
            p.setSkipTurn(false);
            return;
        }

        VBox overlay = (VBox) ((StackPane) scene.getRoot()).getChildren().get(2);

        Text txt = (Text) overlay.getChildren().get(0);
        txt.setText(p.getName() + "'s Turn!");

        int idx = game.getPlayers().indexOf(p);
        Color c = playerColors[idx];

        overlay.setStyle(
                "-fx-background-color: rgba("
                        + (int)(c.getRed()*255) + ","
                        + (int)(c.getGreen()*255) + ","
                        + (int)(c.getBlue()*255) + ", 0.55);"
        );

        Button btnItem = (Button) overlay.getChildren().get(1);

        if (p.getHand().getItems().isEmpty()) {
            btnItem.setDisable(true);
            btnItem.setText("No items");
        } else {
            btnItem.setDisable(false);
            btnItem.setText("Use item");
        }

        overlay.setVisible(true);
        updatePlayerColors();
    }

    private void hideTurnOverlay(VBox overlay) {
        overlay.setVisible(false);
    }

    private void startDiceRoll() {

        final RollDiceUI[] diceUIHolder = new RollDiceUI[1];

        diceUIHolder[0] = new RollDiceUI((n) -> {

            n = 10;

            root.getChildren().remove(diceUIHolder[0].getView());

            Player currentPlayer = game.getCurrentPlayer();
            Board board = game.getBoard();

            movePlayerIcon(currentPlayer, n, board);

        });

        root.getChildren().add(diceUIHolder[0].getView());
        StackPane.setAlignment(diceUIHolder[0].getView(), Pos.CENTER);
    }

    public void movePlayerIcon(Player currentPlayer, int n, Board board) {
        int tileIndex = (currentPlayer.getPosition() + n) % 40;
        int idx = game.getPlayers().indexOf(currentPlayer);
        StackPane wrapper = (StackPane) playerLayer.getChildren().get(idx);

        double targetX = boardPositions[tileIndex][0];
        double targetY = boardPositions[tileIndex][1];

        double startX = wrapper.getLayoutX();
        double startY = wrapper.getLayoutY();

        Duration duration = Duration.millis(500);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(wrapper.layoutXProperty(), startX),
                        new KeyValue(wrapper.layoutYProperty(), startY)
                ),
                new KeyFrame(duration,
                        new KeyValue(wrapper.layoutXProperty(), targetX),
                        new KeyValue(wrapper.layoutYProperty(), targetY)
                )
        );

        timeline.setCycleCount(1);
        timeline.play();

        timeline.setOnFinished(e -> {
            System.out.println("Moved to: " + wrapper.getLayoutX() + ", " + wrapper.getLayoutY());

            board.movePlayer(currentPlayer, n, game);

            if (currentPlayer.isDoNothing()) {
                currentPlayer.setDoNothing(false);
                nextTurn();
            }
        });
    }

    private void nextTurn() {
        game.nextPlayer();

        if (game.isGameEnd()) {
            Platform.runLater(this::showGameEndOverlay);
            return;
        }

        Player next = game.getCurrentPlayer();

        Platform.runLater(() -> {
            System.out.println("currentPlayer: " + next.getName());
            showTurnOverlay(next);
            updatePlayerColors();
        });
    }

    private void playIntroFade(Pane black, VBox turnOverlay) {

        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));

        delay.setOnFinished(ev -> {

            Timeline fadeOut = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(black.opacityProperty(), 1)
                    ),
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(black.opacityProperty(), 0)
                    )
            );

            fadeOut.setOnFinished(e -> {
                root.getChildren().remove(black);
                showTurnOverlay(game.getCurrentPlayer());
            });
            fadeOut.play();
        });

        delay.play();
    }

    private void updatePlayerColors() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player p = game.getPlayers().get(i);
            StackPane wrapper = (StackPane) playerLayer.getChildren().get(i);

            if (p.isSkipTurn()) {
                wrapper.setOpacity(0.5);
            } else {
                wrapper.setOpacity(1.0);
            }
        }
    }

    public void showGameEndOverlay() {
        List<Player> players = new ArrayList<>(game.getPlayers());
        players.sort(Comparator.comparingInt(Player::getAllCoin).reversed());

        VBox overlay = new VBox(20);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.9);");
        overlay.setPadding(new Insets(30));

        Label title = new Label(players.get(0).getName() + " is win!");
        title.setFont(Font.font("Pixelify Sans", 48));
        title.setTextFill(Color.GOLD);
        overlay.getChildren().add(title);

        VBox resultsBox = new VBox(10);
        resultsBox.setAlignment(Pos.CENTER);

        int rank = 1;
        for (Player p : players) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER);

            Label rankLabel = new Label("#" + rank);
            rankLabel.setFont(Font.font("Pixelify Sans", 32));
            rankLabel.setTextFill(Color.WHITE);

            Label nameLabel = new Label(p.getName());
            nameLabel.setFont(Font.font("Pixelify Sans", 32));
            nameLabel.setTextFill(rank == 1 ? Color.LIME : Color.WHITE);

            Label coinLabel = new Label(p.getAllCoin() + " Coins");
            coinLabel.setFont(Font.font("Pixelify Sans", 32));
            coinLabel.setTextFill(Color.LIGHTGOLDENRODYELLOW);

            row.getChildren().addAll(rankLabel, nameLabel, coinLabel);
            resultsBox.getChildren().add(row);

            rank++;
        }

        ScrollPane scrollPane = new ScrollPane(resultsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefHeight(300);

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);

        Button exitBtn = new Button("Exit Game");
        exitBtn.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 20px; -fx-padding: 8 20;");
        exitBtn.setOnAction(e -> Platform.exit());

        Button newGameBtn = new Button("Start New Game");
        newGameBtn.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 20px; -fx-padding: 8 20;");
        newGameBtn.setOnAction(e -> {
            root.getChildren().remove(overlay);

            MusicManager musicManager = new MusicManager();
            musicManager.playMusicForScene("title");

            Game newGame = new Game(musicManager);
            NumberPlayerSelectUI setupUI = new NumberPlayerSelectUI(newGame, stage, musicManager);
            stage.setScene(setupUI.getScene());
        });

        buttons.getChildren().addAll(exitBtn, newGameBtn);

        overlay.getChildren().addAll(scrollPane, buttons);

        root.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }

    public Scene getScene() {
        return scene;
    }

    private void showPlayerItemOverlay() {
        Player player = game.getCurrentPlayer();
        List<ItemCard> items = player.getHand().getItems();

        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 20;");
        overlay.setMaxWidth(800);

        Label title = new Label(player.getName() + "'s Items");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        overlay.getChildren().add(title);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        overlay.getChildren().add(grid);

        HBox btnBox = new HBox(10);
        btnBox.setAlignment(Pos.CENTER);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            root.getChildren().remove(overlay);
            showTurnOverlay(player);
        });

        final int[] selectedIdx = {-1};
        List<ItemCardUI> cardUIs = new ArrayList<>();

        Button useBtn = new Button("Use Selected Item");
        useBtn.setDisable(true);
        useBtn.setOnAction(e -> {
            if (selectedIdx[0] != -1) {
                ItemCard selectedItem = items.get(selectedIdx[0]);

                player.getHand().remove(selectedItem);
                System.out.println(player.getName() + " used " + selectedItem.getName());

                root.getChildren().remove(overlay);

                if (selectedItem.isAsync()) {
                    selectedItem.activate(game, this);
                } else {
                    selectedItem.activate(game, this);
                    nextTurn();
                }
            }
        });

        for (int i = 0; i < items.size(); i++) {
            ItemCard item = items.get(i);
            ItemCardUI cardUI = new ItemCardUI(item);
            cardUI.setSize(2);

            int idx = i;
            cardUI.setOnMouseClicked(e -> {
                grid.getChildren().forEach(node -> node.setStyle(""));
                cardUI.setStyle("-fx-border-color: red; -fx-border-width: 2;");
                selectedIdx[0] = idx;
                useBtn.setDisable(false);
            });

            grid.add(cardUI, i % 4, i / 4);
            cardUIs.add(cardUI);
        }

        btnBox.getChildren().addAll(cancelBtn, useBtn);
        overlay.getChildren().add(btnBox);

        root.getChildren().add(overlay);
        StackPane.setAlignment(overlay, Pos.CENTER);
    }
}
