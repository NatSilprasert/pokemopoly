package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;
import java.util.Scanner;

public class RareCandy extends ItemCard {
    public RareCandy() {
        super("rarecandy",
                "Rare Candy",
                "Choose 1 Pokémon to evolve!");
    }
    @Override
    public void activate(Game game) {
        List<PokemonCard> evolvablePokemons = game.getCurrentPlayer().getTeam().stream()
                .filter(p -> p instanceof Evolvable)
                .toList();

                        if (!evolvablePokemons.isEmpty()) {
            System.out.println("Choose pokemon to evolve!");
            for (int i = 0; i < evolvablePokemons.size(); i++) {
                System.out.println((i + 1) + ". " + evolvablePokemons.get(i).getName());
            }
        }

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

                        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= evolvablePokemons.size()) {
                    break;
                } else {
                    System.out.println("❌ Invalid number. Please select between 1 and " + evolvablePokemons.size() + "!");
                }
            } else {
                System.out.println("❌ Please enter a valid number!");
                scanner.next();
            }
        }

        PokemonCard selected = evolvablePokemons.get(choice - 1);
                        System.out.println("You chose " + selected.getName() + " to evolve!");

        Evolvable evolvable = (Evolvable) selected;
        PokemonCard evolvedPokemon = evolvable.evolve();

        int index = game.getCurrentPlayer().getTeam().indexOf(selected);
        if (index != -1) {
            game.getCurrentPlayer().getTeam().set(index, evolvedPokemon);
        }
        System.out.println("It has evolved to " + selected.getName() + "!");
    }
}
