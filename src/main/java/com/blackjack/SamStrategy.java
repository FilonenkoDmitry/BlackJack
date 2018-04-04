package com.blackjack;

public class SamStrategy implements IPlayerStrategy {
  @Override
  public boolean hasPriorityWithBlackjack() {
    return true;
  }

  @Override
  public boolean canSurviveWithBust() {
    return false;
  }

  @Override
  public boolean needMore(int currentHandScore) {
    return currentHandScore < 17;
  }
}
