package com.pokemopoly.cards;

public enum PokemonDeckColor {
    BLUE("#3DA9FC"),
    GREEN("#16C172"),
    PURPLE("#A66DD4"),
    RED("#FF595E"),
    CROWN("#F4CA16");

    private final String colorHex;

    PokemonDeckColor(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getColorHex() {
        return colorHex;
    }
}