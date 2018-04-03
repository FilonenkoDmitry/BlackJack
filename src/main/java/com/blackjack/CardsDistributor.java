package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CardsDistributor implements ICardsDistributor {
  private Iterator<ICard> iterator;

  public CardsDistributor(@NotNull Iterator<ICard> iterator) {
    this.iterator = iterator;
  }

  @Override
  public IPlayer[] distributeCards(int playersNumber) throws GameException {
    ICard[][] cardSets = new ICard[playersNumber][2];

    for (int cardsPerPlayer = 0; cardsPerPlayer < 2; cardsPerPlayer++)
      for (int playerNo = 0; playerNo < playersNumber; playerNo++)
        cardSets[playerNo][cardsPerPlayer] = drawACard();

    IPlayer[] players = new Player[playersNumber];
    for (int playerNo = 0; playerNo < playersNumber; playerNo++) {
      players[playerNo] = new Player(cardSets[playerNo][0], cardSets[playerNo][1]);
    }

    return players;
  }

  @Override
  public @NotNull ICard drawACard() throws GameException {
    if (!iterator.hasNext())
      throw new GameException("Not enough cards in the deck for game");

    return iterator.next();
  }
}
