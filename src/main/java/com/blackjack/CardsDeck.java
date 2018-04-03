package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CardsDeck implements ICardsDeck {
  private Iterator<ICard> iterator;

  public CardsDeck(@NotNull Iterator<ICard> iterator) {
    this.iterator = iterator;
  }

  @Override
  public @NotNull ICard drawACard() throws GameException {
    if (!iterator.hasNext())
      throw new GameException("Not enough cards in the deck for game");

    return iterator.next();
  }
}
