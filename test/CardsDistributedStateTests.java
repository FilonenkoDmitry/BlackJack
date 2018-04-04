import com.blackjack.CardsDistributedState;
import com.blackjack.IGameState;
import com.blackjack.IPlayer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardsDistributedStateTests extends BaseGameStateTests {

  @ParameterizedTest
  @CsvSource({"22, 22, Dealer",
          "21, 21, Sam",
          "22, 15, Dealer",
          "12, 22, Sam",
          "17, 17, Undefined",
          "20, 15, Undefined",
          "13, 18, Undefined"})
  public void tryDefineAWinner_GivesAWinnerOnlyAtBorderCases(int samScore, int dealerScore, String winnerName) {
    testAWinner(samScore, dealerScore, winnerName);
  }

  @Override
  protected IGameState createAState(IPlayer sam, IPlayer dealer) {
    return new CardsDistributedState(sam, dealer);
  }
}

