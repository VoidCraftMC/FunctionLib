package net.voidcraftmc.skills;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

public class FunctionLib extends JavaPlugin {

	HashMap<Player, Integer> map = new HashMap<Player, Integer>();
	
	@SuppressWarnings("deprecation")
	public boolean cooldown(final Player player, final int seconds, final String message, final String donemsg) {
		
		map.put(player, seconds);
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask((Plugin) this,
				new BukkitRunnable() {

					public void run() {
						
						int time = 1;
						time = map.get(player) - 1;
						map.put(player, time);
						String string = time + "";
						String msg = message.replaceAll("{time}", string).replaceAll("(&([a-f0-9]))", "\u00A7$2");
						ActionBarAPI.sendActionBar(player, msg);
						
						if (time < 1) {
							String msgdone = donemsg.replaceAll("{time}", string).replaceAll("(&([a-f0-9]))", "\u00A7$2");
							ActionBarAPI.sendActionBar(player, msgdone);
							map.remove(player);
							cancel();
						}

					}
				}, 0L, 20L);
		if (!map.containsKey(player)){
			return true;
		}
		else {
			return false;
		}
	}
@SuppressWarnings("deprecation")
public boolean cooldown(final Player player, final int seconds) {
		
		map.put(player, seconds);
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask((Plugin) this,
				new BukkitRunnable() {

					public void run() {
						
						int time = 1;
						time = map.get(player) - 1;
						map.put(player, time);
						
						ActionBarAPI.sendActionBar(player, "Cooldown: " + time);
						
						if (time < 1) {
							
							ActionBarAPI.sendActionBar(player, "Cooldown Complete!");
							map.remove(player);
							cancel();
						}

					}
				}, 0L, 20L);
		if (!map.containsKey(player)){
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
