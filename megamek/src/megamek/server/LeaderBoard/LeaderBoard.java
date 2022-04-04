package megamek.server.LeaderBoard;

import megamek.common.Player;
import java.util.ArrayList;
import java.util.List;


public class LeaderBoard {

    private static LeaderBoard SINGLETON;
    public LeaderBoard() {
        rankings = new ArrayList<>();
    }

    private final ArrayList<LeaderBoardEntry> rankings;


    public LeaderBoardEntry addRanking(Player player, int elo) {
        LeaderBoardEntry entry = new LeaderBoardEntry(player,elo);
        addRanking(entry);

        return entry;

    }
    private void addRanking(LeaderBoardEntry e) {
        rankings.add(e);
    }

    private void sortHighscores() {
        this.rankings.sort(new AscendingComparator());
    }
    
    public LeaderBoardEntry get(Player player) {
        for (LeaderBoardEntry leaderBoardEntry : rankings) {
            if(leaderBoardEntry.equals(player))
                return leaderBoardEntry;
        }

        return addRanking(player,1000);
    }


    public List<LeaderBoardEntry> getSortedRankings() {
        sortHighscores();
        return rankings;
    }

    public void emptyBoard() {
        rankings.clear();
    }

    public static LeaderBoard get() {
        if(SINGLETON == null)
            SINGLETON = new LeaderBoard();
        return SINGLETON;
    }



    public void print(){
        if(rankings.isEmpty()){
            return;
        }
        this.sortHighscores();
        System.out.println("----- LEADERBOARD SORTED BY HIGHEST ELO ----");
        System.out.println("Rank # - Player - Elo Score");

        int rank = 1;
        for (LeaderBoardEntry x: rankings
             ) {
            if(x.getPlayer() == null)
                break;
            String name = x.getPlayer().getName();
            int elo = x.getElo();
            System.out.println(rank++ + "-" +name + "-" +elo);
        }
    }
}
