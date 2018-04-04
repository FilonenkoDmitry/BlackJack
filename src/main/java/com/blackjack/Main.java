package com.blackjack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    try {
      Iterator<ICard> deckIterator;

      if (args.length > 0) {
        try {
          File f = new File(args[0]);
          InputStream fileStream = new FileInputStream(f);
          deckIterator = DeckReader.fromStream(fileStream);
        } catch (FileNotFoundException e) {
          System.out.println(String.format("File %s can't be found", args[0]));
          return;
        }
      } else {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream fileStream = classLoader.getResourceAsStream("StandardDeck.txt");
        deckIterator = DeckReader.shuffledFromStream(fileStream);
      }

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
          System.out.print(String.format("%s\nsam: %s\ndealer: %s",
                  winner.get().getName(),
                  getPlayerCards(players[0]),
                  getPlayerCards(players[1])));
          return;
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String getPlayerCards(final IPlayer player) {
    return String.join(", ", player.getCards().stream().map(card -> card.toString()).collect(Collectors.toList()));
  }
}