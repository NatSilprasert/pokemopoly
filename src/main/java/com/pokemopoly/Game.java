
package com.pokemopoly;

import com.pokemopoly.board.Board;
import com.pokemopoly.board.GrassColor;
import com.pokemopoly.board.Tile;
import com.pokemopoly.board.tile.*;
import com.pokemopoly.cards.*;
import com.pokemopoly.cards.event.*;
import com.pokemopoly.cards.items.*;
import com.pokemopoly.cards.pokemon.*;
import com.pokemopoly.player.Player;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private Board board;
    private DeckManager deckManager;
    private List<Player> players;
    private int playerCount;
    private int turn = 0;

    private StackPane root;
    private Runnable nextTurnCallback;

    private final MusicManager musicManager;

    public Game(MusicManager musicManager) {
        this.musicManager = musicManager;
        this.players = new ArrayList<>();
    }

    public void setUpGame(StackPane root, Runnable nextTurnCallback) {
        this.root = root;
        this.nextTurnCallback = nextTurnCallback;

        setUpBoard();
        setUpDeckManager();
        setUpPlayerItems();
    }

    public boolean isGameEnd() {
        return turn >= 50 * playerCount;
    }

    public void setUpBoard() {
        List<Tile> tiles = new ArrayList<>(Arrays.asList(
                new StartTile("Start Tile", 0, root, nextTurnCallback),
                new GrassTile("Green Grass Tile", 1, GrassColor.GREEN, root, v -> nextTurnCallback.run()),
                new EventTile("Event Tile", 2, root, v -> nextTurnCallback.run()),
                new GrassTile("Green Grass Tile", 3, GrassColor.GREEN, root, v -> nextTurnCallback.run()),
                new GrassTile("Green Grass Tile", 4, GrassColor.GREEN, root, v -> nextTurnCallback.run()),
                new CityTile("City Tile", 5, root, v -> nextTurnCallback.run(), musicManager),
                new GrassTile("Green Grass Tile", 6, GrassColor.GREEN, root, v -> nextTurnCallback.run()),
                new GrassTile("Green Grass Tile", 7, GrassColor.GREEN, root, v -> nextTurnCallback.run()),
                new ItemTile("Item Tile", 8, root, v -> nextTurnCallback.run()),
                new GrassTile("Green Grass Tile", 9, GrassColor.GREEN, root, v -> nextTurnCallback.run()),
                new BattleTile("Gym 1", 10, root, v -> nextTurnCallback.run(), musicManager),
                new GrassTile("Green Grass Tile", 11, GrassColor.BLUE, root, v -> nextTurnCallback.run()),
                new CaveTile("Cave Tile", 12, root, nextTurnCallback, musicManager),
                new GrassTile("Green Grass Tile", 13, GrassColor.BLUE, root, v -> nextTurnCallback.run()),
                new GrassTile("Green Grass Tile", 14, GrassColor.BLUE, root, v -> nextTurnCallback.run()),
                new CityTile("City Tile", 15, root, v -> nextTurnCallback.run(), musicManager),
                new GrassTile("Green Grass Tile", 16, GrassColor.BLUE, root, v -> nextTurnCallback.run()),
                new GrassTile("Green Grass Tile", 17, GrassColor.BLUE, root, v -> nextTurnCallback.run()),
                new DaycareTile("Daycare Tile", 18, root, nextTurnCallback, musicManager),
                new GrassTile("Green Grass Tile", 19, GrassColor.BLUE, root, v -> nextTurnCallback.run()),
                new BattleTile("Villain", 20, root, v -> nextTurnCallback.run(), musicManager),
                new GrassTile("Purple Grass Tile", 21, GrassColor.PURPLE, root, v -> nextTurnCallback.run()),
                new EventTile("Event Tile", 22, root, v -> nextTurnCallback.run()),
                new GrassTile("Purple Grass Tile", 23, GrassColor.PURPLE, root, v -> nextTurnCallback.run()),
                new GrassTile("Purple Grass Tile", 24, GrassColor.PURPLE, root, v -> nextTurnCallback.run()),
                new CityTile("City Tile", 25, root, v -> nextTurnCallback.run(), musicManager),
                new GrassTile("Purple Grass Tile", 26, GrassColor.PURPLE, root, v -> nextTurnCallback.run()),
                new GrassTile("Purple Grass Tile", 27, GrassColor.PURPLE, root, v -> nextTurnCallback.run()),
                new ItemTile("Item Tile", 28, root, v -> nextTurnCallback.run()),
                new GrassTile("Purple Grass Tile", 29, GrassColor.PURPLE, root, v -> nextTurnCallback.run()),
                new BattleTile("Gym 2", 30, root, v -> nextTurnCallback.run(), musicManager),
                new GrassTile("Red Grass Tile", 31, GrassColor.RED, root, v -> nextTurnCallback.run()),
                new CaveTile("Cave Tile", 32, root, nextTurnCallback, musicManager),
                new GrassTile("Red Grass Tile", 33, GrassColor.RED, root, v -> nextTurnCallback.run()),
                new GrassTile("Red Grass Tile", 34, GrassColor.RED, root, v -> nextTurnCallback.run()),
                new CityTile("City Tile", 35, root, v -> nextTurnCallback.run(), musicManager),
                new GrassTile("Red Grass Tile", 36, GrassColor.RED, root, v -> nextTurnCallback.run()),
                new GrassTile("Red Grass Tile", 37, GrassColor.RED, root, v -> nextTurnCallback.run()),
                new GrassTile("Red Grass Tile", 38, GrassColor.RED, root, v -> nextTurnCallback.run()),
                new GrassTile("Crown Grass Tile", 39, GrassColor.CROWN, root, v -> nextTurnCallback.run())
        ));

        this.board = new Board(tiles);
    }

    public DeckManager getDeckManager() {
        return deckManager;
    }

    private void setUpDeckManager() {
        Deck<ItemCard> itemDeck = new Deck<>();
        itemDeck.addCard(List.of(new ItemCard[]{
                new Bicycle(), new Bicycle(), new Bicycle(), new Bicycle(), new Bicycle(),
                new EjectButton(), new EjectButton(), new EjectButton(), new EjectButton(), new EjectButton(),
                new EXPShare(), new EXPShare(), new EXPShare(), new EXPShare(), new EXPShare(),
                new FullHeal(), new FullHeal(), new FullHeal(), new FullHeal(), new FullHeal(),
                new HM02Fly(), new HM02Fly(), new HM02Fly(), new HM02Fly(), new HM02Fly(),
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
                new TrainPass(),
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
                new TrainPass(),
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
                new TrainPass(),
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
                new TrainPass(),
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
                new TrainPass(),
        }));

        Deck<EventCard> eventDeck = new Deck<>();
        eventDeck.addCard(List.of(new EventCard[]{
                new DoesPokeBallHaveEye(), new DoesPokeBallHaveEye(), new DoesPokeBallHaveEye(), new DoesPokeBallHaveEye(), new DoesPokeBallHaveEye(),
                new FightGiovanni(), new FightGiovanni(), new FightGiovanni(), new FightGiovanni(), new FightGiovanni(),
                new GoldenMagikarp(), new GoldenMagikarp(), new GoldenMagikarp(), new GoldenMagikarp(), new GoldenMagikarp(), new GoldenMagikarp(),
                new ItemReset(), new ItemReset(), new ItemReset(), new ItemReset(), new ItemReset(), new ItemReset(),
                new LegendaryInArea(), new LegendaryInArea(), new LegendaryInArea(), new LegendaryInArea(), new LegendaryInArea(), new LegendaryInArea(),
                new Mother(), new Mother(), new Mother(), new Mother(), new Mother(), new Mother(),
                new NuggetBridgeChallenge(), new NuggetBridgeChallenge(), new NuggetBridgeChallenge(), new NuggetBridgeChallenge(), new NuggetBridgeChallenge(), new NuggetBridgeChallenge(),
                new StopThisIsPolice(), new StopThisIsPolice(), new StopThisIsPolice(), new StopThisIsPolice(), new StopThisIsPolice(), new StopThisIsPolice(),
                new SuspiciousMerchant(), new SuspiciousMerchant(), new SuspiciousMerchant(), new SuspiciousMerchant(), new SuspiciousMerchant(),
                new SuspiciousMerchant(), new TrueRocket(), new TrueRocket(), new TrueRocket(), new TrueRocket(), new TrueRocket(), new TrueRocket()
        }));

        Deck<PokemonCard> bluePokemonDeck = new Deck<>();
        List<PokemonCard> blueCards = List.of(
                new Abra(), new Bulbasaur(), new Charmander(), new Clefable(), new Cubone(),
                new Ditto(), new Drowzee(), new Dratini(), new Eevee(), new FarfetchD(),
                new Gloom(), new Golbat(), new Graveler(), new Grimer(), new Growlithe(),
                new Jynx(), new Kabuto(), new Kingler(), new Lickitung(), new Machoke(),
                new Marowak(), new Meowth(), new MrMime(), new Nidorina(), new Nidorino(),
                new Omanyte(), new Parasect(), new Pinsir(), new Pidgeotto(), new Pikachu(),
                new Poliwhirl(), new Ponyta(), new Primeape(), new Raticate(), new Rhyhorn(),
                new Seadra(), new Seel(), new Shellder(), new Slowpoke(), new Tauros(),
                new Scyther(), new Seaking(), new Sandslash(), new Squirtle(), new Venomoth(),
                new Voltorb(), new Weepinbell(), new Wigglytuff()
        );
        blueCards.forEach(card -> card.setDeckColor(PokemonDeckColor.BLUE));
        bluePokemonDeck.addCard(blueCards);

        Deck<PokemonCard> greenPokemonDeck = new Deck<>();
        List<PokemonCard> greenCards = List.of(
                new Magikarp(), new Bellsprout(), new Caterpie(), new Clefairy(), new Diglett(),
                new Doduo(), new Exeggcute(), new Gastly(), new Geodude(), new Goldeen(),
                new Horsea(), new Jigglypuff(), new Kakuna(), new Koffing(), new Krabby(),
                new Magnemite(), new Mankey(), new Machop(), new Metapod(), new NidoranFemale(),
                new NidoranMale(), new Oddish(), new Paras(), new Pidgey(), new Poliwag(),
                new Psyduck(), new Rattata(), new Sandshrew(), new Spearow(), new Staryu(),
                new Tentacool(), new Venonat(), new Vulpix(), new Weedle(), new Zubat()
        );
        greenCards.forEach(card -> card.setDeckColor(PokemonDeckColor.GREEN));
        greenPokemonDeck.addCard(greenCards);

        Deck<PokemonCard> purplePokemonDeck = new Deck<>();
        List<PokemonCard> purpleCards = List.of(
                new Aerodactyl(), new Arbok(), new Beedrill(), new Butterfree(), new Charmeleon(),
                new Chansey(), new Dewgong(), new Dodrio(), new Dugtrio(), new Dragonair(),
                new Electabuzz(), new Electrode(), new Ekans(), new Exeggutor(), new Fearow(),
                new Flareon(), new Golduck(), new Golem(), new Gyarados(), new Haunter(),
                new Hitmonchan(), new Hitmonlee(), new Hypno(), new Ivysaur(), new Jolteon(),
                new Kadabra(), new Kabutops(), new Kangaskhan(), new Magmar(), new Magneton(),
                new Nidoking(), new Nidoqueen(), new Ninetales(), new Omastar(), new Persian(),
                new Poliwrath(), new Porygon(), new Raichu(), new Rapidash(), new Slowbro(),
                new Starmie(), new Tangela(), new Tentacruel(), new Vaporeon(), new Victreebel(),
                new Vileplume(), new Wartortle(), new Weezing()
        );
        purpleCards.forEach(card -> card.setDeckColor(PokemonDeckColor.PURPLE));
        purplePokemonDeck.addCard(purpleCards);

        Deck<PokemonCard> redPokemonDeck = new Deck<>();
        List<PokemonCard> redCards = List.of(
                new Alakazam(), new Arcanine(), new Articuno(), new Blastoise(), new Charizard(),
                new Cloyster(), new Dragonite(), new Gengar(), new Lapras(), new Machamp(),
                new Muk(), new Onix(), new Pidgeot(), new Rhydon(), new Snorlax(),
                new Venusaur(), new Zapdos(), new Moltres(), new Mew()
        );
        redCards.forEach(card -> card.setDeckColor(PokemonDeckColor.RED));
        redPokemonDeck.addCard(redCards);

        Deck<PokemonCard> crownPokemonDeck = new Deck<>();
        List<PokemonCard> crownCards = List.of(new Mewtwo());
        crownCards.forEach(card -> card.setDeckColor(PokemonDeckColor.CROWN));
        crownPokemonDeck.addCard(crownCards);

        deckManager = new DeckManager(itemDeck, eventDeck, bluePokemonDeck, greenPokemonDeck, purplePokemonDeck, redPokemonDeck, crownPokemonDeck);
        deckManager.shuffleAll();
    }

    public void setUpPlayerItems() {
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                ItemCard itemCard = deckManager.drawItem();
                player.getHand().add(itemCard);
            }
        }
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void nextPlayer() {
        turn += 1;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void setPlayerCount(int n) {
        playerCount = n;
    }

    public Player getCurrentPlayer() {
        return players.get(turn % players.size());
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

}
