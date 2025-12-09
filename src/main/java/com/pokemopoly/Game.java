package com.pokemopoly;

import com.pokemopoly.board.Board;
import com.pokemopoly.board.GrassColor;
import com.pokemopoly.board.Tile;
import com.pokemopoly.board.tile.*;
import com.pokemopoly.cards.*;
import com.pokemopoly.cards.event.*;
import com.pokemopoly.cards.items.*;
import com.pokemopoly.cards.pokemon.*;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private DeckManager deckManager;
    private List<Player> players;
    private int playerCount;
    private int turn = 0;

    public Game() {
        // setup
        this.players = new ArrayList<>();
        setUpDeckManager();
    }

    public void setUpBoard(List<Tile> tiles) {
        this.board = new Board(tiles);
    }

    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    public DeckManager getDeckManager() {
        return deckManager;
    }

    public void start() {
        System.out.println("Starting game setup...");
        setupPlayers();
        setUpDeckManager();

        System.out.println("--- Game Start ---");

        while (turn <= players.size() * 10) {
            Player currentPlayer = players.get(turn % players.size());

            if (currentPlayer.isSkipTurn()) {
                System.out.println("💤 " + currentPlayer.getName() + " is asleep and skips this turn!");
                currentPlayer.setSkipTurn(false); // wake up after skipping one turn
                turn++;
                continue;
            } //Edit 10/23/68

            System.out.println("\n🎲 It's " + currentPlayer.getName() + "'s turn!");

            // 🔥 Burn damage phase
            for (PokemonCard pokemon : currentPlayer.getTeam()) {
                if (pokemon.isBurned() && pokemon.isAlive()) {
                    pokemon.setHp(pokemon.getHp() - 2);
                    System.out.println("🔥 " + pokemon.getName() + " is burned and takes 2 damage! (HP: "
                            + pokemon.getHp() + "/" + pokemon.getMaxHp() + ")");
                }
            } //Edit 10/23/68

            Scanner scanner = new Scanner(System.in);

            // use ability
            System.out.println("Select ability to use:");
            System.out.println("1. Pokemon ability");
            System.out.println("2. Items ability");
            System.out.println("3. Not use");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                if (!currentPlayer.getTeam().isEmpty()) {
                    boolean check = false;
                    for (int i = 0; i < currentPlayer.getTeam().size(); i++) {
                        if (currentPlayer.getTeam().get(i) instanceof PreRollAbility) {
                            System.out.println(i+1 + ". " + currentPlayer.getTeam().get(i).getName());
                            check = true;
                        }
                    }

                    if (!check) {
                        System.out.println("No such ability!");
                    }
                    else {
                        int pokemonChoice = scanner.nextInt();
                        scanner.nextLine();

                        PokemonCard selected = currentPlayer.getTeam().get(pokemonChoice - 1);

                        if (selected instanceof PreRollAbility) {
                            PreRollAbility abilityPokemon = (PreRollAbility) selected;
                            abilityPokemon.usePreRollPassive(this);
                        }
                    }
                }
                else {
                    System.out.println("No pokemon in team.");
                }

            } else if (choice == 2) {
                if (!currentPlayer.getHand().getItems().isEmpty()) {
                    for (int i = 0; i < currentPlayer.getHand().getItems().size(); i++) {
                        System.out.println(i+1 + ". " + currentPlayer.getHand().getItems().get(i).getName());
                    }
                    int itemChoice = scanner.nextInt();
                    scanner.nextLine();

                    currentPlayer.getHand().getItems().get(itemChoice - 1).activate(this);
                }
                else {
                    System.out.println("No item.");
                }

            } else if (choice == 3) {
                System.out.println(currentPlayer.getName() + " not use ability.");
            }

            // roll dice
            if (!getCurrentPlayer().isDoNothing()) {
                int n = rollDice();
                currentPlayer.setLastRoll(n);
                System.out.println(currentPlayer.getName() + " rolled a " + n + "!");

                checkAdditionalConditions(currentPlayer, n);

                board.movePlayer(currentPlayer, n, this);
            }

            currentPlayer.setDoNothing(false);
            turn++;
        }
    }

    public void checkAdditionalConditions(Player currentPlayer, int n) {
        // Check if walk pass daycare
        if (currentPlayer.getPosition() < 18 && currentPlayer.getPosition() + n >= 18) {
            DaycareTile daycareTile = (DaycareTile) board.getTileAt(18);
            daycareTile.walkPass(currentPlayer, this);
        }

        // Check if walk pass start tile
        if (currentPlayer.getPosition() + n >= 40) {
            StartTile startTile = (StartTile) board.getTileAt(0);
            startTile.walkPass(currentPlayer, this);
        }
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

        // Blue Deck
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

        // Green Deck
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

        // Purple Deck
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

        // Red Deck
        Deck<PokemonCard> redPokemonDeck = new Deck<>();
        List<PokemonCard> redCards = List.of(
                new Alakazam(), new Arcanine(), new Articuno(), new Blastoise(), new Charizard(),
                new Cloyster(), new Dragonite(), new Gengar(), new Lapras(), new Machamp(),
                new Muk(), new Onix(), new Pidgeot(), new Rhydon(), new Snorlax(),
                new Venusaur(), new Zapdos(), new Moltres(), new Mew()
        );
        redCards.forEach(card -> card.setDeckColor(PokemonDeckColor.RED));
        redPokemonDeck.addCard(redCards);

        // Crown Deck
        Deck<PokemonCard> crownPokemonDeck = new Deck<>();
        List<PokemonCard> crownCards = List.of(new Mewtwo());
        crownCards.forEach(card -> card.setDeckColor(PokemonDeckColor.CROWN));
        crownPokemonDeck.addCard(crownCards);

        deckManager = new DeckManager(itemDeck, eventDeck, bluePokemonDeck, greenPokemonDeck, purplePokemonDeck, redPokemonDeck, crownPokemonDeck);
        deckManager.shuffleAll();
    }

    private void setupPlayers() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of players: ");
        int n = input.nextInt();
        input.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter player " + (i + 1) + " name: ");
            String name = input.nextLine();

            System.out.println("Choose profession: 1) Trainer 2) Fisher 3) Rocket 4) Scientist");
            int choice = input.nextInt();
            input.nextLine();
            ProfessionType type = switch (choice) {
                case 2 -> ProfessionType.FISHER;
                case 3 -> ProfessionType.ROCKET;
                case 4 -> ProfessionType.SCIENTIST;
                default -> ProfessionType.TRAINER;
            };

            players.add(new Player(name, type));
        }

    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void nextPlayer() {
        turn = (turn + 1) % players.size();
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
