package com.pokemopoly.ui;

import com.pokemopoly.cards.*;
import com.pokemopoly.cards.event.*;
import com.pokemopoly.cards.items.*;
import com.pokemopoly.cards.pokemon.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class RunCardPreview extends Application {

    @Override
    public void start(Stage stage) {

        // Create mock Pokémon card
        PokemonCard card = new Pikachu();

        Deck<ItemCard> itemDeck = new Deck<>();
        itemDeck.addCard(List.of(new ItemCard[]{
                new Bicycle(),
                new EjectButton(),
                new EXPShare(),
                new FullHeal(),
                new HM02Fly(),
                new MaxPotion(),
                new MaxRepel(),
                new Pokedex(),
                new Potion(),
                new RareCandy(),
                new Repel(),
                new Revive(),
                new Snowball(),
                new SuperPotion(),
                new SuperRepel(),
                new TrainPass()
        }));

        Deck<EventCard> eventDeck = new Deck<>();
        eventDeck.addCard(List.of(new EventCard[]{
                new DoesPokeBallHaveEye(),
                new FightGiovanni(),
                new GoldenMagikarp(),
                new ItemReset(),
                new LegendaryInArea(),
                new Mother(),
                new NuggetBridgeChallenge(),
                new StopThisIsPolice(),
                new SuspiciousMerchant(),
                new TrueRocket()
        }));

        Deck<PokemonCard> bluePokemonDeck = new Deck<>();
        bluePokemonDeck.addCard(List.of(card));

        Deck<PokemonCard> greenPokemonDeck = new Deck<>();
        greenPokemonDeck.addCard(List.of(new PokemonCard[]{
                new Magikarp(),
                new Bellsprout(),
                new Caterpie(),
                new Clefairy(),
                new Diglett(),
                new Doduo(),
                new Exeggcute(),
                new Gastly(),
                new Geodude(),
                new Goldeen(),
                new Horsea(),
                new Jigglypuff(),
                new Kakuna(),
                new Koffing(),
                new Krabby(),
                new Magnemite(),
                new Mankey(),
                new Machop(),
                new Metapod(),
                new NidoranFemale(),
                new NidoranMale(),
                new Oddish(),
                new Paras(),
                new Pidgey(),
                new Poliwag(),
                new Psyduck(),
                new Rattata(),
                new Sandshrew(),
                new Spearow(),
                new Staryu(),
                new Tentacool(),
                new Venonat(),
                new Vulpix(),
                new Weedle(),
                new Zubat()
        }));

        Deck<PokemonCard> purplePokemonDeck = new Deck<>();
        purplePokemonDeck.addCard(List.of(new PokemonCard[]{
                new Aerodactyl(),
                new Arbok(),
                new Beedrill(),
                new Butterfree(),
                new Charmeleon(),
                new Chansey(),
                new Dewgong(),
                new Dodrio(),
                new Dugtrio(),
                new Dragonair(),
                new Electabuzz(),
                new Electrode(),
                new Ekans(),
                new Exeggutor(),
                new Fearow(),
                new Flareon(),
                new Golduck(),
                new Golem(),
                new Gyarados(),
                new Haunter(),
                new Hitmonchan(),
                new Hitmonlee(),
                new Hypno(),
                new Ivysaur(),
                new Jolteon(),
                new Kadabra(),
                new Kabutops(),
                new Kangaskhan(),
                new Magmar(),
                new Magneton(),
                new Nidoking(),
                new Nidoqueen(),
                new Ninetales(),
                new Omastar(),
                new Persian(),
                new Poliwrath(),
                new Porygon(),
                new Raichu(),
                new Rapidash(),
                new Slowbro(),
                new Starmie(),
                new Tangela(),
                new Tentacruel(),
                new Vaporeon(),
                new Victreebel(),
                new Vileplume(),
                new Wartortle(),
                new Weezing()
        }));

        Deck<PokemonCard> redPokemonDeck = new Deck<>();
        redPokemonDeck.addCard(List.of(new PokemonCard[]{
                new Alakazam(),
                new Arcanine(),
                new Articuno(),
                new Blastoise(),
                new Charizard(),
                new Cloyster(),
                new Dragonite(),
                new Gengar(),
                new Lapras(),
                new Machamp(),
                new Muk(),
                new Onix(),
                new Pidgeot(),
                new Rhydon(),
                new Snorlax(),
                new Venusaur(),
                new Zapdos(),
                new Moltres(),
                new Mew()
        }));

        Deck<PokemonCard> crownPokemonDeck = new Deck<>();
        crownPokemonDeck.addCard(List.of(new PokemonCard[]{
                new Mewtwo(),
        }));

        // Mock DeckManager (สร้าง DeckManager ว่าง ๆ เพื่อใช้ทดสอบ)
        DeckManager deckManager = new DeckManager(itemDeck,eventDeck,bluePokemonDeck,greenPokemonDeck,purplePokemonDeck,redPokemonDeck,crownPokemonDeck);

        // Create the card UI
        PokemonCardUI view = new PokemonCardUI(card, deckManager);

        // Wrap in container
        StackPane root = new StackPane(view);

        Scene scene = new Scene(root, 320, 540);
        stage.setScene(scene);
        stage.setTitle("Pokemon Card Preview");
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}