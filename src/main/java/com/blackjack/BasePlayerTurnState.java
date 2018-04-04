package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public abstract class BasePlayerTurnState implements IGameState {
  private ICardsDistributor cardsDistributor;
  private IPlayer currentTurnPlayer;
  private IPlayer waitingPlayer;

  public BasePlayerTurnState(@NotNull ICardsDistributor cardsDistributor, @NotNull IPlayer currentTurnPlayer, @NotNull IPlayer waitingPlayer) {
    this.cardsDistributor = cardsDistributor;
    this.currentTurnPlayer = currentTurnPlayer;
    this.waitingPlayer = waitingPlayer;
  }

  @Override
  public void play() throws GameException {
    currentTurnPlayer.play(cardsDistributor);
  }

  @NotNull
  @Override
  public Optional<IPlayer> tryDefineAWinner() {
    if (currentTurnPlayer.getScore() > blackjackScore)
      return Optional.of(waitingPlayer);

    if (currentTurnPlayer.getScore() == blackjackScore)
      return Optional.of(currentTurnPlayer);

    return Optional.empty();
  }

  protected @NotNull IPlayer getPlayerWithGreaterScore() {
    return currentTurnPlayer.getScore() > waitingPlayer.getScore() ? currentTurnPlayer : waitingPlayer;
  }
}
