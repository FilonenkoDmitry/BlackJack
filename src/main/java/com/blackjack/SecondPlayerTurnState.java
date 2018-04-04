package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SecondPlayerTurnState extends BasePlayerTurnState {
  public SecondPlayerTurnState(@NotNull ICardsDistributor cardsDistributor, @NotNull IPlayer player1, @NotNull IPlayer player2) {
    super(cardsDistributor, player2, player1);
  }

  @NotNull
  @Override
  public Optional<IPlayer> tryDefineAWinner() {
    Optional<IPlayer> winner = super.tryDefineAWinner();
    return winner.isPresent() ? winner : Optional.of(getPlayerWithGreaterScore());
  }
}
