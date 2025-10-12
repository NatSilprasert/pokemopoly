package com.pokemopoly.player;

import com.pokemopoly.board.Board;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;

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
    private int badges = 0;
    private int redBall = 4;
    private int greatBall = 0;
    private int hyperBall = 0;


    public Player(String name, ProfessionType profession) {
        setName(name);
        setProfession(profession);
        team = new ArrayList<>();
        hand = new Hand(4);

        if (profession == ProfessionType.TRAINER) {
            hand.setCapacity(6);
        }
        else if (profession == ProfessionType.FISHER) {
            // todo
        }
        else if (profession == ProfessionType.ROCKET) {
            // todo
        }
        else if (profession == ProfessionType.SCIENTIST) {
            // todo
        }
    }

    public void move(int n) {
        setPosition(getPosition() + n);
    }

    public void addPokemon(PokemonCard pokemon) {
        if (team.size() != MAX_POKEMON) team.add(pokemon);
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

    public int getBadges() {
        return badges;
    }

    public void setBadges(int badges) {
        this.badges = badges;
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
}
