package plugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItem implements Listener {
    @SuppressWarnings("unused")
    @EventHandler
    public void dropped(PlayerDropItemEvent event) {
        if(event.getPlayer().isOp()) {
            return;
        }
        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.RED + "아이템을 버릴 수 없습니다.");
    }
}
