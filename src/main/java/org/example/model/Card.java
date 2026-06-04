package org.example.model;


import java.util.Objects;

public class Card implements Comparable<Card>{
    protected Value value;

    protected String color;

    public Card(Value value, String color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public String toString() {
        return value + " de " + color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public int compareTo(Card o) {
        return this.value.compareTo(o.value);
    }


    public boolean rivals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && Objects.equals(color, card.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, color);
    }
}