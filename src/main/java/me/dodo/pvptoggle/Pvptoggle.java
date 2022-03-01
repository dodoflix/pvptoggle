package me.dodo.pvptoggle;

import me.dodo.pvptoggle.cache.User;
import me.dodo.pvptoggle.commands.Toggle;
import me.dodo.pvptoggle.events.CMIPvPStart;
import me.dodo.pvptoggle.events.CMIPvPEnd;
import me.dodo.pvptoggle.events.EntityDamageByEntity;
import me.dodo.pvptoggle.events.PlayerJoin;
import me.dodo.pvptoggle.expansions.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Pvptoggle extends JavaPlugin {
    private static Pvptoggle Main;
    private List<User> Users;
    private boolean CMI;
    private boolean PlaceholderAPI;

    @Override
    public void onEnable() {
        Main = this;

        Users = new ArrayList<>();
        for (Player player : getServer().getOnlinePlayers()) {
            User onlinePlayer = new User(player.getUniqueId(), false);
            Users.add(onlinePlayer);
        }

        if (getServer().getPluginManager().getPlugin("CMI") != null) {
            getServer().getPluginManager().registerEvents(new CMIPvPStart(), this);
            getServer().getPluginManager().registerEvents(new CMIPvPEnd(), this);
            CMI = true;
        }

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            PlaceholderAPI = true;
            new Placeholder(this).register();
        }

        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        this.getCommand("pvptoggle").setExecutor(new Toggle());
    }

    public static Pvptoggle getInstance() {
        return Main;
    }

    public List<User> findUser(Player player) {
        return Users.stream()
                .filter(a -> Objects.equals(a.getUuid(), player.getUniqueId()))
                .collect(Collectors.toList());
    }

    public List<User> getUsers() {
        return Users;
    }

    public boolean isCMI() {
        return CMI;
    }

    public boolean isPlaceholderAPI() {
        return PlaceholderAPI;
    }
}
