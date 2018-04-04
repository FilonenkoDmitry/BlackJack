import com.blackjack.ICardsDistributor;
import com.blackjack.IGameState;
import com.blackjack.IPlayer;
import com.blackjack.SecondPlayerTurnState;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Mockito.mock;

public class SecondPlayerTurnStateTests extends BaseGameStateTests {

  @ParameterizedTest
  @CsvSource({"12, 22, Sam",
              "15, 21, Dealer",
              "12, 17, Dealer",
              "9, 6, Sam"})
  public void tryDefineAWinner_GivesAnUltimateWinner(int samScore, int dealerScore, String winnerName) {
    testAWinner(samScore, dealerScore, winnerName);
  }

  @Override
  protected IGameState createAState(IPlayer sam, IPlayer dealer) {
    return new SecondPlayerTurnState(mock(ICardsDistributor.class), sam, dealer);
  }
}
