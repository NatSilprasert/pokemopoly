package com.pokemopoly;

import com.pokemopoly.board.Board;
import com.pokemopoly.board.GrassColor;
import com.pokemopoly.board.Tile;
import com.pokemopoly.board.tile.*;
import com.pokemopoly.cards.*;
import com.pokemopoly.cards.event.DoesPokeBallHaveEye;
import com.pokemopoly.cards.items.Bicycle;
import com.pokemopoly.cards.items.Revive;
import com.pokemopoly.cards.pokemon.*;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private DeckManager deckManager;
    private List<Player> players;
    private int turn = 0;

    public Game() {
        // setup board
        List<Tile> tiles = new ArrayList<>(Arrays.asList(
                new StartTile("Start Tile", 0),
                new GrassTile("Green Grass Tile", 1, GrassColor.GREEN),
                new ItemTile("Item Tile", 2),
                new GrassTile("Green Grass Tile", 3, GrassColor.GREEN),
                new GrassTile("Green Grass Tile", 4, GrassColor.GREEN),
                new CityTile("City Tile", 5),
                new GrassTile("Green Grass Tile", 6, GrassColor.GREEN),
                new GrassTile("Green Grass Tile", 7, GrassColor.GREEN),
                new EventTile("Event Tile", 8),
                new GrassTile("Green Grass Tile", 9, GrassColor.GREEN),
                new BattleTile("Gym 1", 10),
                new GrassTile("Green Grass Tile", 11, GrassColor.BLUE),
                new CaveTile("Cave Tile", 12),
                new GrassTile("Green Grass Tile", 13, GrassColor.BLUE),
                new GrassTile("Green Grass Tile", 14, GrassColor.BLUE),
                new CityTile("City Tile", 15),
                new GrassTile("Green Grass Tile", 16, GrassColor.BLUE),
                new GrassTile("Green Grass Tile", 17, GrassColor.BLUE),
                new DaycareTile("Daycare Tile", 18),
                new GrassTile("Green Grass Tile", 19, GrassColor.BLUE),
                new BattleTile("Villain", 20),
                new GrassTile("Purple Grass Tile", 21, GrassColor.PURPLE),
                new ItemTile("Item Tile", 22),
                new GrassTile("Purple Grass Tile", 23, GrassColor.PURPLE),
                new GrassTile("Purple Grass Tile", 24, GrassColor.PURPLE),
                new CityTile("City Tile", 25),
                new GrassTile("Purple Grass Tile", 26, GrassColor.PURPLE),
                new GrassTile("Purple Grass Tile", 27, GrassColor.PURPLE),
                new EventTile("Event Tile", 28),
                new GrassTile("Purple Grass Tile", 29, GrassColor.PURPLE),
                new BattleTile("Gym 2", 30),
                new GrassTile("Red Grass Tile", 31, GrassColor.RED),
                new CaveTile("Cave Tile", 32),
                new GrassTile("Red Grass Tile", 33, GrassColor.RED),
                new GrassTile("Red Grass Tile", 34, GrassColor.RED),
                new CityTile("City Tile", 35),
                new GrassTile("Red Grass Tile", 36, GrassColor.RED),
                new GrassTile("Red Grass Tile", 37, GrassColor.RED),
                new GrassTile("Red Grass Tile", 38, GrassColor.RED),
                new GrassTile("Crown Grass Tile", 39, GrassColor.CROWN)
        ));

        this.board = new Board(tiles);
        this.players = new ArrayList<>();
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

    private void checkAdditionalConditions(Player currentPlayer, int n) {
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
        // Example Deck
        // todo
        Deck<ItemCard> itemDeck = new Deck<>();
        itemDeck.addCard(List.of(new ItemCard[]{
                new Revive(),
                new Bicycle(),
        }));

        Deck<EventCard> eventDeck = new Deck<>();
        eventDeck.addCard(List.of(new EventCard[]{
                new DoesPokeBallHaveEye(),
        }));

        Deck<PokemonCard> bluePokemonDeck = new Deck<>();
        bluePokemonDeck.addCard(List.of(new PokemonCard[]{
                new Ditto(),
        }));

        Deck<PokemonCard> greenPokemonDeck = new Deck<>();
        greenPokemonDeck.addCard(List.of(new PokemonCard[]{
                new Diglett(),
        }));

        Deck<PokemonCard> purplePokemonDeck = new Deck<>();
        purplePokemonDeck.addCard(List.of(new PokemonCard[]{
                new Golem(),
        }));

        Deck<PokemonCard> redPokemonDeck = new Deck<>();
        redPokemonDeck.addCard(List.of(new PokemonCard[]{
                new Gengar(),
        }));

        Deck<PokemonCard> crownPokemonDeck = new Deck<>();
        crownPokemonDeck.addCard(List.of(new PokemonCard[]{
                new Mewtwo(),
        }));

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

    public List<Player> getPlayers() {
        return players;
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
