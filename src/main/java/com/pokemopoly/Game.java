package com.pokemopoly;

import com.pokemopoly.board.Board;
import com.pokemopoly.board.GrassColor;
import com.pokemopoly.board.Tile;
import com.pokemopoly.board.tile.*;
import com.pokemopoly.cards.*;
import com.pokemopoly.cards.items.Revive;
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
                new QuestTile("Quest Tile", 2),
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
                new QuestTile("Quest Tile", 22),
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

            // draw items
            ItemCard itemCard = deckManager.drawItem();
            System.out.println(currentPlayer.getName() + " has drawn an item!");
            System.out.println("Item Card Details: ");
            System.out.println("Name: " + itemCard.getName());
            System.out.println("Description: " + itemCard.getDescription());

            Hand hand = currentPlayer.getHand();
            if (hand.isFull()) {
                System.out.println("Your hand is full!");
                System.out.println("Do you want to:");
                System.out.println("1. Replace an existing item");
                System.out.println("2. Discard the new card");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice == 1) {
                    System.out.println("Your current items:");
                    for (int i = 0; i < hand.getItems().size(); i++) {
                        ItemCard c = hand.getItems().get(i);
                        System.out.println((i + 1) + ". " + c.getName() + " - " + c.getDescription());
                    }

                    while (true) {
                        System.out.print("Select the item to replace (1-" + hand.getItems().size() + "): ");
                        int index = scanner.nextInt() - 1;

                        if (index >= 0 && index < hand.getItems().size()) {
                            ItemCard removed = hand.getItems().remove(index);
                            System.out.println("Removed: " + removed.getName());
                            hand.add(itemCard);
                            System.out.println("Added: " + itemCard.getName());
                            break;
                        } else {
                            System.out.println("Invalid selection. Please try again!");
                        }
                    }
                } else if (choice == 2) {
                    System.out.println("You discarded the new item card: " + itemCard.getName());
                    deckManager.getItemDeck().discard(itemCard);
                }

            } else {
                hand.add(itemCard);
                System.out.println(itemCard.getName() + " has been added to your hand!");
            }

            // use ability
            System.out.println("Select ability to use:");
            System.out.println("1. Player ability");
            System.out.println("2. Pokemon ability");
            System.out.println("3. Items ability");
            System.out.println("4. Not use");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                // todo
            } else if (choice == 2) {
                // Example: choose a Pokémon with a PreRollAbility
                // (You may later add a proper selection system)
                for (PokemonCard p : currentPlayer.getTeam()) {
                    if (p instanceof PreRollAbility ability) {
                        ability.usePreRollPassive(this);
                        break;
                    }
                }
            } else if (choice == 3) {
                // todo
            } else if (choice == 4) {
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
        itemDeck.addCard(new Revive());

        Deck<EventCard> eventDeck = new Deck<>();
        //eventDeck.addCard(new EventCard("E001", "You found 100 coins!", "My description"));
        //eventDeck.addCard(new EventCard("E002", "Wild Pokémon appears!", "My description"));

        Deck<QuestCard> questDeck = new Deck<>();
        questDeck.addCard(new QuestCard("Q001", "Catch 3 Pokémon", "Do it"));

        Deck<PokemonCard> bluePokemonDeck = new Deck<>();
        Deck<PokemonCard> greenPokemonDeck = new Deck<>();
        Deck<PokemonCard> purplePokemonDeck = new Deck<>();
        Deck<PokemonCard> redPokemonDeck = new Deck<>();
        Deck<PokemonCard> crownPokemonDeck = new Deck<>();

        deckManager = new DeckManager(itemDeck, eventDeck, questDeck, bluePokemonDeck, greenPokemonDeck, purplePokemonDeck, redPokemonDeck, crownPokemonDeck);
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
