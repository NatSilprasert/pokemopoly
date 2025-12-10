
package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.MusicManager;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.List;
import java.util.stream.Collectors;

public class DaycareTile extends Tile {

    private final StackPane rootPane;
    private final Runnable endTurnCallback;
    private final MusicManager musicManager;

    public DaycareTile(String name, int index, StackPane rootPane, Runnable endTurnCallback, MusicManager musicManager) {
        super(name, index);
        this.rootPane = rootPane;
        this.endTurnCallback = endTurnCallback;
        this.musicManager = musicManager;
    }

    @Override
    public void onLand(Player player, Game game) {


        List<PokemonCard> evolvables = player.getTeam().stream()
                .filter(p -> p instanceof Evolvable)
                .toList();

        if (evolvables.isEmpty()) {
            if (endTurnCallback != null) endTurnCallback.run();
            return;
        }

        musicManager.fadeOutCurrent(1, () -> musicManager.playMusicForScene("daycare"));

        VBox overlay = new VBox(25);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 30;");
        overlay.setMaxWidth(1100);

        Label title = makeLabel("Select a Pokémon to Evolve", 28, Color.WHITE);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        boolean shrink = evolvables.size() > 3;
        grid.setHgap(shrink ? 20 : 40);
        grid.setVgap(shrink ? 10 : 20);

        final PokemonCard[] selected = {null};
        int index = 0;

        for (PokemonCard card : evolvables) {
            VBox cardUI = buildPokemonCard(card, shrink);
            int col = index % 3;
            int row = shrink ? 2 : 1;
            grid.add(cardUI, col, row);
            index++;

            cardUI.setOnMouseClicked(e -> {
                grid.getChildren().forEach(n -> n.setStyle(baseCardStyle(shrink)));
                cardUI.setStyle(baseCardStyle(shrink) + "-fx-border-color: yellow; -fx-border-width: 3;");
                selected[0] = card;
            });
        }

        overlay.getChildren().addAll(title, grid);
        rootPane.getChildren().add(overlay);

        Button confirm = new Button("Confirm Evolution");
        confirm.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 20px; -fx-padding: 12 30;");
        overlay.getChildren().add(confirm);

        confirm.setOnAction(e -> {
            if (selected[0] == null) return;

            PokemonCard oldCard = selected[0];
            PokemonCard evo = ((Evolvable) oldCard).evolve();
            int oldIdx = player.getTeam().indexOf(oldCard);

            player.setPokemon(oldIdx, evo);
            System.out.println(player.getName() + " evolved " + oldCard.getName() + " into " + evo.getName());

            overlay.getChildren().clear();

            Label success = makeLabel("Evolution Successful!", 28, Color.LIGHTGREEN);

            HBox evoBox = new HBox(40);
            evoBox.setAlignment(Pos.CENTER);

            VBox oldBox = new VBox(5);
            oldBox.setAlignment(Pos.CENTER);
            oldBox.getChildren().addAll(buildPokemonCard(oldCard, false).getChildren());

            VBox newBox = new VBox(5);
            newBox.setAlignment(Pos.CENTER);
            newBox.getChildren().addAll(buildPokemonCard(evo, false).getChildren());

            Label arrow = new Label("→");
            arrow.setFont(Font.font("Pixelify Sans", 36));
            arrow.setTextFill(Color.LIGHTGRAY);
            arrow.setAlignment(Pos.CENTER);

            evoBox.getChildren().addAll(oldBox, arrow, newBox);

            Button ok = new Button("OK");
            ok.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 20px; -fx-padding: 12 30;");
            ok.setOnAction(ev -> {
                rootPane.getChildren().remove(overlay);
                if (endTurnCallback != null) endTurnCallback.run();
                musicManager.fadeOutCurrent(1, () -> musicManager.playWithFade("palletTown", true, 1.0));
            });

            overlay.getChildren().addAll(success, evoBox, ok);
        });
    }

    private VBox buildPokemonCard(PokemonCard card, boolean shrink) {
        VBox box = new VBox(shrink ? 5 : 10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(shrink ? 5 : 10));
        box.setStyle(baseCardStyle(shrink));

        Label name = makeLabel(card.getName(), 20, Color.WHITE);
        String typeText = card.getTypes().stream().map(Enum::name).collect(Collectors.joining(" / "));
        Label type = makeLabel("Type: " + typeText, 14, Color.LIGHTGRAY);

        ImageView img = new ImageView(getImage(card));
        img.setFitHeight(shrink ? 80 : 120);
        img.setPreserveRatio(true);

        Label stats = makeLabel("ATK " + card.getPower() + "   HP " + card.getHp() + "/" + card.getMaxHp(), 16, Color.WHITE);

        box.getChildren().addAll(name, type, img, stats);
        return box;
    }

    private String baseCardStyle(boolean shrink) {
        return "-fx-background-color: #1e1e1e;" +
                "-fx-border-color: white;" +
                (shrink ? "-fx-border-width: 1;" : "-fx-border-width: 2;") +
                "-fx-background-radius: 12;";
    }

    private Label makeLabel(String text, int size, Color color) {
        Label lb = new Label(text);
        lb.setFont(Font.font("Pixelify Sans", size));
        lb.setTextFill(color);
        return lb;
    }

    private Image getImage(PokemonCard card) {
        try {
            return new Image(getClass().getResourceAsStream("/sprites/" + card.getName().toLowerCase() + ".png"));
        } catch (Exception e) {
            return new Image(getClass().getResourceAsStream("/sprites/placeholder.png"));
        }
    }
}