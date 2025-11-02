package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

public class Blastoise extends PokemonCard implements BattleAbility {

    public Blastoise() {
        super("P009","Blastoise",
                "Blastoise is a Water type Pokémon introduced in Generation 1.",
                15,
                17,
                8);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //Aqua Jet
        //Waiting Battle Class
    }
    //Create 10/23/68
}
