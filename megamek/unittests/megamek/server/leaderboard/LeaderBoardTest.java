package megamek.server.leaderboard;

import megamek.common.Player;
import megamek.server.LeaderBoard.LeaderBoard;
import megamek.server.LeaderBoard.LeaderBoardEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(value = JUnit4.class)
public class LeaderBoardTest {
    @Test
    public void testAddRanking() throws IOException {
        int numberToAdd = 10;
        Player mockedPlayer = Mockito.mock(Player.class);
        LeaderBoard leaderBoard = new LeaderBoard();

        leaderBoard.addRanking(mockedPlayer, numberToAdd);
        assertTrue(leaderBoard.get(mockedPlayer).getElo() == numberToAdd);
    }

    @Test
    public void testSortHighscores() throws IOException {
        Player mockedPlayer1 = Mockito.mock(Player.class);
        Player mockedPlayer2 = Mockito.mock(Player.class);
        Player mockedPlayer3 = Mockito.mock(Player.class);
        LeaderBoard leaderBoard = new LeaderBoard();

        leaderBoard.addRanking(mockedPlayer1, 100);
        leaderBoard.addRanking(mockedPlayer2, 300);
        leaderBoard.addRanking(mockedPlayer3, 200);

        List<LeaderBoardEntry> entries = leaderBoard.getSortedRankings();

        assertTrue(entries.get(0).getElo() == 300);
        assertTrue(entries.get(1).getElo() == 200);
        assertTrue(entries.get(2).getElo() == 100);
    }

    @Test
    public void testGet() throws IOException {
        Player mockedPlayer1 = Mockito.mock(Player.class);
        Player mockedPlayer2 = Mockito.mock(Player.class);
        Player mockedPlayer3 = Mockito.mock(Player.class);
        LeaderBoard leaderBoard = new LeaderBoard();

        leaderBoard.addRanking(mockedPlayer1, 100);
        leaderBoard.addRanking(mockedPlayer2, 300);

        assertTrue(leaderBoard.get(mockedPlayer1).getElo() == 100);
        assertTrue(leaderBoard.get(mockedPlayer2).getElo() == 300);
        assertTrue(leaderBoard.get(mockedPlayer3).getElo() == 1000);
    }

    @Test
    public void testEmptyBoard() throws IOException {
        Player mockedPlayer = Mockito.mock(Player.class);
        LeaderBoard leaderBoard = new LeaderBoard();

        assertTrue(leaderBoard.getSortedRankings().isEmpty());
        leaderBoard.addRanking(mockedPlayer, 100);
        assertFalse(leaderBoard.getSortedRankings().isEmpty());
        leaderBoard.emptyBoard();
        assertTrue(leaderBoard.getSortedRankings().isEmpty());
    }
}
