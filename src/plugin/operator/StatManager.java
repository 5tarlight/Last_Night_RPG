package plugin.operator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import plugin.model.Stat;

import java.io.*;

public class StatManager {
    private final static StatManager instance = new StatManager();

    public static StatManager getInstance() {
        return instance;
    }

    @SuppressWarnings("all")
    public void writeStat(Player player, Stat stat) {
        File dir = new File("Stat" + File.separator + "stat");
        File root = new File(dir, player.getUniqueId() + ".txt");
        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            if(!dir.exists()) {
                dir.mkdirs();
            }
            if(!root.exists()) {
                root.createNewFile();
            }
        } catch (IOException ex) {
            player.sendMessage(ChatColor.RED + "스텟 파일 생성중 오류 발생");
            return;
        }

        try {
            fos = new FileOutputStream(root);
            out = new ObjectOutputStream(fos);
        } catch(IOException ex) {
            player.sendMessage(ChatColor.RED + "ObjectOutputStream 생성중 오류 발생");
            return;
        }

        try {
            out.writeObject(stat);
            out.flush();
        } catch(IOException ex) {
            player.sendMessage("스탯 파일에 정보 작성중 오류 발생");
            return;
        }

        player.sendMessage(ChatColor.GREEN + "스탯 생성 완료");
        try {
            out.close();
        } catch(IOException ex) {
            System.gc();
        }
    }

    public void loadStat(Player player) {
        File dir = new File("Stat" + File.separator + "stat");
        File root = new File(dir, player.getUniqueId() + ".txt");
        FileInputStream fis;
        ObjectInputStream in;
        Stat stat;

        if(!root.exists()) {
            player.sendMessage(ChatColor.RED + "스텟을 찾을 수 없습니다.");
            player.sendMessage(ChatColor.GREEN + "스탯을 새로 생성합니다.");
            writeStat(player, new Stat());
        }
        try {
            fis = new FileInputStream(root);
            in = new ObjectInputStream(fis);
        } catch (IOException ex) {
            player.sendMessage(ChatColor.RED + "ObjectInputStream 생성중 오류 발생");
            return;
        }
        try {
            stat = (Stat) in.readObject();
        } catch(IOException ex1) {
            player.sendMessage(ChatColor.RED + "스텟 로딩중 오류 발생");
            return;
        } catch (ClassNotFoundException ex2) {
            player.sendMessage(ChatColor.RED + "ClassNotFoundException 발생");
            return;
        }

        printStat(player, stat);
    }

    private void printStat(Player player, Stat stat) {
        player.sendMessage("힘 : " + stat.getPower());
        player.sendMessage("마나 : " + stat.getMana());
        player.sendMessage("손재주 : " + stat.getDexterity());
        player.sendMessage("행운 : " + stat.getLuck());
        player.sendMessage("이동속도 : " + stat.getSpeed());
        player.sendMessage("돈 : " + stat.getMoney());
    }

    public Stat getStat(Player player) {
        File dir = new File("Stat" + File.separator + "stat");
        File root = new File(dir, player.getUniqueId() + ".txt");
        FileInputStream fis;
        ObjectInputStream in;
        Stat stat;

        if(!root.exists()) {
            player.sendMessage(ChatColor.RED + "스텟을 찾을 수 없습니다.");
            player.sendMessage(ChatColor.GREEN + "스탯을 새로 생성합니다.");
            writeStat(player, new Stat());
        }
        try {
            fis = new FileInputStream(root);
            in = new ObjectInputStream(fis);
        } catch (IOException ex) {
            player.sendMessage(ChatColor.RED + "ObjectInputStream 생성중 오류 발생");
            return null;
        }
        try {
            stat = (Stat) in.readObject();
        } catch(IOException ex1) {
            player.sendMessage(ChatColor.RED + "스텟 로딩중 오류 발생");
            ex1.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex2) {
            player.sendMessage(ChatColor.RED + "ClassNotFoundException 발생");
            return null;
        }
        return stat;
    }
}
