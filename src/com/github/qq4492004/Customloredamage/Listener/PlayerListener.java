package com.github.qq4492004.Customloredamage.Listener;

import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.UtilAtt;
import com.github.qq4492004.Customloredamage.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    @EventHandler
    public void PlayerQuitGame(PlayerQuitEvent event){
        main.playersatt.remove(event.getPlayer().getName());
    }

    @EventHandler
    public void PlayerDamgeEntity(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player))return;
        Player player= (Player) event.getDamager();
        PlayerAttribute playerAttribute = main.playersatt.get(player.getName());
        System.out.println(playerAttribute);
        double damage1 = playerAttribute.getDamage();
        double critRate = playerAttribute.getCritRate();
        double critDouble = playerAttribute.getCritDouble();
        if(Math.random()<critRate/100){
            damage1+= damage1*critDouble;
            player.sendMessage("暴击成功");
        }
        if(event.getEntity() instanceof Player){
            PlayerAttribute playerAttribute1 = main.playersatt.get(((Player) event.getEntity()).getName());
            double pluginDamage = damage1-playerAttribute1.getDefense();
            if (pluginDamage>=0){
                event.setDamage(event.getDamage()+pluginDamage);
            }else {
                event.setDamage(event.getDamage());
            }
        }else {
            event.setDamage(damage1);
        }
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerAttribute attr = UtilAtt.GetPlayerAttribute(player);
        main.playersatt.put(player.getName(), attr);
    }

    @EventHandler
    public void onchangeweapon(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int newSlot = event.getNewSlot();
        PlayerAttribute attr = UtilAtt.GetPlayerItemAttribute(player,newSlot);
        main.playersatt.put(player.getName(), attr);
    }


    @EventHandler
    public void dropEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        PlayerAttribute attr = UtilAtt.GetPlayerAttribute(player);
        main.playersatt.put(player.getName(), attr);
    }

    @EventHandler
    public void breakEvent(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        PlayerAttribute attr = UtilAtt.GetPlayerAttribute(player);
        main.playersatt.put(player.getName(), attr);
    }

    @EventHandler
    public void closeInv(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        PlayerAttribute attr = UtilAtt.GetPlayerAttribute(player);
        main.playersatt.put(player.getName(), attr);
    }



}
