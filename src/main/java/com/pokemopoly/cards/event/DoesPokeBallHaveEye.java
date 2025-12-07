package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.List;

public class DoesPokeBallHaveEye extends EventCard {
    public DoesPokeBallHaveEye() {
        super("event_does_pokeball_have_eye",
                "Does a Poké Ball have an eye?",
                "Roll dice equal to your team size. "
                        + "If any roll is odd, a random Pokémon in your team faints.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        List<PokemonCard> team = player.getTeam();

        System.out.println("🎭 Event: Does a Poké Ball have an eye?");
        System.out.println(player.getName() + " must roll " + team.size() + " dice!");

        // No Pokémon = no effect
        if (team.isEmpty()) {
            System.out.println("You have no Pokémon. Nothing happens.");
            return;
        }

        for (int i = 0; i < team.size(); i++) {
            int roll = game.rollDice();
            System.out.println("🎲 Roll #" + (i + 1) + ": " + roll);

            // If odd → faint a random Pokémon
            if (roll % 2 == 1) {
                System.out.println("⚠️ Odd number detected! A Pokémon will faint!");

                // Random Pokémon
                int index = (int) (Math.random() * team.size());
                PokemonCard faintTarget = team.get(index);

                faintTarget.setHp(0);
                System.out.println("💀 " + faintTarget.getName() + " has fainted!");

                System.out.println("Event ends immediately.");
                return;
            }
        }

        System.out.println("✨ All rolls were even. No Pokémon fainted.");
    }
}
