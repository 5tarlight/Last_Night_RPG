package plugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            commandSender.sendMessage("/help : 이 메시지를 출력합니다.");
            commandSender.sendMessage("/help stat : 스텟 도움말을 출력합니다.");
            commandSender.sendMessage("/help command : 사용가능한 커맨드를 보여줍니다.");
            return true;
        }
        if(strings[0].equalsIgnoreCase("stat")) {
            commandSender.sendMessage("/stat print : 스텟 정보를 츨력합니다.");
            commandSender.sendMessage("/stat create : 스텟 정보를 생성합니다. 기존의 스텟은 삭제됩니다.");
            return true;
        } else if (strings[0].equalsIgnoreCase("command")) {
            commandSender.sendMessage("/stat : 스탯을 생성 및 출력합니다.");
            commandSender.sendMessage("/upgrade : 스텟값을 돈을 사용해 올립니다.");
            return true;
        } else {
            commandSender.sendMessage(ChatColor.RED + "찾을수 없는 도움말입니다.");
            commandSender.sendMessage("/help : 이 메시지를 출력합니다.");
            commandSender.sendMessage("/help stat : 스텟 도움말을 출력합니다.");
            commandSender.sendMessage("/help command : 사용가능한 커맨드를 보여줍니다.");
            return false;
        }
    }
}
