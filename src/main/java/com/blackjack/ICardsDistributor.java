package com.blackjack;

public interface ICardsDistributor {
  IPlayer[] distributeCards(int playersNumber) throws GameException;
  ICard drawACard() throws GameException;
}
