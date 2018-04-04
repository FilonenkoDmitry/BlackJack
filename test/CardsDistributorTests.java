import com.blackjack.CardsDistributor;
import com.blackjack.GameException;
import com.blackjack.ICard;
import com.blackjack.IPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class CardsDistributorTests {
  @Test
  public void DistributeCards_PlayersGetCardsAlternately() throws GameException {
    //given
    ICard[] cards = new ICard[4];
    for (int i = 0; i < 4; i++)
      cards[i] = mock(ICard.class);

    Iterator<ICard> cardsIterator = (Iterator<ICard>)mock(Iterator.class);
    when(cardsIterator.hasNext()).thenReturn(true);
    when(cardsIterator.next()).thenReturn(cards[0])
                              .thenReturn(cards[1])
                              .thenReturn(cards[2])
                              .thenReturn(cards[3]);
    CardsDistributor distributor = new CardsDistributor(cardsIterator);

    //when
    IPlayer[] players = distributor.distributeCards();

    //then
    Assert.assertArrayEquals(new ICard[] {cards[0], cards[2]}, players[0].getCards().toArray());
    Assert.assertArrayEquals(new ICard[] {cards[1], cards[3]}, players[1].getCards().toArray());
  }

  @Test
  public void DrawACard_ReturnsNextCardEachTime() throws GameException {
    //given
    ICard[] cards = new ICard[3];
    for (int i = 0; i < 3; i++) {
      cards[i] = mock(ICard.class);
      when(cards[i].toString()).thenReturn(Integer.toString(i));
    }

    Iterator<ICard> cardsIterator = (Iterator<ICard>)mock(Iterator.class);
    when(cardsIterator.hasNext()).thenReturn(true);
    when(cardsIterator.next()).thenReturn(cards[0])
            .thenReturn(cards[1])
            .thenReturn(cards[2]);
    CardsDistributor distributor = new CardsDistributor(cardsIterator);

    for (int i = 0; i < 3; i++) {
      //when
      ICard card = distributor.drawACard();
      //then
      Assert.assertEquals(Integer.toString(i), card.toString());
    }
  }

  @Test(expected = GameException.class)
  public void DrawACard_NoMoreCards_Throws() throws GameException {
    //given
    Iterator<ICard> cardsIterator = (Iterator<ICard>)mock(Iterator.class);
    when(cardsIterator.hasNext()).thenReturn(true)
                                 .thenReturn(false);
    when(cardsIterator.next()).thenReturn(mock(ICard.class));
    CardsDistributor distributor = new CardsDistributor(cardsIterator);

    //when
    distributor.drawACard();
    distributor.drawACard();
  }
}
