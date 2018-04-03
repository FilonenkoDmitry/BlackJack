package com.blackjack;

public class GameResult {
  private PlayerType winner;
  private IPlayer dealer;
  private IPlayer sam;

  public GameResult(PlayerType winner, IPlayer dealer, IPlayer sam) {
    this.winner = winner;
    this.dealer = dealer;
    this.sam = sam;
  }

  public PlayerType getWinner() {
    return winner;
  }

  public IPlayer getDealer() {
    return dealer;
  }

  public IPlayer getSam() {
    return sam;
  }
}
