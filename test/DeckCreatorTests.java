import com.blackjack.Card;
import com.blackjack.DeckCreator;
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

public class DeckCreatorTests {
  @Test
  public void FromStream_ReadFromFile_AllCardsAreRead() throws IOException {
    //given
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream textStream = classLoader.getResourceAsStream("testResources/DeckFile.txt");

    //when
    Iterator<Card> deck = DeckCreator.fromStream(textStream);

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
    InputStream stream = new ByteArrayInputStream(cardsWithDuplications.getBytes(StandardCharsets.UTF_8));

    //when .. then
    DeckCreator.fromStream(stream);
  }

  @Test
  public void ShuffledFromStream_AllCardsAreRead() throws IOException {
    //given
    String inputCards = "CA, D4, H7, SJ, S5, S9, D10";
    HashSet<String> inputCardsSet = new HashSet<>(Arrays.stream(inputCards.split(",")).map(String::trim).collect(Collectors.toSet()));
    InputStream stream = new ByteArrayInputStream(inputCards.getBytes(StandardCharsets.UTF_8));

    //when
    Iterator<Card> deck = DeckCreator.shuffledFromStream(stream);

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
    String inputCards = "CA, D4, H7, SJ, S5, S9, D10";
    InputStream stream = new ByteArrayInputStream(inputCards.getBytes(StandardCharsets.UTF_8));

    //when
    Iterator<Card> deck = DeckCreator.shuffledFromStream(stream);

    //then
    StringBuilder builder = new StringBuilder();

    while (deck.hasNext()) {
      builder.append(deck.next().toString()).append(" ");
    }

    //then
    Assert.assertNotEquals("CA D4 H7 SJ S5 S9 D10 ", builder.toString());
  }
}
