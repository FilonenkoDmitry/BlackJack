package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements IPlayer {
  private ArrayList<ICard> cards = new ArrayList<>();
  private String name;
  private IPlayerStrategy strategy;

  public Player(final @NotNull String name, final @NotNull ICard card1, final @NotNull ICard card2, final @NotNull IPlayerStrategy strategy) {
    this.name = name;
    this.strategy = strategy;
    cards.add(card1);
    cards.add(card2);
  }

  public void play(final @NotNull ICardsDistributor cardsDistributor) throws GameException {
    while (strategy.needMore(getScore())) {
      cards.add(cardsDistributor.drawACard());
    }
  }

  @Override
  public String getName() {
    return name;
  }

  public int getScore() {
    return cards.stream().mapToInt(ICard::getValue).sum();
  }

  @Override
  public List<ICard> getCards() {
    return Collections.unmodifiableList(cards);
  }
}