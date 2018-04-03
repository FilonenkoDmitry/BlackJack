package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Game {
  private final int blackJackScore = 21;

  private @NotNull ICardsDeck deck;

  public Game(@NotNull ICardsDeck deck) {
    this.deck = deck;
  }

  public GameResult play() throws Exception {
    ICard card1 = deck.drawACard();
    ICard card2 = deck.drawACard();
    ICard card3 = deck.drawACard();
    ICard card4 = deck.drawACard();
    Player sam = new Player(card1, card3);
    Player dealer = new Player(card2, card4);

    Optional<PlayerType> winner = findWinnerAfterFirstDraw(dealer, sam);
    if (winner.isPresent())
      return new GameResult(winner.get(), dealer, sam);

    sam.play(deck, new SamStrategy());

    if (sam.getScore() > blackJackScore)
      return new GameResult(PlayerType.DEALER, dealer, sam);
    if (sam.getScore() == blackJackScore)
      return new GameResult(PlayerType.SAM, dealer, sam);

    dealer.play(deck, new DealerStrategy(sam));

    if (dealer.getScore() > blackJackScore)
      return new GameResult(PlayerType.SAM, dealer, sam);

    return new GameResult(dealer.getScore() > sam.getScore() ? PlayerType.DEALER : PlayerType.SAM, dealer, sam);
  }

  private Optional<PlayerType> findWinnerAfterFirstDraw(Player dealer, Player sam) {
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