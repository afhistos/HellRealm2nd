package be.afhistos.hellrealm2;

import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tjplaysnow.discord.main.commands.HelpCommand;
import com.tjplaysnow.discord.main.commands.MuteCommand;
import com.tjplaysnow.discord.main.commands.UnmuteCommand;
import com.tjplaysnow.discord.main.consolecommands.BroadcastConsoleCommand;
import com.tjplaysnow.discord.main.consolecommands.HelpConsoleCommand;
import com.tjplaysnow.discord.main.consolecommands.StopConsoleCommand;
import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.CommandConsoleManager;
import com.tjplaysnow.discord.object.ProgramCommand;
import com.tjplaysnow.discord.object.ThreadHandle;

import be.afhistos.hellrealm2.commands.HealZoneSet;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Main extends JavaPlugin implements Listener{
	public Bot bot;
	public final String TOKEN = "";
	public final String PREFIX = "hr.";
	public void onEnable() {
		getLogger().info("Starting HellRealm. . .");
		getLogger().info("starting register events & commands. . .");
		Listener l = new EventListener();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(l, this);
		getLogger().info("Event registered succesfully!");
		CommandExecutor rp = new HealZoneSet();
		getCommand("sethz").setExecutor(rp);
		getLogger().info("Commands registered succesfully");
		getLogger().info("Starting Discord-Minecraft linked bot. . .");
		bot = new Bot(TOKEN, PREFIX);
		bot.setBotThread(new ThreadHandle());
		bot.setConsoleCommandManager(new CommandConsoleManager());
		getLogger().info("Minecraft-Discord linked bot started !");
		getLogger().warning("The bot may cause some lags!");
		getLogger().severe("REGSITERING BOT'S COMMANDS, IT SHALL CREATE SOME LAGS!");
		bot.addCommand(new HelpCommand(bot));
		bot.addCommand(new MuteCommand(bot));
		bot.addCommand(new UnmuteCommand(bot));
		bot.addConsoleCommand(new HelpConsoleCommand(bot));
		bot.addConsoleCommand(new BroadcastConsoleCommand(bot));
		bot.addConsoleCommand(new StopConsoleCommand(bot));
		bot.addCommand(new ProgramCommand() {
			@Override
			public boolean run(User user, MessageChannel channel, Guild guild, String label, List<String> args) {
				channel.sendMessage("Pong!").queue(delete());
				return true;
			}
			@Override
			public Permission getPermissionNeeded() {
				return Permission.MESSAGE_WRITE;
			}
			@Override
			public String getLabel() {
				return "ping";
			}
			@Override
			public String getDescription() {
				return "A ping pong command.";
			}
		});
	}

}
