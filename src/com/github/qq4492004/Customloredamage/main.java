package com.github.qq4492004.Customloredamage;

import com.github.qq4492004.Customloredamage.Entity.ConfigAttribute;
import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.Listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        if(label.equalsIgnoreCase("LoreArms")&&sender instanceof Player&&sender.hasPermission("lorecreate.use")&&args.length==2){
            Player player = (Player) sender;
            ItemStack itemInHand = player.getItemInHand();
            if (itemInHand==null){
                player.sendMessage("§e§l手持有问题");
                return false;
            }
            if (itemInHand.getType()== Material.AIR){
                player.sendMessage("§e§l手持有问题");
                return false;
            }
            if (!itemInHand.hasItemMeta()){
                player.sendMessage("§e§l手持有问题");
                return false;
            }
            if (!itemInHand.getItemMeta().hasLore()){
                player.sendMessage("§e§l手持有问题");
                return false;
            }
            ItemMeta itemMeta = itemInHand.getItemMeta();
            List<String> lore = itemMeta.getLore();
            if (!lore.toString().contains(args[0])){
                player.sendMessage("§e§l无关键字lore");
                return false;
            }
            List<String> newlore = new ArrayList<>();
            for (String temllore:lore){
                if (temllore.contains(args[0])){
                    String[] split = temllore.split(ConfigAttribute.split);
                    double number = Double.parseDouble(split[1]);
                    number+=Double.parseDouble(args[1]);
                    String addlore = split[0] + number;
                    newlore.add(addlore);
                }else {
                    newlore.add(temllore);
                }
            }
            itemMeta.setLore(newlore);
            itemInHand.setItemMeta(itemMeta);
            player.setItemInHand(itemInHand);
            player.sendMessage("添加成功");
        }else if (label.equalsIgnoreCase("LoreCreate")&&sender instanceof Player&&sender.hasPermission("lorecreate.use")&&args.length==2){
            Player player = (Player) sender;
            ItemStack itemInHand = player.getItemInHand();
            if (itemInHand==null){
                player.sendMessage("§e§l手持有问题");
                return false;
            }
            if (itemInHand.getType()== Material.AIR){
                player.sendMessage("§e§l手持有问题");
                return false;
            }
            ItemMeta itemMeta = itemInHand.getItemMeta();
            if (itemMeta.hasLore()){
                List<String> lore = itemMeta.getLore();
                lore.add(args[0]+ConfigAttribute.split+args[1]);
                itemMeta.setLore(lore);
                itemInHand.setItemMeta(itemMeta);
                player.setItemInHand(itemInHand);
            }else {
                List<String> lore = new ArrayList<>();
                lore.add(args[0]+ConfigAttribute.split+args[1]);
                itemMeta.setLore(lore);
                itemInHand.setItemMeta(itemMeta);
                player.setItemInHand(itemInHand);
            }

        }
        if (args.length==1&&sender.isOp()){
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
