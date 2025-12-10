package com.pokemopoly.player;

import com.pokemopoly.cards.ItemCard;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<ItemCard> items;
    private int capacity;

    public Hand(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public boolean add(ItemCard card) {
        if (items.size() >= capacity) {
            System.out.println("Hand is full! Cannot add more items.");
            return false;
        }
        items.add(card);
        return true;
    }

    public void setItem(int idx, ItemCard card) {
        items.set(idx, card);
    }

    public void clearItems() {
        items.clear();
    }

    public boolean remove(ItemCard card) {
        return items.remove(card);
    }

    public List<ItemCard> getItems() {
        return items;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}