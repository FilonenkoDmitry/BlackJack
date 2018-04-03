package com.blackjack;

public class GameResult {
  private PlayerType winner;
  private Player dealer;
  private Player sam;

  public GameResult(PlayerType winner, Player dealer, Player sam) {
    this.winner = winner;
    this.dealer = dealer;
    this.sam = sam;
  }

  public PlayerType getWinner() {
    return winner;
  }

  public Player getDealer() {
    return dealer;
  }

  public Player getSam() {
    return sam;
  }
}
