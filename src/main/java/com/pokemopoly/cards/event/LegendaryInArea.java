package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.Mew;
import com.pokemopoly.cards.pokemon.Mewtwo;
import com.pokemopoly.player.Player;

import java.util.*;

public class LegendaryInArea extends EventCard {
    public LegendaryInArea() {
        super("event_legendary", "Legendary in Area",
                "ทุกคนทอยเต๋า 2 ลูก ผู้ที่ได้แต้มรวมสูงสุด ได้สิทธิจับโปเกมอนในตำนาน");
    }

    @Override
    public void activate(Game game) {
        System.out.println("✨ A Legendary Pokémon appeared in this area!");
        System.out.println("👑 Everyone rolls 2 dice. Highest roll will get a chance to catch MEWTWO!");

        List<Player> players = game.getPlayers();
        Scanner scanner = new Scanner(System.in);

        List<Player> contenders = new ArrayList<>(players);

        while (true) {

            int highest = -1;
            List<Player> winners = new ArrayList<>();

            System.out.println("\n🎲 Rolling dice for all current contenders...");

            for (Player p : contenders) {
                int roll1 = game.rollDice();
                int roll2 = game.rollDice();
                int sum = roll1 + roll2;

                System.out.println(" - " + p.getName() + " rolled " + roll1 + " + " + roll2 + " = " + sum);

                if (sum > highest) {
                    highest = sum;
                    winners.clear();
                    winners.add(p);
                } else if (sum == highest) {
                    winners.add(p);
                }
            }

            // ถ้ามีผู้ชนะคนเดียว ให้จบ
            if (winners.size() == 1) {
                Player winner = winners.get(0);
                System.out.println("\n🏆 " + winner.getName() + " wins the roll and may attempt to capture MEWTWO!");

                // ให้ Mewtwo แบบง่าย ๆ (คุณแก้เป็นระบบจับจริงได้)
                PokemonCard mewtwo = new Mewtwo();
                winner.addPokemon(mewtwo);

                System.out.println("🎉 " + winner.getName() + " has obtained the Legendary Pokémon: MEWTWO!");
                break;
            }

            // ถ้าเสมอ → ต้องทอยใหม่เฉพาะคนที่เสมอ
            System.out.println("\n⚔️ Tie detected among: ");
            for (Player p : winners) {
                System.out.println(" - " + p.getName());
            }
            System.out.println("🎲 Rolling again only for tied players...");

            contenders = winners;
        }
    }
}
