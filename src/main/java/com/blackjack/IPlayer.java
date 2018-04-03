package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IPlayer extends ICompetitor {
    List<ICard> getCards();
    void play(final @NotNull ICardsDistributor cardsDistributor, final @NotNull IPlayerStrategy playerStrategy) throws GameException;
}
