package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Player {
  private ArrayList<Card> cards = new ArrayList<>();

  public Player(@NotNull Card card1, @NotNull Card card2) {
    cards.add(card1);
    cards.add(card2);
  }

  public void play(@NotNull ICardsDeck deck, int minScore) throws GameException {
    while (getScore() < minScore) {
      cards.add(deck.drawACard());
    }
  }

  public int getScore() {
    return cards.stream().mapToInt(Card::getValue).sum();
  }
}
