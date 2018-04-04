package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IPlayer extends ICompetitor {
    String getName();
    List<ICard> getCards();
    void play(final @NotNull ICardsDistributor cardsDistributor) throws GameException;
}
