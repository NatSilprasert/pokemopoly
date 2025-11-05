package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;

public class Kadabra extends PokemonCard implements Evolvable , PreRollAbility {
    public Kadabra() {
        super("P063",
                "Kadabra",
                "Kadabra is a Psychic type Pokémon introduced in Generation 1.",
                8,
                12,
                6);
    }

    @Override
    public PokemonCard evolve() {
        return new Alakazam();
    }

    @Override
    public void usePreRollPassive(Game game) {
        //Psychic
    }
}
