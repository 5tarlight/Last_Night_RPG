package plugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StarlightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("Hey" + ChatColor.GOLD + " " + commandSender.getName() + " " +ChatColor.GOLD + "Starlight" + ChatColor.WHITE + " is G.O.D.");
        return true;
    }
}
