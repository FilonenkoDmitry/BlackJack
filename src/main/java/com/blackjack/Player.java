package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {
  private ArrayList<ICard> cards = new ArrayList<>();

  public Player(@NotNull ICard card1, @NotNull ICard card2) {
    cards.add(card1);
    cards.add(card2);
  }

  public void play(@NotNull ICardsDeck deck, IPlayerStrategy strategy) throws GameException {
    while (strategy.needMore(getScore())) {
      cards.add(deck.drawACard());
    }
  }

  public int getScore() {
    return cards.stream().mapToInt(ICard::getValue).sum();
  }

  @Override
  public List<ICard> getCards() {
    return cards;
  }
}