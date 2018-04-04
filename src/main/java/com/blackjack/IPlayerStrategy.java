package com.blackjack;

public interface IPlayerStrategy {
  boolean hasPriorityWithBlackjack();
  boolean canSurviveWithBust();
  boolean needMore(int currentHandScore);
}
