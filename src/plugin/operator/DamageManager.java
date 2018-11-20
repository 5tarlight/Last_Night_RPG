package plugin.operator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Random;

public class DamageManager {
    private static DamageManager instance = new DamageManager();
    public static DamageManager getInstance() { return instance; }

    public long getDamage(Player attacker, long defaultDamage) {
        if(isCrit(StatManager.getInstance().getStat(attacker).getLuck())) {
            attacker.sendMessage(ChatColor.YELLOW + "크리티컬!!");
            return (long)(defaultDamage * 1.5);
        } else {
            return defaultDamage;
        }
    }

    private boolean isCrit(int luck) {
        Random r = new Random();
        int bound = r.nextInt(100) + 1;

        return bound <= luck;
    }
}
