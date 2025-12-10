package com.pokemopoly.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T> {
    private List<T> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public void addCard(List<T> cardsList) {
        cards.addAll(cardsList);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public T draw() {
        if (cards.isEmpty()) {
            System.out.println("The deck is empty!");
            return null;
        }
        return cards.removeFirst();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public boolean contains(T card) {
        return cards.contains(card);
    }
}
