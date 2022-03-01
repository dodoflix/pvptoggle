package me.dodo.pvptoggle.events;

import me.dodo.pvptoggle.Pvptoggle;
import me.dodo.pvptoggle.cache.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;

public class EntityDamageByEntity implements Listener {
    private final Pvptoggle instance;

    public EntityDamageByEntity() {
        instance = Pvptoggle.getInstance();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if(!(entity instanceof Player))
            return;
        Entity attacker = event.getDamager();
        if(!(attacker instanceof Player))
            return;
        List<User> filteredAttacker = instance.findUser((Player) attacker);
        if(!filteredAttacker.get(0).isPvp()) {
            event.setCancelled(true);
            attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&bPandoPvP&8] &cPvP Kapalıyken diğer oyunculara saldıramazsın."));
            return;
        }
        List<User> filteredEntity = instance.findUser((Player) entity);
        if(!filteredEntity.get(0).isPvp()){
            event.setCancelled(true);
            entity.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&8[&bPandoPvP&8] " + ((Player) entity).getDisplayName() +" &c adlı oyuncunun PvP'si kapalı."));
        }
    }
}
