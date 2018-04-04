import com.blackjack.GameException;
import com.blackjack.ICard;
import com.blackjack.ICardsDistributor;
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
    Player player = new Player("Test", card1, card2, mock(IPlayerStrategy.class));

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
    Player player = new Player("Test", card1, card2, mock(IPlayerStrategy.class));

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

    final IPlayerStrategy stubStrategy = mock(IPlayerStrategy.class);

    when(stubStrategy.needMore(anyInt())).thenReturn(true)
                                         .thenReturn(true)
                                         .thenReturn(false);

    Player player = new Player("Danny Ocean", card1, card2, stubStrategy);


    final ICardsDistributor stubCardsDistributor = mock(ICardsDistributor.class);
    when(stubCardsDistributor.drawACard()).thenReturn(card3)
                          .thenReturn(card4);

    //when
    player.play(stubCardsDistributor);

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

    final IPlayerStrategy stubStrategy = mock(IPlayerStrategy.class);
    when(stubStrategy.needMore(anyInt())).thenReturn(true)
            .thenReturn(true)
            .thenReturn(false);

    Player player = new Player("Rusty Ryan", card1, card2, stubStrategy);

    final ICardsDistributor stubCardsDistributor = mock(ICardsDistributor.class);
    when(stubCardsDistributor.drawACard()).thenReturn(card3)
                          .thenReturn(card4)
                          .thenReturn(joker);

    //when
    player.play(stubCardsDistributor);

    //then
    Assert.assertFalse(player.getCards().contains(joker));
  }
}
