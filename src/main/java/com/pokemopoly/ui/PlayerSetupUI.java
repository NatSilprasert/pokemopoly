package com.pokemopoly.ui;

import com.pokemopoly.Game;
import com.pokemopoly.MusicManager;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayerSetupUI {

    private final Scene scene;
    private final Game game;
    private final Stage stage;

    private final int totalPlayers;
    private final MusicManager musicManager;
    private int currentIndex = 1;

    private ProfessionType selectedProfession = null;
    private boolean hasName = false;

    private final java.util.List<VBox> professionCards = new java.util.ArrayList<>();
    private final Label stepLabel;
    private final TextField nameField;
    private final Button confirmButton;

    private final StackPane rootContainer;

    public PlayerSetupUI(Game game, Stage stage, int totalPlayers, MusicManager musicManager) {
        this.game = game;
        this.stage = stage;
        this.totalPlayers = totalPlayers;
        this.musicManager = musicManager;

        stepLabel = new Label("Player 1 - Enter your name");
        stepLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        nameField = new TextField();
        nameField.setPromptText("Enter name...");
        nameField.setMaxWidth(250);

        nameField.textProperty().addListener((obs, oldVal, newVal) -> {
            hasName = !newVal.trim().isEmpty();
            for (VBox card : professionCards) {
                card.setDisable(!hasName);
                card.setOpacity(hasName ? 1.0 : 0.4);
            }
            if (!hasName) {
                selectedProfession = null;
                for (VBox card : professionCards) {
                    card.setStyle("-fx-border-color: black; -fx-border-width: 2;");
                }
            }
            validateForm();
        });

        HBox professionRow = createProfessionSelector();

        confirmButton = new Button("Confirm");
        confirmButton.setDisable(true);
        confirmButton.setStyle("-fx-font-size: 18px; -fx-padding: 8 20;");
        confirmButton.setOnAction(e -> handleConfirm());

        VBox setupUI = new VBox(30, stepLabel, nameField, professionRow, confirmButton);
        setupUI.setAlignment(Pos.CENTER);
        setupUI.setPadding(new Insets(0));

        rootContainer = new StackPane(setupUI);

        scene = new Scene(rootContainer, 800, 600);
        scene.getStylesheets().add(
                getClass().getResource("/global.css").toExternalForm()
        );
    }

    public Scene getScene() {
        return scene;
    }

    private VBox createProfessionCard(ProfessionType type, String imgPath, String abilityText) {
        ImageView img = new ImageView(
                new Image(getClass().getResourceAsStream(imgPath))
        );
        img.setFitWidth(120);
        img.setFitHeight(120);

        Label name = new Label(type.toString());
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label ability = new Label(abilityText);
        ability.setWrapText(true);
        ability.setMaxWidth(160);
        ability.setStyle("-fx-font-size: 14px;");

        VBox card = new VBox(10, img, name, ability);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: black; -fx-border-width: 2;");

        card.setDisable(true);

        card.setOnMouseClicked(e -> {
            if (!card.isDisable()) selectProfession(type, card);
        });

        professionCards.add(card);
        return card;
    }

    private HBox createProfessionSelector() {
        VBox trainer = createProfessionCard(
                ProfessionType.TRAINER,
                "/prof/trainer.png",
                "- Pikachu"
        );

        VBox fisher = createProfessionCard(
                ProfessionType.FISHER,
                "/prof/fisher.png",
                "- Magikarp"
        );

        VBox scientist = createProfessionCard(
                ProfessionType.SCIENTIST,
                "/prof/scientist.png",
                "- Ditto"
        );

        VBox rocket = createProfessionCard(
                ProfessionType.ROCKET,
                "/prof/rocket.png",
                "- Rattata"
        );

        HBox row = new HBox(25, trainer, fisher, scientist, rocket);
        row.setAlignment(Pos.CENTER);
        return row;
    }

    private void selectProfession(ProfessionType type, VBox selectedCard) {
        this.selectedProfession = type;

        HBox row = (HBox) selectedCard.getParent();
        for (javafx.scene.Node n : row.getChildren()) {
            n.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        }
        selectedCard.setStyle("-fx-border-color: #2196f3; -fx-border-width: 2;");

        validateForm();
    }

    private void validateForm() {
        confirmButton.setDisable(
                nameField.getText().trim().isEmpty() ||
                        selectedProfession == null
        );
    }

    private void handleConfirm() {
        String name = nameField.getText().trim();
        game.addPlayer(new Player(name, selectedProfession));

        if (currentIndex < totalPlayers) {
            currentIndex++;
            resetForm();
        } else {
            startTransitionToMainUI();
        }
    }

    private void resetForm() {
        stepLabel.setText("Player " + currentIndex + " - Enter your name");
        nameField.setText("");
        selectedProfession = null;
        confirmButton.setDisable(true);

        VBox root = (VBox) rootContainer.getChildren().get(0);
        HBox row = (HBox) root.getChildren().get(2);

        for (javafx.scene.Node n : row.getChildren()) {
            n.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        }

        for (VBox card : professionCards) {
            card.setDisable(true);
            card.setOpacity(0.4);
        }
    }

    private void startTransitionToMainUI() {

        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: black;");
        overlay.setOpacity(0);

        Text welcome = new Text("Welcome to Pokemopoly");
        welcome.setFill(Color.WHITE);
        welcome.setStyle("-fx-font-size: 48px; -fx-font-weight: bold;");
        welcome.setOpacity(0);

        overlay.getChildren().add(welcome);
        StackPane.setAlignment(welcome, Pos.CENTER);

        rootContainer.getChildren().add(overlay);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), overlay);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition textFade = new FadeTransition(Duration.seconds(1), welcome);
        textFade.setFromValue(0);
        textFade.setToValue(1);

        ScaleTransition zoom = new ScaleTransition(Duration.seconds(2), overlay);
        zoom.setFromX(1.2);
        zoom.setFromY(1.2);
        zoom.setToX(1);
        zoom.setToY(1);

        FadeTransition textFadeOut = new FadeTransition(Duration.seconds(1), welcome);
        textFadeOut.setFromValue(1);
        textFadeOut.setToValue(0);

        SequentialTransition seq = new SequentialTransition(
                fadeIn,
                textFade,
                zoom,
                textFadeOut
        );
        seq.setOnFinished(e -> {
            musicManager.fadeOutCurrent(2, () -> musicManager.playMusicForScene("palletTown"));
            MainGameUI ui = new MainGameUI(game, stage);
            stage.setScene(ui.getScene());
        });

        seq.play();
    }
}