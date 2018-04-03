import com.blackjack.Card;
import com.blackjack.DeckReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

public class DeckReaderTests {
  @Test
  public void FromStream_ReadFromFile_AllCardsAreRead() throws IOException {
    //when
    Iterator<Card> deck = DeckReader.fromStream(string2Stream("CA, D4, H7, SJ,S5, S9, D10"));

    StringBuilder builder = new StringBuilder();

    while (deck.hasNext()) {
      builder.append(deck.next().toString()).append(" ");
    }

    //then
    Assert.assertEquals("CA D4 H7 SJ S5 S9 D10 ", builder.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void FromStream_InputContainsDuplications_Throws() throws IOException {
    //given
    String cardsWithDuplications = "S3, D8, C6, H10, D8, H4";
    InputStream stream = string2Stream(cardsWithDuplications);

    //when .. then
    DeckReader.fromStream(stream);
  }

  @Test
  public void ShuffledFromStream_AllCardsAreRead() throws IOException {
    //given
    String inputCards = "CA, D4, H7, SJ, S5, S9, D10";
    HashSet<String> inputCardsSet = new HashSet<>(Arrays.stream(inputCards.split(",")).map(String::trim).collect(Collectors.toSet()));
    InputStream stream = string2Stream(inputCards);

    //when
    Iterator<Card> deck = DeckReader.shuffledFromStream(stream);

    //then
    //check if every cards from result deck can be found in input...
    while (deck.hasNext()) {
      String cardFromDeck = deck.next().toString();
      Assert.assertTrue(inputCardsSet.contains(cardFromDeck));
      inputCardsSet.remove(cardFromDeck);
    }

    //check if all cards from input were read
    Assert.assertEquals(0, inputCardsSet.size());
  }

  @Test
  public void ShuffledFromStream_CardsInDeckAreOrderedDifferently() throws IOException {
    //given
    InputStream stream = string2Stream("CA, D4, H7, SJ, S5, S9, D10");

    //when
    Iterator<Card> deck = DeckReader.shuffledFromStream(stream);

    //then
    StringBuilder builder = new StringBuilder();

    while (deck.hasNext()) {
      builder.append(deck.next().toString()).append(" ");
    }

    //then
    Assert.assertNotEquals("CA D4 H7 SJ S5 S9 D10 ", builder.toString());
  }

  private static InputStream string2Stream(final String str) {
    return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
  }
}
