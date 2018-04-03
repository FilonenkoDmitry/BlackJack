package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Player implements ICompetitor {
  private ArrayList<Card> cards = new ArrayList<>();

  public Player(@NotNull Card card1, @NotNull Card card2) {
    cards.add(card1);
    cards.add(card2);
  }

  public void play(@NotNull ICardsDeck deck, IPlayerStrategy strategy) throws GameException {
    while (strategy.needMore(getScore())) {
      cards.add(deck.drawACard());
    }
  }

  public int getScore() {
    return cards.stream().mapToInt(Card::getValue).sum();
  }
}