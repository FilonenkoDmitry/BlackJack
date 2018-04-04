package com.blackjack;

import org.jetbrains.annotations.NotNull;

public class FirstPlayerTurnState extends BasePlayerTurnState {
  public FirstPlayerTurnState(@NotNull ICardsDistributor cardsDistributor, @NotNull IPlayer player1, @NotNull IPlayer player2) {
    super(cardsDistributor, player1, player2);
  }
}
