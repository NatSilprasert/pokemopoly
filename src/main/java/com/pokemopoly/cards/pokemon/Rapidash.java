package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

import java.util.List;

public class Rapidash extends PokemonCard implements PreRollAbility {
    public Rapidash() {
        super("P078",
                "Rapidash",
                "Rapidash is a Fire type Pokémon introduced in Generation 1.",
                11,
                11,
                6,
                List.of(PokemonType.FIRE));
    }

    @Override
    public void usePreRollPassive(Game game) {
        Player p = getOwner();

        System.out.println("🔥 Rapidash used Run Away! Moving forward 6 spaces!");

        // 1) เดินทันที 6 ช่อง
        game.getBoard().movePlayer(p, 6, game);
        game.getCurrentPlayer().setDoNothing(true);

        // 2) ปิดการทอยเต๋าในเทิร์นนี้ (เพราะ lastRoll != 0 = เทิร์นนี้ถือว่าเดินแล้ว)
        p.setLastRoll(1); // หรือ 6 ก็ได้ แต่ต้อง ≠ 0

        // 3) ปิดการจับโปเกมอนในเทิร์นนี้
        // หากคุณมี flag ให้ใช้ เช่น p.setSkipCatch(true);
        // ถ้ายังไม่มี ระบบคุณผูกการจับเข้ากับ "หลังทอยเต๋า" ดังนั้นไม่ต้องทำเพิ่ม

        System.out.println(p.getName() + " moved to position: " + p.getPosition());
    }
}
