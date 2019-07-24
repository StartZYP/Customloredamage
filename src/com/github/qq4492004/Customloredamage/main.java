package com.github.qq4492004.Customloredamage;

import com.github.qq4492004.Customloredamage.Entity.ConfigAttribute;
import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.Listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class main extends JavaPlugin implements Listener {
    public static Map<String, PlayerAttribute> playersatt = new HashMap<>();
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        ConfigReload();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }

    private void ConfigReload(){
        reloadConfig();
       ConfigAttribute.Damage = getConfig().getString("state.damage");
        ConfigAttribute.Defense = getConfig().getString("state.denfense");
        ConfigAttribute.CritRate = getConfig().getString("state.CritRate");
        ConfigAttribute.CritDouble = getConfig().getString("state.CritDouble");
        ConfigAttribute.split = getConfig().getString("split");
        System.out.println(ConfigAttribute.Damage);
        System.out.println(ConfigAttribute.Defense);
        System.out.println(ConfigAttribute.CritRate);
        System.out.println(ConfigAttribute.CritDouble);
        System.out.println(ConfigAttribute.split);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==1){
            if (args[0].equalsIgnoreCase("reload")){
                ConfigReload();
                sender.sendMessage("重载ok");
            }else if (args[0].equalsIgnoreCase("info")){
                sender.sendMessage(playersatt.get(sender.getName()).toString());
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}
