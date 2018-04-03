package com.blackjack;

public interface ICardsDeck {
  ICard drawACard() throws GameException;
}
