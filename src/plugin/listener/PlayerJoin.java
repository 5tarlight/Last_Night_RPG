package plugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.model.Stat;
import plugin.operator.StatManager;

import java.util.Collection;

public class PlayerJoin implements Listener {
    @SuppressWarnings("unused")
    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.WHITE + " 님 께서 입장 하셨습니다.");

        Player player = e.getPlayer();
        Collection<PotionEffect> effects = player.getActivePotionEffects();
        Stat stat = StatManager.getInstance().getStat(player);

        effects.add(new PotionEffect(PotionEffectType.SPEED, 1000000, stat.getSpeed() / 100));
    }
}
