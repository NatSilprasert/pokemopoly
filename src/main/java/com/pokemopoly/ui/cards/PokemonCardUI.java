package com.pokemopoly.ui.cards;

import com.pokemopoly.cards.DeckManager;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonDeckColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PokemonCardUI extends StackPane {

    private double CARD_WIDTH = 300;
    private double CARD_HEIGHT = 450;

    // โหลดฟอนต์ (ถ้ามีไฟล์ .ttf ให้เปลี่ยน path ตรงนี้)
    // ถ้าไม่มีจะใช้ Monospaced เพื่อให้ดูเป็น Pixel
    private static final String PIXEL_FONT = "Pixelify Sans";

    public PokemonCardUI(PokemonCard card) {

        this.setPrefSize(CARD_WIDTH, CARD_HEIGHT);
        this.setMaxSize(CARD_WIDTH, CARD_HEIGHT);

        // --- LAYER 1: Border Background (สีตาม Deck) ---
        Rectangle borderRect = new Rectangle(CARD_WIDTH, CARD_HEIGHT);

        if (card.getDeckColor() != null) {
            Color deckColor = getColorFromEnum(card.getDeckColor());
            borderRect.setFill(deckColor);
            borderRect.setStroke(deckColor);
        } else {
            // ถ้าไม่มี deck color ให้ใส่โปร่งใส หรือใช้ default สีเทา
            borderRect.setFill(Color.TRANSPARENT);
            borderRect.setStroke(Color.TRANSPARENT);
        }

        borderRect.setStrokeWidth(0);

        // --- LAYER 2: Inner Content (พื้นหลังการ์ดสีครีม/เทาอ่อน) ---
        VBox innerContent = new VBox();
        double borderWidth = 8.0; // ความหนาของขอบสี Deck
        innerContent.setMaxSize(CARD_WIDTH - (borderWidth * 2), CARD_HEIGHT - (borderWidth * 2));
        innerContent.setStyle("-fx-background-color: #E8E8E8; -fx-border-color: black; -fx-border-width: 2;");
        innerContent.setPadding(new Insets(10));
        innerContent.setSpacing(8);
        innerContent.setAlignment(Pos.TOP_CENTER);

        // ==========================================
        // SECTION 1: HEADER "POKEMON" (กล่องดำ)
        // ==========================================
        Label headerLabel = new Label("POKEMON");
        headerLabel.setFont(Font.font(PIXEL_FONT, FontWeight.BOLD, 16));
        headerLabel.setTextFill(Color.WHITE);

        StackPane headerBox = new StackPane(headerLabel);
        headerBox.setStyle("-fx-background-color: #222222;"); // สีดำ
        headerBox.setPadding(new Insets(4, 10, 4, 10));
        headerBox.setMaxWidth(120);

        // ==========================================
        // SECTION 2: NAME & TYPE (กล่องชื่อ)
        // ==========================================
        VBox nameBox = new VBox(2);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #E8E8E8;");
        nameBox.setPadding(new Insets(5));
        nameBox.setMaxWidth(Double.MAX_VALUE);

        Label nameLabel = new Label(card.getName().toUpperCase());
        nameLabel.setFont(Font.font(PIXEL_FONT, FontWeight.BOLD, 20)); // ชื่อตัวใหญ่

        // แปลง List<Type> เป็น String
        String typeString = card.getTypes().toString().replace("[", "").replace("]", "");
        Label typeLabel = new Label(typeString);
        typeLabel.setFont(Font.font(PIXEL_FONT, FontWeight.NORMAL, 10)); // ธาตุตัวเล็ก

        nameBox.getChildren().addAll(nameLabel, typeLabel);

        // ==========================================
        // SECTION 3: SPRITE IMAGE (รูปโปเกม่อน)
        // ==========================================
        ImageView pokemonImage = new ImageView();
        try {
            // อย่าลืมตรวจสอบ path รูปภาพให้ถูกต้องตามโปรเจคของคุณ
            String imagePath = "/sprites/" + card.getName().toLowerCase() + ".png";
            Image img = new Image(getClass().getResourceAsStream(imagePath));
            pokemonImage.setImage(img);
        } catch (Exception e) {
            System.out.println("Image not found for: " + card.getName());
            // อาจจะใส่รูป Placeholder แทนถ้าหาไม่เจอ
        }

        // ----- แก้ไขขนาดตรงนี้ -----
        // จากเดิม 120 เปลี่ยนเป็น 220 (หรือค่าที่ต้องการ)
        pokemonImage.setFitHeight(120);
        pokemonImage.setPreserveRatio(true);
        // เพิ่มการจัดวางให้อยู่กึ่งกลางเผื่อรูปมีขนาดความกว้างไม่เท่ากัน
        StackPane imageContainer = new StackPane(pokemonImage);
        imageContainer.setAlignment(Pos.CENTER);
        // -------------------------

        // ==========================================
        // SECTION 4: STATS BAR (HP/Damage + Price)
        // ==========================================
        HBox statsBar = new HBox(0);
        statsBar.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        statsBar.setMaxWidth(Double.MAX_VALUE);

        // 4.1 Left Side: Damage & HP (พื้นหลังเทาอ่อน)
        HBox leftStats = new HBox(5);
        leftStats.setAlignment(Pos.CENTER);
        leftStats.setPadding(new Insets(5));
        leftStats.setStyle("-fx-background-color: #D6D6D6;"); // สีเทาอ่อน
        HBox.setHgrow(leftStats, Priority.ALWAYS); // ให้ขยายเต็มพื้นที่ที่เหลือ

        // ใช้ TextFlow เพื่อทำตัวอักษรสีแดงผสมดำ
        Text txtDmg = new Text("atk ");
        txtDmg.setFont(Font.font(PIXEL_FONT, FontWeight.BOLD, 12));

        Text valDmg = new Text(String.valueOf(card.getPower()));
        valDmg.setFont(Font.font(PIXEL_FONT, FontWeight.BOLD, 12));
        valDmg.setFill(Color.RED);

        Text txtHp = new Text(" hp ");
        txtHp.setFont(Font.font(PIXEL_FONT, FontWeight.BOLD, 12));

        Text valHp = new Text(String.valueOf(card.getHp()));
        valHp.setFont(Font.font(PIXEL_FONT, FontWeight.BOLD, 12));
        valHp.setFill(Color.RED);

        TextFlow statsText = new TextFlow(txtDmg, valDmg, txtHp, valHp);
        leftStats.getChildren().add(statsText);

        // 4.2 Right Side: Price (พื้นหลังเทาเข้ม)
        HBox rightStats = new HBox(5);
        rightStats.setAlignment(Pos.CENTER);
        rightStats.setPadding(new Insets(5, 10, 5, 10));
        rightStats.setStyle("-fx-background-color: #666666;"); // สีเทาเข้ม
        rightStats.setMinWidth(80);

        // รูปเหรียญ (ถ้ามีไฟล์รูปให้ใช้ ImageView แทน Circle)
        Circle coinIcon = new Circle(7, Color.GOLD);
        coinIcon.setStroke(Color.BLACK);
        coinIcon.setStrokeWidth(1);

        Label priceLabel = new Label(String.valueOf(card.getPrice()));
        priceLabel.setFont(Font.font(PIXEL_FONT, FontWeight.BOLD, 14));
        priceLabel.setTextFill(Color.WHITE);

        rightStats.getChildren().addAll(coinIcon, priceLabel);

        statsBar.getChildren().addAll(leftStats, rightStats);

        // ==========================================
        // SECTION 5: ABILITY / DESCRIPTION (กล่องล่างสุด)
        // ==========================================
        StackPane descBox = new StackPane();
        descBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #E8E8E8;");
        descBox.setPadding(new Insets(8));
        descBox.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(descBox, Priority.ALWAYS); // ให้ยืดเต็มพื้นที่ด้านล่าง

        Label descLabel = new Label(card.getDescription());
        descLabel.setWrapText(true);
        descLabel.setFont(Font.font(PIXEL_FONT, 11));
        descLabel.setAlignment(Pos.CENTER);
        descLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        descBox.getChildren().add(descLabel);

        // --- ASSEMBLY ---
        innerContent.getChildren().addAll(
                headerBox,
                nameBox,
                pokemonImage,
                statsBar,
                descBox
        );

        this.getChildren().addAll(borderRect, innerContent);
    }

    // Helper Method แปลง Enum สี เป็น JavaFX Color
    private Color getColorFromEnum(PokemonDeckColor colorEnum) {
        if (colorEnum == null) return Color.GRAY; // Default

        switch (colorEnum) {
            case BLUE: return Color.web("#4287f5"); // สีฟ้า
            case GREEN: return Color.web("#42f560"); // สีเขียว
            case RED: return Color.web("#f54242"); // สีแดง
            case PURPLE: return Color.web("#9b42f5"); // สีม่วง
            case CROWN: return Color.web("#f5d742"); // สีทอง
            default: return Color.GRAY;
        }
    }

    public void setSize(int ratio) {
        if (ratio <= 0) return;

        this.setPrefSize(CARD_WIDTH / ratio, CARD_HEIGHT / ratio);
        this.setMaxSize(CARD_WIDTH / ratio, CARD_HEIGHT / ratio);

        // borderRect
        if (!getChildren().isEmpty() && getChildren().get(0) instanceof Rectangle borderRect) {
            borderRect.setWidth(CARD_WIDTH / ratio);
            borderRect.setHeight(CARD_HEIGHT / ratio);
        }

        // innerContent
        if (getChildren().size() > 1 && getChildren().get(1) instanceof VBox innerContent) {

            double borderWidth = 8.0 / ratio;
            innerContent.setMaxSize(
                    (CARD_WIDTH - borderWidth * 2) / ratio,
                    (CARD_HEIGHT - borderWidth * 2) / ratio
            );
            innerContent.setSpacing(8.0 / ratio);
            innerContent.setPadding(new Insets(10 / ratio));

            for (var node : innerContent.getChildren()) {

                // ✔ Label inside StackPane
                if (node instanceof StackPane sp && !sp.getChildren().isEmpty()
                        && sp.getChildren().get(0) instanceof Label lbl) {

                    Font oldFont = lbl.getFont();
                    lbl.setFont(Font.font(
                            oldFont.getFamily(),
                            oldFont.getSize() / ratio      // ❗ไม่ยุ่งกับ FontWeight
                    ));

                    sp.setPadding(scaleInsets(sp.getPadding(), ratio));
                }

                // ✔ VBox inside
                else if (node instanceof VBox vb) {
                    vb.setSpacing(vb.getSpacing() / ratio);
                    vb.setPadding(scaleInsets(vb.getPadding(), ratio));

                    for (var child : vb.getChildren()) {
                        if (child instanceof Label lbl) {
                            Font oldFont = lbl.getFont();
                            lbl.setFont(Font.font(
                                    oldFont.getFamily(),
                                    oldFont.getSize() / ratio   // ❗เฉพาะลด size
                            ));
                        }
                    }
                }

                // ✔ Image
                else if (node instanceof ImageView imgView) {
                    imgView.setFitHeight(imgView.getFitHeight() / ratio);
                    imgView.setFitWidth(imgView.getFitWidth() / ratio);
                }

                // ✔ HBox
                else if (node instanceof HBox hbox) {
                    hbox.setSpacing(hbox.getSpacing() / ratio);
                    hbox.setPadding(scaleInsets(hbox.getPadding(), ratio));

                    for (var child : hbox.getChildren()) {

                        if (child instanceof Label lbl) {
                            Font oldFont = lbl.getFont();
                            lbl.setFont(Font.font(
                                    oldFont.getFamily(),
                                    oldFont.getSize() / ratio   // ❗ไม่แตะน้ำหนักฟอนต์
                            ));
                        }

                        else if (child instanceof TextFlow tf) {
                            tf.getChildren().forEach(txt -> {
                                if (txt instanceof Text t) {
                                    Font oldFont = t.getFont();
                                    t.setFont(Font.font(
                                            oldFont.getFamily(),
                                            oldFont.getSize() / ratio
                                    ));
                                }
                            });
                        }

                        else if (child instanceof Circle circle) {
                            circle.setRadius(circle.getRadius() / ratio);
                        }
                    }
                }

                // generic StackPane
                else if (node instanceof StackPane sp) {
                    sp.setPadding(scaleInsets(sp.getPadding(), ratio));
                }
            }
        }
    }

    private Insets scaleInsets(Insets in, int ratio) {
        return new Insets(
                in.getTop() / ratio,
                in.getRight() / ratio,
                in.getBottom() / ratio,
                in.getLeft() / ratio
        );
    }
}