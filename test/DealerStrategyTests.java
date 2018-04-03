import com.blackjack.DealerStrategy;
import com.blackjack.ICompetitor;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DealerStrategyTests {
  @ParameterizedTest
  @CsvSource({"3, 2, true",
              "3, 3, true",
              "3, 4, false"})
  public void NeedMore_StrictlyMoreThanCompetitor(int competitorScore, int ownScore, boolean expectedResult) {
    //given
    final ICompetitor competitor = mock(ICompetitor.class);
    when(competitor.getScore()).thenReturn(competitorScore);

    DealerStrategy strategy = new DealerStrategy(competitor);

    //then
    Assert.assertEquals(expectedResult, strategy.needMore(ownScore));
  }
}
