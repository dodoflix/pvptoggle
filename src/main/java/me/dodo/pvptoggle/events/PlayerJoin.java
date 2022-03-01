package me.dodo.pvptoggle.events;

import me.dodo.pvptoggle.Pvptoggle;
import me.dodo.pvptoggle.cache.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private Pvptoggle instance;

    public PlayerJoin() {
        instance = Pvptoggle.getInstance();
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(instance.findUser(event.getPlayer()).size() < 1) {
            Player player = event.getPlayer();
            User onlinePlayer = new User(player.getUniqueId(), false);
            instance.getUsers().add(onlinePlayer);
        }
    }
}
