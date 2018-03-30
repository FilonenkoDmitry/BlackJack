package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CardsDeck implements ICardsDeck {
  private Iterator<Card> iterator;

  public CardsDeck(@NotNull Iterator<Card> iterator) {
    this.iterator = iterator;
  }

  @Override
  public @NotNull Card drawACard() throws GameException {
    if (!iterator.hasNext())
      throw new GameException("Not enough cards in the deck for game");

    return iterator.next();
  }
}
