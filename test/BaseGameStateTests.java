import com.blackjack.IGameState;
import com.blackjack.IPlayer;
import org.junit.Assert;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class BaseGameStateTests {
  protected void testAWinner(int samScore, int dealerScore, String winnerName) {
    //given
    IPlayer sam = mock(IPlayer.class);
    when(sam.getName()).thenReturn("Sam");
    when(sam.canSurviveAfterBust()).thenReturn(false);
    when(sam.hasPriorityWithBlackjack()).thenReturn(true);
    when(sam.getScore()).thenReturn(samScore);

    IPlayer dealer = mock(IPlayer.class);
    when(dealer.getName()).thenReturn("Dealer");
    when(dealer.canSurviveAfterBust()).thenReturn(true);
    when(dealer.hasPriorityWithBlackjack()).thenReturn(false);
    when(dealer.getScore()).thenReturn(dealerScore);

    IGameState state = createAState(sam, dealer);

    //when
    Optional<IPlayer> winner = state.tryDefineAWinner();

    //then
    if (winner.isPresent())
      Assert.assertEquals(winnerName, winner.get().getName());
    else
      Assert.assertEquals("Undefined", winnerName);
  }

  protected abstract IGameState createAState(IPlayer sam, IPlayer dealer);
}
