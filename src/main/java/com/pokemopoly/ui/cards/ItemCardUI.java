package com.pokemopoly.ui.cards;

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

public class ItemCardUI extends VBox {

    private int CARD_WIDTH = 300;
    private int CARD_HEIGHT = 450;

    private static final Color BG_COLOR = Color.rgb(230, 230, 227);
    private static final Color IMG_PLACEHOLDER_COLOR = Color.rgb(110, 110, 110);
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final int BORDER_THICKNESS = 2;

    private final ItemCard card;
    private static final String PIXEL_FONT = "Pixelify Sans";

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

        initComponents();
    }

    private void initComponents() {

        Label eventTag = new Label("ITEM");
        eventTag.setFont(Font.font(PIXEL_FONT, 16));
        eventTag.setTextFill(Color.WHITE);
        eventTag.setAlignment(Pos.CENTER);
        eventTag.setPrefSize(80, 30);
        eventTag.setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)
        ));

        StackPane tagWrapper = new StackPane(eventTag);
        tagWrapper.setPadding(new Insets(10, 0, 0, 0));

        Label titleLabel = new Label(card.getName());
        titleLabel.setFont(Font.font(PIXEL_FONT, 20));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPrefSize(260, 45);

        titleLabel.setBorder(new Border(new BorderStroke(
                BORDER_COLOR,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(BORDER_THICKNESS)
        )));

        Node imageRegion;

        String resPath = "/item/" + card.getId() + ".png";

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

        TextArea descArea = new TextArea(card.getDescription());
        descArea.setWrapText(true);
        descArea.setEditable(false);
        descArea.setFont(Font.font(PIXEL_FONT, 12));
        descArea.setPrefSize(260, 100);

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

        getChildren().addAll(
                tagWrapper,
                titleLabel,
                imageRegion,
                descPanel
        );
    }

    public void setSize(int ratio) {
        if (ratio <= 0) return;

        CARD_WIDTH = CARD_WIDTH / ratio;
        CARD_HEIGHT = CARD_HEIGHT / ratio;
        setPrefSize(CARD_WIDTH, CARD_HEIGHT);
        setMaxSize(CARD_WIDTH, CARD_HEIGHT);

        // ปรับ padding และ spacing ของ VBox
        Insets currentPadding = getPadding();
        setPadding(new Insets(
                currentPadding.getTop() / ratio,
                currentPadding.getRight() / ratio,
                currentPadding.getBottom() / ratio,
                currentPadding.getLeft() / ratio
        ));
        setSpacing(getSpacing() / ratio);

        for (Node node : getChildren()) {
            if (node instanceof Label label) {
                label.setFont(Font.font(label.getFont().getName(), label.getFont().getSize() / ratio));
                label.setPrefWidth(label.getPrefWidth() / ratio);
                label.setPrefHeight(label.getPrefHeight() / ratio);
            } else if (node instanceof StackPane sp) {
                sp.setPadding(new Insets(
                        sp.getPadding().getTop() / ratio,
                        sp.getPadding().getRight() / ratio,
                        sp.getPadding().getBottom() / ratio,
                        sp.getPadding().getLeft() / ratio
                ));
                sp.setPrefWidth(sp.getPrefWidth() / ratio);
                sp.setPrefHeight(sp.getPrefHeight() / ratio);
            } else if (node instanceof ImageView iv) {
                iv.setFitWidth(iv.getFitWidth() / ratio);
                iv.setFitHeight(iv.getFitHeight() / ratio);
            } else if (node instanceof Pane pane) {
                pane.setPrefWidth(pane.getPrefWidth() / ratio);
                pane.setPrefHeight(pane.getPrefHeight() / ratio);
            }
        }

        for (Node node : lookupAll(".text-area")) {
            if (node instanceof TextArea ta) {
                ta.setFont(Font.font(ta.getFont().getName(), ta.getFont().getSize() / ratio));
            }
        }
    }
}
