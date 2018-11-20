package plugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.model.Stat;
import plugin.operator.StatManager;

public class SetStatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage(ChatColor.RED + "op 만 사용 가능한 명령어 입니다.");
            return false;
        }
        if(strings.length != 3) {
            commandSender.sendMessage("/setstat <플레이어> <스텟 항목> <새 값>");
            commandSender.sendMessage("/setstat <플레이어> power <새 값> : 플레이어의 힘을 설정합니다.");
            commandSender.sendMessage("/setstat <플레이어> mana <새 값> : 플레이어의 마나를 설정합니다.");
            commandSender.sendMessage("/setstat <플레이어> dexerity <새 값> : 플레이어의 손재주를 설정합니다.");
            commandSender.sendMessage("/setstat <플레이어> luck <새 값> : 플레이어의 행운을 설정합니다.");
            commandSender.sendMessage("/setstat <플레이어> speed <새 값> : 플레이어의 이동속도를 설정합니다.");
            commandSender.sendMessage("/setstat <플레이어> money <새 값> : 플레이어의 돈을 설정합니다.");

            return false;
        }

        Player player = Bukkit.getPlayer(strings[0]);

        if(player == null) {
            commandSender.sendMessage(ChatColor.RED + "플레이어를 찾을 수 없습니다.");
            return false;
        }

        Stat stat = StatManager.getInstance().getStat(player);

        if(stat == null) {
            return false;
        }

        try {
            switch(strings[1]) {
                case "power":
                    stat.setPower(Integer.parseInt(strings[2]));
                    StatManager.getInstance().writeStat(player, stat);
                    break;
                case "mana":
                    stat.setMana(Integer.parseInt(strings[2]));
                    StatManager.getInstance().writeStat(player, stat);
                    break;
                case "dexerity":
                    stat.setDexterity(Integer.parseInt(strings[2]));
                    StatManager.getInstance().writeStat(player, stat);
                    break;
                case "luck":
                    stat.setLuck(Integer.parseInt(strings[2]));
                    StatManager.getInstance().writeStat(player, stat);
                    break;
                case "speed":
                    stat.setSpeed(Integer.parseInt(strings[2]));
                    StatManager.getInstance().writeStat(player, stat);
                    break;
                case "money":
                    stat.setMoney(Long.parseLong(strings[2]));
                    StatManager.getInstance().writeStat(player, stat);
                    break;
                default:
                    commandSender.sendMessage(ChatColor.RED + "잘못된 스텟 항목 입니다.");
                    commandSender.sendMessage("/setstat <플레이어> <스텟 항목> <새 값>");
                    commandSender.sendMessage("/setstat <플레이어> power <새 값> : 플레이어의 힘을 설정합니다.");
                    commandSender.sendMessage("/setstat <플레이어> mana <새 값> : 플레이어의 마나를 설정합니다.");
                    commandSender.sendMessage("/setstat <플레이어> dexerity <새 값> : 플레이어의 손재주를 설정합니다.");
                    commandSender.sendMessage("/setstat <플레이어> luck <새 값> : 플레이어의 행운을 설정합니다.");
                    commandSender.sendMessage("/setstat <플레이어> speed <새 값> : 플레이어의 이동속도를 설정합니다.");
                    commandSender.sendMessage("/setstat <플레이어> money <새 값> : 플레이어의 돈을 설정합니다.");

                    return false;
            }
            return true;
        }catch (Exception ex2) {
            commandSender.sendMessage(ChatColor.RED + "오류 발생");
            return false;
        }
    }
}