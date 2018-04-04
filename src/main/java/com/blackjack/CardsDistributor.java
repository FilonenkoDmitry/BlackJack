package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CardsDistributor implements ICardsDistributor {
  private Iterator<ICard> iterator;

  public CardsDistributor(@NotNull Iterator<ICard> iterator) {
    this.iterator = iterator;
  }

  @Override
  public IPlayer[] distributeCards() throws GameException {
    final int playersNumber = 2;
    final int initialCardsNumber = 2;

    ICard[][] cardSets = new ICard[playersNumber][initialCardsNumber];

    for (int cardsPerPlayer = 0; cardsPerPlayer < initialCardsNumber; cardsPerPlayer++)
      for (int playerNo = 0; playerNo < playersNumber; playerNo++)
        cardSets[playerNo][cardsPerPlayer] = drawACard();

    IPlayer[] players = new Player[playersNumber];
    players[0] = new Player("sam", cardSets[0][0], cardSets[0][1], new SamStrategy());
    players[1] = new Player("dealer", cardSets[1][0], cardSets[1][1], new DealerStrategy(players[0]));

    return players;
  }

  @Override
  public @NotNull ICard drawACard() throws GameException {
    if (!iterator.hasNext())
      throw new GameException("Not enough cards in the deck for game");

    return iterator.next();
  }
}
