import com.blackjack.FirstPlayerTurnState;
import com.blackjack.ICardsDistributor;
import com.blackjack.IGameState;
import com.blackjack.IPlayer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Mockito.mock;

public class FirstPlayerTurnStateTests extends BaseGameStateTests {

  @ParameterizedTest
  @CsvSource({"22, 15, Dealer",
              "21, 18, Sam",
              "17, 17, Undefined",
              "20, 15, Undefined",
              "13, 18, Undefined"})
  public void tryDefineAWinner_GivesAWinnerOnlyAtBorderCases(int samScore, int dealerScore, String winnerName) {
    testAWinner(samScore, dealerScore, winnerName);
  }

  @Override
  protected IGameState createAState(IPlayer sam, IPlayer dealer) {
    return new FirstPlayerTurnState(mock(ICardsDistributor.class), sam, dealer);
  }
}