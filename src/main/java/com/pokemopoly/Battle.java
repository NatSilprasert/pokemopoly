
package com.pokemopoly;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.TypeEffectiveness;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.player.Player;

public class Battle {

    private final Game game;
    private final Player player;
    private final Player opponent;

    public Battle(Game game, Player p1, Player p2, boolean bot) {
        this.game = game;
        this.player = p1;
        this.opponent = p2;
    }


    public boolean attack(Player attacker, Player defender, PokemonCard atkPoke, PokemonCard defPoke) {

        int damage = atkPoke.getPower();

        for (PokemonType atkType : atkPoke.getTypes()) {
            for (PokemonType defType : defPoke.getTypes()) {
                if (TypeEffectiveness.getSuperEffective(atkType).contains(defType)) damage += 2;
                if (TypeEffectiveness.getNotEffective(atkType).contains(defType)) damage -= 2;
            }
        }

        defPoke.setHp(defPoke.getHp() - damage);

        return defPoke.getHp() <= 0;
    }
}