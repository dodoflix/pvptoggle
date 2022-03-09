package me.dodo.pvptoggle.commands;

import me.dodo.pvptoggle.Pvptoggle;
import me.dodo.pvptoggle.cache.User;
import me.dodo.pvptoggle.settings.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Toggle implements CommandExecutor {
    private final Pvptoggle instance;
    private static ConfigManager configManager;

    public Toggle() {
        instance = Pvptoggle.getInstance();
        configManager = instance.getConfigManager();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player player = ((Player) sender).getPlayer();

        User user = instance.findUser(player).get(0);
        if(!user.isCmiPvP()){
            user.setPvp(!user.isPvp());
            if(user.isPvp())
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getMessagesConf().pvpOn()));
            else
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getMessagesConf().pvpOff()));
        }
        else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getMessagesConf().cantUseWhilePvp()));
        }
        return true;
    }
}
