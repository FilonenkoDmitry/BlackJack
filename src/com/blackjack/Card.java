package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class Card {
  private static final HashSet<String> suites;
  private static final HashSet<String> ranks;
  static {
    suites = new HashSet<>();
    suites.add("C");
    suites.add("D");
    suites.add("H");
    suites.add("S");

    ranks = new HashSet<>();
    ranks.add("2");
    ranks.add("3");
    ranks.add("4");
    ranks.add("5");
    ranks.add("6");
    ranks.add("7");
    ranks.add("8");
    ranks.add("9");
    ranks.add("10");
    ranks.add("J");
    ranks.add("K");
    ranks.add("Q");
    ranks.add("A");
  }

  private String suite;
  private String rank;
  private int value;

  private Card(String suite, String rank) {
    this.suite = suite;
    this.rank = rank;

    switch (rank) {
      case "J": case "Q": case "K":
        value = 10;
        break;
      case "A":
        value = 11;
        break;
      default:
        value = Integer.parseInt(rank);
    }
  }

  @NotNull
  public static Card createCard(String description) {
    if (description.length() < 2 || description.length() > 3)
      throw new IllegalArgumentException("Card description falls out of normal range");

    return createCard(description.substring(0, 1), description.substring(1));
  }

  @NotNull
  public static Card createCard(String suite, String rank) {
    String upperSuite = suite.toUpperCase();
    if (!suites.contains(upperSuite))
      throw new IllegalArgumentException(String.format("%s is not a correct suite. 'C', 'D', 'H' or 'S' was expected", upperSuite));

    String upperRank = rank.toUpperCase();
    if (!ranks.contains(upperRank))
      throw new IllegalArgumentException(String.format("%s is not a correct rank. Number from '2' to '10' or 'J', 'Q', 'K', 'A' was expected", upperRank));

    return new Card(upperSuite, upperRank);
  }

  @Override
  public String toString() {
    return suite + rank;
  }

  public int getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Card card = (Card) o;

    if (!suite.equals(card.suite)) return false;
    return rank.equals(card.rank);
  }

  @Override
  public int hashCode() {
    int result = suite.hashCode();
    result = 31 * result + rank.hashCode();
    return result;
  }
}
