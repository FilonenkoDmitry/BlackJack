package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    try {
      Iterator<ICard> deckIterator = setUpCardsSource(args);
      ICardsDistributor distributor = new CardsDistributor(deckIterator);
      IPlayer[] players = distributor.distributeCards();
      IGameState[] gameStates = new IGameState[] {
              new CardsDistributedState(players[0], players[1]),
              new FirstPlayerTurnState(distributor, players[0], players[1]),
              new SecondPlayerTurnState(distributor, players[0], players[1])};

      for (IGameState state : gameStates) {
        state.play();
        Optional<IPlayer> winner = state.tryDefineAWinner();
        if (winner.isPresent()) {
          System.out.print(String.format("%s\n%s\n%s",
                  winner.get().getName(),
                  getPlayerCards(players[0]),
                  getPlayerCards(players[1])));
          return;
        }
      }
    }
    catch (Exception e) {
      System.out.print("Error: " + e.getMessage());
    }
  }

  @NotNull
  private static Iterator<ICard> setUpCardsSource(String[] args) throws IOException {
    if (args.length > 0) {
        File f = new File(args[0]);
        InputStream fileStream = new FileInputStream(f);
        return DeckReader.fromStream(fileStream);
    } else {
      ClassLoader classLoader = Main.class.getClassLoader();
      InputStream fileStream = classLoader.getResourceAsStream("StandardDeck.txt");
      return DeckReader.shuffledFromStream(fileStream);
    }
  }

  private static String getPlayerCards(final IPlayer player) {
    return new StringBuilder(player.getName())
                .append(": ")
                .append(String.join(", ", player.getCards().stream().map(card -> card.toString()).collect(Collectors.toList())))
                .toString();
  }
}