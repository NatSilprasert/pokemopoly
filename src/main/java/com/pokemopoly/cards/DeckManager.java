package com.pokemopoly.cards;


public class DeckManager {
    private Deck<ItemCard> itemDeck;
    private Deck<EventCard> eventDeck;
    private Deck<PokemonCard> bluePokemonDeck;
    private Deck<PokemonCard> greenPokemonDeck;
    private Deck<PokemonCard> purplePokemonDeck;
    private Deck<PokemonCard> redPokemonDeck;
    private Deck<PokemonCard> crownPokemonDeck;

    public DeckManager(
            Deck<ItemCard> itemDeck,
            Deck<EventCard> eventDeck,
            Deck<PokemonCard> bluePokemonDeck,
            Deck<PokemonCard> greenPokemonDeck,
            Deck<PokemonCard> purplePokemonDeck,
            Deck<PokemonCard> redPokemonDeck,
            Deck<PokemonCard> crownPokemonDeck
    ) {
        this.itemDeck = itemDeck;
        this.eventDeck = eventDeck;
        this.bluePokemonDeck = bluePokemonDeck;
        this.greenPokemonDeck = greenPokemonDeck;
        this.purplePokemonDeck = purplePokemonDeck;
        this.redPokemonDeck = redPokemonDeck;
        this.crownPokemonDeck = crownPokemonDeck;
    }

    public ItemCard drawItem() {
        return itemDeck.draw();
    }

    public EventCard drawEvent() {
        return eventDeck.draw();
    }

    public PokemonCard drawBluePokemon() {
        return bluePokemonDeck.draw();
    }

    public PokemonCard drawGreenPokemon() {
        return greenPokemonDeck.draw();
    }

    public PokemonCard drawPurplePokemon() {
        return purplePokemonDeck.draw();
    }

    public PokemonCard drawRedPokemon() {
        return redPokemonDeck.draw();
    }

    public PokemonCard drawCrownPokemon() {
        return crownPokemonDeck.draw();
    }

    public Deck<ItemCard> getItemDeck() {
        return itemDeck;
    }

    public Deck<EventCard> getEventDeck() {
        return eventDeck;
    }

    public Deck<PokemonCard> getBluePokemonDeck() {
        return bluePokemonDeck;
    }

    public Deck<PokemonCard> getGreenPokemonDeck() {
        return greenPokemonDeck;
    }

    public Deck<PokemonCard> getPurplePokemonDeck() {
        return purplePokemonDeck;
    }

    public Deck<PokemonCard> getRedPokemonDeck() {
        return redPokemonDeck;
    }

    public Deck<PokemonCard> getCrownPokemonDeck() {
        return crownPokemonDeck;
    }

    public void shuffleAll() {
        itemDeck.shuffle();
        eventDeck.shuffle();
        bluePokemonDeck.shuffle();
        greenPokemonDeck.shuffle();
        purplePokemonDeck.shuffle();
        redPokemonDeck.shuffle();
        crownPokemonDeck.shuffle();
    }

    public PokemonDeckColor getDeckColorOf(PokemonCard card) {
        if (bluePokemonDeck.contains(card)) return PokemonDeckColor.BLUE;
        if (greenPokemonDeck.contains(card)) return PokemonDeckColor.GREEN;
        if (purplePokemonDeck.contains(card)) return PokemonDeckColor.PURPLE;
        if (redPokemonDeck.contains(card)) return PokemonDeckColor.RED;
        if (crownPokemonDeck.contains(card)) return PokemonDeckColor.CROWN;
        return null; // ไม่เจอ
    }
}
