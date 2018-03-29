package com.blackjack;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

public class DeckCreator {
  public static @NotNull Iterator<Card> fromStream(@NotNull InputStream inputStream) throws IOException {
    return readCardsFromStream(inputStream).iterator();
  }

  public static @NotNull Iterator<Card> shuffledFromStream(@NotNull InputStream inputStream) throws IOException {
    ArrayList<Card> cardsList = readCardsFromStream(inputStream);
    Collections.shuffle(cardsList);
    return cardsList.iterator();
  }

  @NotNull
  private static ArrayList<Card> readCardsFromStream(@NotNull InputStream inputStream) throws IOException {
    ArrayList<Card> result = new ArrayList<>();
    InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    BufferedReader reader = new BufferedReader(streamReader);
    for (String line; (line = reader.readLine()) != null;) {
      result.addAll(Arrays.stream(line.split(","))
                        .map(card -> Card.createCard(card.trim()))
                        .collect(Collectors.toList()));
    }
    return result;
  }
}
