package be.afhistos.hellrealm2.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HealZoneSet implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethz")) {
			if(sender instanceof Player) {
				ItemStack hoe = new ItemStack(Material.WOOD_HOE, 1);
				ItemMeta hoeM = hoe.getItemMeta();
				hoeM.setDisplayName("§cHealZone");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("§7Clic gauche sur un bloc pour définir le 1er");
				lore.add("§7coin de la §cHealZone");
				lore.add("  ");
				lore.add("  ");
				lore.add("§7Clic droit sur un bloc pour définir le 2ème");
				lore.add("§7coin de la §cHealZone");
				hoeM.setLore(lore);
				hoe.setItemMeta(hoeM);
				((Player) sender).getInventory().addItem(hoe);
				return true;
			}
			else {
				sender.sendMessage("§4System Failure.");
				return true;
			}
		}
		return false;
	}

}
