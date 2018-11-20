package plugin.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import plugin.operator.DamageManager;

public class Attack implements Listener {
    @SuppressWarnings("unused")
    @EventHandler
    public void attack(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Entity && !(event.getDamager() instanceof Player)) {
            event.setCancelled(true);
            return;
        } else {
            long damage = DamageManager.getInstance().getDamage((Player)event.getDamager(), (long)event.getFinalDamage());
            event.setDamage(damage);
            event.setCancelled(false);
        }
    }
}
