package plugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.model.Stat;
import plugin.operator.StatManager;

public class UpgradeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length != 1) {
            if(strings.length == 2) {
                if(!commandSender.isOp()) {
                    commandSender.sendMessage(ChatColor.RED + "op가 필요한 명령어입니다.");
                    return false;
                }
                Player target = Bukkit.getPlayer(strings[0]);
                Model result = editstat(target, strings[1]);
                if(result.getResult() == -2) {
                    commandSender.sendMessage(ChatColor.RED + "돈이 모자랍니다.");
                    return false;
                } else if(result.getResult() == -1) {
                    commandSender.sendMessage(ChatColor.RED + "잘못된 사용법입니다.");
                    commandSender.sendMessage("/upgrade <항목>");
                    commandSender.sendMessage("/upgrade <플레이어이름> <항목>");
                    commandSender.sendMessage("power : 힘, mana : 마나, dexerity : 손재주, luck : 행운, speed : 스피드");
                    return false;
                } else if(result.getResult() == 0) {
                    StatManager.getInstance().writeStat(target, result.getStat());
                    return true;
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Internal Error has occur");
                    return false;
                }
            }
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "플레이어만 사용가능한 형식입니다.");
                return false;
            }
            Model result = editstat((Player)commandSender, strings[0]);
            if(result.getResult() == -2) {
                commandSender.sendMessage(ChatColor.RED + "돈이 모자랍니다.");
                return false;
            } else if(result.getResult() == -1) {
                commandSender.sendMessage(ChatColor.RED + "잘못된 사용법입니다.");
                commandSender.sendMessage("/upgrade <항목>");
                commandSender.sendMessage("/upgrade <플레이어이름> <항목>");
                commandSender.sendMessage("power : 힘, mana : 마나, dexerity : 솑재주, luck : 행운, speed : 스피드");
                return false;
            } else if(result.getResult() == 0) {
                StatManager.getInstance().writeStat((Player)commandSender, result.getStat());
                return true;
            } else {
                commandSender.sendMessage(ChatColor.RED + "Internal Error has occur");
                return false;
            }
        } else {
            commandSender.sendMessage("/upgrade <항목>");
            commandSender.sendMessage("/upgrade <플레이어이름> <항목>");
            commandSender.sendMessage("power : 힘, mana : 마나, dexerity : 솑재주, luck : 행운, speed : 스피드");
            return true;
        }
    }
    private Model editstat(Player target, String type){
        Model result = new Model();
        Stat stat = StatManager.getInstance().getStat(target);
        switch (type) {
            case "power":
                if (!(stat.getMoney() >= 250)) {
                    result.setResult(-2);
                    break;
                }
                stat.setMoney(stat.getMoney() - 250);
                long power = Math.round(stat.getPower() * 1.2);
                if (power >= 100) {
                    power = 100;
                }
                stat.setPower((int) power);
                result.setStat(stat);
                break;
            case "mana":
                if (!(stat.getMoney() >= 150)) {
                    result.setResult(-2);
                    break;
                }
                stat.setMoney(stat.getMoney() - 150);
                long mana = Math.round(stat.getMana() * 1.2);
                if (mana >= 100) {
                    mana = 100;
                }
                stat.setMana((int) mana);
                result.setStat(stat);
                break;
            case "dexerity":
                if(!(stat.getMoney() >= 100)) {
                    result.setResult(-2);
                    break;
                }
                stat.setDexterity((int)Math.round(stat.getDexterity() * 1.2));
                result.setStat(stat);
                break;
            case "luck":
                if(!(stat.getMoney() >= 200)) {
                    result.setResult(-2);
                    break;
                }
                long luck = Math.round(stat.getLuck() * 1.2);
                if(luck >= 100) {
                    luck = 100;
                }
                stat.setLuck((int)luck);
                result.setStat(stat);
                break;
            case "speed":
                if(!(stat.getMoney() >= 170)) {
                    result.setResult(-2);
                    break;
                }
                long speed = Math.round(stat.getSpeed() * 1.2);
                if(speed >= 100) {
                    speed = 100;
                }
                stat.setSpeed((int)speed);
                result.setStat(stat);
                break;
            default:
                result.setResult(-1);
                result.setStat(null);
        }
        return result;
    }

    private class Model {
        private int result;
        private Stat stat;

        private int getResult() {
            return result;
        }

        private Stat getStat() {
            return stat;
        }

        private void setResult(int result) {
            this.result = result;
        }

        private void setStat(Stat stat) {
            this.stat = stat;
        }

        private Model() {
            result = 0;
            stat = null;
        }
    }
}
