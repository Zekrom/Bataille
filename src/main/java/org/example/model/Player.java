package org.example.model;


import java.util.ArrayList;

public class Player {

    private int id;

    private ArrayList<Card> deck;

    public Player(int id) {
        this.id = id;
        this.deck = new ArrayList<Card>();
    }

    public Card draw(){
        return deck.removeFirst();
    }

    public void init(Card card) {
        deck.add(card);
    }

    public int getId() {
        return id;
    }

    public void battleWon(ArrayList<Card> board) {
        deck.addAll(board);
    }

    public boolean gameOver(){
        return deck.isEmpty();
    }

    public ArrayList<Card> getDeck() {
        return (ArrayList<Card>) deck.clone();
    }
}