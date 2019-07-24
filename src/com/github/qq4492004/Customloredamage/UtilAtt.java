package com.github.qq4492004.Customloredamage;

import com.github.qq4492004.Customloredamage.Entity.ConfigAttribute;
import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.Entity.TempAttribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class UtilAtt {
    public static PlayerAttribute GetPlayerItemAttribute(Player player,int i){
        PlayerAttribute playerAttribute = new PlayerAttribute(0, 0, 0, 0);
        TempAttribute tempAttribute =null;
        if (player.getInventory().getItem(i) != null) {
            tempAttribute = getEquipStates(player.getInventory().getItem(i));
            playerAttribute.setTempAttribute(tempAttribute);
            playerAttribute = playerAttribute.GetAllAttr();
            System.out.println(playerAttribute);
        }

        ItemStack[] itemStacks = player.getInventory().getArmorContents();
        ItemStack[] arr = itemStacks;
        int len$ = itemStacks.length;
        for (int i$ = 0; i$ < len$; ++i$) {
            ItemStack itemStack = arr[i$];
            if (itemStack != null) {
                TempAttribute tempAtt = getEquipStates(itemStack);
                if (tempAtt != null) {
                    playerAttribute.setTempAttribute(tempAtt);
                    playerAttribute = playerAttribute.GetAllAttr();
                }
            }
        }
        return playerAttribute;
    }


    public static PlayerAttribute GetPlayerAttribute(Player player){
        PlayerAttribute playerAttribute = new PlayerAttribute(0, 0, 0, 0);
        TempAttribute tempAttribute =null;
        if (player.getItemInHand() != null) {
            System.out.println("手上有物品"+player.getItemInHand().getType());
            tempAttribute = getEquipStates(player.getItemInHand());
            playerAttribute.setTempAttribute(tempAttribute);
            playerAttribute = playerAttribute.GetAllAttr();
            System.out.println(playerAttribute);
        }else {
            System.out.println("手上没有物品");
        }

        ItemStack[] itemStacks = player.getInventory().getArmorContents();
        ItemStack[] arr = itemStacks;
        int len$ = itemStacks.length;
        for (int i$ = 0; i$ < len$; ++i$) {
            ItemStack itemStack = arr[i$];
            if (itemStack != null) {
                TempAttribute tempAtt = getEquipStates(itemStack);
                if (tempAtt != null) {
                    playerAttribute.setTempAttribute(tempAtt);
                    playerAttribute = playerAttribute.GetAllAttr();
                }
            }
        }
        return playerAttribute;
    }

    private static TempAttribute getEquipStates(ItemStack itemStack){
        if (itemStack.getItemMeta() == null) {
            return null;
        } else if (itemStack.getItemMeta().getLore() == null) {
            return null;
        } else if (itemStack.getItemMeta().getLore().size() == 0) {
            return null;
        } else {
            double Damage=0;
            double Defense=0;
            double CritRate=0;
            double CritDouble=0;
            Iterator Is = itemStack.getItemMeta().getLore().iterator();
            while (Is.hasNext()){
                String lore = (String) Is.next();
                Damage += getDamage(lore);
                Defense += getDenfense(lore);
                CritRate += getCritRate(lore);
                CritDouble += getCritDouble(lore);
            }
            return new TempAttribute(Damage,Defense,CritRate,CritDouble);
        }
    }

    private static double getDamage(String lore){
        if (lore.contains(ConfigAttribute.Damage)) {
            System.out.println(lore);
            String[] s = lore.split(ConfigAttribute.split);
            System.out.println(s.length);
            System.out.println(s[1]);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }

    private static double getDenfense(String lore){
        if (lore.contains(ConfigAttribute.Defense)) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }

    private static double getCritRate(String lore){
        if (lore.contains(ConfigAttribute.CritRate)) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }

    private static double getCritDouble(String lore){
        if (lore.contains(ConfigAttribute.CritDouble)) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }


}
