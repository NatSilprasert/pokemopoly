
package com.pokemopoly.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.Random;

public class RollDiceUI {

    private VBox root;
    private Timeline diceAnimation;
    private int currentFace = 1;
    private ImageView diceView;       // ⬅️ ย้ายไปเป็น field
    private DiceResultCallback callback;  // ⬅️ ย้ายไปเป็น field

    public interface DiceResultCallback {
        void onResult(int diceValue);
    }

    public RollDiceUI(DiceResultCallback callback) {
        this.callback = callback;

        diceView = new ImageView();
        diceView.setFitWidth(150);
        diceView.setFitHeight(150);
        diceView.setImage(loadDice(1));

        // หมุนลูกเต๋า
        diceAnimation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            currentFace++;
            if (currentFace > 6) currentFace = 1;
            diceView.setImage(loadDice(currentFace));
        }));
        diceAnimation.setCycleCount(Timeline.INDEFINITE);
        diceAnimation.play();

        Label resultLabel = new Label("");
        resultLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button rollButton = new Button("ROLL!");
        rollButton.setOnAction(e -> {
            diceAnimation.stop();

            int result = new Random().nextInt(6) + 1;
            diceView.setImage(loadDice(result));
            resultLabel.setText("You rolled " + result + "!");

            rollButton.setText("OK");
            rollButton.setOnAction(ev -> callback.onResult(result));
        });

        root = new VBox(20, diceView, resultLabel, rollButton);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-padding: 40;");
    }

    /** ใช้สำหรับให้ Bot roll อัตโนมัติ */
    public void autoRoll() {
        // หยุด timeline หลังจาก random สุ่มครั้งเดียว
        Timeline autoTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            currentFace++;
            if (currentFace > 6) currentFace = 1;
            diceView.setImage(loadDice(currentFace));
        }));
        autoTimeline.setCycleCount(10); // หมุน 10 ครั้ง
        autoTimeline.setOnFinished(e -> {
            autoTimeline.stop();
            int result = new Random().nextInt(6) + 1;
            diceView.setImage(loadDice(result));
            callback.onResult(result);
        });
        autoTimeline.play();
    }

    public VBox getView() {
        return root;
    }

    private Image loadDice(int face) {
        return new Image(getClass().getResource("/dices/dice" + face + ".png").toExternalForm());
    }
}
