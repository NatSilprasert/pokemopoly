package com.pokemopoly.ui;

import com.pokemopoly.Game;
import com.pokemopoly.board.Board;
import com.pokemopoly.board.GrassColor;
import com.pokemopoly.board.Tile;
import com.pokemopoly.board.tile.*;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainGameUI {

    private final Game game;
    private final Stage stage;
    private final Scene scene;

    // Player icons layer
    private StackPane root;
    private Pane playerLayer = new Pane();

    // Border color
    private final Color[] playerColors = {
            Color.RED, Color.DODGERBLUE, Color.GOLD, Color.LIMEGREEN
    };
    // Player Icon
    private final List<ImageView> playerIcons = new ArrayList<>();

    // Tile Position
    private final double[][] boardPositions = new double[40][2];

    private void initBoardPositions() {
        // bottom row (0 → 9)
        boardPositions[0] = new double[]{615, 515};  // Start
        boardPositions[1] = new double[]{562, 515};
        boardPositions[2] = new double[]{518, 515};
        boardPositions[3] = new double[]{475, 515};
        boardPositions[4] = new double[]{431, 515};
        boardPositions[5] = new double[]{387, 515};
        boardPositions[6] = new double[]{342, 515};
        boardPositions[7] = new double[]{299, 515};
        boardPositions[8] = new double[]{253, 515};
        boardPositions[9] = new double[]{211, 515};

        // left column (10 → 19)
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

        // top row (20 → 29)
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

        // right column (30 → 39)
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

    private void startGame() {
        Player first = game.getCurrentPlayer();
        showTurnOverlay(first);
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


        // create overlay
        VBox turnOverlay = createTurnOverlay();
        turnOverlay.setVisible(false);

        // create black intro overlay
        Pane blackOverlay = new Pane();
        blackOverlay.setStyle("-fx-background-color: black;");
        blackOverlay.setOpacity(1.0);
        blackOverlay.setMouseTransparent(true);

        // stack layers
        root = new StackPane(boardLayer, playerLayer, turnOverlay, blackOverlay);
        root.setAlignment(Pos.CENTER);

        // make sure black starts in front
        blackOverlay.toFront();

        // scene
        this.scene = new Scene(root, 800, 600);

        // CSS
        if (getClass().getResource("/global.css") != null) {
            this.scene.getStylesheets().add(
                    getClass().getResource("/global.css").toExternalForm()
            );
        }

        // Setup basic requirement
        setUpBoard();
        initBoardPositions();
        initPlayerIcons();

        Platform.runLater(() -> playIntroFade(blackOverlay, turnOverlay));
    }

    public Scene getScene() {
        return scene;
    }

    private void initPlayerIcons() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player p = game.getPlayers().get(i);

            String iconFile = getIconForProfession(p.getProfession());
            Image img = new Image(getClass().getResource(iconFile).toExternalForm());

            ImageView iv = new ImageView(img);
            iv.setFitWidth(24);
            iv.setFitHeight(24);

            // create wrapper for border
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

            // set players position
            iconWrapper.setLayoutX(startX);
            iconWrapper.setLayoutY(startY - (i * 20));

            playerLayer.getChildren().add(iconWrapper);
            playerIcons.add(iv);
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

        Button btnPokemon = new Button("Use Pokemon Skill");
        Button btnItem = new Button("Use Item Skill");
        Button btnSkip = new Button("Do Nothing");

        btnPokemon.setPrefWidth(220);
        btnItem.setPrefWidth(220);
        btnSkip.setPrefWidth(220);

        // Game logic
        btnPokemon.setOnAction(e -> {
            // TODO
            hideTurnOverlay(box);
            startDiceRoll();
        });

        btnItem.setOnAction(e -> {
            // TODO
            hideTurnOverlay(box);
            startDiceRoll();
        });

        btnSkip.setOnAction(e -> {
            hideTurnOverlay(box);
            startDiceRoll();
        });

        box.getChildren().addAll(turnText, btnPokemon, btnItem, btnSkip);
        box.setVisible(false);

        return box;
    }


    public void showTurnOverlay(Player p) {
        VBox overlay = (VBox) ((StackPane) scene.getRoot()).getChildren().get(2);

        Text txt = (Text) overlay.getChildren().get(0);
        txt.setText(p.getName() + "'s Turn!");

        // change color for each player
        int idx = game.getPlayers().indexOf(p);
        Color c = playerColors[idx];

        overlay.setStyle(
                "-fx-background-color: rgba("
                        + (int)(c.getRed()*255) + ","
                        + (int)(c.getGreen()*255) + ","
                        + (int)(c.getBlue()*255) + ", 0.55);"
        );

        overlay.setVisible(true);
    }

    private void hideTurnOverlay(VBox overlay) {
        overlay.setVisible(false);
    }

    private void startDiceRoll() {

        final RollDiceUI[] diceUIHolder = new RollDiceUI[1];

        diceUIHolder[0] = new RollDiceUI((n) -> {

            root.getChildren().remove(diceUIHolder[0].getView());

            Player currentPlayer = game.getCurrentPlayer();
            Board board = game.getBoard();

            // move player icon
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

        // move animation 500ms
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
            if (!currentPlayer.isDoNothing()) {
                currentPlayer.setLastRoll(n);
                game.checkAdditionalConditions(currentPlayer, n);
                board.movePlayer(currentPlayer, n, game);
            }

            currentPlayer.setDoNothing(false);
        });
    }

    private void nextTurn() {
        game.nextPlayer();

        Player next = game.getCurrentPlayer();

        Platform.runLater(() -> showTurnOverlay(next));
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

    public void setUpBoard() {
        List<Tile> tiles = new ArrayList<>(Arrays.asList(
                new StartTile("Start Tile", 0),
                new GrassTile("Green Grass Tile", 1, GrassColor.GREEN, root, v -> nextTurn()),
                new EventTile("Event Tile", 2),
                new GrassTile("Green Grass Tile", 3, GrassColor.GREEN),
                new GrassTile("Green Grass Tile", 4, GrassColor.GREEN),
                new CityTile("City Tile", 5, root, v -> nextTurn()),
                new GrassTile("Green Grass Tile", 6, GrassColor.GREEN),
                new GrassTile("Green Grass Tile", 7, GrassColor.GREEN),
                new GrassTile("Green Grass Tile", 3, GrassColor.GREEN, root, v -> nextTurn()),
                new GrassTile("Green Grass Tile", 4, GrassColor.GREEN, root, v -> nextTurn()),
                new CityTile("City Tile", 5),
                new GrassTile("Green Grass Tile", 6, GrassColor.GREEN, root, v -> nextTurn()),
                new GrassTile("Green Grass Tile", 7, GrassColor.GREEN, root, v -> nextTurn()),
                new ItemTile("Item Tile", 8, root, v -> nextTurn()),
                new GrassTile("Green Grass Tile", 9, GrassColor.GREEN, root, v -> nextTurn()),
                new BattleTile("Gym 1", 10),
                new GrassTile("Green Grass Tile", 11, GrassColor.BLUE, root, v -> nextTurn()),
                new CaveTile("Cave Tile", 12),
                new GrassTile("Green Grass Tile", 13, GrassColor.BLUE),
                new GrassTile("Green Grass Tile", 14, GrassColor.BLUE),
                new CityTile("City Tile", 15,  root, v -> nextTurn()),
                new GrassTile("Green Grass Tile", 16, GrassColor.BLUE),
                new GrassTile("Green Grass Tile", 17, GrassColor.BLUE),
                new GrassTile("Green Grass Tile", 13, GrassColor.BLUE, root, v -> nextTurn()),
                new GrassTile("Green Grass Tile", 14, GrassColor.BLUE, root, v -> nextTurn()),
                new CityTile("City Tile", 15),
                new GrassTile("Green Grass Tile", 16, GrassColor.BLUE, root, v -> nextTurn()),
                new GrassTile("Green Grass Tile", 17, GrassColor.BLUE, root, v -> nextTurn()),
                new DaycareTile("Daycare Tile", 18),
                new GrassTile("Green Grass Tile", 19, GrassColor.BLUE, root, v -> nextTurn()),
                new BattleTile("Villain", 20),
                new GrassTile("Purple Grass Tile", 21, GrassColor.PURPLE, root, v -> nextTurn()),
                new EventTile("Event Tile", 22),
                new GrassTile("Purple Grass Tile", 23, GrassColor.PURPLE),
                new GrassTile("Purple Grass Tile", 24, GrassColor.PURPLE),
                new CityTile("City Tile", 25,  root, v -> nextTurn()),
                new GrassTile("Purple Grass Tile", 26, GrassColor.PURPLE),
                new GrassTile("Purple Grass Tile", 27, GrassColor.PURPLE),
                new GrassTile("Purple Grass Tile", 23, GrassColor.PURPLE, root, v -> nextTurn()),
                new GrassTile("Purple Grass Tile", 24, GrassColor.PURPLE, root, v -> nextTurn()),
                new CityTile("City Tile", 25),
                new GrassTile("Purple Grass Tile", 26, GrassColor.PURPLE, root, v -> nextTurn()),
                new GrassTile("Purple Grass Tile", 27, GrassColor.PURPLE, root, v -> nextTurn()),
                new ItemTile("Item Tile", 28, root, v -> nextTurn()),
                new GrassTile("Purple Grass Tile", 29, GrassColor.PURPLE, root, v -> nextTurn()),
                new BattleTile("Gym 2", 30),
                new GrassTile("Red Grass Tile", 31, GrassColor.RED, root, v -> nextTurn()),
                new CaveTile("Cave Tile", 32),
                new GrassTile("Red Grass Tile", 33, GrassColor.RED),
                new GrassTile("Red Grass Tile", 34, GrassColor.RED),
                new CityTile("City Tile", 35,  root, v -> nextTurn()),
                new GrassTile("Red Grass Tile", 36, GrassColor.RED),
                new GrassTile("Red Grass Tile", 37, GrassColor.RED),
                new GrassTile("Red Grass Tile", 38, GrassColor.RED),
                new GrassTile("Crown Grass Tile", 39, GrassColor.CROWN)
                new GrassTile("Red Grass Tile", 33, GrassColor.RED, root, v -> nextTurn()),
                new GrassTile("Red Grass Tile", 34, GrassColor.RED, root, v -> nextTurn()),
                new CityTile("City Tile", 35),
                new GrassTile("Red Grass Tile", 36, GrassColor.RED, root, v -> nextTurn()),
                new GrassTile("Red Grass Tile", 37, GrassColor.RED, root, v -> nextTurn()),
                new GrassTile("Red Grass Tile", 38, GrassColor.RED, root, v -> nextTurn()),
                new GrassTile("Crown Grass Tile", 39, GrassColor.CROWN, root, v -> nextTurn())
        ));

        game.setUpBoard(tiles);
    }
}