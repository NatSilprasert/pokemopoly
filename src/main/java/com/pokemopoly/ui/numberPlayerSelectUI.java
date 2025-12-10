package com.pokemopoly.ui;

import com.pokemopoly.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NumberPlayerSelectUI {

    private Scene scene;
    private int selectedPlayers = 0;
    private final MusicManager musicManager;

    public NumberPlayerSelectUI(Game game, Stage stage, MusicManager musicManager) {
        this.musicManager = musicManager;

        Label title = new Label("Choose number of players");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button btn2 = createSquareButton("2");
        Button btn3 = createSquareButton("3");
        Button btn4 = createSquareButton("4");

        btn2.setOnAction(e -> select(btn2, btn3, btn4, 2));
        btn3.setOnAction(e -> select(btn2, btn3, btn4, 3));
        btn4.setOnAction(e -> select(btn2, btn3, btn4, 4));

        HBox playerButtonRow = new HBox(20, btn2, btn3, btn4);
        playerButtonRow.setAlignment(Pos.CENTER);

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle("-fx-font-size: 18px; -fx-padding: 8 20;");
        confirmBtn.setDisable(true);

        confirmBtn.setOnAction(e -> {
            game.setPlayerCount(selectedPlayers);
            musicManager.fadeOutCurrent(0.5, () -> musicManager.playMusicForScene("opening"));
            PlayerSetupUI nextUI = new PlayerSetupUI(game, stage, selectedPlayers, musicManager);
            stage.setScene(nextUI.getScene());
        });

        VBox root = new VBox(30, title, playerButtonRow, confirmBtn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));

        scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(
                getClass().getResource("/global.css").toExternalForm()
        );
    }

    private Button createSquareButton(String text) {
        Button b = new Button(text);
        b.setPrefSize(80, 80);
        b.setStyle(
                "-fx-font-size: 32px; -fx-font-weight: bold; " +
                        "-fx-background-radius: 10; "
        );
        return b;
    }

    private void select(Button b2, Button b3, Button b4, int num) {
        selectedPlayers = num;

        b2.setStyle(defaultStyle());
        b3.setStyle(defaultStyle());
        b4.setStyle(defaultStyle());

        Button selectedBtn = switch (num) {
            case 2 -> b2;
            case 3 -> b3;
            case 4 -> b4;
            default -> null;
        };

        selectedBtn.setStyle(
                "-fx-font-size: 32px; -fx-font-weight: bold;" +
                        "-fx-background-color: #9cd6ff;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10 -fx-border-color: black; -fx-border-width: 2;"
        );

        Scene s = scene;
        VBox root = (VBox) s.getRoot();
        Button confirmBtn = (Button) root.getChildren().get(2);
        confirmBtn.setDisable(false);
    }

    private String defaultStyle() {
        return "-fx-font-size: 32px; -fx-font-weight: bold; " +
                "-fx-background-radius: 10; ";
    }

    public Scene getScene() { return scene; }
}