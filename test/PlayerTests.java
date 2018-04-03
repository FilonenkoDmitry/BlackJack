import com.blackjack.GameException;
import com.blackjack.ICard;
import com.blackjack.ICardsDeck;
import com.blackjack.IPlayerStrategy;
import com.blackjack.Player;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTests {
  @Test
  public void Initialization_HasTwoCards() {
    //given
    final ICard card1 = mock(ICard.class);
    final ICard card2 = mock(ICard.class);

    //when
    Player player = new Player(card1, card2);

    //then
    Assert.assertArrayEquals(new ICard[] {card1, card2}, player.getCards().toArray());
  }

  @Test
  public void Initialization_HasScoreByInitialCards() {
    //given
    final ICard card1 = mock(ICard.class);
    when(card1.getValue()).thenReturn(8);

    final ICard card2 = mock(ICard.class);
    when(card2.getValue()).thenReturn(5);

    //when
    Player player = new Player(card1, card2);

    //then
    Assert.assertEquals(8 + 5, player.getScore());
  }

  @Test
  public void Play_DrawsCardsFromDeckAccordingToStrategy() throws GameException {
    //given
    final ICard card1 = mock(ICard.class);
    when(card1.getValue()).thenReturn(1);

    final ICard card2 = mock(ICard.class);
    when(card2.getValue()).thenReturn(2);

    final ICard card3 = mock(ICard.class);
    when(card3.getValue()).thenReturn(3);

    final ICard card4 = mock(ICard.class);
    when(card4.getValue()).thenReturn(4);

    Player player = new Player(card1, card2);

    final IPlayerStrategy strategy = mock(IPlayerStrategy.class);
    when(strategy.needMore(anyInt())).thenReturn(true)
                                     .thenReturn(true)
                                     .thenReturn(false);

    final ICardsDeck deck = mock(ICardsDeck.class);
    when(deck.drawACard()).thenReturn(card3)
                          .thenReturn(card4);

    //when
    player.play(deck, strategy);

    //then
    Assert.assertArrayEquals(new ICard[] {card1, card2, card3, card4}, player.getCards().toArray());
    Assert.assertEquals(1 + 2 + 3 + 4, player.getScore());
  }

  @Test
  public void Play_DoesntDrawMoreThanStrategyAllows() throws GameException {
    //given
    final ICard card1 = mock(ICard.class);
    when(card1.getValue()).thenReturn(1);

    final ICard card2 = mock(ICard.class);
    when(card2.getValue()).thenReturn(2);

    final ICard card3 = mock(ICard.class);
    when(card3.getValue()).thenReturn(3);

    final ICard card4 = mock(ICard.class);
    when(card4.getValue()).thenReturn(4);

    final ICard joker = mock(ICard.class);
    when(joker.getValue()).thenReturn(10);

    Player player = new Player(card1, card2);

    final IPlayerStrategy strategy = mock(IPlayerStrategy.class);
    when(strategy.needMore(anyInt())).thenReturn(true)
                                     .thenReturn(true)
                                     .thenReturn(false);

    final ICardsDeck deck = mock(ICardsDeck.class);
    when(deck.drawACard()).thenReturn(card3)
                          .thenReturn(card4)
                          .thenReturn(joker);

    //when
    player.play(deck, strategy);

    //then
    Assert.assertFalse(player.getCards().contains(joker));
  }
}
