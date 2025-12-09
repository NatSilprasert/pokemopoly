package com.pokemopoly.ui;

import com.pokemopoly.cards.ItemCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class ItemCardUI extends VBox {

    // Configuration
    private static final int CARD_WIDTH = 300;
    private static final int CARD_HEIGHT = 450;

    private static final Color BG_COLOR = Color.rgb(230, 230, 227);
    private static final Color IMG_PLACEHOLDER_COLOR = Color.rgb(110, 110, 110);
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final int BORDER_THICKNESS = 2;

    private final ItemCard card;
    private Font pixelFont;

    public ItemCardUI(ItemCard card) {
        this.card = card;

       setPrefSize(CARD_WIDTH, CARD_HEIGHT);
        setMaxSize(CARD_WIDTH, CARD_HEIGHT);

        setBackground(new Background(
                new BackgroundFill(BG_COLOR, CornerRadii.EMPTY, Insets.EMPTY)
        ));

        setBorder(new Border(new BorderStroke(
                BORDER_COLOR,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(BORDER_THICKNESS)
        )));

        setAlignment(Pos.TOP_CENTER);
        setSpacing(10);
        setPadding(new Insets(15, 20, 20, 20));

        loadCustomFont();
        initComponents();
    }

    private void loadCustomFont() {
        try {
            // ถ้ามีฟอนต์จริง
            // pixelFont = Font.loadFont(new File("resource/font/PokemonGB.ttf").toURI().toString(), 14);
            pixelFont = Font.font("Monospaced", 14);
        } catch (Exception e) {
            pixelFont = Font.font("System", 14);
        }
    }

    private void initComponents() {

        // =========================
        // 1. EVENT TAG
        // =========================
        Label eventTag = new Label("ITEM");
        eventTag.setFont(Font.font(pixelFont.getFamily(), 16));
        eventTag.setTextFill(Color.WHITE);
        eventTag.setAlignment(Pos.CENTER);
        eventTag.setPrefSize(80, 30);
        eventTag.setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)
        ));

        StackPane tagWrapper = new StackPane(eventTag);
        tagWrapper.setPadding(new Insets(10, 0, 0, 0));

        // =========================
        // 2. EVENT TITLE
        // =========================
        Label titleLabel = new Label(card.getName());
        titleLabel.setFont(Font.font(pixelFont.getFamily(), 20));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPrefSize(260, 45);

        titleLabel.setBorder(new Border(new BorderStroke(
                BORDER_COLOR,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(BORDER_THICKNESS)
        )));

        // =========================
        // 3. IMAGE AREA (FIXED)
        // =========================
        Node imageRegion;

        // โหลดจาก resource ภายใน jar/classpath
        String resPath = "/item/" + card.getId() + ".png";
        System.out.println("Loading image: " + resPath);

        var stream = getClass().getResourceAsStream(resPath);

        if (stream != null) {
            Image img = new Image(stream);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(260);
            imageView.setFitHeight(160);
            imageView.setPreserveRatio(false);

            imageRegion = imageView;

        } else {
            System.out.println("Image NOT FOUND -> " + resPath);

            Pane placeholder = new Pane();
            placeholder.setPrefSize(260, 160);
            placeholder.setBackground(new Background(
                    new BackgroundFill(IMG_PLACEHOLDER_COLOR, CornerRadii.EMPTY, Insets.EMPTY)
            ));
            imageRegion = placeholder;
        }


        // =========================
        // 4. DESCRIPTION
        // =========================
        // DESCRIPTION AREA
        TextArea descArea = new TextArea(card.getDescription());
        descArea.setWrapText(true);
        descArea.setEditable(false);
        descArea.setFont(Font.font(pixelFont.getFamily(), 12));
        descArea.setPrefSize(260, 100);

        // ตั้งพื้นหลังของ TextArea ให้เป็นสีเดียวกับพื้นหลังการ์ด
        String bg = String.format("rgb(%d,%d,%d);",
                (int)(BG_COLOR.getRed() * 255),
                (int)(BG_COLOR.getGreen() * 255),
                (int)(BG_COLOR.getBlue() * 255)
        );

        descArea.setStyle(
                "-fx-control-inner-background:" + bg +
                        "-fx-background-color:" + bg +
                        "-fx-background-insets: 0;" +
                        "-fx-background-radius: 0;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;"
        );

        StackPane descPanel = new StackPane(descArea);
        descPanel.setPadding(new Insets(10));
        descPanel.setPrefSize(260, 100);

// กรอบดำรอบนอก
        descPanel.setBorder(new Border(new BorderStroke(
                BORDER_COLOR,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(BORDER_THICKNESS)
        )));

        // =========================
        // ADD ALL TO ROOT
        // =========================
        getChildren().addAll(
                tagWrapper,
                titleLabel,
                imageRegion,
                descPanel
        );
    }
}
