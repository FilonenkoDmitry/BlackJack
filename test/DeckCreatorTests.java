import com.blackjack.Card;
import com.blackjack.DeckCreator;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class DeckCreatorTests {
  @Test
  public void ReadFromFile_Positive() throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream s = classLoader.getResourceAsStream("DeckFile.txt");

    Iterator<Card> deck = DeckCreator.fromStream(s);

    StringBuilder builder = new StringBuilder();

    while (deck.hasNext()) {
      builder.append(deck.next().toString()).append(" ");
    }

    Assert.assertEquals("CA D4 H7 SJ S5 S9 D10 ", builder.toString());
  }

}
