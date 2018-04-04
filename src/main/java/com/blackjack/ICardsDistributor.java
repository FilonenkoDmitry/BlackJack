package com.blackjack;

public interface ICardsDistributor {
  IPlayer[] distributeCards() throws GameException;
  ICard drawACard() throws GameException;
}
