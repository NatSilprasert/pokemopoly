package com.pokemopoly.ui;

import com.pokemopoly.Game;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PlayerSetupUI {

    private final Scene scene;
    private final Game game;
    private final Stage stage;

    private final int totalPlayers;
    private int currentIndex = 1;

    private ProfessionType selectedProfession = null;

    // UI Components
    private final Label stepLabel;
    private final TextField nameField;
    private final Button confirmButton;

    public PlayerSetupUI(Game game, Stage stage, int totalPlayers) {
        this.game = game;
        this.stage = stage;
        this.totalPlayers = totalPlayers;

        // Title: "Player 1 - Enter your name"
        stepLabel = new Label("Player 1 - Enter your name");
        stepLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        nameField = new TextField();
        nameField.setPromptText("Enter name...");
        nameField.setMaxWidth(250);

        HBox professionRow = createProfessionSelector();

        confirmButton = new Button("Confirm");
        confirmButton.setDisable(true);
        confirmButton.setStyle("-fx-font-size: 18px; -fx-padding: 8 20;");
        confirmButton.setOnAction(e -> handleConfirm());

        VBox root = new VBox(30, stepLabel, nameField, professionRow, confirmButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(200));

        scene = new Scene(root, 1024, 1024);
        scene.getStylesheets().add(
                getClass().getResource("/global.css").toExternalForm()
        );
    }

    public Scene getScene() {
        return scene;
    }

    private VBox createProfessionCard(ProfessionType type, String imgPath, String abilityText) {

        // Image
        ImageView img = new ImageView(
                new Image(getClass().getResourceAsStream(imgPath))
        );
        img.setFitWidth(160);
        img.setFitHeight(160);

        Label name = new Label(type.toString());
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label ability = new Label(abilityText);
        ability.setWrapText(true);
        ability.setMaxWidth(140);
        ability.setStyle("-fx-font-size: 14px;");

        VBox card = new VBox(10, img, name, ability);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setStyle(
                "-fx-border-color: black; -fx-border-width: 2; " +
                        "-fx-background-radius: 10;"
        );

        card.setOnMouseClicked(e -> selectProfession(type, card));

        return card;
    }

    private HBox createProfessionSelector() {

        VBox trainer = createProfessionCard(
                ProfessionType.TRAINER,
                "/prof/trainer.png",
                "- Pikachu \n +2 base damage \n +2 item slots"
        );

        VBox fisher = createProfessionCard(
                ProfessionType.FISHER,
                "/prof/fisher.png",
                "- Magikarp \n +3 HP (water) \n +3 damage (water)"
        );

        VBox scientist = createProfessionCard(
                ProfessionType.SCIENTIST,
                "/prof/scientist.png",
                "- Ditto \n +1 base damage \n +1 base HP"
        );

        VBox rocket = createProfessionCard(
                ProfessionType.ROCKET,
                "/prof/rocket.png",
                "- Rattata \n - steal Pokémon \n (Battle/Daycare)"
        );

        HBox row = new HBox(25, trainer, fisher, scientist, rocket);
        row.setAlignment(Pos.CENTER);

        return row;
    }
    
    private void selectProfession(ProfessionType type, VBox selectedCard) {
        this.selectedProfession = type;

        // Clear all highlight
        HBox row = (HBox) selectedCard.getParent();
        for (javafx.scene.Node n : row.getChildren()) {
            n.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        }

        // Highlight selected one
        selectedCard.setStyle(
                "-fx-border-color: #2196f3; -fx-border-width: 2;"
        );

        validateForm();
    }
    
    private void validateForm() {
        boolean ready = !nameField.getText().trim().isEmpty() && selectedProfession != null;
        confirmButton.setDisable(!ready);
    }
    
    private void handleConfirm() {
        String name = nameField.getText().trim();
        
        game.addPlayer(new Player(name, selectedProfession));

        if (currentIndex < totalPlayers) {
            currentIndex++;
            resetForm();
        } else {
            // ไปหน้าเกมจริง
            // stage.setScene(new MainGameUI(game, stage).getScene());
        }
    }

    // Reset UI
    private void resetForm() {
        stepLabel.setText("Player " + currentIndex + " - Enter your name");
        nameField.setText("");
        selectedProfession = null;
        confirmButton.setDisable(true);

        VBox root = (VBox) scene.getRoot();
        HBox row = (HBox) root.getChildren().get(2);

        for (javafx.scene.Node n : row.getChildren()) {
            n.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        }
    }
}