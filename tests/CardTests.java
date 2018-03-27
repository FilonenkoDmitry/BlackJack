import com.blackjack.Card;
import org.junit.Test;

import static com.blackjack.Card.createCard;
import static org.junit.Assert.assertEquals;

public class CardTests {
  @Test
  public void createACard_Positive() {
    Card card1 = createCard("S2");
    assertEquals("S2", card1.toString());
    assertEquals(2, card1.getValue());

    Card card2 = createCard("da");
    assertEquals("DA", card2.toString());
    assertEquals(11, card2.getValue());

    Card card3 = createCard("h10");
    assertEquals("H10", card3.toString());
    assertEquals(10, card3.getValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryCreateWithASingleChar_Exception() {
    createCard("A");
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryCreateWithTooManyChar_Exception() {
    createCard("AB12");
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryCreateWithWrongSuite_Exception() {
    createCard("Q9");
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryCreateWithWrongRank_Exception() {
    createCard("H12");
  }
}
