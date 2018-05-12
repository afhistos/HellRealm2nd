package be.afhistos.hellrealm2;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListener implements Listener {
	static HashMap<String, Location> healzone = new HashMap<String, Location>();
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		String item = event.getMaterial().getData().getName();
		if(event.getMaterial() == Material.WOOD_HOE) {
			event.getPlayer().sendMessage(item);
			if(item.contains("§cHealZone")) {
				Player p = event.getPlayer();
				if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					event.setCancelled(true);
					Location cpos = event.getClickedBlock().getLocation();
					healzone.put(p.getName() + "pos2", cpos);
					p.sendMessage("§4HealZone §c» §6Position 2 définie à " + cpos + "§7(" + event.getClickedBlock().toString() + ")");
				}
				else if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
					event.setCancelled(true);
					Location cpos = event.getClickedBlock().getLocation();
					healzone.put(p.getName() + "pos1", cpos);
					p.sendMessage("§4HealZone §c» §6Position 1 définie à " + cpos + "§7(" + event.getClickedBlock().toString() + ")");
				}
			}
		}
	}
}
