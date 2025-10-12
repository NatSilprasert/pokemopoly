package com.pokemopoly.cards;

public abstract class PokemonCard implements Card {
    private final String id;
    private final String name;
    private final String description;
    private final int maxHp;
    private final int price;
    private int hp;
    private int power;
    private boolean isAlive = true;

    public PokemonCard(String id, String name, String description, int hp, int power, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.maxHp = hp;
        this.power = power;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getPower() {
        return power;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
