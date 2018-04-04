package com.blackjack;

import org.jetbrains.annotations.NotNull;

public class DealerStrategy implements IPlayerStrategy {
  private @NotNull ICompetitor sam;

  public DealerStrategy(@NotNull ICompetitor sam) {
    this.sam = sam;
  }

  @Override
  public boolean hasPriorityWithBlackjack() {
    return false;
  }

  @Override
  public boolean canSurviveWithBust() {
    return true;
  }

  @Override
  public boolean needMore(int currentHandScore) {
    return currentHandScore <= sam.getScore();
  }
}
