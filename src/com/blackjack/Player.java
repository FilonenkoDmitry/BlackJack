package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {
  private ArrayList<Card> cards = new ArrayList<>();

  public Player(@NotNull Card card1, @NotNull Card card2) {
    cards.add(card1);
    cards.add(card2);
  }

  public void play(@NotNull Iterator<Card> deck, int minScore) {
    while (deck.hasNext() && getScore() < minScore)
      cards.add(deck.next());
  }

  public int getScore() {
    return cards.stream().mapToInt(Card::getValue).sum();
  }
}
