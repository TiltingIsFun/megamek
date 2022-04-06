package megamek.server.leaderboard;

import megamek.common.Player;
import megamek.server.LeaderBoard.LeaderBoard;
import megamek.server.leaderBoardUtil.EloFormulaDefault;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;
import static org.junit.Assert.assertTrue;

@RunWith(value = JUnit4.class)
public class EloFormulaDefaultTest {

    @Test
    public void testCalcElo() throws IOException {
        Player mockedPlayer1 = Mockito.mock(Player.class);
        Player mockedPlayer2 = Mockito.mock(Player.class);
        Mockito.when(mockedPlayer1.getName()).thenReturn("Parzival");
        Mockito.when(mockedPlayer2.getName()).thenReturn("Art3mis");

        LeaderBoard leaderBoard = new LeaderBoard();

        EloFormulaDefault eloFormula = new EloFormulaDefault();
        int eloDefault = eloFormula.calcElo(leaderBoard, mockedPlayer1, mockedPlayer2);

        leaderBoard.addRanking(mockedPlayer1, leaderBoard.get(mockedPlayer1).getElo());
        leaderBoard.addRanking(mockedPlayer2, leaderBoard.get(mockedPlayer2).getElo());

        leaderBoard.get(mockedPlayer1).addElo(leaderBoard.get(mockedPlayer1).getElo());
        int eloResult = eloFormula.calcElo(leaderBoard, mockedPlayer1, mockedPlayer2);

        assertTrue(eloResult == eloDefault * 0.5);
    }

}
