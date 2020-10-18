package us.someteamname.tabedit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamAPI {
 Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

 @SuppressWarnings("deprecation")
public void setTag(Player p, String prefix) {
	 Team team = board.getTeam(p.getName());
	 if (team == null)
		 team = board.registerNewTeam(p.getName());
	 team.setPrefix(prefix);
	 team.addPlayer((OfflinePlayer)p);
	 for (Player ps : Bukkit.getOnlinePlayers())
		 ps.setScoreboard(board);
 }
}