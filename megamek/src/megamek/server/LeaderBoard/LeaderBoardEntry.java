package megamek.server.LeaderBoard;

import megamek.common.Player;

import java.util.Objects;

public class LeaderBoardEntry {

    private final Player player;

    private int elo;

    public LeaderBoardEntry(Player player, int amount) {
        this.player = player;
        this.elo = amount;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getElo() {
        return elo;
    }

    public LeaderBoardEntry copy() {
        return new LeaderBoardEntry(player, elo);
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    // Elo should never be negative
    public void addElo(int elo) { this.elo = this.elo + elo > 0 ? this.elo + elo : 1; }

    @Override
    public String toString() {
        return "LeaderBoardEntry{" +
                "Player='" + player.getName() + '\'' +
                ", Elo=" + elo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaderBoardEntry that = (LeaderBoardEntry) o;
        return player == that.getPlayer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
