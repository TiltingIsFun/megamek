package megamek.server.leaderboard;

import megamek.common.Player;
import megamek.server.LeaderBoard.LeaderBoardEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(value = JUnit4.class)
public class LeaderBoardEntryTest {
    @Test
    public void testEquals() throws IOException {
        Player mockedPlayer1 = Mockito.mock(Player.class);
        Player mockedPlayer2 = Mockito.mock(Player.class);
        Mockito.when(mockedPlayer1.getName()).thenReturn("Parzival");
        Mockito.when(mockedPlayer2.getName()).thenReturn("Art3mis");

        LeaderBoardEntry leaderBoardEntry1 = new LeaderBoardEntry(mockedPlayer1, 0);
        LeaderBoardEntry leaderBoardEntry2 = new LeaderBoardEntry(mockedPlayer1, 10);
        LeaderBoardEntry leaderBoardEntry3 = new LeaderBoardEntry(mockedPlayer2, 20);

        assertTrue(leaderBoardEntry1.equals(leaderBoardEntry1));
        assertTrue(leaderBoardEntry1.equals(leaderBoardEntry2));

        assertFalse(leaderBoardEntry1.equals(null));
        assertFalse(leaderBoardEntry1.equals(leaderBoardEntry3));
    }

    @Test
    public void testAddElo() throws IOException {
        int numberToAdd = 5;
        int startElo = 10;
        Player mockedPlayer = Mockito.mock(Player.class);

        LeaderBoardEntry leaderBoardEntry = new LeaderBoardEntry(mockedPlayer, startElo);

        assertFalse(leaderBoardEntry.getElo() == startElo + numberToAdd);

        leaderBoardEntry.addElo(numberToAdd);
        assertTrue(leaderBoardEntry.getElo() == startElo + numberToAdd);

        leaderBoardEntry.addElo(-numberToAdd);
        assertTrue(leaderBoardEntry.getElo() == startElo);

        leaderBoardEntry.addElo(-startElo);
        assertTrue(leaderBoardEntry.getElo() == 1);
    }

    @Test
    public void testCopy() throws IOException {
        Player mockedPlayer = Mockito.mock(Player.class);
        Mockito.when(mockedPlayer.getName()).thenReturn("Parzival");

        LeaderBoardEntry leaderBoardEntry1 = new LeaderBoardEntry(mockedPlayer, 0);

        LeaderBoardEntry leaderBoardEntry2 = leaderBoardEntry1.copy();
        assertTrue(leaderBoardEntry1.getElo() == leaderBoardEntry2.getElo());
        assertEquals(leaderBoardEntry1.getPlayer(), leaderBoardEntry2.getPlayer());
        assertNotSame(leaderBoardEntry1, leaderBoardEntry2);
    }

    @Test
    public void testToString() throws IOException {
        Player mockedPlayer = Mockito.mock(Player.class);
        Mockito.when(mockedPlayer.getName()).thenReturn("Parzival");

        LeaderBoardEntry leaderBoardEntry1 = new LeaderBoardEntry(mockedPlayer, 0);

        assertTrue(leaderBoardEntry1.toString().contains(mockedPlayer.getName()));
        assertTrue(leaderBoardEntry1.toString().contains(leaderBoardEntry1.getElo().toString()));
    }
}
