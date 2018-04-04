package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CardsDistributedState implements IGameState {
  private IPlayer player1;
  private IPlayer player2;

  public CardsDistributedState(@NotNull IPlayer player1, @NotNull IPlayer player2) {

    this.player1 = player1;
    this.player2 = player2;
  }

  @Override
  public void play() {

  }

  @Override
  public @NotNull Optional<IPlayer> tryDefineAWinner() {
    int player1Score = player1.getScore();
    int player2Score = player2.getScore();

    if (player1Score > blackjackScore) {
      if (player2Score > blackjackScore)
        return Optional.of(player1.canSurviveAfterBust() ? player1 : player2);
      else
        return Optional.of(player2);
    }

    if (player2Score > blackjackScore)
      return Optional.of(player1);

    if (player1Score == blackjackScore) {
      if (player2Score == blackjackScore)
        return Optional.of(player1.hasPriorityWithBlackjack() ? player1 : player2);
      else
        return Optional.of(player1);
    }

    if (player2Score == blackjackScore)
      return Optional.of(player2);

    return Optional.empty();
  }
}
