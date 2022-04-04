package megamek.server.LeaderBoard;

import megamek.common.Player;
import java.util.ArrayList;



public class LeaderBoard {

    private static LeaderBoard SINGLETON;
    public LeaderBoard() {
        leaderboard = new ArrayList<>();
    }

    private final ArrayList<LeaderBoardEntry> leaderboard;


    public LeaderBoardEntry addRanking(Player player, int elo) {
        LeaderBoardEntry entry = new LeaderBoardEntry(player,elo);
        addRanking(entry);

        return entry;

    }
    private void addRanking(LeaderBoardEntry e) {
        leaderboard.add(e);
    }

    private void sortHighscores() {
        this.leaderboard.sort(new AscendingComparator());
    }
    
    public LeaderBoardEntry get(Player player) {
        for (LeaderBoardEntry leaderBoardEntry : leaderboard) {
            if(leaderBoardEntry.equals(player))
                return leaderBoardEntry;
        }

        return addRanking(player,1000);
    }


    public ArrayList<LeaderBoardEntry> getSortedRankings() {
        sortHighscores();
        return leaderboard;
    }

    public void emptyBoard() {
        leaderboard.clear();
    }

    public static LeaderBoard get() {
        if(SINGLETON == null)
            SINGLETON = new LeaderBoard();
        return SINGLETON;
    }



    public void print(){
        this.sortHighscores();
        System.out.println("----- LEADER BORD SORTED BY HIGHEST ELO ----");
        System.out.println("Rank # - Player - Elo Score");

        for(int i = 0; i < leaderboard.size(); i++){
            String name = leaderboard.get(i).getPlayer().getName();
            int elo = leaderboard.get(i).getElo();
            System.out.println(i + "-" +name + "-" +elo);

        }
    }
}
