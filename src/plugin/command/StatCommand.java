package plugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.model.Stat;
import plugin.operator.StatManager;

public class StatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "플레이어만 사용 가능한 명령어 입니다.");
            return false;
        }
        if(strings.length == 0) {
            commandSender.sendMessage("/stat print : 스텟을 출력합니다.");
            commandSender.sendMessage("/stat create : 스텟을 생성합니다. ");
            return false;
        }
        Player player = (Player) commandSender;
        if(strings[0].equalsIgnoreCase("print")) {
            StatManager.getInstance().loadStat(player);
            return true;
        } else if (strings[0].equalsIgnoreCase("create")) {
            StatManager.getInstance().writeStat(player, new Stat());
            return true;
        } else {
            player.sendMessage("/stat print : 스텟을 출력합니다.");
            player.sendMessage("/stat create : 스텟을 생성합니다. ");
            return false;
        }
    }
}
