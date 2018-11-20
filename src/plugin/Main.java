package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.command.*;
import plugin.listener.Attack;
import plugin.listener.DropItem;
import plugin.listener.PlayerJoin;

@SuppressWarnings("unused")
public class Main extends JavaPlugin implements Listener {
    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "RPG 플러그인이 로드 되었습니다.");
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "RPG 플러그인이 활성화 되었습니다.");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "기획 : 이율");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "제작 : Starlight");

        getCommand("help").setExecutor(new HelpCommand());
        getCommand("stat").setExecutor(new StatCommand());
        getCommand("setstat").setExecutor(new SetStatCommand());
        getCommand("starlight").setExecutor(new StarlightCommand());
        getCommand("upgrade").setExecutor(new UpgradeCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new DropItem(), this);
        Bukkit.getPluginManager().registerEvents(new Attack(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "저장중...");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "RPG 플러그인이 비활성화 되었습니다.");
    }

    @EventHandler
    public void repeat(PluginEnableEvent event) {
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.GREEN +"Last Night RPG 서버에 오신것을 환영합니다.");
            }
        }, 20*60*10, 20*60*10);
    }
}
