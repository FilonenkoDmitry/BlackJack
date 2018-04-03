package com.blackjack;

public class SamStrategy implements IPlayerStrategy {
  @Override
  public boolean needMore(int currentHandScore) {
    return currentHandScore < 17;
  }
}
