package com.blackjack;

public interface IPlayerStrategy {
  boolean hasPriorityWithBlackjack();
  boolean canSurviveAfterBust();
  boolean needMore(int currentHandScore);
}
