package com.pokemopoly.player;

import com.pokemopoly.board.Board;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.Ditto;
import com.pokemopoly.cards.pokemon.Pikachu;
import com.pokemopoly.cards.pokemon.Rattata;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private ProfessionType profession;
    private final Hand hand;
    private List<PokemonCard> team;

    private final int MAX_POKEMON = 6;
    private int position = 0;
    private int coin = 0;
    private int redBall = 4;
    private int greatBall = 0;
    private int hyperBall = 0;
    private boolean badge1 = false;
    private boolean badge2 = false;
    private boolean skipTurn = false;
    private int lastRoll = 0;

    public Player(String name, ProfessionType profession) {
        setName(name);
        setProfession(profession);
        team = new ArrayList<>();
        hand = new Hand(4);

        if (profession == ProfessionType.TRAINER) {
            hand.setCapacity(6);
            addPokemon(new Pikachu());
            // Damage += 2
        }
        else if (profession == ProfessionType.FISHER) {
            addPokemon(new Magikarp());
            // HP water += 3
            // Damage water += 3
        }
        else if (profession == ProfessionType.ROCKET) {
            addPokemon(new Rattata());
            // Steal other player's pokemon if win the battle
            // Steal other player's pokemon if walk land on daycare center
        }
        else if (profession == ProfessionType.SCIENTIST) {
            addPokemon(new Ditto());
            // Damage += 1
            // HP += 1
        }
    }

    public boolean isTeamFull() {
        return team.size() == MAX_POKEMON;
    }

    public void addPokemon(PokemonCard pokemon) {
        if (!isTeamFull()) {
            if (profession == ProfessionType.TRAINER) {
                pokemon.setPower(pokemon.getPower() + 2);
            }
            if (profession == ProfessionType.SCIENTIST) {
                pokemon.setPower(pokemon.getPower() + 1);
                pokemon.setHp(pokemon.getHp() + 1);
            }
            if (profession == ProfessionType.FISHER && pokemon.getTypes().contains(PokemonType.WATER)) {
                pokemon.setPower(pokemon.getPower() + 3);
                pokemon.setHp(pokemon.getHp() + 3);
            }

            team.add(pokemon);
        }
        else System.out.println("You have reached the maximum amount of pokemons");
    }

    public void removePokemon(PokemonCard pokemon) {
        team.remove(pokemon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfessionType getProfession() {
        return profession;
    }

    public void setProfession(ProfessionType profession) {
        this.profession = profession;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position % 40;
    }


    public void setTeam(List<PokemonCard> team) {
        this.team = team;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public List<PokemonCard> getTeam() {
        return team;
    }

    public Hand getHand() {
        return hand;
    }

    public int getMaxPokemon() {
        return MAX_POKEMON;
    }

    public boolean getBadges1() {
        return badge1;
    }

    public void setBadges1() {
        this.badge1 = true;
    }

    public boolean getBadges2() {
        return badge1;
    }

    public void setBadges2() {
        this.badge2 = true;
    }

    public int getRedBall() {
        return redBall;
    }
    public void setRedBall(int redBall) {
        this.redBall = redBall;
    }

    public int getGreatBall() {
        return greatBall;
    }

    public void setGreatBall(int greatBall) {
        this.greatBall = greatBall;
    }

    public int getHyperBall() {
        return hyperBall;
    }

    public void setHyperBall(int hyperBall) {
        this.hyperBall = hyperBall;
    }


    public boolean isSkipTurn() {
        return skipTurn;
    }

    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    } //Edit 10/23/68

    public int getLastRoll() {
        return lastRoll;
    }

    public void setLastRoll(int lastRoll) {
        this.lastRoll = lastRoll;
    }
    // Edited 11/2/68
}
