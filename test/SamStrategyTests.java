import com.blackjack.SamStrategy;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SamStrategyTests {
  @ParameterizedTest
  @CsvSource({"16, true",
              "17, false",
              "18, false"})
  public void NeedMore_MoreOrEqualThan17(int ownScore, boolean expectedResult) {
    //given
    SamStrategy strategy = new SamStrategy();

    //then
    Assert.assertEquals(expectedResult, strategy.needMore(ownScore));
  }
}
