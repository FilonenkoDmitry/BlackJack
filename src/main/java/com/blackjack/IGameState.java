package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface IGameState {
  int blackjackScore = 21;

  void play() throws GameException;
  @NotNull Optional<IPlayer> tryDefineAWinner();
}
