package me.dodo.pvptoggle.expansions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.dodo.pvptoggle.Pvptoggle;
import me.dodo.pvptoggle.cache.User;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class Placeholder extends PlaceholderExpansion {

    private final Pvptoggle plugin;

    public Placeholder(Pvptoggle plugin) {
        this.plugin = plugin;
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return "ispvp";
    }

    @Override
    @NotNull
    public String getAuthor() {
        return "dodo";
    }

    @Override
    @NotNull
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        User user = plugin.findUser(player.getPlayer()).get(0);
        if (user.isPvp()) {
            return "Açık";
        } else {
            return "Kapalı";
        }
    }
}
