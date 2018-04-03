package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Game {
  private final int blackJackScore = 21;

  private @NotNull
  ICardsDistributor cardsDistributor;

  public Game(@NotNull ICardsDistributor cardsDistributor) {
    this.cardsDistributor = cardsDistributor;
  }

  public GameResult play() throws Exception {
    IPlayer[] players = cardsDistributor.distributeCards(2);
    IPlayer sam = players[0];
    IPlayer dealer = players[1];

    Optional<PlayerType> winner = findWinnerAfterFirstDraw(dealer, sam);
    if (winner.isPresent())
      return new GameResult(winner.get(), dealer, sam);

    sam.play(cardsDistributor, new SamStrategy());

    if (sam.getScore() > blackJackScore)
      return new GameResult(PlayerType.DEALER, dealer, sam);
    if (sam.getScore() == blackJackScore)
      return new GameResult(PlayerType.SAM, dealer, sam);

    dealer.play(cardsDistributor, new DealerStrategy(sam));

    if (dealer.getScore() > blackJackScore)
      return new GameResult(PlayerType.SAM, dealer, sam);

    return new GameResult(dealer.getScore() > sam.getScore() ? PlayerType.DEALER : PlayerType.SAM, dealer, sam);
  }

  private Optional<PlayerType> findWinnerAfterFirstDraw(IPlayer dealer, IPlayer sam) {
    if (sam.getScore() == blackJackScore)
      return Optional.of(PlayerType.SAM);
    if (dealer.getScore() == blackJackScore)
      return Optional.of(PlayerType.DEALER);
    if (dealer.getScore() > blackJackScore)
      return Optional.of(sam.getScore() > blackJackScore ? PlayerType.DEALER : PlayerType.SAM);
    if (sam.getScore() > blackJackScore)
      return Optional.of(PlayerType.DEALER);

    return Optional.empty();
  }
}